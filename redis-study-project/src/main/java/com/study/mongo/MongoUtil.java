package com.study.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * @Author long
 * @Date 2019/9/2 18:53
 */
public class MongoUtil {

    private static String baseUrl = "192.168.25.147:27017";
    private static String userName = "test";
    private static String passWord = "test";
    private static final String url = "mongodb://"+ userName +":"+ passWord + "@"+ baseUrl + "/";


    public static MongoDatabase getConnection(String dataName) {
        // 获取MongoClient
        com.mongodb.client.MongoClient mongoClient = MongoClients.create(url + dataName);
        // 获取MongoDatabase
        MongoDatabase db = mongoClient.getDatabase(dataName);
        return db;
    }
}
