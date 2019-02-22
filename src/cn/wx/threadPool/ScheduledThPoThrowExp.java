package cn.wx.threadPool;

/**
 * 周期任务如果产生了没有处理的异常，那么周期任务将结束
 */
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThPoThrowExp {

	private static int count=0;
	public static void main(String[] args) {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		executor.scheduleAtFixedRate(new Task(), 0, 1, TimeUnit.SECONDS);
	}
	
	private static class Task implements Runnable{

		@Override
		public void run() {
			count++;
			double result=0;
//			try {
			if(count==2)
				result=3/0;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
			System.out.println("count="+count+"\t,result="+result);
		}
		
	}
}


