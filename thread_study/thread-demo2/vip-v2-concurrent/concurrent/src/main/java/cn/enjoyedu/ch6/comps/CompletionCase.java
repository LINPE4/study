package cn.enjoyedu.ch6.comps;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *类说明：
 */
public class CompletionCase {
    private final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private final int TOTAL_TASK = Runtime.getRuntime().availableProcessors()*10;

    // 方法一，自己写集合来实现获取线程池中任务的返回结果
    public void testByQueue() throws Exception {
    	long start = System.currentTimeMillis();
    	AtomicInteger count = new AtomicInteger(0);
        // 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        //队列,拿任务的执行结果
        BlockingQueue<Future<Integer>> queue = 
        		new LinkedBlockingQueue<>();

        // 向里面扔任务
        for (int i = 0; i < TOTAL_TASK; i++) {
            Future<Integer> future = pool.submit(new WorkTask("ExecTask" + i));
            queue.add(future);
        }

        // 检查线程池任务执行结果
        for (int i = 0; i < TOTAL_TASK; i++) {
        	int sleptTime = queue.take().get();
        	//System.out.println(" slept "+sleptTime+" ms ...");        	
        	count.addAndGet(sleptTime);
        }

        // 关闭线程池
        pool.shutdown();
        System.out.println("-------------tasks sleep time "+count.get()
        		+"ms,and spend time "
        		+(System.currentTimeMillis()-start)+" ms");
    }

    public void testByCompletion() throws Exception{
        long start = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);
        // 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        CompletionService<Integer> cSevice
                = new ExecutorCompletionService<>(pool);

        // 向里面扔任务
        for (int i = 0; i < TOTAL_TASK; i++) {
            cSevice.submit(new WorkTask("ExecTask" + i));
        }

        // 检查线程池任务执行结果
        for (int i = 0; i < TOTAL_TASK; i++) {
            int sleptTime = cSevice.take().get();
            //System.out.println(" slept "+sleptTime+" ms ...");
            count.addAndGet(sleptTime);
        }

        // 关闭线程池
        pool.shutdown();
        System.out.println("-------------tasks sleep time "+count.get()
                +"ms,and spend time "
                +(System.currentTimeMillis()-start)+" ms");

    }

    public static void main(String[] args) throws Exception {
        CompletionCase t = new CompletionCase();
        t.testByQueue();
        t.testByCompletion();
    }
}
