package com.jex.data.dto;

import lombok.Data;
import com.jex.annotation.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
* @author Jex
*/
@Data
public class DeptQueryCriteria{

    @Query(type = Query.Type.IN, propName="id")
    private Set<Long> ids;

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query
    private Long pid;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}