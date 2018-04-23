package cn.wx.base;

import java.util.HashSet;

import cn.wx.info.User;
/**
 * set���ϲ��ܼ����ظ���Ԫ��
 * 
 * ֻ��hashCode��ͬ����equal()������ʱ���ж�λ�ظ���hashcode(),equel()��д�����Զ����ж�����
 * 
 * */
public class L_Set {
	public static void main(String[] args) {
		User user1=new User("A", 1);
		User user2=new User("B", 2);
		User user3=new User("A", 1);
		
		HashSet<User> users=new HashSet<User>();
		users.add(user1);
		System.out.println("add user1:"+user1.hashCode());
		users.add(user2);
		System.out.println("add user2:"+user2.hashCode());
		users.add(user3);
		System.out.println("add user3:"+user3.hashCode());
		for(User e:users)System.out.println(e);
		System.out.println(user1.equals(user3));
	}
}
