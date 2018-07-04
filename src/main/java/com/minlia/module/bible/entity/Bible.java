package com.minlia.module.bible.entity;

import static com.minlia.module.bible.entity.Bible.ENTITY_NAME;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.cloud.stateful.generator.SnowFlakeUtil;
import com.minlia.module.data.entity.AbstractEntity;
import com.minlia.module.pretend.annotation.Pretend;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

/**
 * @author will Bible is the dictionary of system
 */
@Getter
@Setter
@Entity
@Table(name = ENTITY_NAME)
@TableName(ENTITY_NAME)

public class Bible extends AbstractEntity<Long> {


  public static final String ENTITY_NAME = "bible";
  public static final String MAPPED_ENTITY_NAME = "map_items_of_bible";


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonProperty
  private Long id;

//  public Bible (){
//    String id=String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());
//    setId(id);
//  }

  @ApiModelProperty(value = "Bible code")
  @JsonProperty

  private String code;


  @ApiModelProperty(value = "Label")
  @JsonProperty
  private String label;


  @Transient
  @TableField(exist = false)
  @ApiModelProperty(value = "Items of bible")
  @OneToMany(targetEntity = BibleItem.class)
  @JoinTable(name = MAPPED_ENTITY_NAME, joinColumns = @JoinColumn(name = "bibleId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "itemId", referencedColumnName = "id"))
  private List<BibleItem> items;


}
