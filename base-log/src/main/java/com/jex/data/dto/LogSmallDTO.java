package com.jex.data.dto;

import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Jex
 */
@Data
public class LogSmallDTO implements Serializable {

    private String description;

    private String requestIp;

    private Long time;

    private String address;

    private String browser;

    private Timestamp createTime;
}
