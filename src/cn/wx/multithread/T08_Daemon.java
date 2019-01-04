package cn.wx.multithread;

//a.守护线程通过属性设置
//b.守护线程服务于其他线程
//c.如果没有用户线程了，守护线程自动终结
//d.GC线程是一个守护线程
public class T08_Daemon {

	public static void main(String[] args) {
		Thread task=new Thread(new MyTask());
		Thread daemon=new Thread(new MyDaemon());
		daemon.setDaemon(true);
		
		task.start();
		daemon.start();
		
	}
	private static final class MyTask implements Runnable {

		long num = 0;

		@Override
		public void run() {
			System.out.println("task 开始");
			while (num < 1000000000) {
				num++;
			}
			System.out.println("task 结束");
		}
	}

	private static final class MyDaemon implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("我是守护线程，我还活着。。。。。。。。。");
			}
		}

	}
}
