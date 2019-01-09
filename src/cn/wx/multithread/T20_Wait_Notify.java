package cn.wx.multithread;

//a.wait()是线程暂停，notify()使线程继续运行
//b.要调用对象的wait()和notify()，必须在调用的线程中获取其对象锁
//c.notify()不会立即释放对象锁，必须等到同步块执行完毕，对象锁才会被释放

//wait()方法，会释放当前的对象锁，并进入阻塞状态，等待其他唤醒。唤醒后重新持有对象锁并继续远行
//notify()方法，会在当前同步块运行结束后，释放对象锁，并随机唤醒一个等待当前对象锁的线程，使其进入就绪状态（参见T21)
//notifyall()方法，会在会在当前同步块运行结束后，释放对象锁，并唤醒所有等待当前对象锁的线程，使他们全部进入可运行状态，
//但只有一个可以等到运行，因为对象锁只有一个。哪个线程会被执行由具体的虚拟机实现。(参见T22)

//wait()发放会被interrupt()打断。并抛出InterruptedException异常

//wait()和notify()可以在一个同步块中出现，表示其他阻塞线程先运行，自己开始阻塞，并在一段时间以后接受通知继续运行，参见T23中的producer
public class T20_Wait_Notify {

	public static void main(String[] args) throws InterruptedException {
		//message初始值为0
		//A线程 获取message的对象锁,将message的值加到1000，然后调用message.wait()释放message的对象锁并阻塞等待
		Message message=new Message();
		new Thread(new TaskA(message),"ThreadA").start();
		//睡眠10ms以让线程A先获得message的对象锁
		Thread.sleep(10);
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
