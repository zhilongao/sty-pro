package com.gupao.study.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author long
 * @Date 2019/8/15 10:25
 */
public class SelfPartitioner implements Partitioner {


    private final AtomicInteger counter = new AtomicInteger(0);


    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int size = partitions.size();
        if (null == keyBytes) {
            return counter.getAndIncrement() % size;
        } else {
            return Utils.toPositive (Utils .murmur2(keyBytes)) % size;
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
