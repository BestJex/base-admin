package com.jex.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jex.constant.ResponseMessageEnum;
import lombok.Data;

/**
 * 响应封装类
 * @author Jex
 *
 */
@Data
public class ResponseMessage {


    private Integer status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public static ResponseMessage  initializeSuccessMessage(Object data){
        ResponseMessage rm = new ResponseMessage(ResponseMessageEnum.SUCCESS.getStatus(), ResponseMessageEnum.SUCCESS.getMessage(), data);
        return rm;
    }


    public ResponseMessage(Integer status){
        this.status = status;
    }


    public ResponseMessage(Integer status, String message){
        this.status = status;
        this.message = message;
    }

    public ResponseMessage(Integer status, String message, Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

}