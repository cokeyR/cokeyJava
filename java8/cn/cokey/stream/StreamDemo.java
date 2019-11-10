package cn.cokey.stream;

import java.util.Random;
import java.util.stream.IntStream;

public class StreamDemo {
	
	public static void main(String[] args) {
		
		Random random=new Random();
		IntStream ints = random.ints();
		ints.limit(10).sorted().forEach(System.out::println);
	}
}
