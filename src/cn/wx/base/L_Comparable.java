package cn.wx.base;

import java.util.ArrayList;
import java.util.Collections;


class Person_1 implements Comparable<Person_1>{
	private String name;
	private int age;
	
	public Person_1(String name,int age) {
		this.name=name;
		this.age=age;
	}
	
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

	public int compareTo(Person_1 o) {
		// TODO Auto-generated method stub
		return this.age-o.getAge();
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}
public class L_Comparable{
	public static void main(String[] args) {
		ArrayList<Person_1> persons=new ArrayList<Person_1>();
		Person_1 p1=new Person_1("A", 10);
		Person_1 p2=new Person_1("B", 20);
		Person_1 p3=new Person_1("C", 15);
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		for(Person_1 e:persons)
			System.out.println(e);
		System.out.println("after sort");
		Collections.sort(persons);
		for(Person_1 e:persons)
			System.out.println(e);
	}
	
}
