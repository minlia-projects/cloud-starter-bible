package com.minlia.module.bible.v2.example;

import com.minlia.module.bible.v2.annotation.Bible;
import com.minlia.module.bible.v2.abstraction.BibleType;
import java.util.List;
import lombok.Data;

@Data
@Bible
public class Area extends BibleType {

  private List<Area> areas;

  private String name;

}
