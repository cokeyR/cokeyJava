package cn.wx.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class FileLockTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		String filepath="D:/1.txt";
		new Thread(new FilePrinter(filepath)).start();
		Thread.sleep(200);
		new Thread(new T(filepath)).start();
	}
	
	
	private static final class FilePrinter implements Runnable{
		
		private String filePath;
		
		public FilePrinter(String filePath) {
			super();
			this.filePath = filePath;
		}
		@Override
		public void run() {
			RandomAccessFile lockfile=null;
			try {
				File file=new File(filePath);
				lockfile=new RandomAccessFile(file,"rws");
				FileChannel fileChannel=lockfile.getChannel();
				FileLock lock=fileChannel.tryLock();
				if(lock==null){
					System.out.println("δ�����");
				}
				else if(lock.isValid()) {
					System.out.println(Thread.currentThread().getName()+":���ļ��һ����");
					Thread.sleep(5000);
					System.out.println(Thread.currentThread().getName()+"�ͷ���");
					lock.release();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (OverlappingFileLockException e) {
				System.out.println(Thread.currentThread().getName()+":���Ի�ȡ��ʧ��");
			}finally {
				try {
					lockfile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	private static final class T implements Runnable{
	private String filePath;
		
		public T(String filePath) {
			super();
			this.filePath = filePath;
		
		}
		
		@Override
		public void run() {
			FileLock lock=null;
			while(lock==null) {
				try {
					RandomAccessFile lockfile=null;
					File file=new File(filePath);
					lockfile=new RandomAccessFile(file,"rws");
					FileChannel fileChannel=lockfile.getChannel();
					lock=fileChannel.tryLock();
					if(lock==null){
						Thread.sleep(1000);
					}
					else {
						System.out.println(Thread.currentThread().getName()+"�������");
						Thread.sleep(1000);
						File d=new File(filePath);
						lockfile.close();
						d.delete();
						lock.release();
						System.out.println(Thread.currentThread().getName()+"���ͷ���");
					}
					lockfile.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch(OverlappingFileLockException e) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"��δ���������Ϊ�ļ��Ѿ�������");
				}
			}
			
		}
		
		
	}
}
