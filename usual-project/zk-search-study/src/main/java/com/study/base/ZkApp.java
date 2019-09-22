package com.study.base;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/7/23 21:44
 */
public class ZkApp {

    public static void main( String[] args ) throws Exception {
        // nodeOperate();
        nodeWatch();
    }

    /**
     * 使用zookeeper客户端做增删该查操作
     */
    public static void nodeOperate() {
        final CountDownLatch latch = new CountDownLatch(1);
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper("", 4000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
            ZooKeeper.States state = zooKeeper.getState();
            System.out.println(state);

            System.out.println("==========创建节点==========");
            // zooKeeper.create("/nodeA", "1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            Stat stat = new Stat();
            byte[] data1 = zooKeeper.getData("/nodeA", null, stat);
            System.out.println(new String(data1) + "--" + stat.getVersion());

            zooKeeper.setData("/nodeA", "2".getBytes(), stat.getVersion());
            byte[] data2 = zooKeeper.getData("/nodeA", null, stat);
            System.out.println(new String(data2) + "--" + stat.getVersion());

            zooKeeper.delete("/nodeA", stat.getVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            if (zooKeeper != null) {
                try {
                    zooKeeper.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * node的watch监听机制
     */
    public static void nodeWatch() throws Exception{
        final CountDownLatch latch = new CountDownLatch(1);

        try {
            final ZooKeeper zooKeeper  = new ZooKeeper("", 4000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("-->zk默认监听事件!");
                    latch.countDown();
                }
            });
            latch.await();
            ZooKeeper.States state = zooKeeper.getState();
            System.out.println(state);

            // zooKeeper.create("/watchNode", "00".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            Stat stat = zooKeeper.exists("/watchNode", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println(watchedEvent.getType() + "-->" + watchedEvent.getPath());
                    // 循环监听
                    try {
                        zooKeeper.exists("/watchNode", new Watcher() {
                            @Override
                            public void process(WatchedEvent watchedEvent) {
                                System.out.println("hello,world");
                            }
                        });
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            stat = zooKeeper.setData("/watchNode", "11".getBytes(), stat.getVersion());

            TimeUnit.MILLISECONDS.sleep(2000);

            stat = zooKeeper.setData("/watchNode", "22".getBytes(), stat.getVersion());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

}
