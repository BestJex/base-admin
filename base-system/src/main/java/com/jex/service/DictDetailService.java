package com.jex.service;

import com.jex.domain.DictDetail;
import com.jex.service.dto.DictDetailDto;
import com.jex.service.dto.DictDetailQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;

/**
* @author jex
* @version 1.0
*/
public interface DictDetailService {

    /**
     * 根据ID查询
     * @param id /
     * @return /
     */
    DictDetailDto findById(Long id);

    /**
     * 创建
     * @param resources /
     * @return /
     */
    DictDetailDto create(DictDetail resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(DictDetail resources);

    /**
     * 删除
     * @param id /
     */
    void delete(Long id);

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Map<String,Object> queryAll(DictDetailQueryCriteria criteria, Pageable pageable);
}