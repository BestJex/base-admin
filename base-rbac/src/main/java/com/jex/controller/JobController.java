package com.jex.controller;

import com.jex.common.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.jex.aop.log.Log;
import com.jex.config.DataScope;
import com.jex.exception.BadRequestException;
import com.jex.data.entity.Job;
import com.jex.service.JobService;
import com.jex.data.dto.JobQueryCriteria;
import com.jex.utils.ThrowableUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
* @author Jex
*/
@Api(tags = "系统：岗位管理")
@RestController
@RequestMapping("/api/job")
public class JobController {

    private final JobService jobService;

    private final DataScope dataScope;

    private static final String ENTITY_NAME = "job";

    public JobController(JobService jobService, DataScope dataScope) {
        this.jobService = jobService;
        this.dataScope = dataScope;
    }

    @Log("导出岗位数据")
    @ApiOperation("导出岗位数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@jx.check('job:list')")
    public void download(HttpServletResponse response, JobQueryCriteria criteria) throws IOException {
        jobService.download(jobService.queryAll(criteria), response);
    }

    @Log("查询岗位")
    @ApiOperation("查询岗位")
    @GetMapping
    @PreAuthorize("@jx.check('job:list','user:list')")
    public ResponseMessage getJobs(JobQueryCriteria criteria, Pageable pageable){
        // 数据权限
        criteria.setDeptIds(dataScope.getDeptIds());
        return ResponseMessage.initializeSuccessMessage(jobService.queryAll(criteria, pageable));
    }

    @Log("新增岗位")
    @ApiOperation("新增岗位")
    @PostMapping
    @PreAuthorize("@jx.check('job:add')")
    public ResponseMessage create(@Validated  Job resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return ResponseMessage.initializeSuccessMessage(jobService.create(resources));
    }

    @Log("修改岗位")
    @ApiOperation("修改岗位")
    @PutMapping
    @PreAuthorize("@jx.check('job:edit')")
    public ResponseMessage update(@Validated(Job.Update.class)  Job resources){
        jobService.update(resources);
        return ResponseMessage.initializeSuccessMessage(null);
    }

    @Log("删除岗位")
    @ApiOperation("删除岗位")
    @DeleteMapping
    @PreAuthorize("@jx.check('job:del')")
    public ResponseMessage delete( Set<Long> ids){
        try {
            jobService.delete(ids);
        }catch (Throwable e){
            ThrowableUtil.throwForeignKeyException(e, "所选岗位存在用户关联，请取消关联后再试");
        }
        return ResponseMessage.initializeSuccessMessage(null);
    }
}