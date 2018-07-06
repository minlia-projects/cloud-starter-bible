import com.minlia.module.bible.v2.abstraction.BibleType;
import io.github.benas.randombeans.util.ReflectionUtils;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.ClassUtils;
import org.assertj.core.util.Lists;
import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;

public class Test {


  @org.junit.Test
  public void check() {
    List<Class<?>> scannResult = scann();

    for (Class<?> clz : scannResult) {
      if (checkType(clz, scannResult)) {
        System.out.println(clz + "通过 OK");
      } else {
        System.out.println(clz + "不通过 FALSE");
      }
    }


  }

  private Boolean checkType(Class<?> classToCheck, List<Class<?>> classList) {
//    for (Class<?> classToCheck : classList) {
    if (null != classToCheck) {
      Field[] fields = classToCheck.getDeclaredFields();
      for (Field field : fields) {
        Type rawType = field.getGenericType();
        Type baseType=null;
        Boolean isParameterizedType=rawType instanceof ParameterizedType;
        if(isParameterizedType){
          baseType = ((ParameterizedType) rawType).getRawType();
        }else {
          baseType=rawType;
        }
        try {
          //否则如果是List,Set
          //参数是否基础类型或则其它Bible类型

          Boolean isPrimitive = isPrimitive((Class)baseType);
          Boolean isCollection = baseType.equals(List.class) || baseType.equals(Set.class);
          Boolean isBibleType = classList.contains(baseType);

          // 找到了这个Area   in  private List<Area> areas;
          //如果不是基础类型
          //也不是List,Set
          //也不是Bible类型
          //返回FALSE

          if (!isPrimitive && !isCollection && !isBibleType) {
            System.out.println("Not supported base type " + baseType);
            return Boolean.FALSE;
          } else if (isCollection) {
            Type[] types = ((ParameterizedType) rawType).getActualTypeArguments();
            Boolean isParameterizedType0=types[0] instanceof ParameterizedType;
            if (isParameterizedType0) {
              System.out.println("只支持一级参数化 "+ types[0]);
              return Boolean.FALSE;
            }
              //只检查了第一个参数变量
              Class xx = (Class) types[0];

            Boolean isCollectionTypeArgumentIsPrimitive = isPrimitive(xx);
            Boolean isCollectionTypeArgumentIsBibleType = classList.contains(xx);

            if (!(isCollectionTypeArgumentIsPrimitive
                || isCollectionTypeArgumentIsBibleType)) {
              System.out.println("Not supported type parameter " + rawType);
              return Boolean.FALSE;
            }

          }
        } catch (ClassCastException e) {
          //当不是复杂对象时不处理
//          e.printStackTrace();
          System.out.println("异常 " +rawType+""+ e.getMessage());
          return Boolean.FALSE;
        }
      }
      return Boolean.TRUE;
    }
    System.out.println("Class is null");
    return Boolean.FALSE;
  }



  private Boolean isPrimitive(Class<?> clz){
    List supported= Lists.newArrayList(String.class,Long.class,Double.class,Number.class,String.class);

    return ClassUtils.isPrimitiveOrWrapper(clz);
//    return supported.contains(clz);
  }



  private List<Class<?>> scann() {
    System.out.println("BibleInitializerListener onApplicationEvent");
    List<Class<?>> bibles = ReflectionUtils.getPublicConcreteSubTypesOf(BibleType.class);
    return bibles;

  }


  private Class getPrimitiveGenericType(Field field) {
    Type type = field.getGenericType();
    if (type instanceof ParameterizedType) {
      ParameterizedType pt = (ParameterizedType) type;
      if (pt.getActualTypeArguments()[0] instanceof TypeVariableImpl) {
        // so this is a List<D> where D is defined in the owner class.
        // this value is not read here,
        // instead the class of the type is picked up from the json value "class" (Codec#classIdentifier) of each instance.
        return null;
      } else if (pt.getActualTypeArguments()[0] instanceof ParameterizedType) {
        // this is an array/collection/map that contains another array/collection/map
        return (Class) ((ParameterizedType) pt.getActualTypeArguments()[0]).getRawType();
      } else {
        return (Class) pt.getActualTypeArguments()[0];
      }
    } else {
      return null;
//      throw new RuntimeException("Missing generic type argument! " + field.toString());
    }
  }

  private Class[] getGenericTypes(Field field) {
    Type type = field.getGenericType();
    if (type instanceof ParameterizedType) {
      ParameterizedType pt = (ParameterizedType) type;
      Class[] result = new Class[pt.getActualTypeArguments().length];
      for (int i = 0; i < pt.getActualTypeArguments().length; i++) {
        result[i] = (Class) pt.getActualTypeArguments()[i];
      }
      return result;
    } else {
      return null;
//      throw new RuntimeException("Missing generic type argument! " + field.toString());
    }
  }


}
