package com.jex.service.impl;

import com.jex.domain.DictDetail;
import com.jex.service.dto.DictDetailQueryCriteria;
import com.jex.utils.PageUtil;
import com.jex.utils.QueryHelp;
import com.jex.utils.ValidationUtil;
import com.jex.repository.DictDetailRepository;
import com.jex.service.DictDetailService;
import com.jex.service.dto.DictDetailDto;
import com.jex.service.mapper.DictDetailMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

/**
* @author jex
* @version 1.0
*/
@Service
@CacheConfig(cacheNames = "dictDetail")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictDetailServiceImpl implements DictDetailService {

    private final DictDetailRepository dictDetailRepository;

    private final DictDetailMapper dictDetailMapper;

    public DictDetailServiceImpl(DictDetailRepository dictDetailRepository, DictDetailMapper dictDetailMapper) {
        this.dictDetailRepository = dictDetailRepository;
        this.dictDetailMapper = dictDetailMapper;
    }

    @Override
    @Cacheable
    public Map<String,Object> queryAll(DictDetailQueryCriteria criteria, Pageable pageable) {
        Page<DictDetail> page = dictDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(dictDetailMapper::toDto));
    }

    @Override
    @Cacheable(key = "#p0")
    public DictDetailDto findById(Long id) {
        DictDetail dictDetail = dictDetailRepository.findById(id).orElseGet(DictDetail::new);
        ValidationUtil.isNull(dictDetail.getId(),"DictDetail","id",id);
        return dictDetailMapper.toDto(dictDetail);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public DictDetailDto create(DictDetail resources) {
        return dictDetailMapper.toDto(dictDetailRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(DictDetail resources) {
        DictDetail dictDetail = dictDetailRepository.findById(resources.getId()).orElseGet(DictDetail::new);
        ValidationUtil.isNull( dictDetail.getId(),"DictDetail","id",resources.getId());
        resources.setId(dictDetail.getId());
        dictDetailRepository.save(resources);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        dictDetailRepository.deleteById(id);
    }
}