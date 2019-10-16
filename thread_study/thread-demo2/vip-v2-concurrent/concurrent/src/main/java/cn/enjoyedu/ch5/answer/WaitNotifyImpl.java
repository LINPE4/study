package cn.enjoyedu.ch5.answer;

import java.util.LinkedList;
import java.util.List;

public class WaitNotifyImpl<E> implements IBoundedBuffer<E> {

    private List queue = new LinkedList();
    private final int limit;

    public WaitNotifyImpl(int limit) {
        this.limit = limit;
    }


    @Override
    public synchronized void put(E x) throws InterruptedException {
        while(queue.size()>= limit)
            wait();
        queue.add(x);
        notifyAll();

    }

    @Override
    public synchronized E take() throws InterruptedException {
        while(queue.size()==0)
            wait();
        E e = (E)queue.remove(0);
        notifyAll();
        return e;
    }
}
