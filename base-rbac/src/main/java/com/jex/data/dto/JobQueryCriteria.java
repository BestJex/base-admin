package com.jex.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.jex.annotation.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
* @author Jex
*/
@Data
@NoArgsConstructor
public class JobQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query(propName = "id", joinName = "dept")
    private Long deptId;

    @Query(propName = "id", joinName = "dept", type = Query.Type.IN)
    private Set<Long> deptIds;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}