package com.minlia.module.bible.v2.example;

import com.google.common.collect.Maps;
import com.minlia.module.bible.entity.Bible;
import com.minlia.module.bible.v2.abstraction.BibleType;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Data;


@Data
public class Gender implements BibleType {



  public static final Long age=1L;

  private Number age1;

  private String gender;


  private List<Bible> bibleEntity;

  private Map<String,List<Set<String>>> map= Maps.newConcurrentMap();











  // 以下内容每个都要

  private Long id;

  /**
   * 缓存使用
   */

  private Long bibleClassId;

  @Override
  public Long getBibleClassId() {
    return bibleClassId;
  }

  @Override
  public Long getId() {
    return id;
  }


}
