package com.minlia.module.bible.v2.service;


import com.minlia.module.bible.v2.abstraction.BibleType;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface BibleInternalService {

  /**
   * 查找对应类的方法
   */
  List findAllByClass(Class clazz);

  /**
   * 更新
   */
  <BIBLE extends BibleType> BIBLE update(BIBLE bible);


  /**
   * 创建新的
   */
  <BIBLE extends BibleType> BIBLE create(BIBLE bible);


}
