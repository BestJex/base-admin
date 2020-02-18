package com.jex.service.mapper;

import com.jex.base.BaseMapper;
import com.jex.domain.Dict;
import com.jex.service.dto.DictDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jex
* @version 1.0
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictMapper extends BaseMapper<DictDto, Dict> {

}