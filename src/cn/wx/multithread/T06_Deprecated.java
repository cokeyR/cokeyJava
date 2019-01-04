package cn.wx.multithread;

public class T06_Deprecated {
	public static void main(String[] args) {

		//stop()
		//
		//stop()会强行中止线程，此时可能存在一些数据一致性问题
		//如：对一个对象的赋值只进行了一般就被中止了。此时该对象存在数据不一致问题
		
		//suspend()
		//resume()
		//
		//这两个方法可以实现线程暂停和重启，但是可能存在资源独占的问题。被挂起的线程不会释放其持有的资源
		//如：如果线程在一个同步中suspend(),那么这个同步块将被该线程一致占据，其他线程将不可进入
	}
}
