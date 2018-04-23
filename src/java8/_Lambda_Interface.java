package java8;

import java.util.ArrayList;
import java.util.List;

/**
 * lambda表达式、FunctionalInterface
 * 
 * 接口可以有多个默认方法（实现了的方法）。
 * 
 * @author WX
 *
 */
/*
 * jdk8中
 * 
 * 1. 只有一个抽象方法的接口成为函数式接口，可以使用lambda风格创建对象实现接口方法。
 * 
 * 2. lambda风格实现时，变量的作用域和函数一样只能访问内部变量和定义为final的外层变量 
 * 
 * 3. 函数式接口的抽象方法由多个参数时，实现时要用()括起所有参数，实现有多个表达式时要用{}括起所有参数。单个参数、单条语句时(){}可以省略。
 *	      若函数有返回值，当函数体中只有有一个值时，则默认返回这一个值，若函数体中有多值，则要return一个值。
 * 
 * 4. 接口中的默认方法并且可以是静态的。这可能引起实现多个接口时，接口中默认方法冲突（有一样的名字和返回值），此时应该在类中重写此方法（可在重写的方法中调用接口中的默认方法）
 * 
 *另外interface中默认都是public的，字段都是常量
 */
@FunctionalInterface
interface MathOperation{
	double operation(double para1,double para2);
//	double operation(double para1,double para2,double para3);
}

public class _Lambda_Interface {

	public static void main(String[] args) {
		List<String> strings=new ArrayList<>();
		strings.add("B");
		strings.add("V");
		strings.add("C");
		strings.add("A");
		
		//实现为加法
		MathOperation addition=(double para1,double para2)->{return para1+para2;};
		double addResult=addition.operation(2.3,3.2);
		
		//实现为减法
		MathOperation subtraction=(double para1,double para2)->{return para1/para2;};
		
		double subtractResult=subtraction.operation(1, 2);
		
		
		System.out.println("2.3+3.3="+addResult);
		System.out.println("1/2="+subtractResult);
	}
}
