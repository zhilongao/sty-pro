package com.gupao.study.serializer;

import com.gupao.study.entity.Company;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @Author long
 * @Date 2019/8/17 11:13
 * 通用反序列化器
 */
public class CommonDeserializer implements Deserializer {

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        int a = 65;
        System.out.println();
        if (data == null) {
            return null;
        }
        Schema<Company> schema = RuntimeSchema.getSchema(Company.class);
        Company ans = new Company();
        ProtostuffIOUtil.mergeFrom(data, ans, schema);
        return ans;
    }

    @Override
    public void close() {

    }
}
