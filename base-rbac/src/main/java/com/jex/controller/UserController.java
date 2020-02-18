package com.jex.controller;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.jex.common.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.jex.aop.log.Log;
import com.jex.config.DataScope;
import com.jex.exception.BadRequestException;
import com.jex.data.entity.User;
import com.jex.data.vo.UserPassVo;
import com.jex.service.DeptService;
import com.jex.service.RoleService;
import com.jex.service.UserService;
import com.jex.data.dto.RoleSmallDto;
import com.jex.data.dto.UserDto;
import com.jex.data.dto.UserQueryCriteria;
import com.jex.utils.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Jex
 */
@Api(tags = "系统：用户管理")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Value("${rsa.private_key}")
    private String privateKey;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final DataScope dataScope;
    private final DeptService deptService;
    private final RoleService roleService;

    public UserController(PasswordEncoder passwordEncoder, UserService userService, DataScope dataScope, DeptService deptService, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.dataScope = dataScope;
        this.deptService = deptService;
        this.roleService = roleService;

    }

    @Log("导出用户数据")
    @ApiOperation("导出用户数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@jx.check('user:list')")
    public void download(HttpServletResponse response, UserQueryCriteria criteria) throws IOException {
        userService.download(userService.queryAll(criteria), response);
    }

    @Log("查询用户")
    @ApiOperation("查询用户")
    @GetMapping
    @PreAuthorize("@jx.check('user:list')")
    public ResponseMessage getUsers(UserQueryCriteria criteria, Pageable pageable){
        Set<Long> deptSet = new HashSet<>();
        Set<Long> result = new HashSet<>();
        if (!ObjectUtils.isEmpty(criteria.getDeptId())) {
            deptSet.add(criteria.getDeptId());
            deptSet.addAll(dataScope.getDeptChildren(deptService.findByPid(criteria.getDeptId())));
        }
        // 数据权限
        Set<Long> deptIds = dataScope.getDeptIds();
        // 查询条件不为空并且数据权限不为空则取交集
        if (!CollectionUtils.isEmpty(deptIds) && !CollectionUtils.isEmpty(deptSet)){
            // 取交集
            result.addAll(deptSet);
            result.retainAll(deptIds);
            // 若无交集，则代表无数据权限
            criteria.setDeptIds(result);
            if(result.size() == 0){
                return ResponseMessage.initializeSuccessMessage(PageUtil.toPage(null,0));
            } else {
                return ResponseMessage.initializeSuccessMessage(userService.queryAll(criteria,pageable));
            }
        // 否则取并集
        } else {
            result.addAll(deptSet);
            result.addAll(deptIds);
            criteria.setDeptIds(result);
            return ResponseMessage.initializeSuccessMessage(userService.queryAll(criteria,pageable));
        }
    }

    @Log("新增用户")
    @ApiOperation("新增用户")
    @PostMapping
    @PreAuthorize("@jx.check('user:add')")
    public ResponseMessage create(@Validated  User resources){
        checkLevel(resources);
        // 默认密码 123456
        resources.setPassword(passwordEncoder.encode("123456"));
        return ResponseMessage.initializeSuccessMessage(userService.create(resources));
    }

    @Log("修改用户")
    @ApiOperation("修改用户")
    @PutMapping
    @PreAuthorize("@jx.check('user:edit')")
    public ResponseMessage update(@Validated(User.Update.class)  User resources){
        checkLevel(resources);
        userService.update(resources);
        return ResponseMessage.initializeSuccessMessage(null);
    }

    @Log("修改用户：个人中心")
    @ApiOperation("修改用户：个人中心")
    @PutMapping(value = "center")
    public ResponseMessage center(@Validated(User.Update.class)  User resources){
        UserDto userDto = userService.findByName(SecurityUtils.getUsername());
        if(!resources.getId().equals(userDto.getId())){
            throw new BadRequestException("不能修改他人资料");
        }
        userService.updateCenter(resources);
        return ResponseMessage.initializeSuccessMessage(null);
    }

    @Log("删除用户")
    @ApiOperation("删除用户")
    @DeleteMapping
    @PreAuthorize("@jx.check('user:del')")
    public ResponseMessage delete( Set<Long> ids){
        UserDto user = userService.findByName(SecurityUtils.getUsername());
        for (Long id : ids) {
            Integer currentLevel =  Collections.min(roleService.findByUsersId(user.getId()).stream().map(RoleSmallDto::getLevel).collect(Collectors.toList()));
            Integer optLevel =  Collections.min(roleService.findByUsersId(id).stream().map(RoleSmallDto::getLevel).collect(Collectors.toList()));
            if (currentLevel > optLevel) {
                throw new BadRequestException("角色权限不足，不能删除：" + userService.findByName(SecurityUtils.getUsername()).getUsername());
            }
        }
        userService.delete(ids);
        return ResponseMessage.initializeSuccessMessage(null);
    }

    @ApiOperation("修改密码")
    @PostMapping(value = "/updatePass")
    public ResponseMessage updatePass( UserPassVo passVo){
        // 密码解密
        RSA rsa = new RSA(privateKey, null);
        String oldPass = new String(rsa.decrypt(passVo.getOldPass(), KeyType.PrivateKey));
        String newPass = new String(rsa.decrypt(passVo.getNewPass(), KeyType.PrivateKey));
        UserDto user = userService.findByName(SecurityUtils.getUsername());
        if(!passwordEncoder.matches(oldPass, user.getPassword())){
            throw new BadRequestException("修改失败，旧密码错误");
        }
        if(passwordEncoder.matches(newPass, user.getPassword())){
            throw new BadRequestException("新密码不能与旧密码相同");
        }
        userService.updatePass(user.getUsername(),passwordEncoder.encode(newPass));
        return ResponseMessage.initializeSuccessMessage(null);
    }

    @ApiOperation("修改头像")
    @PostMapping(value = "/updateAvatar")
    public ResponseMessage updateAvatar(@RequestParam MultipartFile file){
        userService.updateAvatar(file);
        return ResponseMessage.initializeSuccessMessage(null);
    }

    @Log("修改邮箱")
    @ApiOperation("修改邮箱")
    @PostMapping(value = "/updateEmail/{code}")
    public ResponseMessage updateEmail(@PathVariable String code, User user){
        // 密码解密
        RSA rsa = new RSA(privateKey, null);
        String password = new String(rsa.decrypt(user.getPassword(), KeyType.PrivateKey));
        UserDto userDto = userService.findByName(SecurityUtils.getUsername());
        if(!passwordEncoder.matches(password, userDto.getPassword())){
            throw new BadRequestException("密码错误");
        }

        userService.updateEmail(userDto.getUsername(),user.getEmail());
        return ResponseMessage.initializeSuccessMessage(null);
    }

    /**
     * 如果当前用户的角色级别低于创建用户的角色级别，则抛出权限不足的错误
     * @param resources /
     */
    private void checkLevel(User resources) {
        UserDto user = userService.findByName(SecurityUtils.getUsername());
        Integer currentLevel =  Collections.min(roleService.findByUsersId(user.getId()).stream().map(RoleSmallDto::getLevel).collect(Collectors.toList()));
        Integer optLevel = roleService.findByRoles(resources.getRoles());
        if (currentLevel > optLevel) {
            throw new BadRequestException("角色权限不足");
        }
    }
}
