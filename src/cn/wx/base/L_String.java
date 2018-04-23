 package cn.wx.base;

/**
 * String subString(int fromIndex,int endIndex) 获得从fromIndex（含）到endIndex（不含）的子字符串
 * int indexOf(String s,int fromIndex) 返回从fromIndex（含）开始，s第一次出现的位置，没有返回-1
 * int indexOf(char c,int fromIndex) 同上
 * */
public class L_String {
	public static void main(String[] args) {
//		String string="192.168.1.1";
//		System.out.println(string.substring(4,string.indexOf('.', 4)));
		String string="abcdef";
		int length=string.length();
		String revese="";
		for(int i=length-1;i>=0;i--){
			Character character=string.charAt(i);
			revese=revese+character.toString();
		}
		System.out.println(revese);
	}
	
}
