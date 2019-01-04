package cn.wx.multithread;

//安全的线程（没有线程同步）
//添加同步块(synchronized)
public class T03_SafeExecute_1 {

	public static void main(String[] args) {
		int st='A';
		
		//10个线程执行同一个任务
		MyRun myRun=new MyRun();
		
		for(int i=0;i<10;i++) {
			int ne=st+i;
			Character c=(char) (ne);
			new Thread(myRun,c.toString()).start();
		}
	}
	//每次执行对count减1后，输出count
	private static final  class MyRun implements Runnable{

		int count=10;
		@Override
		synchronized public void run() {
			count--;
			System.out.println("I am "+Thread.currentThread().getName()+" , count is "+count);
		}
		
	}
}

