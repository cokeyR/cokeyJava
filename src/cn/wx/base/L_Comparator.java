package cn.wx.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * ʵ��Comparator�ӿڵ�����һ���Ƚ���(�Ƚ������ṩ���������������)
 * 
 * �����ṩ��Collections.sort(**,**)��Array.sort(**,**)
 * 
 * �ȽϷ�ʽ��Comparable��ͬ
 * 
 * �Ƚ�����ʵ��Compartor�ӿ�) ���øı�Դ����Ϳ���ʵ������
 * */
 
class PersonComParator implements Comparator<Person_2>{
	public int compare(Person_2 o1, Person_2 o2) {
		// TODO Auto-generated method stub
		return o1.getAge()-o2.getAge();
	}
	
}
public class L_Comparator {
	public static void main(String[] args) {
		ArrayList<Person_2> persons=new ArrayList<Person_2>();
		persons.add(new Person_2("A", 10));
		persons.add(new Person_2("B", 20));
		persons.add(new Person_2("C", 15));
		for(Person_2 e:persons)System.out.println(e);
		System.out.println("after");
		Collections.sort(persons, new PersonComParator());
		for(Person_2 e:persons)System.out.println(e);
	}
}
class Person_2{
	String name;
	int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Persion_2 [name=" + name + ", age=" + age + "]";
	}
	public Person_2(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
}
