package com.jex.data.mapper;

import com.jex.base.BaseMapper;
import com.jex.data.entity.Role;
import com.jex.data.dto.RoleSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Jex
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallMapper extends BaseMapper<RoleSmallDto, Role> {

}
