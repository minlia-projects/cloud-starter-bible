package com.minlia.module.bible.v2.entity;

import static com.minlia.module.bible.v2.entity.BibleValue.ENTITY_NAME;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.module.data.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = ENTITY_NAME)
@TableName(ENTITY_NAME)
public class BibleValue extends AbstractEntity<Long> {
  public static final String ENTITY_NAME = "bible_value";


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty
  private Long id;


  @JsonProperty
  @ManyToOne
  private BibleObject bibleObject;


  @JsonProperty
  @ManyToOne
  private BibleField bibleField;



  @JsonProperty
  private String value;


}
