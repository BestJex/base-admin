package com.jex.service.dto;

import lombok.Data;
import com.jex.annotation.Query;

/**
 * @author jex
 * 公共查询类
 */
@Data
public class DictQueryCriteria {

    @Query(blurry = "name,remark")
    private String blurry;
}
