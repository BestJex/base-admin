package com.jex.controller;

import com.jex.common.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.jex.aop.log.Log;
import com.jex.exception.BadRequestException;
import com.jex.data.entity.Menu;
import com.jex.service.MenuService;
import com.jex.service.RoleService;
import com.jex.service.UserService;
import com.jex.data.dto.MenuDto;
import com.jex.data.dto.MenuQueryCriteria;
import com.jex.data.dto.UserDto;
import com.jex.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jex
 */
@Api(tags = "系统：菜单管理")
@RestController
@RequestMapping("/api/menus")
@SuppressWarnings("unchecked")
public class MenuController {

    private final MenuService menuService;

    private final UserService userService;

    private final RoleService roleService;

    private static final String ENTITY_NAME = "menu";

    public MenuController(MenuService menuService, UserService userService, RoleService roleService) {
        this.menuService = menuService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @Log("导出菜单数据")
    @ApiOperation("导出菜单数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@jx.check('menu:list')")
    public void download(HttpServletResponse response, MenuQueryCriteria criteria) throws IOException {
        menuService.download(menuService.queryAll(criteria), response);
    }

    @ApiOperation("获取前端所需菜单")
    @GetMapping(value = "/build")
    public ResponseMessage buildMenus(){
        UserDto user = userService.findByName(SecurityUtils.getUsername());
        List<MenuDto> menuDtoList = menuService.findByRoles(roleService.findByUsersId(user.getId()));
        List<MenuDto> menuDtos = (List<MenuDto>) menuService.buildTree(menuDtoList).get("content");
        return ResponseMessage.initializeSuccessMessage(menuService.buildMenus(menuDtos));
    }

    @ApiOperation("返回全部的菜单")
    @GetMapping(value = "/tree")
    @PreAuthorize("@jx.check('menu:list','roles:list')")
    public ResponseMessage getMenuTree(){
        return ResponseMessage.initializeSuccessMessage(menuService.getMenuTree(menuService.findByPid(0L)));
    }

    @Log("查询菜单")
    @ApiOperation("查询菜单")
    @GetMapping
    @PreAuthorize("@jx.check('menu:list')")
    public ResponseMessage getMenus(MenuQueryCriteria criteria){
        List<MenuDto> menuDtoList = menuService.queryAll(criteria);
        return ResponseMessage.initializeSuccessMessage(menuService.buildTree(menuDtoList));
    }

    @Log("新增菜单")
    @ApiOperation("新增菜单")
    @PostMapping
    @PreAuthorize("@jx.check('menu:add')")
    public ResponseMessage create(@Validated  Menu resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return ResponseMessage.initializeSuccessMessage(menuService.create(resources));
    }

    @Log("修改菜单")
    @ApiOperation("修改菜单")
    @PutMapping
    @PreAuthorize("@jx.check('menu:edit')")
    public ResponseMessage update(@Validated(Menu.Update.class)  Menu resources){
        menuService.update(resources);
        return ResponseMessage.initializeSuccessMessage(null);
    }

    @Log("删除菜单")
    @ApiOperation("删除菜单")
    @DeleteMapping
    @PreAuthorize("@jx.check('menu:del')")
    public ResponseMessage delete( Set<Long> ids){
        Set<Menu> menuSet = new HashSet<>();
        for (Long id : ids) {
            List<Menu> menuList = menuService.findByPid(id);
            menuSet.add(menuService.findOne(id));
            menuSet = menuService.getDeleteMenus(menuList, menuSet);
        }
        menuService.delete(menuSet);
        return ResponseMessage.initializeSuccessMessage(null);
    }
}
