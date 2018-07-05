package com.minlia.module.bible.v2.service;

import com.minlia.module.bible.v2.abstraction.BibleType;
import com.minlia.module.bible.v2.entity.BibleClass;
import com.minlia.module.bible.v2.entity.BibleObject;
import com.minlia.module.bible.v2.repository.BibleClassRepository;
import com.minlia.module.bible.v2.repository.BibleFieldRepository;
import com.minlia.module.bible.v2.repository.BibleObjectRepository;
import com.minlia.module.bible.v2.repository.BibleValueRepository;
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
  public <BIBLE extends BibleType> BIBLE create(BIBLE bible) {

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

    BibleObject bibleObject = new BibleObject();
    bibleObject.setBibleClass(bibleClassFound);
    bibleObject.setBibleField(null);
    bibleObject.setParentBibleObject(null);
    //TODO
    bibleObject.setSortOrder(0);

    bibleObjectRepository.save(bibleObject);

    Field[] fields = bibleClass.getFields();
    for (Field field : fields) {
      //检查是否为基础类型
      Boolean isPrimitive = field.getType().isPrimitive();
      if (!isPrimitive) {

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
