package com.minlia.module.bible.v2.example;

import com.minlia.module.bible.v2.abstraction.BibleType;
import com.minlia.module.bible.v2.entity.BibleClass;
import com.minlia.module.data.jpa.abstraction.AbstractRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import lombok.Data;


@Data
public class Area implements BibleType {

  private Set<AbstractRepository<BibleClass, Long>> set1;

  private List<Area> bibleClasses;

  private BibleClass bibleClass;

  private AbstractRepository<BibleClass, Long> x22;

  private Double xx;
  private BigDecimal bd;

  private Long id;

  private Long bibleClassId;

  private List<Area> areas;

  private String name;


  @Override
  public Long getBibleClassId() {
    return bibleClassId;
  }

  @Override
  public Long getId() {
    return id;
  }


}
