package cn.wx.genery;


public class OneClient {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		MyCollection collection=new MyCollection();
		String s1="sss";
		String s2="bbb";
		collection.push(s1);
		collection.push(s2);
		
		String s3=(String) collection.pop();
		String s4=(String) collection.pop();
		
		System.out.println(s3);
		System.out.println(s4);
	}
}
