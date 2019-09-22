package com.gupao.study.serializer;

import com.gupao.study.entity.Company;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @Author long
 * @Date 2019/8/15 11:16
 */
public class CompanyDeserializer implements Deserializer {
    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        if (data.length < 8) {
            throw new SerializationException("size of data received by Deserializer is short expected!");
        }
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int nameLen, addressLen;
        String name = null, address = null;

        nameLen = buffer.getInt();
        byte[] nameBytes = new byte[nameLen];
        buffer.get(nameBytes);

        addressLen = buffer.getInt();
        byte[] addressBytes = new byte[addressLen];
        buffer.get(addressBytes);

        try {
            name = new String(nameBytes, "UTF-8");
            address = new String(addressBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Company(name, address);
    }

    @Override
    public void close() {

    }
}
