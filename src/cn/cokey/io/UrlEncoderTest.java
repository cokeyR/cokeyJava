package cn.cokey.io;

import java.io.UnsupportedEncodingException;

public class UrlEncoderTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String info="王";
//		String encode = URLEncoder.encode(info, "GBK");
		byte[] bytes = info.getBytes("GBK");
		String byteToHex = byteToHex(bytes[0]);
		String byteToHex2 = byteToHex(bytes[1]);
		System.out.println(byteToHex);
		System.out.println(byteToHex2);
	}
	

/** 
 * 字节转十六进制 
 * @param b 需要进行转换的byte字节 
 * @return  转换后的Hex字符串 
 */  
public static String byteToHex(byte b){  
    String hex = Integer.toHexString(b & 0xFF);  
    if(hex.length() < 2){  
        hex = "0" + hex;  
    }  
    return hex;  
	}	
}
