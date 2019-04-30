package cn.wx.threadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool_l {
	public static void main(String[] args) {
		ThreadPoolExecutor executor=new ThreadPoolExecutor(10, 10, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
		for(int i=0;i<10;i++) {
			Runnable runnable=new Task(i);
			executor.execute(runnable);
		}
	}
	
	private static class Task implements Runnable{

		private int j;
		public Task(int i) {
			super();
			this.j = i;
		}
		@Override
		public void run() {
			for(int i=0;i<100;i++) {
				System.out.println(j);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
}
