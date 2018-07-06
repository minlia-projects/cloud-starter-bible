package com.minlia.module.bible.endpoint;

import com.minlia.cloud.loggable.annotation.Loggable;
import com.minlia.module.bible.entity.Bible;
import com.minlia.module.bible.query.BibleQueryRequestBody;
import com.minlia.module.bible.service.BibleBatisService;
import com.minlia.module.data.abstraction.endpoint.CountByConditionEndpoint;
import com.minlia.module.data.abstraction.endpoint.CreationEndpoint;
import com.minlia.module.data.abstraction.endpoint.DeleteByConditionEndpoint;
import com.minlia.module.data.abstraction.endpoint.DeleteByIdsEndpoint;
import com.minlia.module.data.abstraction.endpoint.ExistsByConditionEndpoint;
import com.minlia.module.data.abstraction.endpoint.FindListByConditionEndpoint;
import com.minlia.module.data.abstraction.endpoint.FindOneByIdEndpoint;
import com.minlia.module.data.abstraction.endpoint.FindPaginatedByConditionEndpoint;
import com.minlia.module.data.abstraction.endpoint.UpdateableEndpoint;
import com.minlia.module.data.abstraction.service.ConditionalService;
import com.minlia.module.data.interfaces.IRawService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author will
 * @since 2.0.3
 */
@Slf4j
@Api(tags = "Bible Batis Api", description = "Bible")
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/open/bible/batis")
//@ApiVersion(value = ApiVersionPrefix.V1)
@Loggable
//如果需要用到部分功能的时候这样用
public class BibleBatisEndpoint implements
    CreationEndpoint<Bible, Long>,
    FindOneByIdEndpoint<Bible, Long>,
    FindListByConditionEndpoint<Bible, BibleQueryRequestBody>,
    FindPaginatedByConditionEndpoint<Bible, BibleQueryRequestBody>,
    UpdateableEndpoint<Bible, Long>,
    DeleteByIdsEndpoint<Bible, Long>,
    DeleteByConditionEndpoint<Bible, BibleQueryRequestBody>,
    ExistsByConditionEndpoint<Bible,BibleQueryRequestBody>,
    CountByConditionEndpoint<Bible,BibleQueryRequestBody>

{
//如果用到全部功能的时候这样用
//public class BibleBatisEndpoint implements    AbstractEndpoint<Bible, Long, BibleQueryRequestBody> {

  //演示使用Mybatis时的服务
  @Autowired
  private BibleBatisService bibleBatisService;

  @Override
  public IRawService<Bible, Long> getRawService() {
    return bibleBatisService;
  }

  @Override
  public ConditionalService<Bible, BibleQueryRequestBody> getConditionalService() {
    return bibleBatisService;
  }
//  //演示使用JPA时的服务
//  @Autowired
//  private BibleJpaService bibleJpaService;
//  @Override
//  public IRawService<Bible, Long> getRawService() {
//    return bibleJpaService;
//  }
//  @Override
//  public AbstractConditionalService<Bible, BibleQueryRequestBody> getConditionalService() {
//    return bibleJpaService;
//  }
  
  
  
}
