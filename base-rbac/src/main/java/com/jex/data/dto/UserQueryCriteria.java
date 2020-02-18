package com.jex.data.dto;

import lombok.Data;
import com.jex.annotation.Query;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * @author Jex
 */
@Data
public class UserQueryCriteria implements Serializable {

    @Query
    private Long id;

    @Query(propName = "id", type = Query.Type.IN, joinName = "dept")
    private Set<Long> deptIds;

    @Query(blurry = "email,username,nickName")
    private String blurry;

    @Query
    private Boolean enabled;

    private Long deptId;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
