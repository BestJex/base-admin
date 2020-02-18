package com.jex.controller;

import com.jex.common.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.jex.aop.log.Log;
import com.jex.service.OnlineUserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * 在线用户管理
 * @author Jex
 *
 */
@RestController
@RequestMapping("/admin/online")
@Api(tags = "系统：在线用户管理")
public class OnlineController {

    private final OnlineUserService onlineUserService;

    public OnlineController(OnlineUserService onlineUserService) {
        this.onlineUserService = onlineUserService;
    }

    @ApiOperation("在线用户列表")
    @GetMapping
    @PreAuthorize("@jx.check()")
    public ResponseMessage getAll(String filter, Pageable pageable){
        return ResponseMessage.initializeSuccessMessage(onlineUserService.getAll(filter, pageable));
    }

    @Log("导出在线用户数据")
    @ApiOperation("导出在线用户数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@jx.check()")
    public void download(HttpServletResponse response, String filter) throws IOException {
        onlineUserService.download(onlineUserService.getAll(filter), response);
    }

    @ApiOperation("踢出在线用户")
    @DeleteMapping
    @PreAuthorize("@jx.check()")
    public ResponseMessage delete(Set<String> keys) throws Exception {
        for (String key : keys) {
            onlineUserService.kickOut(key);
        }
        return ResponseMessage.initializeSuccessMessage(null);
    }
}
