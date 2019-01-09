package cn.wx.multithread;


//a.同步的静态方法会获取Class锁，这意味着，其他线程不可以再获取该对象的Class锁，也就不能进入其Class的静态同步方法。
//b.Class锁和对象锁互不相关，Class被锁，Class对象的锁不受影响，对象锁依旧可以被其他线程获取，并进入改对象的非静态的同步方法
//c.像instance.synstaticMethod()这样，虽然使用实例调用类静态同步方法，但获取的依旧是Class锁。只是调用方法不同


//synchronized(Class)和静态同步方法一样，获取的都是Class锁
public class T13_Synchronized{

	public static void main(String[] args) {
		
		new Thread(new MyRunA()).start();
		new Thread(new MyRunB()).start();
		new Thread(new MyRunC()).start();
		new Thread(new MyRun(new StaticMethod())).start();
	}
	
	
	private static final class StaticMethod{
		
		synchronized public void instanceMethod() {
			System.out.println(Thread.currentThread().getName()+"进入StaticMethod的instanceMethod");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"离开StaticMethod的instanceMethod");
		}
		synchronized public static void printA() {
			System.out.println(Thread.currentThread().getName()+"进入StaticMethod的PrintA()");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"离开StaticMethod的PrintA()");
		}
		synchronized public static void printB(){
			System.out.println(Thread.currentThread().getName()+"进入StaticMethod的PrintB()");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"离开StaticMethod的PrintB()");
		}
		synchronized public static void printC(){
			System.out.println(Thread.currentThread().getName()+"进入StaticMethod的PrintC()");
			try { 
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"离开StaticMethod的PrintC()");
		}
	}
	
	private static final class MyRunA implements Runnable{
		@Override
		public void run() {
			StaticMethod.printA();
		}
	}
	private static final class MyRunB implements Runnable{
		@Override
		public void run() {
			StaticMethod.printB();
		}
	}
	private static final class MyRunC implements Runnable{
		@Override
		public void run() {
			StaticMethod.printC();
		}
	}
	
	private static final class MyRun implements Runnable{

		StaticMethod sm;
		public MyRun(StaticMethod sm) {
			super();
			this.sm = sm;
		}
		@Override
		public void run() {
			sm.instanceMethod();
		}
		
	}
}