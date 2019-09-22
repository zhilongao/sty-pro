package com.gupao.study;

import com.gupao.study.entity.Company;
import com.gupao.study.interceptor.ProducerInterceptorPrefix;
import com.gupao.study.serializer.CommonSerializer;
import com.gupao.study.serializer.CompanySerializer;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @Author long
 * @Date 2019/8/12 20:38
 */
public class KafkaProducerService extends Thread {

    public static final String brokeList = "192.168.25.147:9092";
    public static final String topic = "topic-demon";

    public static void main(String[] args) {
        sendMessage();
    }


    public static void sendMessage() {
        // 初始化配置
        Properties props = init();
        //创建KafkaProducer实例
        KafkaProducer<String, Company> producer = new KafkaProducer<String, Company>(props);
        // 构建所需要发送的消息
        Company company = Company.builder().name("管家").address("大钟寺").build();
        ProducerRecord<String, Company> record = new ProducerRecord<String, Company>(topic, company);
        // 发送消息
        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 关闭生产者客户端
        producer.close();
    }

    public static Properties init() {
        Properties properties = new Properties();
        // key.serializer->org.apache.kafka.common.serialization.StringSerializer
        properties.put("key.serializer", StringSerializer.class.getName());
        // value.serializer->org.apache.kafka.common.serialization.StringSerializer
        properties.put("value.serializer", CommonSerializer.class.getName());
        // bootstrap.servers
        properties.put("bootstrap.servers", brokeList);
        // filter
        properties.put("interceptor.classes", ProducerInterceptorPrefix.class.getName());
        return properties;
    }


}
