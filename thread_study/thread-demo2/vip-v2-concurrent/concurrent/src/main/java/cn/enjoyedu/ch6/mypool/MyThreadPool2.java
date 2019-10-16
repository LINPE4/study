package cn.enjoyedu.ch6.mypool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *类说明：自定义线程池实现
 */
public class MyThreadPool2 {

    /*缺省线程数据量*/
    private static int WORK_COUNT = 5;

    /*存放任务*/
    private final BlockingQueue<Runnable> taskQueue;
    /*工作线程*/
    private WorkThread[] workThreads;
    private final int work_number;

    public MyThreadPool2(){
        this(100,WORK_COUNT);
    }

    /*任务数，线程的数量*/
    public MyThreadPool2(int task_count,
                         int work_number) {
        if (work_number<=0) work_number = WORK_COUNT;
        if(task_count<=0) task_count = 100;
        this.taskQueue = new ArrayBlockingQueue<>(task_count);
        this.work_number = work_number;
        workThreads = new WorkThread[work_number];
        /*工作线程准备好了*/
        for(int i=0;i<work_number;i++){
            workThreads[i] = new WorkThread();
            workThreads[i].start();
        }
    }

    /*销毁线程池*/
    public void destroy(){
        System.out.println("ready close pool....");
        for(int i=0;i<work_number;i++){
            workThreads[i].stopWorker();
            workThreads[i] = null;//help gc
        }
        taskQueue.clear();
    }

    /*放入任务，但是只是加入队列*/
    public void execute(Runnable task){
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "WorkThread number:"+work_number
                +" wait task number:"+taskQueue.size();
    }

    /*内部类，工作线程的实现*/
    private class WorkThread extends Thread{
        @Override
        public void run() {
            Runnable r = null;
            try {
                while(!isInterrupted()){
                    r = taskQueue.take();
                    if(r!=null){
                        System.out.println(getId()+" ready execute"
                                +((TestMyThreadPool.MyTask)r).getName());
                        r.run();
                    }
                   r = null;
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }

        }

        /*停止工作*/
        public void stopWorker() {
            interrupt();
        }
    }


}
