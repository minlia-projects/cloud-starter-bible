package com.minlia.module.bible.endpoint;

import com.minlia.module.bible.entity.Bible;
import com.minlia.module.bible.query.BibleQueryRequestBody;
import com.minlia.module.bible.service.BibleJpaService;
import com.minlia.module.data.endpoint.AbstractEndpoint;
import com.minlia.module.data.interfaces.IRawService;
import com.minlia.module.data.service.AbstractReadonlyService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author will
 *
 * 请求体使用JPA的 返回体使用自已的
 */
@Slf4j
@Api(tags = "Bible Jpa Api", description = "Bible")
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/open/bible/jpa")
//@ApiVersion(value = ApiVersionPrefix.V1)
//@Loggable
public class BibleJpaEndpoint extends AbstractEndpoint<Bible, Long, BibleQueryRequestBody> {

  @Autowired
  private BibleJpaService bibleJpaService;

  @Override
  public IRawService<Bible, Long> getRawService() {
    return bibleJpaService;
  }

  @Override
  public AbstractReadonlyService<Bible, BibleQueryRequestBody> getReadonlyService() {
    return bibleJpaService;
  }



}
