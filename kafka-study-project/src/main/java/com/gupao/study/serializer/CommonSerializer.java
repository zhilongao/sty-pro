package com.gupao.study.serializer;

import com.gupao.study.entity.Company;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @Author long
 * @Date 2019/8/15 13:11
 * 通用序列化器
 */
public class CommonSerializer implements Serializer<Company> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String topic, Company data) {
        return doSerialize(topic, data);
    }


    private  <T> byte[] doSerialize(String topic, T data) {
        if (data == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(data.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] protostuff = null;
        try {
            protostuff = ProtostuffIOUtil.toByteArray(data, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage (), e);
        } finally {
            buffer.clear();
        }
        return protostuff;
    }

    @Override
    public void close() {

    }
}
