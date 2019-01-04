package cn.wx.multithread;

//**********************************************************************
// a.线程优先级越高，获得的CPU时间片越多
// b.线程优先级具有继承性：线程A创建线程B，则默认线程B的优先级和线程A相同
//**********************************************************************
// c.yield()方法会申请让出自己的时间片，但是否让出，何时让出，何时重新获得时间片都是随机的
//**********************************************************************
public class T07_Priority {


	public static void main(String[] args) {
		Thread t1=new Thread(new MyTask(),"aaa");
		t1.setPriority(2);

		Thread t2=new Thread(new MyTask(),"bbb");
		t2.setPriority(8);
		
		t1.start();
		t2.start();
	}
	
	private static final class MyTask implements Runnable{
		long num=0;
		@Override
		public void run() {
			long begintime=System.currentTimeMillis();
			while(num<10000000) {
				num++;
			}		
			long endtime=System.currentTimeMillis();
			System.out.println(Thread.currentThread().getName()+"  用时 "+(endtime-begintime));
		}
	}
}
