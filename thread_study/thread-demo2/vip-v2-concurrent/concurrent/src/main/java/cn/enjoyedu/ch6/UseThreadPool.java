package cn.enjoyedu.ch6;

import cn.enjoyedu.tools.SleepTools;

import java.util.Random;
import java.util.concurrent.*;

/**
 *类说明：线程池的使用范例
 */
public class UseThreadPool {
    /*没有返回值*/
    static class Worker implements Runnable
    {
        private String taskName;
        private Random r = new Random();

        public Worker(String taskName){
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }

        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName()
            		+" process the task : " + taskName);
            SleepTools.ms(r.nextInt(100)*5);
        }
    }

    /*有返回值*/
    static class CallWorker implements Callable<String>{
    	
        private String taskName;
        private Random r = new Random();

        public CallWorker(String taskName){
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }    	

		@Override
		public String call() throws Exception {
            System.out.println(Thread.currentThread().getName()
            		+" process the task : " + taskName);
            return Thread.currentThread().getName()+":"+r.nextInt(100)*5;
		}
    	
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        Runtime.getRuntime().availableProcessors();//逻辑核心
//        ExecutorService threadPool = new ThreadPoolExecutor(2,
//                4,3,TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(10),
//                new ThreadPoolExecutor.DiscardOldestPolicy());
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
        ExecutorService threadPool2 = Executors.newCachedThreadPool();
        ExecutorService threadPool3 = Executors.newWorkStealingPool();
//        ExecutorService threadPool4 = Executors.newScheduledThreadPool();
//        ExecutorService threadPool5 = Executors.newSingleThreadScheduledExecutor()


        for (int i = 0; i <= 6; i++)
        {
            Worker worker = new Worker("worker " + i);
            System.out.println("A new task has been added : " + worker.getName());
            threadPool.execute(worker);
        }
        
        for (int i = 0; i <= 6; i++)
        {
        	CallWorker callWorker = new CallWorker("worker " + i);
            System.out.println("A new task has been added : " + callWorker.getName());
            Future<String> result = threadPool.submit(callWorker);
            System.out.println(result.get());
        }        
        threadPool.shutdown();
        threadPool.shutdownNow();
    }
}
