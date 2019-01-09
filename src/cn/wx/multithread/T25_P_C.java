package cn.wx.multithread;

import java.util.LinkedList;
import java.util.Random;

//一生产者，多消费者应注意事项
//消费中，将唤醒放到while循环，确保被唤醒时有数据可以消费，否则当被同类唤醒时，若数据已经被消费，则会抛异常(见line 58)
public class T25_P_C {	
	public static void main(String[] args) {

		LinkedList<Integer>integers =new LinkedList<>();
		
		new Thread(new Producer(integers)).start();
		new Thread(new Customer(integers)).start();
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
						Thread.sleep(500);
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
					if(integers.size()<=0)//if 判断只判断一次，当下次被唤醒时不会再做判断。如果是1-1模型，不会有问题，因为总是生产者唤醒消费者，但是1-N模型则不然，被唤醒时数据可以已经被其他消费者消费，因此，必须在每次被唤醒时都判断
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
