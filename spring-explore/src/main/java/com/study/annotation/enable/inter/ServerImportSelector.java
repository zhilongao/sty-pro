package com.study.annotation.enable.inter;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @Author long
 * @Date 2019/9/28 17:24
 */
public class ServerImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 读取Enable中所有的属性方法，本例中仅有type() 属性方法
        // 其中 key 为属性方法的名称，value 为属性方法的返回对象
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableServer.class.getName());
        // 获取名为"type"的属性方法，并且将其强制转化成 Server.Type 类型
        Server.Type type = (Server.Type)annotationAttributes.get("type");
        // 导入的类名称数组
        String[] importClassNames = new String[0];
        switch (type) {
            case HTTP:
                importClassNames = new String[]{HttpServer.class.getName()};
                break;
            case FTP:
                importClassNames = new String[]{FtpServer.class.getName()};
                break;
        }
        return importClassNames;
    }
}
