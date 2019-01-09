package cn.wx.multithread;

//锁对象的改变
//同步块根据引用取对象锁（一旦synchronized取得了或开始等待对象锁，那么引用的改变不会由影响，因为，同步块等的是对象，而非引用）。

//**区别锁对象和对象锁** 锁对象是对象，对象锁是锁
public class T14_Synchronized{

	public static void main(String[] args) throws InterruptedException {
		MyRun run=new MyRun("lock");
		new Thread(run,"a").start();
		Thread.sleep(10);//睡眠10ms以保证下一个线程开始前，上一个线程已经改变锁对象
		new Thread(run,"b").start();
		Thread.sleep(10);
		new Thread(run,"c").start();		
		Thread.sleep(10);
		
	}
	
	private static final class MyRun implements Runnable{

		String lock;
		
		public MyRun(String lock) {
			super();
			this.lock = lock;
		}

		@Override
		public void run() {
			synchronized (lock) {
				System.out.println(Thread.currentThread().getName()+" 在 "+System.currentTimeMillis()+"进入");
				lock=lock+"lock";
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" 在 "+System.currentTimeMillis()+"退出");
			}
		}
		
	}
}