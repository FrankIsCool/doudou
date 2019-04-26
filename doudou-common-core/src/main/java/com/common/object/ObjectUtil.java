package com.common.object;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjectUtil {

    /**
     * 根据实体类组装成map<属性名，属性值>
     * @param object    实体类
     * @return  map<属性名，属性值>
     */
    public static Map<String,Object> toMap(Object object){
        Field[] fields=object.getClass().getDeclaredFields();
        Map<String,Object> map = new HashMap<>();
        for(int i=0;i<fields.length;i++){
            map.put(fields[i].getName(),getFieldValueByName(fields[i].getName(),object));
        }
        return map;
    }

    /**
     * 根据实体类的属性名获取属性值
     * @param fieldName     属性名
     * @param object        实体类
     * @return 属性值
     */
    private static Object getFieldValueByName(String fieldName, Object object) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = object.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(object, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }
}
