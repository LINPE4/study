package cn.enjoyedu.ch5.answer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionImpl<E> implements IBoundedBuffer<E> {

    private List queue = new LinkedList();
    private final int limit;

    public LockConditionImpl(int limit) {
        this.limit = limit;
    }

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();



    @Override
    public void put(E x) throws InterruptedException {
        lock.lock();
        try{
            while (queue.size() == limit){
                notFull.await();
            }
            queue.add(x);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public E take() throws InterruptedException {
        E e ;
        lock.lock();
        try{
            while (queue.size() == 0){
                notEmpty.await();
            }
            e  = (E)queue.remove(0);;
            notFull.signal();
            return e;
        }finally {
            lock.unlock();
        }
    }
}
