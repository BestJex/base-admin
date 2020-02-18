package com.jex.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.jex.common.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.jex.aop.log.Log;
import com.jex.config.DataScope;
import com.jex.exception.BadRequestException;
import com.jex.data.entity.Dept;
import com.jex.service.DeptService;
import com.jex.data.dto.DeptDto;
import com.jex.data.dto.DeptQueryCriteria;
import com.jex.utils.ThrowableUtil;
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
@RestController
@Api(tags = "系统：部门管理")
@RequestMapping("/api/dept")
public class DeptController {

    private final DeptService deptService;

    private final DataScope dataScope;

    private static final String ENTITY_NAME = "dept";

    public DeptController(DeptService deptService, DataScope dataScope) {
        this.deptService = deptService;
        this.dataScope = dataScope;
    }

    @Log("导出部门数据")
    @ApiOperation("导出部门数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@jx.check('dept:list')")
    public void download(HttpServletResponse response, DeptQueryCriteria criteria) throws IOException {
        deptService.download(deptService.queryAll(criteria), response);
    }

    @Log("查询部门")
    @ApiOperation("查询部门")
    @GetMapping
    @PreAuthorize("@jx.check('user:list','dept:list')")
    public ResponseMessage getDepts(DeptQueryCriteria criteria){
        // 数据权限
        criteria.setIds(dataScope.getDeptIds());
        List<DeptDto> deptDtos = deptService.queryAll(criteria);
        return ResponseMessage.initializeSuccessMessage(deptService.buildTree(deptDtos));
    }

    @Log("新增部门")
    @ApiOperation("新增部门")
    @PostMapping
    @PreAuthorize("@jx.check('dept:add')")
    public ResponseMessage create(@Validated  Dept resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return ResponseMessage.initializeSuccessMessage(deptService.create(resources));
    }

    @Log("修改部门")
    @ApiOperation("修改部门")
    @PutMapping
    @PreAuthorize("@jx.check('dept:edit')")
    public ResponseMessage update(@Validated(Dept.Update.class)  Dept resources){
        deptService.update(resources);
        return ResponseMessage.initializeSuccessMessage(null);
    }

    @Log("删除部门")
    @ApiOperation("删除部门")
    @DeleteMapping
    @PreAuthorize("@jx.check('dept:del')")
    public ResponseMessage delete( Set<Long> ids){
        Set<DeptDto> deptDtos = new HashSet<>();
        for (Long id : ids) {
            List<Dept> deptList = deptService.findByPid(id);
            deptDtos.add(deptService.findById(id));
            if(CollectionUtil.isNotEmpty(deptList)){
                deptDtos = deptService.getDeleteDepts(deptList, deptDtos);
            }
        }
        try {
            deptService.delete(deptDtos);
        }catch (Throwable e){
            ThrowableUtil.throwForeignKeyException(e, "所选部门中存在岗位或者角色关联，请取消关联后再试");
        }
        return ResponseMessage.initializeSuccessMessage(null);
    }
}