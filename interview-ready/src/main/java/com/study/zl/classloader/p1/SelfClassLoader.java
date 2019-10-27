package com.study.zl.classloader.p1;

import java.io.*;

/**
 * @Author long
 * @Date 2019/10/13 10:40
 */
public class SelfClassLoader extends ClassLoader {

    private String classpath;

    public SelfClassLoader(String classpath) {
        this.classpath = classpath;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = getData(name);
            if (data != null) {
                // 调用 defineClass 方法将字节码转化为Class
                return defineClass(name, data, 0 , data.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }


    /**
     * 返回类的字节码
     *
     * @param className
     * @return
     * @throws IOException
     */
    private byte[] getData(String className) throws IOException {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        String path = classpath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try {
            in = new FileInputStream(path);
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            return out.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
        }
        return null;
    }
}
