package com.example.http.message;

import com.example.entity.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @Author long
 * @Date 2019/7/26 11:05
 * @Description 自定义消息转换器
 */
public class PropertiesPersonHttpMessageConvert extends AbstractHttpMessageConverter<User> {

    public PropertiesPersonHttpMessageConvert() {
        super(MediaType.valueOf("application/properties+user"));
        setDefaultCharset(Charset.forName("UTF-8"));
    }


    @Override
    protected boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(User.class);
    }

    @Override
    protected User readInternal(Class<? extends User> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        // 将请求中的内容转化到Properties中
        InputStream inputStream = httpInputMessage.getBody();
        Properties properties = new Properties();
        properties.load(new InputStreamReader(inputStream, getDefaultCharset()));
        // 将properties中的内容转换到User中
        User user = new User((String)properties.getProperty("user.name"), Integer.valueOf((String)properties.get("user.age")));
        return user;
    }

    @Override
    protected void writeInternal(User user, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream outputStream = httpOutputMessage.getBody();
        Properties p = new Properties();
        p.put("user.name", user.getName());
        p.put("user.age", String.valueOf(user.getAge()));
        p.store(new OutputStreamWriter(outputStream, getDefaultCharset()), "my default comment");
    }
}
