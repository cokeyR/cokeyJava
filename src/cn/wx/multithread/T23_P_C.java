package cn.wx.multithread;

import java.util.LinkedList;
import java.util.Random;

//生产者-消费者 1对1 模型

//生产者和消费者同步运行--生产者生产一个后,阻塞自己并通知消费者消费.消费者消费一个后阻塞自己并通知生产者生产
//先调用notify(),在调用wait()，参见Producer。表示通知其他线程运行并阻塞自己。

public class T23_P_C {
	
	public static void main(String[] args) {
		LinkedList<Integer>integers =new LinkedList<>();
		new Thread(new Producer(integers)).start();
		new Thread(new Customer(integers)).start();
		
	}

	private static final class Producer implements Runnable {
		private LinkedList<Integer> integers;
		private Random random = new Random();

		public Producer(LinkedList<Integer> integers) {
			super();
			this.integers = integers;
		}

		@Override
		public void run() {
			synchronized (integers) {
				while (true) {
					try {
						while(integers.size()>0)
							integers.wait();
						integers.add(random.nextInt(100));
						System.out.println("生产了一个int--" + integers.getFirst());
						Thread.sleep(1000);
						integers.notify();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private static final class Customer implements Runnable {
		private LinkedList<Integer> integers;

		public Customer(LinkedList<Integer> integers) {
			super();
			this.integers = integers;
		}

		@Override
		public void run() {
			synchronized (integers) {
				while(true) {					
					if(integers.size()<=0)
						try {
							integers.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					System.out.println("消费了一个int--"+integers.pop());
					integers.notify();
				}
			}
		}
	}
}
