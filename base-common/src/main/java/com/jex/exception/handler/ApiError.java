package com.jex.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jex.constant.ResponseMessageEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 接口请求异常处理
 * @author Jex
 *
 */
@Data
class ApiError {

    private Integer status = ResponseMessageEnum.REQUEST_ERROR.getStatus();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public static ApiError error(String message){
        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        return apiError;
    }

    public static ApiError error(Integer status, String message){
        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setMessage(message);
        return apiError;
    }
}


