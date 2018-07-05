package com.minlia.module.bible.service;

import com.minlia.module.bible.entity.Bible;
import com.minlia.module.bible.query.BibleQueryRequestBody;
import com.minlia.module.data.jpa.service.AbstractJpaService;

/**
 * @author will
 */
public interface BibleJpaService extends AbstractJpaService<Bible, Long,BibleQueryRequestBody> {


}
