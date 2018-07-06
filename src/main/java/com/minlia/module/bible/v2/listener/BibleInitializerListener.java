package com.minlia.module.bible.v2.listener;


import com.minlia.module.bible.v2.abstraction.BibleType;
import io.github.benas.randombeans.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author will
 */
public class BibleInitializerListener implements
    ApplicationListener<ApplicationReadyEvent> {

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    System.out.println("BibleInitializerListener onApplicationEvent");

    //目的，扫描出所有实现自BibleType的类
    //并将所有包名，类名，属性名取出

    //首先发起一次扫描，取出所有子类
    List<Class<?>> classes = scann();

    //子类取到，每一个检查是否在属性级别有此种(实现BibleType)类型的参数，如果有则添加到上面的classes中，并递归扫描此类

//    check(classes);

  }

  private List<Class<?>> scann() {
    List<Class<?>> bibles = ReflectionUtils.getPublicConcreteSubTypesOf(BibleType.class);
    return bibles;
  }


  private Boolean checkType(Class<?> classToCheck, List<Class<?>> classList) {

//    for (Class<?> classToCheck : classList) {
    if (null != classToCheck) {
      Field[] fields = classToCheck.getFields();
      for (Field field : fields) {
        Type type = field.getGenericType();
        try {
          Type[] types = ((ParameterizedType) type).getActualTypeArguments();
          //只检查了第一个参数变量
          Class xx = (Class) types[0];
          // 找到了这个Area   in  private List<Area> areas;
          //如果不是基础类型
          //也不是List,Set
          //也不是Bible类型

          //返回FALSE

          //否则如果是List,Set
          //参数是否基础类型或则其它Bible类型
          Boolean isPrimitive = ((Class) type).isPrimitive();
          Boolean isCollection = type.equals(List.class) || type.equals(Set.class);
          Boolean isCollectionTypeArgumentIsPrimitive = xx.isPrimitive();
          Boolean isCollectionTypeArgumentIsBibleType = classList.contains(xx);
          Boolean isBibleType = classList.contains(type);

          if (!isPrimitive && !isCollection && !isBibleType) {
            return Boolean.FALSE;
          } else if (isCollection && !(isCollectionTypeArgumentIsPrimitive
              || isCollectionTypeArgumentIsBibleType)) {
            return Boolean.FALSE;
          }
        } catch (ClassCastException e) {
          //当不是复杂对象时不处理
//          e.printStackTrace();
        }
      }
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }


}
