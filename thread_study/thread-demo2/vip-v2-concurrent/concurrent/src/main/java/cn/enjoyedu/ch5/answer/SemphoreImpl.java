package cn.enjoyedu.ch5.answer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemphoreImpl<T> implements IBoundedBuffer<T> {

    private final Semaphore items,spaces;
    private List queue = new LinkedList();

    public SemphoreImpl(int capacityInt) {
        this.spaces = new Semaphore(capacityInt);
        this.items = new Semaphore(0);
    }

    @Override
    public void put(T x) throws InterruptedException {
        spaces.acquire();
        synchronized (this){
            queue.add(x);
        }
        items.release();
    }

    @Override
    public T take() throws InterruptedException {
        items.acquire();
        T t ;
        synchronized (this){
            t = (T)queue.remove(0);
        }
        spaces.release();
        return t;
    }
}
