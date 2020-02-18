package com.jex.controller;

import cn.hutool.core.lang.Dict;
import com.jex.common.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.jex.aop.log.Log;
import com.jex.exception.BadRequestException;
import com.jex.data.entity.Role;
import com.jex.service.RoleService;
import com.jex.service.UserService;
import com.jex.data.dto.RoleDto;
import com.jex.data.dto.RoleQueryCriteria;
import com.jex.data.dto.RoleSmallDto;
import com.jex.data.dto.UserDto;
import com.jex.utils.SecurityUtils;
import com.jex.utils.ThrowableUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Jex
 */
@Api(tags = "系统：角色管理")
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;
    private final UserService userService;

    private static final String ENTITY_NAME = "role";

    public RoleController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @ApiOperation("获取单个role")
    @GetMapping(value = "/{id}")
    @PreAuthorize("@jx.check('roles:list')")
    public ResponseMessage getRoles(@PathVariable Long id){
        return ResponseMessage.initializeSuccessMessage(roleService.findById(id));
    }

    @Log("导出角色数据")
    @ApiOperation("导出角色数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@jx.check('role:list')")
    public void download(HttpServletResponse response, RoleQueryCriteria criteria) throws IOException {
        roleService.download(roleService.queryAll(criteria), response);
    }

    @ApiOperation("返回全部的角色")
    @GetMapping(value = "/all")
    @PreAuthorize("@jx.check('roles:list','user:add','user:edit')")
    public ResponseMessage getAll(@PageableDefault(value = 2000, sort = {"level"}, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseMessage.initializeSuccessMessage(roleService.queryAll(pageable));
    }

    @Log("查询角色")
    @ApiOperation("查询角色")
    @GetMapping
    @PreAuthorize("@jx.check('roles:list')")
    public ResponseMessage getRoles(RoleQueryCriteria criteria, Pageable pageable){
        return ResponseMessage.initializeSuccessMessage(roleService.queryAll(criteria,pageable));
    }

    @ApiOperation("获取用户级别")
    @GetMapping(value = "/level")
    public ResponseMessage getLevel(){
        return ResponseMessage.initializeSuccessMessage(Dict.create().set("level", getLevels(null)));
    }

    @Log("新增角色")
    @ApiOperation("新增角色")
    @PostMapping
    @PreAuthorize("@jx.check('roles:add')")
    public ResponseMessage create(@Validated  Role resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        getLevels(resources.getLevel());
        return ResponseMessage.initializeSuccessMessage(roleService.create(resources));
    }

    @Log("修改角色")
    @ApiOperation("修改角色")
    @PutMapping
    @PreAuthorize("@jx.check('roles:edit')")
    public ResponseMessage update(@Validated(Role.Update.class)  Role resources){
        getLevels(resources.getLevel());
        roleService.update(resources);
        return ResponseMessage.initializeSuccessMessage(null);
    }

    @Log("修改角色菜单")
    @ApiOperation("修改角色菜单")
    @PutMapping(value = "/menu")
    @PreAuthorize("@jx.check('roles:edit')")
    public ResponseMessage updateMenu( Role resources){
        RoleDto role = roleService.findById(resources.getId());
        getLevels(role.getLevel());
        roleService.updateMenu(resources,role);
        return ResponseMessage.initializeSuccessMessage(null);
    }

    @Log("删除角色")
    @ApiOperation("删除角色")
    @DeleteMapping
    @PreAuthorize("@jx.check('roles:del')")
    public ResponseMessage delete( Set<Long> ids){
        for (Long id : ids) {
            RoleDto role = roleService.findById(id);
            getLevels(role.getLevel());
        }
        try {
            roleService.delete(ids);
        } catch (Throwable e){
            ThrowableUtil.throwForeignKeyException(e, "所选角色存在用户关联，请取消关联后再试");
        }
        return ResponseMessage.initializeSuccessMessage(null);
    }

    /**
     * 获取用户的角色级别
     * @return /
     */
    private int getLevels(Integer level){
        UserDto user = userService.findByName(SecurityUtils.getUsername());
        List<Integer> levels = roleService.findByUsersId(user.getId()).stream().map(RoleSmallDto::getLevel).collect(Collectors.toList());
        int min = Collections.min(levels);
        if(level != null){
            if(level < min){
                throw new BadRequestException("权限不足，你的角色级别：" + min + "，低于操作的角色级别：" + level);
            }
        }
        return min;
    }
}
