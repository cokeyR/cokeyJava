package cn.cokey.lambda;

public class Demo1 {

	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("不适用lambda，要写很多行代码，实现一个线程");
			}
		}).start();
		
	
		new Thread(() -> System.out.println("java8引入了lambda表达式，只需要一行就可以实现一个线程") ).start();
	}
}
