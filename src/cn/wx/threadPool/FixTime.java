package cn.wx.threadPool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FixTime {

	public static void main(String[] args) {
		new Task().run();
	}

	private static class Task implements Runnable {

		@Override
		public void run() {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(format.format(new Date()));
		}
	}
}
