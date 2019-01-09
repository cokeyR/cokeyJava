package cn.wx.multithread;

//notify(),随机唤醒一个线程
//看运行结果，会发现，ThreadA、ThreadC和ThreadD中有两个永远不会再被唤醒，程序也一致无法正常结束
public class T21_Wait_Notify {

	public static void main(String[] args) throws InterruptedException {
		//message初始值为0
		//A线程 获取message的对象锁,将message的值加到1000，然后调用message.wait()释放message的对象锁并阻塞等待
		Message message=new Message();
		new Thread(new TaskA(message),"ThreadA").start();
		new Thread(new TaskA(message),"ThreadC").start();
		new Thread(new TaskA(message),"ThreadD").start();
		//睡眠4000ms以让线程A先获得message的对象锁
		Thread.sleep(4000);
		//B线程获取message的对象锁，给message+1000，然后调用wait.notigy(),以准备释放对象锁并通知正在阻塞等待message对象锁的线程。
		new Thread(new TaskB(message),"ThreadB").start();
		
		
	}
	
	
	private static final class Message{
		private int count=0;
		
		public void add(int num) {
			for(int i=0;i<num;i++)
				count++;
		}

		public int getCount() {
			return count;
		}
	}
	
	private static final class TaskA implements Runnable{
		private Message message;

		public TaskA(Message message) {
			super();
			this.message = message;
		}

		@Override
		public void run() {
			System.out.println("进入"+Thread.currentThread().getName()+".run()"+"，准备进入同步块");
			synchronized (message) {
				System.out.println(Thread.currentThread().getName()+" 获取了message的对象锁，并给message+1000");
				message.add(1000);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" 释放message对象锁，并开始等待通知，此时message=="+message.getCount());
				try {
					message.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" 接到通知，此时message=="+message.getCount());
				System.out.println(Thread.currentThread().getName()+" 退出同步块");		
			}
			System.out.println("退出"+Thread.currentThread().getName()+".run()");
		}
	}
	private static final class TaskB implements Runnable{
		private Message message;

		public TaskB(Message message) {
			super();
			this.message = message;
		}

		@Override
		public void run() {
			System.out.println("进入"+Thread.currentThread().getName()+".run()，准备进入同步块");
			synchronized (message) {
				System.out.println(Thread.currentThread().getName()+" 获取了message的对象锁进入同步块，并给message+1000");
				message.add(1000);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" 发出message的通知");
				message.notify();
				System.out.println("退出"+Thread.currentThread().getName()+"同步块");
			}
			System.out.println("退出"+Thread.currentThread().getName()+".run()");
		}
	}
}
