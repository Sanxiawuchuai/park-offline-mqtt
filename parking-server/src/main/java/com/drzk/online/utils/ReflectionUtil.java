package com.drzk.online.utils;

import com.drzk.utils.StringUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 反射帮助类:
 * @Date: 2018/5/7 14:48
 * @Auther: nothing
 */
public class ReflectionUtil {
    /**
     * 获取所有属性集合(包含父类)
     */
    public static Field[] getAllField(Class<?> clazz) {
        List<Field> allFields = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        allFields.addAll( Arrays.asList( fields ) );
        for (Class<?> superClazz = clazz.getSuperclass(); superClazz != Object.class; superClazz = superClazz.getSuperclass()) {
            Field[] superFields = superClazz.getDeclaredFields();
            allFields.addAll( Arrays.asList( superFields ) );
        }
        return allFields.toArray( new Field[allFields.size()] );
    }

    /**
     * 获取所有方法集合(包含父类)
     */
    public static Method[] getAllMethod(Class<?> clazz) {
        List<Method> allMethods = new ArrayList<>();
        Method[] fields = clazz.getDeclaredMethods();
        allMethods.addAll( Arrays.asList( fields ) );
        for (Class<?> superClazz = clazz.getSuperclass(); superClazz != Object.class; superClazz = superClazz.getSuperclass()) {
            Method[] superFields = superClazz.getDeclaredMethods();
            allMethods.addAll( Arrays.asList( superFields ) );
        }
        return allMethods.toArray( new Method[allMethods.size()] );
    }

    /**
     * 获取属性的值
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        try {
            if (obj instanceof Collection<?>) {
                return getCollectionFieldValue( (Collection<?>) obj, fieldName );
            }
            Object value = PropertyUtils.getProperty( obj, fieldName );
            if (value == null) {
                return null;
            }
            if ((value instanceof String) && StringUtils.isNullOrEempty( value.toString() )) {
                return null;
            }
            return value;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 设置属性的值
     * @param obj      要设置值的实体
     * @param fileName 字段名称
     * @param val   要设置到值
     */
    public static void setFieldValue(Object obj, String fileName, Object val) {
        try {
            Field field=getDeclaredField(obj.getClass(),fileName);
            boolean accessible = field.isAccessible();
            if (!accessible) {
                field.setAccessible( true );
            }
            field.set( obj, val );
            field.setAccessible( accessible );
        } catch (Exception e) {

        }
    }

    public static Field getDeclaredField(Class cla,String fieldName) {
        for(Class superClass = cla; superClass != null; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField( fieldName );
            } catch (NoSuchFieldException e) {

            }
        }
        return null;
    }

    private static Object getCollectionFieldValue(Collection<?> collection, String fieldName) {
        if (CollectionUtils.isEmpty( collection )) {
            return null;
        }
        Object[] arrays = collection.toArray();
        int index = fieldName.indexOf( "." );
        if (index != 0) {
            String indexObj = fieldName.substring( 0, index );
            String indexField = fieldName.substring( index + 1 );
            if ("last".equals( indexObj )) {
                return getFieldValue( arrays[arrays.length - 1], indexField );
            }
            return getFieldValue( arrays[Integer.parseInt( indexObj )], indexField );
        }
        return getFieldValue( arrays[0], fieldName );
    }


    /**
     * 将实体转换成map
     */
    public static Map<String, Object> setEntityToMap(Object object) {
        Field[] fields = ReflectionUtil.getAllField( object.getClass() );
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Object value = ReflectionUtil.getFieldValue( object, field.getName() );
            if (value != null) {
                map.put( field.getName(), value );
            }
        }
        return map;
    }

    /**
     * 将map转换成实体
     */
    public static void setMapToEntity(Map<String, Object> map, Object object) {
        Field[] fields = ReflectionUtil.getAllField( object.getClass() );
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (map.containsKey( field.getName() )) {
                field.setAccessible( true );
                try {
                    field.set( object, map.get( field.getName() ) );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将map转换成实体
     */
    public static Map<String,Object> setMapToMap(Map<String,String> map, Map<String,Object> result) {
        Map<String,Object> newMap=new HashMap<>( );
        result.forEach( (key,val)->{
            if(map.containsKey( key )){
                newMap.put( map.get( key ),val );
            }
        } );
        return newMap;
    }

    /**
     * 将map转换成实体
     */
    public static void setMapStrToEntity(Map<String, Object> map, Object object) {
        Field[] fields = ReflectionUtil.getAllField( object.getClass() );
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (map.containsKey( field.getName() )) {
                field.setAccessible( true );
                try {
                    if(field.getType()==String.class){
                        field.set( object, map.get( field.getName() ).toString() );
                    }else{
                        field.set( object, map.get( field.getName() ) );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
