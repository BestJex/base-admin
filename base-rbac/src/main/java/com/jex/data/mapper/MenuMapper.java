package com.jex.data.mapper;

import com.jex.base.BaseMapper;
import com.jex.data.entity.Menu;
import com.jex.data.dto.MenuDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Jex
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends BaseMapper<MenuDto, Menu> {

}
