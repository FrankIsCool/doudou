package com.common.serialize;
import com.common.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class SerializeUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    /**
     * java对象序列化成字节数组
     *
     * @param object
     * @return
     */
    public static byte[] toBytes(Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException ex) {
            logger.error("Serialize object is error：object="+object.toString(),ex);
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            try {
                oos.close();
            } catch (Exception ex) {
                logger.error("Serialize object close is error：object="+object.toString(),ex);
            }
        }
    }


    /**
     * 字节数组反序列化成java对象
     *
     * @param bytes
     * @return
     */
    public static Object toObject(byte[] bytes) {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bais);
            Object object = ois.readObject();
            return object;
        } catch (IOException ex) {
            logger.error("Serialize toObject is error：bytes="+bytes.toString(),ex);
            throw new RuntimeException(ex.getMessage(), ex);
        } catch (ClassNotFoundException ex) {
            logger.error("Serialize toObject is error：bytes="+bytes.toString(),ex);
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            try {
                ois.close();
            } catch (Exception ex) {
                logger.error("Serialize object close is error：bytes="+bytes.toString(),ex);
            }
        }
    }
}
