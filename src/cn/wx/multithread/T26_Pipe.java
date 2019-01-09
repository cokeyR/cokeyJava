package cn.wx.multithread;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

//input 和 output 通过connect产生连接，input.read()会阻塞当前线程，知道output数据写入完成（调用output.close())，如果不关闭输出管道，则输入管道会抛出异常
public class T26_Pipe {

	public static void main(String[] args) throws IOException, InterruptedException {
		PipedInputStream inputStream=new PipedInputStream();
		PipedOutputStream outputStream=new PipedOutputStream();
		outputStream.connect(inputStream);
		
		new Thread(new Reader(inputStream)).start();
		new Thread(new Writer(outputStream)).start();
		
	}
	
	private final static class Writer implements Runnable{
		PipedOutputStream out=null;
		
		public Writer(PipedOutputStream out) {
			super();
			this.out = out;
		}
		@Override
		public void run() {
			System.out.println("进入Writer....");
			try {
				Thread.sleep(1000);
				out.write("hello,world!".getBytes());
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private final static class Reader implements Runnable{
		PipedInputStream in=null;
		
		
		public Reader(PipedInputStream in) {
			super();
			this.in = in;
		}
		@Override
		public void run() {
			System.out.println("进入Reader.....,立即开始读。");
			try {
				byte[] bytes=in.readAllBytes();
				System.out.println(new String(bytes));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
