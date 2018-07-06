package com.minlia.module.bible.service;

import com.minlia.module.bible.entity.Bible;
import com.minlia.module.bible.query.BibleQueryRequestBody;
import com.minlia.module.data.batis.abstraction.service.AbstractBatisService;

/**
 * @author will
 */
public interface BibleBatisService extends AbstractBatisService<Bible,Long, BibleQueryRequestBody> {


}
