package cn.enjoyedu.framework;

import cn.enjoyedu.framework.vo.ItemVo;
import cn.enjoyedu.framework.vo.JobInfo;

import java.util.Map;
import java.util.concurrent.DelayQueue;

/**
 *类说明：任务完成后,在一定的时间供查询，
 * 之后为释放资源节约内存，需要定期处理过期的任务
 */
public class CheckJobProcesser {
    private static DelayQueue<ItemVo<String>> queue
    	= new DelayQueue<ItemVo<String>>();//存放任务的队列

	/*单例化*/
	private static class ProcesserHolder{
		public static CheckJobProcesser processer = new CheckJobProcesser();
	}

	public static CheckJobProcesser getInstance() {
		return ProcesserHolder.processer;
	}
    
    //处理队列中到期任务的线程
    private static class FetchJob implements Runnable{

    	private static DelayQueue<ItemVo<String>> queue 
    		= CheckJobProcesser.queue;
    	private static Map<String,JobInfo<?>> JobInfoMap = PendingJobPool.getMap();

		public void run() {
	        while(true){
	            try {
	            	ItemVo<String> item = queue.take();
	            	String jobName = (String)item.getData();
	                JobInfoMap.remove(jobName);//从缓存中移除过期的任务
	                System.out.println("Job：["+ jobName+"] is out of date,remove from JobList! ");
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		}
    }
    
    /*任务完成后，放入队列，经过expireTime时间后，从整个框架中移除*/
    public void putJob(String jobName,long expireTime) {
    	ItemVo<String> itemTb = new ItemVo<String>(expireTime,jobName);
        queue.offer(itemTb);    
        System.out.println("任务["+jobName+"]已被放入过期检查缓存，过期时长："
				+expireTime+"s");
    }

    static{
		Thread thread = new Thread(new FetchJob());
		thread.setDaemon(true);
		thread.start();
		System.out.println("开启任务过期检查守护线程...........");
	}

}
