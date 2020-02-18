package com.jex.repository;

import com.jex.data.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author Jex
 *
*/
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
}