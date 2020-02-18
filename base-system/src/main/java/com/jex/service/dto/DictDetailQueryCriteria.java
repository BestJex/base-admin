package com.jex.service.dto;

import lombok.Data;
import com.jex.annotation.Query;

/**
* @author jex
* @version 1.0
*/
@Data
public class DictDetailQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String label;

    @Query(propName = "name",joinName = "dict")
    private String dictName;
}