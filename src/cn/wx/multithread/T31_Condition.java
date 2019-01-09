package cn.wx.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//condition 由 lock 创建
//condition.await()之前必须先lock.lock();
//condition.signal()会唤醒condition.await();
//await()会立即释放lock的锁，继续执行需要先获得锁
//signal()会随机唤醒一个同condition的wait()。signalAll()会唤醒同condition的所有wait()。
//signal()不会立即释放锁，而是等运行lock.unlock()的时候才释放锁。

//一个lock可以有多个condition。每个condition只通知自己，不会通知同锁的其他condition。以此可以实现通知部分线程（只有相同condition的线程）

//以上线程类比object.wait()、object.notify()、object.notifyAll()。
//lock功能更强。object只能随机通知一个或全部通知。lock可以通过condition通知制定的一个或多个线程


//公平锁：按加锁的顺序依次获得锁（FIFO）
//非公平锁：获得锁的线程是随机挑选了。这可能导致有些线程一致等不到锁。
//ReenterantLock有一个带boolean值的构造函数,ReentrantLock(boolean isFair)可以指定创建公平锁和非公平锁。

//lock.getHoldLock()返回当前线程加锁次数，每次重入都会加一次，每次释放都会减一次
//lock.getQueueLength()
//lock.getWaitQueueLength(Condition c)
//......
//......
//lock还有许多方法，但是有很多并不保证完全准确。这些方法只用用来监视系统状态，而不能用来同步.

public class T31_Condition {

	public static void main(String[] args) throws InterruptedException {
		Lock lock=new ReentrantLock();
		Condition condition=lock.newCondition();
		MyTaskAwait taskwait=new MyTaskAwait(lock,condition);
		MyTaskAwait taskAwait1=new MyTaskAwait(lock, condition);
		MyTaskSignal taskSignal=new MyTaskSignal(lock,condition);
		new Thread(taskwait,"等待1").start();
		new Thread(taskAwait1,"等待2").start();
		Thread.sleep(100);
		new Thread(taskSignal,"通知1").start();
		
	}
	private static class MyTaskAwait implements Runnable{
		private Lock lock;
		private Condition condition;
		public MyTaskAwait(Lock lock, Condition condition) {
			super();
			this.lock = lock;
			this.condition = condition;
		}
		@Override
		public void run() {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName()+"获得了锁，然后condition阻塞");			
				condition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				lock.unlock();
				System.out.println(Thread.currentThread().getName()+"接到了condition通知，然后释放了锁");			
			}
		}
	}
	
	
	private static class MyTaskSignal implements Runnable{
		private Lock lock;
		private Condition condition;
		public MyTaskSignal(Lock lock, Condition condition) {
			super();
			this.lock = lock;
			this.condition = condition;
		}
		@Override
		public void run() {
			lock.lock();
					
			try {
				System.out.println(Thread.currentThread().getName()+"获得了锁，然后condition通知");
				//condition.signal();
				condition.signalAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				lock.unlock();
				System.out.println(Thread.currentThread().getName()+"释放了锁");
			}
		}
	}
}
