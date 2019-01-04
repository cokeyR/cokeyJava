package cn.wx.composition;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class ErrorShow {
	public static void main(String[] args) {
		MyHashSet<String> set=new MyHashSet<>(); 
		set.addAll(Arrays.asList("a","b","c"));
		System.out.println(set.getAmount());
	}
}

class MyHashSet<E> extends HashSet<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6360274513905180194L;
	
	private int account = 0;

	public boolean add(E e) {
		account++;
		return super.add(e);
	}

	public boolean addAll(Collection<? extends E> c) {
		account += c.size();
		return super.addAll(c);
	}

	public int getAmount() {
		return account;
	}
}
