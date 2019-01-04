package cn.wx.multithread;

public class T05_Interrupt {

	// *********************************************************************************************//
	// 1.调用一个线程的interrupt()只是给线程添加中断标记，至于线程是否中断最终由线程自己控制， //
	// 但会打断线程的某些阻塞（查API），并使被标记线程抛出打断异常，此时中断标记会被清楚 //
	// 2.线程可以调用自己的isInterrupted()方法获取自身是否需要被中断，并且会清除中断标志，并自行决定是否中断 //
	// 3.线程也可以调用其他线程的isInterrupted()方法可查看其中断标志，但不会清除其中断标志 //
	// 4.线程的interrupted()是一个静态方法，可以查看当前线程的中断标志，但不会清除中断标志 //
	// *********************************************************************************************//
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new MyRun());
		thread.start();

		System.out.println("让任务线程运行500ms,然后调用其中断，添加中断标志");
		Thread.sleep(500);
		System.out.println("调用打断前：interrupted==" + thread.isInterrupted());
		thread.interrupt();
		System.out.println("调用打断后：interrupted==" + thread.isInterrupted());
		while (thread.isInterrupted())
			continue;
		System.out.println("任务线程调用isInterrupted()后，中断标志被清除：interrupted==" + thread.isInterrupted());

	}

	private static final class MyRun implements Runnable {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " begin.....");
			for (long i = 0; i < 2000000000; i++) {
				if (Thread.currentThread().isInterrupted()) {
					System.out.println(Thread.currentThread().getName()+" 检测到中断标志，退出");
					return;
				}
				if (i % 100000000 == 0)
					System.out.println(i);
			}
			System.out.println(Thread.currentThread().getName() + " end.....");
		}
	}
}
