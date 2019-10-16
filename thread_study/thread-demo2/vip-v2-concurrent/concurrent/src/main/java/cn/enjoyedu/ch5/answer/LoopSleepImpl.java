package cn.enjoyedu.ch5.answer;

import java.util.LinkedList;
import java.util.List;

public class LoopSleepImpl<E> implements IBoundedBuffer<E> {

    private List queue = new LinkedList();
    private final int limit;
    private final static int  INTERVAL = 50;

    public LoopSleepImpl(int limit) {
        this.limit = limit;
    }


    @Override
    public void put(E x) throws InterruptedException {
        while(true){
            synchronized (this){
                if(!(queue.size()==0)){
                    queue.add(x);
                    return;
                }
            }
            Thread.sleep(INTERVAL);
        }

    }

    @Override
    public E take() throws InterruptedException {
        while(true){
            synchronized (this){
                if(!(queue.size()==0)){
                    return (E)queue.remove(0);
                }
            }
            Thread.sleep(INTERVAL);
        }
    }
}
