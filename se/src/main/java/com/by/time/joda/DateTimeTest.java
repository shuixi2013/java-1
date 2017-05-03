package com.by.time.joda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.joda.time.Minutes;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.ISODateTimeFormat;

import com.by.time.joda.factory.SystemFactory;

@SuppressWarnings("unused")
public class DateTimeTest {
	private static Logger logger = LogManager.getLogger(DateTimeTest.class);

	public static void main(String[] args) throws ParseException {

		example2_1();
		example2_2();
		example2_3();
		example2_4();
		example2_5();

	}

	// 构造器
	private static void example2_1() {
		logger.info("dateTime{}", "初始化方式 ");

		DateTime dateTime = new DateTime();

		dateTime = SystemFactory.getClock().getDateTime();

		dateTime = new DateTime(2000, // year
				1, // month
				1, // day
				0, // hour (midnight is zero)
				0, // minute
				0, // second
				0 // milliseconds
		);

		java.util.Date jdkDate = obtainDateSomehow();
		dateTime = new DateTime(jdkDate.getTime());

		// Use a Calendar
		java.util.Calendar calendar = obtainCalendarSomehow();
		dateTime = new DateTime(calendar);

		// Use another Joda DateTime
		DateTime anotherDateTime = obtainDateTimeSomehow();
		dateTime = new DateTime(anotherDateTime);
		logger.info("6Using another DateTime object: " + dateTime.toString());

		// Use a String (must be formatted properly)
		dateTime = new DateTime("2006-01-26T13:30:00-06:00");

		dateTime = new DateTime("2006-01-26");

		dateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("1985-12-26 23:23:31");
		
		// 默认设置为日本时间
		DateTimeZone.setDefault(DateTimeZone.forID("Asia/Tokyo"));
		DateTime dt1 = new DateTime();
		// 伦敦时间
		DateTime dt2 = new DateTime(DateTimeZone.forID("Europe/London"));
	}

	// Calendar-Date
	private static void example2_2() {
        long time =  1415861538986L;
        DateTime dateTime = new DateTime(time);
        time = dateTime.getMillis();

        Date dateSource = new Date();
        dateTime = new DateTime(dateSource);
        Date date2 = dateTime.toDate();
        
        Calendar cal = Calendar.getInstance();
        dateTime = new DateTime(cal);
        Calendar calendar = dateTime.toGregorianCalendar();
		        
		logger.info("dateTime{}", " Calendar比较");
		// 1
		calendar = Calendar.getInstance();
		calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy HH:mm:ss.SSS");
		calendar.add(Calendar.DAY_OF_MONTH, 90);
		System.out.println(sdf.format(calendar.getTime()));
		// 2
		dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);

		logger.info("dateTime{}", "转换为Calendar-date");
		// 转换1
		calendar.setTime(dateTime.toDate());
		logger.info("Calendar1{}", calendar);

		calendar = dateTime.toCalendar(Locale.getDefault());
		logger.info("Calendar2{}", calendar);

