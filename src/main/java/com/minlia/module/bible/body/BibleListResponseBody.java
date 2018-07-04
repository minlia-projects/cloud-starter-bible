package com.minlia.module.bible.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.cloud.stateful.body.ApiResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author will
 */
@ApiModel(value = "BibleListResponseBody")
public class BibleListResponseBody extends ApiResponseBody {

  @ApiModelProperty(value = "Code")
  @JsonProperty
  private String code;

  @ApiModelProperty(value = "Label")
  @JsonProperty
  private String label;

}
