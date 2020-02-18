package com.jex.constant;


/**
 * 响应枚举
 * @author Jex
 *
 */
public enum ResponseMessageEnum {
    /**
     * 操作成功
     */
    SUCCESS(0, "操作成功"),
    /**
     * 系统错误
     */
    REQUEST_ERROR(400, "请求错误"),

    /**
     * 系统错误
     */
    SYSTEM_ERROR(500, "系统错误");

    /**
     * 状态值
     */
    private final Integer status;

    /**
     * 返回信息
     */
    private final String message;

    ResponseMessageEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}