		Date date = dateTime.toDate();
		logger.info("As Date:" + sdf.format(date));
	}

	// 方法
	private static void example2_3() {
		logger.info("dateTime{}", "方法");

		DateTime dt = SystemFactory.getClock().getDateTime();
		// 昨天
		DateTime yesterday = dt.minusDays(1);
		// 明天
		DateTime tomorrow = dt.plusDays(1);
		// 1个月前
		DateTime before1month = dt.minusMonths(1);
		// 3个月后
		DateTime after3month = dt.plusMonths(3);
		// 2年前
		DateTime before2year = dt.minusYears(2);
		// 5年后
		DateTime after5year = dt.plusYears(5);

		DateTime after2week = dt.plusWeeks(2);

		DateTime after156sceond = dt.plusSeconds(156);

		// 年
		int year = dt.getYear();
		// 月
		int month = dt.getMonthOfYear();
		// 日
		int day = dt.getDayOfMonth();
		// 星期
		int week = dt.getDayOfWeek();
		// 点
		int hour = dt.getHourOfDay();
		// 分
		int min = dt.getMinuteOfHour();
		// 秒
		int sec = dt.getSecondOfMinute();
		// 毫秒
		int msec = dt.getMillisOfSecond();

		// 判断是否闰月
		org.joda.time.DateTime.Property monthTmp = dt.monthOfYear();
		System.out.println("是否闰月:" + monthTmp.isLeap());
		// then = now.minusYears(5).monthOfYear().setCopy(2).dayOfMonth().withMaximumValue();
		// 取得 3秒前的时间
		DateTime dt5 = dt.secondOfMinute().addToCopy(-3);
		
		// 月末日期
		DateTime lastday = dt.dayOfMonth().withMaximumValue();
		System.out.println(dt.dayOfWeek().withMaximumValue().toString("E MM/dd/yyyy HH:mm:ss.SSS"));
		// 90天后那周的周一
		DateTime firstday = dt.plusDays(90).dayOfWeek().withMinimumValue();

		// 星期
		switch (dt.getDayOfWeek()) {
		case DateTimeConstants.SUNDAY:
			System.out.println("星期日");
			break;
		case DateTimeConstants.MONDAY:
			System.out.println("星期一");
			break;
		case DateTimeConstants.TUESDAY:
			System.out.println("星期二");
			break;
		case DateTimeConstants.WEDNESDAY:
			System.out.println("星期三");
			break;
		case DateTimeConstants.THURSDAY:
			System.out.println("星期四");
			break;
		case DateTimeConstants.FRIDAY:
			System.out.println("星期五");
			break;
		case DateTimeConstants.SATURDAY:
			System.out.println("星期六");
			break;
		}
	}

	// 计算时间差-区间-比较日期
	public static void example2_4() throws ParseException {
		DateTime dt1 = new DateTime(2016, 2, 14, 16, 0, 0, 0);
		DateTime dt2 = new DateTime(2016, 2, 15, 16, 0, 0, 0);
		System.out.print("时间相差：");
		System.out.print(Days.daysBetween(dt1, dt2).getDays() + " 天 ");
		System.out.print(Hours.hoursBetween(dt1, dt2).getHours() % 24 + " 小时 ");
		System.out.print(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + " 分钟 ");
		System.out.print(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60 + " 秒.");

		Interval interval = new Interval(dt1.getMillis(), dt2.getMillis());
		Period p = interval.toPeriod();
		System.out.println(
				"时间相差：" + p.getDays() + " 天 " + p.getHours() + " 小时 " + p.getMinutes() + " 分钟" + p.getSeconds() + " 秒");
		DateTime start = interval.getStart();
		DateTime end = interval.getEnd();
		DateTime testDate = new DateTime(2004, 2, 1, 0, 0, 0, 0);
		// 计算特定日期是否在该区间内
		boolean contained = interval.contains(new DateTime("2012-03-01"));
		boolean contains = interval.contains(testDate);
		
		DateTime begin = new DateTime("2012-02-01");
		end = new DateTime("2012-05-01");

		// 计算区间毫秒数
		Duration d = new Duration(begin, end);
		long time = d.getMillis();

		// 计算区间天数
		Period p2 = new Period(begin, end, PeriodType.days());
		int days = p2.getDays();

		DateTime d1 = new DateTime("2012-02-01");
		DateTime d2 = new DateTime("2012-05-01");

		// 和系统时间比
		boolean b1 = d1.isAfterNow();
		boolean b2 = d1.isBeforeNow();
		boolean b3 = d1.isEqualNow();

		// 和其他日期比
		boolean f1 = d1.isAfter(d2);
		boolean f2 = d1.isBefore(d2);
		boolean f3 = d1.isEqual(d2);
		
		start = new DateTime(1975, 5, 26, 0, 0, 0);
		Duration oneThousandMillis = new Duration(1000);
		DateTime end1 = start.plus(oneThousandMillis);
	}

	// 格式化
	private static void example2_5() {
		logger.info("dateTime{}", "格式化");
		DateTime dateTime = SystemFactory.getClock().getDateTime();
		logger.info("Using ISODateTimeFormat.basicDateTime: " + dateTime.toString(ISODateTimeFormat.basicDateTime()));
		logger.info("Using ISODateTimeFormat.basicDateTimeNoMillis: "
				+ dateTime.toString(ISODateTimeFormat.basicDateTimeNoMillis()));
		logger.info("Using ISODateTimeFormat.basicOrdinalDateTime: "
				+ dateTime.toString(ISODateTimeFormat.basicOrdinalDateTime()));
		logger.info("Using ISODateTimeFormat.basicWeekDateTime: "
				+ dateTime.toString(ISODateTimeFormat.basicWeekDateTime()));

		logger.info("Using MM/dd/yyyy hh:mm:ss.SSSa: " + dateTime.toString("MM/dd/yyyy hh:mm:ss.SSSa"));
		logger.info("Using dd-MM-yyyy HH:mm:ss: " + dateTime.toString("dd-MM-yyyy HH:mm:ss"));
		logger.info("Using EEEE dd MMMM, yyyy HH:mm:ss: " + dateTime.toString("EEEE dd MMMM, yyyy HH:mm:ss"));
		logger.info("Using MM/dd/yyyy HH:mm ZZZZ: " + dateTime.toString("MM/dd/yyyy HH:mm ZZZZ"));
		logger.info("Using MM/dd/yyyy HH:mm Z: " + dateTime.toString("MM/dd/yyyy HH:mm Z"));
	}

	private static Date obtainDateSomehow() {
		return SystemFactory.getClock().getDate();
	}

	private static Calendar obtainCalendarSomehow() {
		return SystemFactory.getClock().getCalendar();
	}

	private static DateTime obtainDateTimeSomehow() {
		return SystemFactory.getClock().getDateTime();
	}

}
