package com.mvc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.stereotype.Service;

@Service
public class DataUtil {

	public Map<String, String> getTime(String flag) {
		Map<String, String> timeObject = new HashMap<String, String>();
		Date endDate = new Date();
		// 创建基于当前时间的日历对象
		Calendar cl = Calendar.getInstance();
		cl.setTime(endDate);
		// 距离今天，一个月内数据
		if (flag.equals("month")) {
			cl.add(Calendar.MONTH, -1);
		}
		// 距离今天，一周内的数据
		if (flag.equals("week")) {
			cl.add(Calendar.DATE, -7);
		}
		if (flag.equals("day")) {
			cl.add(Calendar.DATE, -1);
		}
		Date startDate = cl.getTime();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
		// 格式化开始日期和结束日期
		String start = dd.format(startDate);
		String end = dd.format(endDate);
		timeObject.put("start", start);
		timeObject.put("end", end);
		return timeObject;
	}
	
	public Map<String, String> getTimeAfter(String flag) {
		Map<String, String> timeObject = new HashMap<String, String>();
		Date startDate = new Date();
		// 创建基于当前时间的日历对象
		Calendar cl = Calendar.getInstance();
		cl.setTime(startDate);
		// 距离今天，一个月内数据
		if (flag.equals("month")) {
			cl.add(Calendar.MONTH, +1);
		}
		// 距离今天，一周内的数据
		if (flag.equals("week")) {
			cl.add(Calendar.DATE, +7);
		}
		if (flag.equals("day")) {
			cl.add(Calendar.DATE, +1);
		}
		Date endDate = cl.getTime();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
		// 格式化开始日期和结束日期
		String start = dd.format(startDate);
		String end = dd.format(endDate);
		timeObject.put("start", start);
		timeObject.put("end", end);
		return timeObject;
	}
	
	public String getTimeAfterGivenDay(String date,int day) throws ParseException {
		SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String afterdate="";
		Date startDate = df.parse(date);
		// 创建基于当前时间的日历对象
		Calendar cl = Calendar.getInstance();
		cl.setTime(startDate);
		cl.add(Calendar.DATE, +day);
		Date endDate = cl.getTime();
		// 格式化开始日期和结束日期
		afterdate = dd.format(endDate);
		return afterdate;
	}
	
	public static int getMonthSpace(String start, String end)
			throws java.text.ParseException {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(sdf.parse(start));
		c2.setTime(sdf.parse(end));
		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		return result == 0 ? 1 : Math.abs(result);
	}

	public int getDateSpace(String start, String end)
			throws java.text.ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar calst = Calendar.getInstance();
		;
		Calendar caled = Calendar.getInstance();

		calst.setTime(sdf.parse(start));
		caled.setTime(sdf.parse(end));

		// 设置时间为0时
		calst.set(Calendar.HOUR_OF_DAY, 0);
		calst.set(Calendar.MINUTE, 0);
		calst.set(Calendar.SECOND, 0);
		caled.set(Calendar.HOUR_OF_DAY, 0);
		caled.set(Calendar.MINUTE, 0);
		caled.set(Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
				.getTime().getTime() / 1000)) / 3600 / 24;

		return days;
	}
	
	public String getTimeNow(String flag){
		Date startDate = new Date();
		// 创建基于当前时间的日历对象
		// 距离今天，一个月内数据
		SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
		if(flag.equals("day")){
			dd = new SimpleDateFormat("yyyy/MM/dd");
		}else if(flag.equals("month")){
			dd = new SimpleDateFormat("yyyy/MM");
		}else if(flag.equals("year")){
			dd = new SimpleDateFormat("yyyy");
		}else if(flag.equals("hours")){
			dd = new SimpleDateFormat("yyyy/MM/dd HH"); 
		}else if(flag.equals("minute")){
			dd = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		}else if(flag.equals("second")){
			dd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		}
		// 格式化开始日期和结束日期
		String now = dd.format(startDate);
		return now;
	}
	
	public Date str2date(String time) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = sdf.parse(time);
		return date;
	}
	
	public long getTimeSub(String newtime, String oldtime) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		long date1=sdf.parse(newtime).getTime();
		long date2=sdf.parse(oldtime).getTime();
		return date1-date2;
	}
	
	@Test
	public void testoutn() throws java.text.ParseException {
		int i=28;
		int k=57;
		System.out.println(k/i);
	}
}
