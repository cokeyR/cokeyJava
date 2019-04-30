package cn.wx.thread;

import java.util.Random;

public class LockT {

	public static void main(String[] args) {
		T t = new T();

		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (t) {
					while (true) {
						for (int i = 0; i < 10000; i++)
							new Random();
						t.setI(t.getI() + 1);
					}
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (t) {
					while (true) {
						for (int i = 0; i < 10000; i++)
							new Random();
						System.out.println(t.getI());
					}
				}
			}
		}).start();
	}

	private static class T {
		int i = 0;

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}
	}
}
