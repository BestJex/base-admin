package com.jex.data.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jex
 */
@Data
public class RoleSmallDto implements Serializable {

    private Long id;

    private String name;

    private Integer level;

    private String dataScope;
}
