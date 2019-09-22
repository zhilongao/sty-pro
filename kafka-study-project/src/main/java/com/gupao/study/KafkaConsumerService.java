package com.gupao.study;

import com.gupao.study.entity.Company;
import com.gupao.study.serializer.CommonDeserializer;
import com.gupao.study.serializer.CompanyDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

/**
 * @Author long
 * @Date 2019/8/13 7:26
 */
public class KafkaConsumerService extends Thread {


    public static final String brokeList = "192.168.25.147:9092";
    public static final String topic = "topic-demon";
    public static final String group = "group.demon";

    public static void main(String[] args) {
        receiveMessage();
    }


    public static void receiveMessage() {
        // 初始化配置
        Properties props = init();
        // 创建一个消费者客户端
        KafkaConsumer<String, Company> consumer = new KafkaConsumer<String, Company>(props);
        // 订阅主题
        consumer.subscribe(Collections.singletonList(topic));
        // 循环消费消息
        while (true) {
            ConsumerRecords<String, Company> records = consumer.poll(1000);
            for (ConsumerRecord<String, Company> record : records) {
                System.out.printf("接收到消息:%s\n", record.value());
            }
        }
    }

    public static Properties init() {
        Properties properties = new Properties();
        // key.deserializer->org.apache.kafka.common.serialization.StringDeserializer
        properties.put("key.deserializer", StringDeserializer.class.getName());
        // value.deserializer->org.apache.kafka.common.serialization.StringDeserializer
        properties.put("value.deserializer", CommonDeserializer.class.getName());
        // bootstrap.servers
        properties.put("bootstrap.servers", brokeList);
        // 设置消费的组名称
        properties.put("group.id", group);
        properties.put("client.id", "consumer.client.id.demo");
        return properties;
    }

}