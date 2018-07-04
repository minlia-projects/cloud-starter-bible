package com.minlia.module.bible.query;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.module.bible.entity.Bible;
import com.minlia.module.data.body.AbstractQueryRequestBody;
import lombok.Data;

/**
 * @author will
 */
@Data
public class BibleQueryRequestBody extends AbstractQueryRequestBody {

  @JsonProperty
  private String code;
  @JsonProperty
  private String label;

}
