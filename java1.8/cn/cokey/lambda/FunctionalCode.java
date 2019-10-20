package cn.cokey.lambda;

public class FunctionalCode {

	public static void main(String[] args) {
		int op1=3,op2=9;
		FunctionalInterface add=(i,j)->{System.out.println(i+j);return i+j;};
		FunctionalInterface sub=(i,j)->{System.out.println(i-j);return i+j;};
		FunctionalInterface multi=(i,j)->{System.out.println(i*j);return i+j;};
		FunctionalInterface div=(i,j)->{System.out.println(i/j);return i+j;};
		System.out.println(add.getClass().getName());
		System.out.println(sub.getClass().getName());
		System.out.println(multi.getClass().getName());
		System.out.println(div.getClass().getName());
		add.opearte(op1,op2);
		sub.opearte(op1,op2);
		multi.opearte(op1,op2);
		div.opearte(op1,op2);
	}
	
}
