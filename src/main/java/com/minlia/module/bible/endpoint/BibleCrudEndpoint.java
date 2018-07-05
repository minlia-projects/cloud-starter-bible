package com.minlia.module.bible.endpoint;

import com.minlia.module.bible.entity.Bible;
import com.minlia.module.bible.service.BibleJpaService;
import com.minlia.module.data.endpoint.CreationEndpoint;
import com.minlia.module.data.endpoint.DeleteByIdsEndpoint;
import com.minlia.module.data.endpoint.FindOneByIdEndpoint;
import com.minlia.module.data.endpoint.UpdateableEndpoint;
import com.minlia.module.data.interfaces.IRawService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author will
 *
 * 请求体使用JPA的 返回体使用自已的
 */
@Slf4j
@Api(tags = "Bible Jpa Crud Api", description = "Bible")
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/open/bible/crud")
//@ApiVersion(value = ApiVersionPrefix.V1)
//@Loggable
public class BibleCrudEndpoint implements
    CreationEndpoint<Bible, Long>
    , UpdateableEndpoint<Bible, Long>
    , DeleteByIdsEndpoint<Bible, Long>
    , FindOneByIdEndpoint<Bible, Long> {

  @Autowired
  private BibleJpaService bibleJpaService;

  @Override
  public IRawService<Bible, Long> getRawService() {
    return bibleJpaService;
  }

  //由于creation只需要一个服务，所以这里不需要体现多个服务
//  @Override
//  public AbstractConditionalService<Bible, BibleQueryRequestBody> getConditionalService() {
//    return bibleJpaService;
//  }


}
