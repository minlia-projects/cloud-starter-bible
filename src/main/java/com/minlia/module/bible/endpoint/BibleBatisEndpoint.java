package com.minlia.module.bible.endpoint;

import com.minlia.module.bible.entity.Bible;
import com.minlia.module.bible.query.BibleQueryRequestBody;
import com.minlia.module.bible.service.BibleBatisService;
import com.minlia.module.data.endpoint.AbstractEndpoint;
import com.minlia.module.data.interfaces.IRawService;
import com.minlia.module.data.service.AbstractReadonlyService;
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
@Api(tags = "Bible Batis Api", description = "Bible")
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/open/bible/batis")
//@ApiVersion(value = ApiVersionPrefix.V1)
//@Loggable
public class BibleBatisEndpoint extends AbstractEndpoint<Bible, Long, BibleQueryRequestBody> {


  @Autowired
  private BibleBatisService bibleBatisService;


  @Override
  public IRawService<Bible, Long> getRawService() {
    return bibleBatisService;
  }

  @Override
  public AbstractReadonlyService<Bible, BibleQueryRequestBody> getReadonlyService() {
    return bibleBatisService;
  }


}
