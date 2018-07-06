package com.minlia.module.bible.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.minlia.module.bible.dao.BibleDao;
import com.minlia.module.bible.entity.Bible;
import com.minlia.module.bible.query.BibleQueryRequestBody;
import com.minlia.module.data.batis.abstraction.AbstractMapper;
import com.minlia.module.data.batis.abstraction.service.AbstractBatisServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author will
 */
@Service
public class BibleBatisServiceImpl extends
    AbstractBatisServiceImpl<Bible, Long, BibleQueryRequestBody> implements
    BibleBatisService {

  @Autowired
  private BibleDao bibleDao;

  @Override
  public AbstractMapper<Bible> getBatisDao() {
    return bibleDao;
  }

  @Override
  public EntityWrapper<Bible> getFindAllSpecification(BibleQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public EntityWrapper<Bible> getCountSpecification(BibleQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public EntityWrapper<Bible> getExistsSpecification(BibleQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public EntityWrapper<Bible> getDeleteByConditionSpecification(
      BibleQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }


  public EntityWrapper<Bible> getConditions(BibleQueryRequestBody queryRequestBody) {
    EntityWrapper<Bible> entityWrapper = new EntityWrapper<Bible>();

    if (!StringUtils.isEmpty(queryRequestBody.getCode())) {
      entityWrapper.like("code", queryRequestBody.getCode());
    }

    if (!StringUtils.isEmpty(queryRequestBody.getLabel())) {
      entityWrapper.like("label", queryRequestBody.getLabel());
    }
    return entityWrapper;
  }

}
