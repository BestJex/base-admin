package com.jex.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
* @author jex
* @version 1.0
*/
@Getter
@Setter
public class DictDto implements Serializable {

    private Long id;

    private String name;

    private String remark;

    private List<DictDetailDto> dictDetails;

    private Timestamp createTime;
}
