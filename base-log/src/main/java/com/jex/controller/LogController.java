package com.jex.controller;

import com.jex.common.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.jex.aop.log.Log;
import com.jex.service.LogService;
import com.jex.data.dto.LogQueryCriteria;
import com.jex.utils.SecurityUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 日志管理控制层
 * @author Jex
 */
@RestController
@RequestMapping("/admin/logs")
@Api(tags = "系统：日志管理")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@jx.check()")
    public void download(HttpServletResponse response, LogQueryCriteria criteria) throws IOException {
        criteria.setLogType("INFO");
        logService.download(logService.queryAll(criteria), response);
    }

    @Log("导出错误数据")
    @ApiOperation("导出错误数据")
    @GetMapping(value = "/error/download")
    @PreAuthorize("@jx.check()")
    public void errorDownload(HttpServletResponse response, LogQueryCriteria criteria) throws IOException {
        criteria.setLogType("ERROR");
        logService.download(logService.queryAll(criteria), response);
    }
    @GetMapping
    @ApiOperation("日志查询")
    @PreAuthorize("@jx.check()")
    public ResponseMessage getLogs(LogQueryCriteria criteria, Pageable pageable){
        criteria.setLogType("INFO");
        return ResponseMessage.initializeSuccessMessage(logService.queryAll(criteria,pageable));
    }

    @GetMapping(value = "/user")
    @ApiOperation("用户日志查询")
    public ResponseMessage getUserLogs(LogQueryCriteria criteria, Pageable pageable){
        criteria.setLogType("INFO");
        criteria.setBlurry(SecurityUtils.getUsername());
        return ResponseMessage.initializeSuccessMessage(logService.queryAllByUser(criteria,pageable));
    }

    @GetMapping(value = "/error")
    @ApiOperation("错误日志查询")
    @PreAuthorize("@jx.check()")
    public ResponseMessage getErrorLogs(LogQueryCriteria criteria, Pageable pageable){
        criteria.setLogType("ERROR");
        return ResponseMessage.initializeSuccessMessage(logService.queryAll(criteria,pageable));
    }


    @GetMapping(value = "/error/{id}")
    @ApiOperation("日志异常详情查询")
    @PreAuthorize("@jx.check()")
    public ResponseMessage getErrorLogs(@PathVariable Long id){
        return ResponseMessage.initializeSuccessMessage(logService.findByErrDetail(id));
    }


    @DeleteMapping(value = "/del/error")
    @Log("删除所有ERROR日志")
    @ApiOperation("删除所有ERROR日志")
    @PreAuthorize("@jx.check()")
    public ResponseMessage delAllByError(){
        logService.delAllByError();
        return ResponseMessage.initializeSuccessMessage(null);
    }

    @DeleteMapping(value = "/del/info")
    @Log("删除所有INFO日志")
    @ApiOperation("删除所有INFO日志")
    @PreAuthorize("@jx.check()")
    public ResponseMessage delAllByInfo(){
        logService.delAllByInfo();
        return ResponseMessage.initializeSuccessMessage(null);
    }
}
