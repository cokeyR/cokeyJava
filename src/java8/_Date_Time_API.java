package java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 
 * 原来的时间日期类设计都有缺陷
 * 引入java.time包，里面有几个重要的时间日期类API
 * @author WX
 *
 */
public class _Date_Time_API {
	
	public static void main(String[] args) {
		//LocalDateTime类
		LocalDateTime curDateTime=LocalDateTime.now();
		System.out.println("当前日期时间："+curDateTime);
		
		//LocalDate类
		LocalDate curDate=curDateTime.toLocalDate();
		System.out.println("当前日期:"+curDate);
		int year=curDate.getYear();
		int month=curDate.getMonthValue();//curDate.getMonth();
		int dayofMonth=curDate.getDayOfMonth();//curDate.getDayOfWeek();curDate.getDayOfYear();
		System.out.println("year:"+year+" month:"+month+" DayofNomth:"+dayofMonth);
		
		//LocalTime
		LocalTime curTime=curDateTime.toLocalTime();
		System.out.println("当前时间："+curTime);
		int hour=curTime.getHour();//获取小时
		int minute=curTime.getMinute();//获取分钟
		int second=curTime.getSecond();//获取秒
		int nona=curTime.getNano();//获取微妙
		System.out.println("hour:"+hour+" minute:"+minute+" second:"+second+" millisecond"+nona/1000000+" nonasecond:"+nona%1000);
		
	}

}
