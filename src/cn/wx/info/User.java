package cn.wx.info;

import java.io.Serializable;


public class User implements Serializable,Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private int age;
	
	
	public User(String username, int i) {
		super();
		this.username = username;
		this.age = i;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int i) {
		this.age = i;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (age != other.age)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", age=" + age + "]";
	}
	@Override
	public Object clone() {
		User user=null;
		try {
			user=(User) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.username="clone";
		return user;
	}
	public static void main(String[] args) {
		User user=new User("A", 10);
		User user2=(User) user.clone();
		System.out.println(user);
		System.out.println(user2);
		user2.setAge(20);
		System.out.println(user);
		System.out.println(user2);
	}
}
