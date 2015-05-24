package com.fenlonsky.campaign.commons.utils;

import java.text.ParseException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 
 * @ClassName: FenlonDateFormat
 * @Description: 日期格式化处理
 * @author <a href="mailto:fenlonxiong@gmail.com">Fenlon</a>
 * @date 2015年5月24日 下午6:09:41
 * 
 */
public class FenlonDateFormat {
	
	public static class DatePattern {
		
		public static final String isoTime = "yyyy-MM-dd HH:mm:ss";
		public static final String isoDate = "yyyy-MM-dd";
		public static final String yyyyMMdd = "yyyyMMdd";
	}
	
	public static class DateFormatter {
		
		public static final DateTimeFormatter timeFormat = DateTimeFormat
				.forPattern(DatePattern.isoTime);
		public static final DateTimeFormatter dateFormat = DateTimeFormat
				.forPattern(DatePattern.isoDate);
	}
	
	/**
	 * 转换String格式时间为yyyy-MM-dd HH:mm:ss格式的Timestamp
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static DateTime stringToTime(String time) {
		try {
			return DateTime.parse(time, DateFormatter.timeFormat);
		} catch (IllegalArgumentException e) {
			return null;
		}
		
	}
	
	/**
	 * 转换String格式时间为yyyy-MM-dd格式的Timestamp
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static DateTime stringToDate(String time) {
		try {
			return DateTime.parse(time, DateFormatter.dateFormat);
		} catch (IllegalArgumentException e) {
			return null;
		}
		
	}
	
	/**
	 * 将datetime 转换为yyyy-MM-dd 格式的DateTime
	 * 
	 * @param time
	 * @return
	 */
	public static DateTime timeToDate(DateTime time) {
		try {
			return DateTime.parse(time.toString().substring(0, 10),
					DateFormatter.dateFormat);
		} catch (IllegalArgumentException e) {
			return null;
		}
		
	}
	
	/**
	 * 转换Timestamp 为指定templet 的字符串时间格式
	 * 
	 * @param time
	 * @param templet
	 * @return
	 */
	public static String formatDateTime(DateTime time, String templet) {
		DateTimeFormatter timeFormat = DateTimeFormat.forPattern(templet);
		return time.toString(timeFormat);
	}
	
	/**
	 * 判断当前时间是否位于时间区间
	 * 
	 * @param starttime
	 * @param aftertime
	 * @return
	 */
	public static int nowInTimaRange(DateTime starttime, DateTime aftertime) {
		DateTime now = DateTime.now();
		if (now.isAfter(aftertime)) {
			return 1;
		} else if (now.isBefore(starttime)) {
			return -1;
		}
		return 0;
	}
	
	public static boolean isSameDay(DateTime date1, DateTime date2) {
		DateTime d1 = timeToDate(date1);
		DateTime d2 = timeToDate(date2);
		if (d1.isBefore(d2) || d1.isAfter(d2)) {
			return false;
		}
		return true;
	}
	
}
