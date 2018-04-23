package cn.wx.base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cn.wx.info.User;


public class L_Serialializable {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		final String filePath="User";
		
		User user=new User("A", 23);
		ObjectOutputStream oos=null;
		oos=new ObjectOutputStream(new FileOutputStream(filePath));
		oos.writeObject(user);
		oos.close();
		
		ObjectInputStream ois=null;
		ois=new ObjectInputStream(new FileInputStream(filePath));
		user=null;
		user=(User)ois.readObject();
		System.out.println("user :"+user);
		ois.close();
	}
}
