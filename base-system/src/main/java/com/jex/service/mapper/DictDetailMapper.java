package com.jex.service.mapper;

import com.jex.base.BaseMapper;
import com.jex.domain.DictDetail;
import com.jex.service.dto.DictDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jex
* @version 1.0
*/
@Mapper(componentModel = "spring", uses = {DictSmallMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictDetailMapper extends BaseMapper<DictDetailDto, DictDetail> {

}