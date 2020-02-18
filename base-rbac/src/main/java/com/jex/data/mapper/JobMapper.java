package com.jex.data.mapper;

import com.jex.base.BaseMapper;
import com.jex.data.entity.Job;
import com.jex.data.dto.JobDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
* @author Jex
*/
@Mapper(componentModel = "spring",uses = {DeptMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper extends BaseMapper<JobDto, Job> {

    /**
     * 转Dto
     * @param job 原始数据
     * @param deptSuperiorName /
     * @return /
     */
    @Mapping(source = "deptSuperiorName", target = "deptSuperiorName")
    JobDto toDto(Job job, String deptSuperiorName);
}