package com.study.mongo;


import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.study.entity.User;
import org.bson.Document;

import java.util.regex.Pattern;

/**
 * @Author long
 * @Date 2019/9/2 18:50
 */
public class MongoDb {

    public static void main(String[] args) {
        try {
            findAll("test", "user");
            System.out.println("--------------------------------------");
            /*Document document = new Document()
                    .append("name", "mick11")
                    .append("age", "33")
                    .append("address", "长沙")
                    .append("sex", "男");
            insert("test", "user", document);*/
            findAll("test", "user");
            System.out.println("---------------------------------------");
            User user = new User();
            user.setName("mick");
            findByEq("test", "user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取某个collection下面的document,模糊查询
     * @param databaseName
     * @param collectionName
     * @param user
     */
    public static void findByEq(String databaseName, String collectionName, User user) {
        MongoDatabase database = MongoUtil.getConnection(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        BasicDBObject query = new BasicDBObject();
        // 填写查询条件
        if (user .getName() != null) {
            // 名称模糊匹配
            Pattern pattern = Pattern.compile("^.*"+user.getName()+".*$", Pattern.CASE_INSENSITIVE);
            query.put("name", pattern);
        }
        if (user.getAge() != 0) {
            query.put("age", user.getAge());
        }
        // 排序
        BasicDBObject sort = new BasicDBObject();
        // 1,表示正序； －1,表示倒序
        sort.put("name", 1);
        FindIterable<Document> documents = collection.find(query).sort(sort);
        // 打印
        print(documents);
    }


    /**
     * 获取某个collection下面的document
     *
     * @param databaseName
     * @param collectionName
     */
    public static void findAll(String databaseName, String collectionName) {
        MongoDatabase database = MongoUtil.getConnection(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        FindIterable<Document> documents = collection.find();
        print(documents);
    }

    /**
     * 插入一条文档
     *
     * @param databaseName
     * @param collectionName
     * @param document
     */
    public static void insert(String databaseName, String collectionName, Document document) {
        MongoDatabase database = MongoUtil.getConnection(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(document);
    }


    /**
     * 打印Documents列表
     *
     * @param documents
     */
    public static void print(FindIterable<Document> documents) {
        for (Document document : documents) {
            System.out.println(document.toJson());
        }
    }
}
