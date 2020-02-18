package com.jex.data.mapper;

import com.jex.base.BaseMapper;
import com.jex.data.entity.Dept;
import com.jex.data.dto.DeptDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Jex
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDto, Dept> {

}