package cn.wx.multithread;

public class T04_Thread {

	//继承Thread有许多弊端
	//继承Thread是创建了一个持有任务的线程，而不是创建了任务
	//this 和  currentThread() 的 混淆
	public static void main(String[] args) {
		MyThread thread=new MyThread();
//		thread.start();
		
		new Thread(thread,"aaa").start();
	}
	private static final class MyThread extends Thread{
		
		public void run() {
			System.out.println("i am myThread execute in "+currentThread().getName());
			System.out.println("i am myThread myname is "+this.getName());
			System.out.println("my id is " + this.getId());
			System.out.println("thread that i am executed in 's id is "+ currentThread().getId());
		}
	}
}
