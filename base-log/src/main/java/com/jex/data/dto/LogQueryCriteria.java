package com.jex.data.dto;

import lombok.Data;
import com.jex.annotation.Query;
import java.sql.Timestamp;
import java.util.List;

/**
 * 日志查询类
 * @author Jex
 */
@Data
public class LogQueryCriteria {

    @Query(blurry = "username,description,address,requestIp,method,params")
    private String blurry;

    @Query
    private String logType;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
