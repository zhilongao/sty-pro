package com.study.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/9/1 17:27
 */
public class OperateApp {


    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.25.150", 6379);
        // insertKeys(jedis);
        scanOperate(jedis);
    }

    /**
     * 插入测试数据
     * @param jedis
     */
    private static void insertKeys(Jedis jedis) {
        int count = 10000;
        for (int i = 0; i < count; i++) {
            jedis.set("key" + i, i + "");
        }
    }

    /**
     * scan命令
     * @param jedis
     */
    public static void scanOperate(Jedis jedis) {
        String cursor = "0";
        ScanParams scanParams = new ScanParams();
        scanParams.match("key99*");
        scanParams.count(1000);
        while (true) {
            ScanResult<String> result = jedis.scan(cursor, scanParams);
            List<String> resultList = result.getResult();
            for (String resultStr : resultList) {
                System.out.print(resultStr + "\t");
            }
            System.out.println("\n--------------");
            String stringCursor = result.getStringCursor();
            if (stringCursor.equals("0")) {
                break;
            } else {
                cursor = stringCursor;
            }
        }
    }

}
