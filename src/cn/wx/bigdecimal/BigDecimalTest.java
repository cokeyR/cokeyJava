package cn.wx.bigdecimal;

import java.math.BigDecimal;

public class BigDecimalTest {

	public static void main(String[] args) {
		
		BigDecimal decimal1=new BigDecimal("1.03"),
				   decimal2=new BigDecimal("0.42");
		System.out.println(decimal1.subtract(decimal2));
		
		double f1=1.03,f2=0.42;
		System.out.println(f1-f2);
		
	}
}
