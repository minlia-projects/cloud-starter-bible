package com.minlia.module.bible.v2.service;

import com.minlia.module.bible.entity.Bible;
import com.minlia.module.bible.v2.abstraction.BibleType;
import com.minlia.module.bible.v2.entity.BibleClass;
import com.minlia.module.bible.v2.entity.BibleField;
import com.minlia.module.bible.v2.entity.BibleObject;
import com.minlia.module.bible.v2.entity.BibleValue;
import com.minlia.module.bible.v2.repository.BibleClassRepository;
import com.minlia.module.bible.v2.repository.BibleFieldRepository;
import com.minlia.module.bible.v2.repository.BibleObjectRepository;
import com.minlia.module.bible.v2.repository.BibleValueRepository;
import eu.infomas.annotation.AnnotationDetector;
import eu.infomas.annotation.AnnotationDetector.TypeReporter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BibleInternalServiceImpl implements BibleInternalService {


  @Autowired
  private BibleClassRepository bibleClassRepository;


  @Autowired
  private BibleFieldRepository bibleFieldRepository;


  @Autowired
  private BibleObjectRepository bibleObjectRepository;

  @Autowired
  private BibleValueRepository bibleValueRepository;


  @Override
  public  <BIBLE extends BibleType> BIBLE create(BIBLE bible){
    return this.create(bible,null,null);
  }


  /**
   * 当系统启动时扫描 @Bible
   * 分析每一个BibleType 里面的结构
   *
   * 检查子对象是否继承自BibleType
   * 如果没有继承则抛出异常
   *
   *
   * @param bible
   * @param parent
   * @param bibleField
   * @param <BIBLE>
   * @return
   */
  private <BIBLE extends BibleType> BIBLE create(BIBLE bible,BibleObject parent,BibleField bibleField) {

    bible.getClass().isAssignableFrom(Bible.class);

    if(null!=bible.getBibleClassId()){
      //
    }
    Class bibleClass = bible.getClass();
    String packageName = bibleClass.getPackage().getName();
    String className = bibleClass.getSimpleName();

    BibleClass bibleClassFound = bibleClassRepository
        .findOneByPackageNameAndClassName(packageName, className);

    if (null == bibleClassFound) {
      //创建一个
      BibleClass create = new BibleClass();
      bibleClassFound.setClassName(className);
      bibleClassFound.setPackageName(packageName);
      BibleClass created = bibleClassRepository.save(bibleClassFound);
      bibleClassFound = created;
    }


    //创建BibleObject
    BibleObject bibleObject = new BibleObject();
    bibleObject.setBibleClass(bibleClassFound);
    bibleObject.setBibleField(bibleField);
    bibleObject.setParentBibleObject(parent);
    //TODO
    bibleObject.setSortOrder(0);
    BibleObject bibleObjectCreated=bibleObjectRepository.save(bibleObject);

    Field[] fields = bibleClass.getFields();
    for (Field field : fields) {
      //检查是否为基础类型
      Boolean isPrimitive = field.getType().isPrimitive();
      if (isPrimitive) {

        //如果是基础类型则insert a bibleValue
        //创建BibleValue


        //    BibleValue bibleValue=new BibleValue();
//    bibleValue.setBibleField();
//    bibleValue.setBibleObject(bibleObjectCreated);

      }else {
        //TODO
        //1. 如果不是递归本方法

//        field.getType().isAssignableFrom()
        this.create(bible,bibleObjectCreated,null);


      }

    }
    return null;
  }






  @Override
  public <BIBLE extends BibleType> BIBLE update(BIBLE bible) {
    return null;
  }


  @Override
  public List findAllByClass(Class clazz) {
    return null;
  }


}
