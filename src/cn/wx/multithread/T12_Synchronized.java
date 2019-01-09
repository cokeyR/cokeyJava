package cn.wx.multithread;


//用一个对象的对象锁控制多个线程的同步执行
//详细内容查看T11
public class T12_Synchronized{
	
	
	public static void main(String[] args) {
		Object object=new Object();
		
		for(int i=0;i<10;i++) {
			new Thread(new SerialTask(object)).start();
		}
	}
	
	private static final class SerialTask implements Runnable{

		Object object;
		public SerialTask(Object object) {
			super();
			this.object = object;
		}
		@Override
		public void run(){
			if(object==null)
				return;
			synchronized(object) {
				long begin=System.currentTimeMillis();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long end=System.currentTimeMillis();
				System.out.println(Thread.currentThread().getName()+"开始于："+begin%100000);
				System.out.println(Thread.currentThread().getName()+"结束于："+end%100000);
			
			}
		}
		
		
	}
}