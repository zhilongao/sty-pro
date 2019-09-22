package com.gupao.study.interceptor;

import com.gupao.study.entity.Company;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @Author long
 * @Date 2019/8/15 10:31
 * Kafka生产者拦截器
 */
public class ProducerInterceptorPrefix implements ProducerInterceptor<String, Company> {

    private volatile long sendSuccess = 0;
    private volatile long sendFailure = 0;

    @Override
    public ProducerRecord<String, Company> onSend(ProducerRecord<String, Company> record) {
        Company company = record.value();
        System.out.printf("ProducerInterceptorPrefix before:%s\n", company);
        company.setAddress("prefix1-" + company.getAddress());
        System.out.printf("ProducerInterceptorPrefix after:%s\n", company);
        return new ProducerRecord<>(record.topic(), record.partition(), record.timestamp(), record.key(), company, record.headers());
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e == null) {
            sendSuccess ++;
        } else {
            sendFailure ++;
        }
    }

    @Override
    public void close() {
        double successRation = (double) sendSuccess / (sendSuccess + sendFailure);
        System.out.println("[INFO] 发送成功率=" + String.format("%f", successRation * 100) + "%");
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
