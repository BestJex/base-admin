package com.jex.data.dto;

import lombok.Data;
import com.jex.annotation.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Jex
 * 公共查询类
 */
@Data
public class RoleQueryCriteria {

    @Query(blurry = "name,remark")
    private String blurry;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
