package java10;

import java.util.Arrays;

/**
 * var 类型推断
 * @author WX
 *
 */
public class _var {
	
	public static void varsyso() {
		var i=Arrays.asList("a","b","c");
		i.forEach(e->System.out.print(e+"\t"));
	}
	public static void main(String[] args) {
		varsyso();
		
	}
}
