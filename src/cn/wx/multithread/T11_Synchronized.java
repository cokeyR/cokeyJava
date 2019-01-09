package cn.wx.multithread;

import java.util.Random;

//a.实例变量是线程不安全的 （JVM实例都在堆中，所有线程都可以访问）
//b.类的synchronized方法对于同一个对象是不可重入的（在不同的线程中调用同一个对象的同一个同步方法，依次排队进入执行，synchronized获取对象的锁，锁住了对象）
//c.synchronized块对于多个对象，则每个对象都可以进入，不同的对象各自执行（可以通过不同的对象重复进入同一同步方法）
// ， 即，同步块会获取对象锁，当一个实例的锁被一个同步块获取后，该对象无法再次进入同步块。但这不影响其他实例进入该同步块
//d.锁重入：一个同步块获得了一个对象锁后，该对象仍然可以再次进入该同步块（递归调用）
//e.锁释放：线程如果抛出异常，线程终止，并且会释放其获得的对象锁。
//总结:同步块执行的条件是获得对象锁,一个对象锁同时只能被一个同步块获得(可以被获得多次即重入),一个同步块可以获得不同对象的锁


//同步方法有弊端:如果同步方法执行时间很长,那么其他线程就不等不等待很长时间,直到当前对象锁被释放,才能使用当前对象进入该同步方法
//只在必要的地方使用同步块,可以节约时间
//a.同步块可以获取任何传入对象的锁 synchronized(this) 或者 synchronized(anyObject)，同步块会取得this或anyObject的对象锁
//应用：一个对象作为参数传入不同线程，以控制多线程依次执行，参见T12

//静态方法中的同步块，参见T3。静态方法的同步块会获取类锁。一旦类被锁，那么该类的所有对象都不能
public class T11_Synchronized {

	public static void main(String[] args) throws InterruptedException {
		
//******************线程不同步演示****************************
		UnThradSafeTask task=new UnThradSafeTask(0, 20000);
		//启动20个线程，每个线程对aTask中的实例变量自加20000次
		for(int i=0;i<20;i++)
			new Thread(task).start();
		while(Thread.activeCount()>1)
			continue;		
		System.out.println("未同步： "+task.getAcc());	
		System.out.println();
//******************线程不同步演示****************************
		
//******************线程同步演示-同步块****************************
		ThradSafeTask task2=new ThradSafeTask(0, 20000);
		for(int i=0;i<20;i++)
			new Thread(task2).start();
		while(Thread.activeCount()>1)
			continue;		
		System.out.println("添加同步块后： "+task2.getAcc());
		System.out.println();
//******************线程同步演示-同步块****************************
		
//******************线程同步演示-不同的对象可各自进入同步块****************
		ThradSafeTask task3=new ThradSafeTask(0, 20000);
		ThradSafeTask task4=new ThradSafeTask(0, 20000);
		
		for(int i=0;i<20;i++) {
			new Thread(task3).start();
			new Thread(task4).start();
		}
		Thread.sleep(10);
		System.out.println("启动两个线程，控制两个实例进入同步块，睡眠10ms后");
		System.out.println("task3: "+task3.getAcc());
		System.out.println("task4: "+task4.getAcc());
		System.out.println("上述结果表面，两个实例，同时进入了同步块，即多个对象多个锁");
		System.out.println();
//******************线程同步演示-不同的对象可各自进入同步块****************
		
//****************同一个对象进入不同的同步块也是同步的 一旦对象锁被获得 对象就不能再进入任何同步块*********
		
		TwoSyn twoSyn=new TwoSyn();
		for(int i=0;i<10;i++) {
			new Thread(new MyRun1(twoSyn, 20000)).start();
			new Thread(new MyRun2(twoSyn, 20000)).start();
		}
		while(Thread.activeCount()>1)
			;
		System.out.println("两个同步块也是同步的");
		System.out.println(twoSyn.num);
//****************同一个对象进入不同的同步块也是同步的 一旦对象锁被获得 对象就不能再进入任何同步块*********

	}

	// 没有同步控制
	private static final class UnThradSafeTask implements Runnable {
		private int acc = 0;
		private int dis;

		public UnThradSafeTask(int acc, int dis) {
			super();
			this.acc = acc;
			this.dis = dis;
		}

		@Override
		public void run() {
			for (int i = 0; i < dis; i++)
				acc++;
		}

		public int getAcc() {
			return acc;
		}
	}

	// 在修改实例变量值的方法上添加同步块控制
	private static final class ThradSafeTask implements Runnable {
		private int acc = 0;
		private int dis;

		public ThradSafeTask(int acc, int dis) {
			super();
			this.acc = acc;
			this.dis = dis;
		}

		@Override
		synchronized public void run() {
			for (int i = 0; i < dis; i++) {
				acc++;
				// 做一个费时的事情
				for (int f = 0; f < 10; f++) {
					Random random = new Random();
					random.nextInt(2000);
				}
			}
		}

		public int getAcc() {
			return acc;
		}
	}

	private static final class TwoSyn {
		int num = 0;

		synchronized public void addo(long l) {
			for (int i = 0; i < l; i++) {
				num++;
				// 做一个费时的事情
				for (int f = 0; f < 10; f++) {
					Random random = new Random();
					random.nextInt(2000);
				}
			}
		}

		synchronized public void addt(long l) {
			for (int i = 0; i < l; i++) {
				num++;
				for (int f = 0; f < 10; f++) {
					Random random = new Random();
					random.nextInt(2000);
				}
			}
		}
	}

	private static final class MyRun1 implements Runnable {

		TwoSyn two;
		int num;

		public MyRun1(TwoSyn two,int num) {
			super();
			this.two = two;
			this.num=num;
		}

		@Override
		public void run() {
			two.addo(num);
		}
	}
	private static final class MyRun2 implements Runnable {

		TwoSyn two;
		int num;

		public MyRun2(TwoSyn two,int num) {
			super();
			this.two = two;
			this.num=num;
		}

		@Override
		public void run() {
			two.addt(num);
		}
	}
}
