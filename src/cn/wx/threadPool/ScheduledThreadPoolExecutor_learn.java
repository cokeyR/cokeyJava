package cn.wx.threadPool;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * getActiveCount()返回执行该语句时，正在执行的线程的数量。对于定时任务。如果在正在执行，即使Active的
 * 如果处在执行的间隙，就不是Active的。
 * @author wangxiang2
 *
 */
public class ScheduledThreadPoolExecutor_learn {
	
	public static void main(String[] args) throws InterruptedException {
		ScheduledThreadPoolExecutor executor=new ScheduledThreadPoolExecutor(3);
		
		executor.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.err.println("我要执行了");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.err.println("我执行完了");
				}
		},0L, 2, TimeUnit.SECONDS);
		
		Thread.sleep(1000);
		while(true) {
			Thread.sleep(400);
				System.out.println("*****activeCount="+executor.getActiveCount()+",taskCount="+executor.getTaskCount()+"*******");
		}
	}
	

}
