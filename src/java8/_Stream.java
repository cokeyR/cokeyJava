package java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 *  Stream（流）是一个来自数据源的元素队列并支持聚合操作
 *  
 *  1.元素:是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。
 *  
 *  2.数据源: 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
 *  
 *  3.聚合操作:类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
 *  
 *  4.和以前的Collection操作不同， Stream操作还有两个基础的特征：
 *  
 *  	Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。
 * 		内部迭代： 以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。 Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现。

 * 
 * * * * * *IllegalStateException:一个流只能被迭代使用一次（消费，用完就没有了）
 * 
 * * * * * *Collectors将流规约为集合类对象或聚合元素
 * 
 * @author WX
 *
 */
public class _Stream {

	public static void main(String[] args) {
		List<String> list=Arrays.asList("three","five","one","four","two","eight","sit","seven","ten","nine");
		
		list.forEach(string->System.out.print(string+"\t"));
		//创建流
		Stream<String> stream=list.stream();
		
		//count统计数量
		System.out.println("\n\ncount统计流中元素数量");
		System.out.println(stream.count());
		
		//过滤流
		System.out.println("\n含字母e的有");
		stream=list.stream();
		stream.filter(e->e.indexOf('e')!=-1).forEach(e->System.out.print(e+"\t"));
		System.out.println("map");
		
		//映射list中的值到另一个值
		//.collect(...)用流生成集合
		System.out.println("\n使用mapper改变元素，为每个值加上_1");
		stream=list.stream();//必须重写创建，上一个流已经被消费了
		List<String>newlist=stream.map(e->e+="_1").collect(Collectors.toList());
		newlist.forEach(string->System.out.print(string+"\t"));
		
		//limit限制流中对象的数量
		System.out.println("\n\nlimit限制流中数量");
		stream=list.stream();
		stream.limit(5).forEach(e->System.out.print(e+"\t"));
		
		//sort()对流排序
		System.out.println("\n\nsorted排序");
		stream=list.stream();
		stream.sorted().forEach(e->System.out.print(e+"\t"));
	}
}
