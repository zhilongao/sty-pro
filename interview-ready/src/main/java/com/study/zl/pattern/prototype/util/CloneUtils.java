package com.study.zl.pattern.prototype.util;

import java.io.*;

/**
 * @Author long
 * @Date 2019/3/10 16:49
 */
public class CloneUtils {

    // 基于字节流的拷贝方式
    public static Object deepCloneByStream(Object o){
        //first step:write object to ObjectOutputStream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //second step:read object from ObjectInputStream
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = null;
        Object result = null;
        try {
            ois = new ObjectInputStream(bis);
            result = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}
