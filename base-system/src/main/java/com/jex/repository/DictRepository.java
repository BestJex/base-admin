package com.jex.repository;

import com.jex.domain.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jex
* @version 1.0
*/
public interface DictRepository extends JpaRepository<Dict, Long>, JpaSpecificationExecutor<Dict> {
}