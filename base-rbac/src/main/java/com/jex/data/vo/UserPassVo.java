package com.jex.data.vo;

import lombok.Data;

/**
 * 修改密码的 Vo 类
 * @author Jex
 */
@Data
public class UserPassVo {

    private String oldPass;

    private String newPass;
}
