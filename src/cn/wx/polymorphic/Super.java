package cn.wx.polymorphic;

import java.io.File;

public class Super extends Basic{
	
	
	
	public void methodOne(String s) {
		System.out.println("In String");
		System.out.println(s);
	}
	
	//�����غ������������Ͳ�ͬ,����������������͸����ϵʱ,�������������ѡ����õĺ���
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
