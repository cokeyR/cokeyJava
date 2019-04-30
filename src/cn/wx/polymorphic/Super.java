package cn.wx.polymorphic;

import java.io.File;

public class Super extends Basic{
	
	
	
	public void methodOne(String s) {
		System.out.println("In String");
		System.out.println(s);
	}
	
	//当重载函数仅参数类型不同,但参数类型是子类和父类关系时,会根据引用类型选择调用的函数
	public static void main(String[] args) {
		Super su=new Super();
		File file=new File("D:/sp/test.txt");
		su.methodOne(file);
		String s="ss";
		Object o=s;
		su.methodOne(s);
		su.methodOne(o);
	}
}
