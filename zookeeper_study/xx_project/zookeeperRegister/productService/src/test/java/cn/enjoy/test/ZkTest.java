package cn.enjoy.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;


public class ZkTest {

    private String path = "/testnode";

    @Test
    public void createNode() throws Exception {

        ZooKeeper zooKeeper =  new ZooKeeper("127.0.0.1:2181",5000, null);

        Stat exists = zooKeeper.exists(path, false);
        //先判断服务根路径是否存在
        if(exists == null) {
            zooKeeper.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        zooKeeper.close();
    }


    @Test
    public void createWatch() throws Exception {

        ZooKeeper zooKeeper =  new ZooKeeper("127.0.0.1:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if(watchedEvent.getType() == Event.EventType.NodeDeleted && path.equals(watchedEvent.getPath())){
                    System.out.println("你的东西已经被一刀切了，不在了！");
                }
            }
        });

        Stat exists = zooKeeper.exists(path, true);
        if(exists !=null) {
            zooKeeper.delete(path,-1);
        }

        Thread.sleep(3);
        zooKeeper.close();
    }
}
