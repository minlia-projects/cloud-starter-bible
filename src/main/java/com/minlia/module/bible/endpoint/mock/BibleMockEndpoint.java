package com.minlia.module.bible.endpoint.mock;

import com.google.common.collect.Lists;
import com.minlia.cloud.stateful.Responses;
import com.minlia.cloud.stateful.body.StatefulBody;
import com.minlia.cloud.stateful.body.impl.SuccessResponseBody;
import com.minlia.module.bible.entity.Bible;
import com.minlia.module.bible.query.BibleQueryRequestBody;
import com.minlia.module.bible.service.BibleJpaService;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Pageable;


@Slf4j
@Api(tags = "Bible Mock Api", description = "Bible")
@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/open/mock/bible")
//@ApiVersion(value = ApiVersionPrefix.V1)
//@Loggable
public class BibleMockEndpoint {


  @Autowired
  private BibleJpaService bibleJpaService;


  @PostMapping(value = "/exampleQuery")
  @ResponseBody
  @ApiOperation(value = "Example Query")
  public ResponseEntity<StatefulBody<Bible>> exampleQuery(@RequestBody BibleQueryRequestBody body,Pageable pageable) {

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withMatcher("code", GenericPropertyMatchers.startsWith())
        .withIgnorePaths("label");
    Bible entity =new Bible();
    entity.setCode(body.getCode());
    entity.setLabel(body.getLabel());
    entity.setDataStatus(body.getDataStatus());
    Example<Bible> query = Example.of(entity, matcher);
    return Responses.ok(SuccessResponseBody.builder().payload(bibleJpaService.findAllByCondition(query,pageable)).build());

  }

  @RequestMapping(value = "/createMockData", method = RequestMethod.GET)
  @ResponseBody
  @ApiOperation(value = "create mock data")
  public ResponseEntity<StatefulBody<Bible>> createMockData() {

    Date lastTime = null;
    //生成100个上游的成功记录
    for (int i = 0; i < 400; i++) {
      EnhancedRandom enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().build();
      Bible entity = enhancedRandom.nextObject(Bible.class);
//      if (lastTime != null) {
//        entity.setAccessTime(lastTime);
//      }
//      entity.setAppid(String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId()));
//      entity.setAccessType(enhancedRandom.nextBoolean() ? AccessType.DOWNSTREAM : AccessType.UPSTREAM);
//      entity.setResult(enhancedRandom.nextBoolean() ? AccessResult.FAILED : AccessResult.SUCCESS);
//      entity.setMerchantCurrencySymbol("HK$");
//      entity.setProviderCurrencySymbol("HK$");
//      if (entity.getAccessType() == AccessType.DOWNSTREAM || entity.getResult() == AccessResult.FAILED) {
//        entity.setMerchantAccessFee(BigDecimal.valueOf(0));
//        entity.setProviderAccessFee(BigDecimal.valueOf(0));
//      }
//      entity.setDuration(50 + enhancedRandom.nextInt(150));
      entity.setCode(RandomStringUtils.randomAlphanumeric(12));
      entity.setLabel(RandomStringUtils.randomAlphanumeric(20));
      System.out.println(entity);
      entity.setItems(Lists.newArrayList());
      bibleJpaService.save(entity);
//            lastTime = new Date(entity.getAccessTime().getTime() + 2 * 60 * 1000);
//      lastTime = new Date(entity.getAccessTime().getTime() + 20 * 60 * 1000 + enhancedRandom.nextLong() % (20 * 60 * 1000));
    }

    return Responses.ok(SuccessResponseBody.builder().build());
  }
}
