package com.gupao.study.serializer;

import com.gupao.study.entity.Company;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @Author long
 * @Date 2019/8/15 10:13
 */
public class CompanySerializer implements Serializer<Company> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String topic, Company data) {
        System.out.println("CompanySerializer.serialize");
        System.out.printf("topic:%s,data:%s\n", topic, data);
        byte[] name, address;

        try {
            if (data.getName() != null) {
                name = data.getName().getBytes("UTF-8");
            } else {
                name = new byte[0];
            }
            if (data.getAddress() != null) {
                address = data.getAddress().getBytes("UTF-8");
            } else {
                address = new byte[0];
            }
            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + name.length + address.length);
            buffer.putInt(name.length);
            buffer.put(name);
            buffer.putInt(address.length);
            buffer.put(address);
            return buffer.array();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
