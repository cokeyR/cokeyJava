package cn.wx.multithread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock 一旦被锁，那么如果不解锁，那么其他线程的lock都将阻塞（可重入锁在同线程是可重入）
 * @author wangxiang2
 *
 */
public class T30_Lock {
	
	public static void main(String[] args) {
		Lock lock=new ReentrantLock();
		MyTask task1=new MyTask(lock);
		MyTask task2=new MyTask(lock);
		
		new Thread(task1).start();
		new Thread(task2).start();
	}

	private static final class MyTask implements Runnable{

		int k=1;
		Lock lock;
		public MyTask(Lock lock) {
			super();
			this.lock = lock;
		}
		@Override
		public void run() {
			lock.lock();
			System.out.println(Thread.currentThread().getName()+":进入\t"+System.currentTimeMillis());
			if(k==1) {
				k++;
				run();//可重入
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock.unlock();
		}
		
	}
}
