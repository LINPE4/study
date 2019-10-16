package cn.enjoyedu.ch4.future;

import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 *
 * FutureTask的get方法实现：
 * 1、允许多个线程get这个结果
 * 2、多个线程get这个结果时，可能任务还没运行完。
 * 3、任务运行完成后才能拿到结果，而且这个时候要让get结果的多个线程都可以拿到结果
 */
public class MyFutureTask<V> implements Runnable, Future<V> {

	private final Sync sync;

	public MyFutureTask(Callable<V> callable) {
		if (callable == null)
			throw new NullPointerException();
		sync = new Sync(callable);
	}

	private final class Sync extends AbstractQueuedSynchronizer {
		/** 表示任务正在执行 */
		private static final int RUNNING = 1;
		/** 表示任务已经运行完毕 */
		private static final int RAN = 2;
		/** 执行结果 */
		private V result;
		private Callable<V> callable;

		public Sync(Callable<V> callable) {
			super();
			this.callable = callable;
		}


		/*任务没完成，让get结果的线程全部进入同步队列
		 * acquireShared方法返回了，说明可以拿结果了，直接返回结果*/
		V innerGet() throws InterruptedException, ExecutionException {
			acquireShared(0);
			return result; // 成功执行完成，返回执行结果。
		}

		/*对任务的状态进行变化，设置执行结果，并唤醒所有等待结果的线程*/
		void innerSet(V v) {
			for (;;) {
				int s = getState(); // 获取任务执行状态。
				if (s == RAN)
					return; // 如果任务已经执行完毕，退出。
				// 尝试将任务状态设置为执行完成。
				if (compareAndSetState(s, RAN)) {
					result = v; // 设置执行结果。
					releaseShared(0); // 释放控制权。
					return;
				}
			}
		}
		
		protected boolean tryReleaseShared(int releases) {  
		    return true;
		}  

		/*任务没完成，返回-1，让get结果的线程全部进入同步队列
		* 返回1，可以让所有在同步队列上等待的线程一一去拿结果*/
		protected int tryAcquireShared(int acquires) {
            return this.getState()==RAN ? 1 : -1;
        }
		
		void innerRun() {
            if (this.compareAndSetState(0, RUNNING)) {
				  if (this.getState() == RUNNING) {//再检查一次，双重保障
					   try {
						   /*将call()方法的执行结果赋值给Sync中的result*/
						this.innerSet(this.callable.call());
					} catch (Exception e) {
						e.printStackTrace();
					}
				  } else {
					  /*如果不等于RUNNING，表示被取消或者是抛出了异常。这时候唤醒调用get的线程。*/
					   this.releaseShared(0);
				  }
            }
        }

	}

	@Override
	public void run() {
		 this.sync.innerRun();
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCancelled() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isDone() {
		throw new UnsupportedOperationException();
	}

	@Override
	public V get() throws InterruptedException, ExecutionException {
		return this.sync.innerGet();
	}



	@Override
	public V get(long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException,
			TimeoutException {
		throw new UnsupportedOperationException();
	}

}
