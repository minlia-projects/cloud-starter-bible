package com.minlia.module.bible;

import com.minlia.cloud.stateful.body.Body;
import lombok.Data;

@Data
public class SearchBody implements Body {

    private String startDate;

    private String endDate;

}
