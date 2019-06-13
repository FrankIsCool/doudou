package com.common.object;

import com.common.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjectUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
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
            logger.error("get object field value is error: fieldName = "+fieldName+",object = "+object,e);
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 数组转对象
     * @param bytes
     * @return
     */
    public static Object toObject (byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            logger.error("bytes to object is error: bytes = "+bytes.toString(),ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            logger.error("bytes to object is error: bytes = "+bytes.toString(),ex);
            ex.printStackTrace();
        }
        return obj;
    }
}
