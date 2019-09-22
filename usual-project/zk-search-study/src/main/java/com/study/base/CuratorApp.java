package com.study.base;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @Author long
 * @Date 2019/7/23 21:44
 */
public class CuratorApp {

    public static void main(String[] args) {
        // curatorOperate();
        curatorWatch();
    }

    /**
     * watch机制
     */
    public static void curatorWatch() {
        CuratorFramework curator = CuratorFrameworkFactory
                .builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("curator")
                .build();
        curator.start();
        // 当前节点的创建和删除事件监听
        // addListenerWithNodeCache(curator, "/mic");

        // 子节点事件监听
        // addListenerWithChildrenNodeCache(curator, "/mic");

        // 综合节点事件监听
        addListenerWithTreeNodeCache(curator, "/mic");
        
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 综合节点事件监听
     * @param curator
     * @param path
     */
    private static void addListenerWithTreeNodeCache(CuratorFramework curator, String path) {
        TreeCache treeCache=new TreeCache(curator, path);
        TreeCacheListener treeCacheListener=new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println(event.getType()+"->"+event.getData().getPath());
            }
        };

        treeCache.getListenable().addListener(treeCacheListener);
        try {
            treeCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 针对已经存在节点的子节点创建监听 添加 修改 删除
     * @param curator
     * @param path
     */
    private static void addListenerWithChildrenNodeCache(CuratorFramework curator, String path) {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curator, path, true);
        PathChildrenCacheListener listener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
                System.out.println("publish event, type: " +  event.getType());
            }
        };
        pathChildrenCache.getListenable().addListener(listener);
        try {
            pathChildrenCache.start(PathChildrenCache.StartMode.NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 只针对某个节点的创建和修改做监听,不会监听子节点的变化
     * @param curator
     * @param path
     */
    private static void addListenerWithNodeCache(CuratorFramework curator, String path) {
        final NodeCache nodeCache = new NodeCache(curator, path, false);
        NodeCacheListener listener = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("receive  event:" + nodeCache.getCurrentData().getPath());
            }
        };
        nodeCache.getListenable().addListener(listener);
        try {
            nodeCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 基本操作
     */
    public static void curatorOperate() {
        CuratorFramework curator = CuratorFrameworkFactory
                .builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("curator")
                .build();
        curator.start();
        // 节点创建
        try {
           curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/zl/node1", "0".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 节点修改
        Stat stat = new Stat();
        try {
            curator.getData().storingStatIn(stat).forPath("/zl/node1");
            curator.setData().withVersion(stat.getVersion()).forPath("/zl/node1", "xx".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 节点删除
        try {
            curator.delete().deletingChildrenIfNeeded().forPath("/zl");
        } catch (Exception e) {
            e.printStackTrace();
        }
        curator.close();
    }

}
