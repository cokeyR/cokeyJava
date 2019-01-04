package cn.wx.multithread;

public class T01_ExecuteOrder {

	//调用顺序和执行顺序无关
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			Task task1 = new Task(i);
			new Thread(task1).start();
		}
	}

	private static class Task implements Runnable {
		int order;

		public Task(int order) {
			// TODO Auto-generated constructor stub
			this.order = order;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("I am " + order);
		}

	}
}
