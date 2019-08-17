package com.peter.zookeeper.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class CuratorDistributedLock implements Lock, java.io.Serializable{

    private static final long serialVersionUID = -849788834855571605L;
    private static final Logger LOG = LoggerFactory.getLogger(CuratorDistributedLock.class);
    /** module对应lock */
    /** 模块 */
    private String module;
    /** 基于zookepper的分布式锁 */
    private InterProcessMutex interProcessMutex;

    private static final CuratorFramework client;

    private boolean hold = true;
    static {
        client = CuratorFrameworkFactory.newClient("192.168.140.128:2181",
                new ExponentialBackoffRetry(1000, 3));
        client.start();
    }

    public CuratorDistributedLock(String module) {
        this.module = module;
    }

    @Override
    public void lock() {
        try {
            interProcessMutex = getInterProcessMutex();
            interProcessMutex.acquire();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取连接
     *
     * @return
     */
    private InterProcessMutex getInterProcessMutex() {
        return new InterProcessMutex(client, "/lock/" + module);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException("分布式锁不支持lockInterruptibly方法");
    }

    @Override
    public boolean tryLock() {
        try {
            interProcessMutex = getInterProcessMutex();
            boolean result = interProcessMutex.acquire(0, TimeUnit.SECONDS);
            if (!result) {
                hold = false;
            }
            return result;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        long start = System.currentTimeMillis();
        try {
            long surplusTime = calcSurplusTime(time, unit, System.currentTimeMillis() - start);
            boolean result = interProcessMutex.acquire(surplusTime, unit);
            if (!result) {
                hold = false;
            }
            return result;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 计算剩余时间,转换单位
     *
     * @param time
     * @param unit
     * @param pastTimes
     * @return
     */
    private long calcSurplusTime(long time, TimeUnit unit, long pastTimes) {
        long milliTime = unit.convert(time, TimeUnit.MILLISECONDS);
        long surplusTime = Math.max(0, milliTime - pastTimes);
        return TimeUnit.MILLISECONDS.convert(surplusTime, unit);
    }

    @Override
    public void unlock() {
        try {
            if (hold) {
                interProcessMutex.release();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}
