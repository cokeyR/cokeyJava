package cn.wx.base;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//md5 信息摘要
public class L_MD5 {
	public static String getMD5(String str) {
		try {
//			1 获取md5
//			MessageDigest md5=MessageDigest.getInstance("MD5");
//			2 加密
//			md5.update(str.getBytes());
//			3 获取加密后的信息
//			return md5.digest().toString();
			return MessageDigest.getInstance("MD5").digest(str.getBytes()).toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(L_MD5.getMD5("wangxiang"));
		System.out.println(L_MD5.getMD5("wangxisdflasjkhdflaasdfang"));
	}
}
