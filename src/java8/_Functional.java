package java8;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法引用函数作为参数进行传递
 * @author WX
 *
 */
class User{
		private int age;

		public User(int age) {
			super();
			this.age = age;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "user [age=" + age + "]";
		}
}
public class _Functional {
	
	public static void main(String[] args) {
		
		List<User> uList=new ArrayList<>();
		//生成list
		for(int i=0;i<4;i++) {
			User user=new User(i);
			uList.add(user);
		}
		//函数引用操作
		System.out.println("输出user");
		uList.forEach(System.out::println);
		System.out.println("年龄置0");
		uList.forEach(user->user.setAge(0));
		System.out.println("输出user");
		uList.forEach(System.out::println);
	}
}
