package cn.enjoyedu.ch4.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 类说明：FutureTask的get方法实现：
 */
public class MyFutureTaskToo <V> implements Runnable,Future<V> {

    Callable<V> callable ;  //封装业务逻辑

    V result = null ; //执行结果

    public MyFutureTaskToo(Callable<V> callable){
        this.callable = callable;
    }


    //多线程执行run
    @Override
    public void run() {
        try {
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        synchronized(this){
            System.out.println("-----");
            this.notifyAll();
        }
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        if(result != null){
            return result;
        }
        System.out.println("等待执行结果……等待中");
        synchronized (this) {
            this.wait();  //等待futurtask执行完，全部线程…………。
        }
        return result;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public V get(long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException {
        return null;
    }
}
