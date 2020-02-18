package com.jex.data.mapper;

import com.jex.base.BaseMapper;
import com.jex.data.entity.Log;
import com.jex.data.dto.LogSmallDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Jex
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogSmallMapper extends BaseMapper<LogSmallDTO, Log> {

}