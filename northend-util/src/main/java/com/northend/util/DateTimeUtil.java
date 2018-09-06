package com.northend.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	/**
	 * 获取当前时间戳
	 * 
	 * @return
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 格式化时间戳
	 * 
	 * @return
	 */
	public static long formatTimestamp(Timestamp ts) {
		if (ts == null) {
			return System.currentTimeMillis() / 1000;
		}
		return ts.getTime() / 1000;
	}

	/**
	 * 格式化unix日期
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String formatTimestamp(long time, String format) {
		if (format == null)
			format = "yyyy-MM-dd kk:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(time * 1000));
	}

	/**
	 * 格式化后的明文数据
	 * 刚刚（1-5），5分钟前，10分钟前，30分钟前，1小时前，2小时前，5小时前，12小时前，4月7号，3月7号，2016年，2011年
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTimestamp2String(long time) {
		long delay = System.currentTimeMillis() / 1000 - time;
		if (delay < 60 * 5) {
			return "刚刚";
		} else if (delay < 60 * 10) {
			return "5分钟前";
		} else if (delay < 60 * 30) {
			return "10分钟前";
		} else if (delay < 60 * 60) {
			return "30分钟前";
		} else if (delay < 60 * 60 * 2) {
			return "1小时前";
		} else if (delay < 60 * 60 * 5) {
			return "2小时前";
		} else if (delay < 60 * 60 * 12) {
			return "5小时前";
		} else if (delay < 60 * 60 * 24) {
			return "12小时前";
		} else if (delay < 60 * 60 * 48) {
			return "昨天";
		} else {
			return formatTimestamp(time, "yyyy-MM-dd");
		}
	}

	/**
	 * 格式化成long
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static long formatTimestamp2Long(String str, String format) {
		if (format == null)
			format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return System.currentTimeMillis();
	}

	/**
	 * 获取用户年龄
	 * 
	 * @param timeStamp
	 * @return
	 */
	public static int getAge(long timeStamp) {
		Date beginDate = new Date(timeStamp * 1000);
		Date endDate = new Date(System.currentTimeMillis());
		int age = (int) ((endDate.getTime() - beginDate.getTime()) / 24 / 60 / 60 / 1000 / 365);
		return age;
	}

	public static int getSecondToNow(long timeStamp) {
		Date beginDate = new Date(timeStamp * 1000);
		Date endDate = new Date(System.currentTimeMillis());
		return (int) ((endDate.getTime() - beginDate.getTime()) / 1000);
	}

	/**
	 * 获取时间
	 * 
	 * @param mills
	 * @return
	 */
	public static String getDuration(long mills) {
		long s = mills;
		if (s > 60) {
			int m = (int) (s / 60);
			if (m > 60) {
				int h = m / 60;
				return get0Num(h) + get0Num(m % 60) + get0Num((int) s % 60);
			} else {
				return "00:" + get0Num(m) + ":" + get0Num((int) s % 60);
			}
		} else {
			return "00:" + get0Num((int) s);
		}
	}

	public static String get0Num(int n) {
		return n < 10 ? "0" + n : String.valueOf(n);
	}

}
