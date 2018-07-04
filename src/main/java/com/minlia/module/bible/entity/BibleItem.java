package com.minlia.module.bible.entity;

import static com.minlia.module.bible.entity.BibleItem.ENTITY_NAME;
import static com.minlia.module.bible.entity.Bible.MAPPED_ENTITY_NAME;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.cloud.stateful.generator.SnowFlakeUtil;
import com.minlia.module.data.entity.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author will Bible as a system dictionary
 */
@Data
@Entity
@Table(name = ENTITY_NAME)
@TableName(ENTITY_NAME)
public class BibleItem extends AbstractEntity<Long> {

  public static final String ENTITY_NAME = "bible_item";

//  @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
//  @JsonProperty
//  private String id=String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty
  private Long id;


  @ApiModelProperty(value = "Item value")
  private String value;

  @ApiModelProperty(value = "Label")
  private String label;


  @ManyToOne(targetEntity = Bible.class)
  @JoinTable(name = MAPPED_ENTITY_NAME, inverseJoinColumns = @JoinColumn(name = "bibleId", referencedColumnName = "id"), joinColumns = @JoinColumn(name = "itemId", referencedColumnName = "id"))
  private Bible bible;

}
