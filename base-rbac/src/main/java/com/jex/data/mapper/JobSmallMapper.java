package com.jex.data.mapper;

import com.jex.base.BaseMapper;
import com.jex.data.entity.Job;
import com.jex.data.dto.JobSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author Jex
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobSmallMapper extends BaseMapper<JobSmallDto, Job> {

}