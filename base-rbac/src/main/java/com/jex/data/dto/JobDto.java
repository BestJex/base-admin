package com.jex.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author Jex
*/
@Getter
@Setter
@NoArgsConstructor
public class JobDto implements Serializable {

    private Long id;

    private Long sort;

    private String name;

    private Boolean enabled;

    private DeptDto dept;

    private String deptSuperiorName;

    private Timestamp createTime;

    public JobDto(String name, Boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }
}