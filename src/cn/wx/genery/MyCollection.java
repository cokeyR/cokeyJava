package cn.wx.genery;

//a.没有被泛化的类，泛化后，客户端类依然可以用，只是客户端编程了窄类型

//public class MyCollection {
//	private Object[] objects;
//	private int index;
//	
//	public OldCollection() {
//		objects=new Object[10];
//		index=0;
//	}
//	public void push(Object o) {
//		 objects[index++]=o;
//	}
//	public Object pop() {
//		return objects[--index];
//	}
//}


public class MyCollection<T>{
	private T[] ts;
	private int index;
	
	@SuppressWarnings("unchecked")
	public MyCollection() {
		ts=(T[]) new Object[10];		
	}
	
	public void push(T t) {
		ts[index++]=t;
	}
	public T pop() {
		return ts[--index];
	}
}