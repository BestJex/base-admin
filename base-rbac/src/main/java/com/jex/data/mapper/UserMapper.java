package com.jex.data.mapper;

import com.jex.base.BaseMapper;
import com.jex.data.entity.User;
import com.jex.data.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author Jex
 */
@Mapper(componentModel = "spring",uses = {RoleMapper.class, DeptMapper.class, JobMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {

    /**
     * 转换
     * @param user 原始数据
     * @return /
     */
    @Override
    @Mapping(source = "user.userAvatar.realName",target = "avatar")
    UserDto toDto(User user);
}
