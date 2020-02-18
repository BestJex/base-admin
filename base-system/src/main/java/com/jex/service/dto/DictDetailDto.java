package com.jex.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author jex
* @version 1.0
*/
@Getter
@Setter
public class DictDetailDto implements Serializable {

    private Long id;

    private String label;

    private String value;

    private String sort;

    private DictSmallDto dict;

    private Timestamp createTime;
}