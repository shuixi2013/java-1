package com.by.time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 
 * Extended GregorianCalendar with more common operation.
 * <ul>
 * <li>Retrieve partial of time,
 * <code>getYear(), getMonth(), getDay(), getHour(), getMinute(), getSecond()</code>
 * <li>Truncate time by kinds of time units.
 * <li>Increase and decrease time by time units.
 * <li>
 * <code>setToFirstDayOfThisWeek(), getFirstDayOfThisWeek(), setToLastDayOfThisWeek(), getLastDayOfThisWeek()</code>
 * </ul>
 * <a href="http://my.oschina.net/arthor" class="referer" target="_blank">
 * @author</a> Wang Yuxing
 */
public class TimeUtil extends GregorianCalendar implements Cloneable {
	private static final long serialVersionUID = 1L;
	static final int[] DAYS_OF_MONTH = new int[12];
	static {
		DAYS_OF_MONTH[0] = 31;
		DAYS_OF_MONTH[1] = 28;// 29 when leap year.
		DAYS_OF_MONTH[2] = 31;
		DAYS_OF_MONTH[3] = 30;
		DAYS_OF_MONTH[4] = 31;
		DAYS_OF_MONTH[5] = 30;
		DAYS_OF_MONTH[6] = 31;
		DAYS_OF_MONTH[7] = 31;
		DAYS_OF_MONTH[8] = 30;
		DAYS_OF_MONTH[9] = 31;
		DAYS_OF_MONTH[10] = 30;
		DAYS_OF_MONTH[11] = 31;
	}

	public TimeUtil(Calendar calendar) {
		this.setTime(calendar.getTime());
	}

	public TimeUtil(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
		super(year, month - 1, dayOfMonth, hourOfDay, minute, second);
	}

	public TimeUtil(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
		super(year, month - 1, dayOfMonth, hourOfDay, minute);
	}

	public TimeUtil(int year, int month, int dayOfMonth, int hourOfDay) {
		super(year, month - 1, dayOfMonth, hourOfDay, 0);
	}

	public TimeUtil(int year, int month, int dayOfMonth) {
		super(year, month - 1, dayOfMonth);
	}

	public TimeUtil(int year, int month) {
		super(year, month - 1, 1);
	}

	public TimeUtil(int year) {
		super(year, 0, 1);
	}

	public TimeUtil(TimeZone zone, Locale aLocale) {
		super(zone, aLocale);
	}

	public TimeUtil(Locale aLocale) {
		super(aLocale);
	}

	public TimeUtil(TimeZone zone) {
		super(zone);
	}

	public TimeUtil(Date date) {
		this.setTime(date);
	}

	public TimeUtil() {
	}

	@Override
	public TimeUtil clone() {
		return (TimeUtil) super.clone();
	}
	
	public TimeUtil increaseYears(int offset) {
		this.add(YEAR, offset);
		return this;
	}

	public TimeUtil increaseYear() {
		return increaseYears(1);
	}

	public TimeUtil increaseMonths(int offset) {
		this.add(MONTH, offset);
		return this;
	}

	public TimeUtil increaseMonth() {
		return this.increaseMonths(1);
	}

	public TimeUtil increaseWeeks(int offset) {
		this.add(DATE, offset * 7);
		return this;
	}

	public TimeUtil increaseWeek() {
		return increaseWeeks(1);
	}

	public TimeUtil increaseDates(int offset) {
		this.add(DATE, offset);
		return this;
	}

	public TimeUtil increaseDate() {
		return this.increaseDates(1);
	}

	public TimeUtil increaseHours(int offset) {
		this.add(HOUR_OF_DAY, offset);
		return this;
	}

	public TimeUtil increaseHour() {
		return this.increaseHours(1);
	}

	public TimeUtil increaseMinutes(int offset) {
		this.add(MINUTE, offset);
		return this;
	}

	public TimeUtil increaseMinute() {
		return this.increaseMinutes(1);
	}

	public TimeUtil increaseSeconds(int offset) {
		this.add(SECOND, offset);
		return this;
	}

	public TimeUtil increaseSecond() {
		return this.increaseSeconds(1);
	}

	/**
	 * Set time to the first day of the week that current time in.
	 *
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public TimeUtil setToFirstDayOfThisWeek() {
		this.increaseDates(SUNDAY - this.get(DAY_OF_WEEK));
		return this;
	}

	/**
	 * Return the first day of the week that current time in.
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public TimeUtil getFirstDayOfThisWeek() {
		return this.clone().setToFirstDayOfThisWeek();
	}

	/**
	 * Set time to the last day of the week that current time in.
	 *
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public TimeUtil setToLastDayOfThisWeek() {
		this.increaseDates(SATURDAY - this.get(DAY_OF_WEEK));
		return this;
	}

	/**
	 * Return the last day of the week that current time in.
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public TimeUtil getLastDayOfThisWeek() {
		return this.clone().setToLastDayOfThisWeek();
	}

	/**
	 * Check if it is working day in a week.
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public boolean isWorkingDay() {
		int dayOfWeek = this.get(DAY_OF_WEEK);
		return dayOfWeek > 1 && dayOfWeek < 7;
	}

	/**
	 * Check if it is NOT working day in a week.
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public boolean isRestDay() {
		return !isWorkingDay();
	}

	/**
	 * Truncate part of time at year
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public TimeUtil truncateAtYear() {
		this.truncateTime(this, Calendar.YEAR);
		return this;
	}

	/**
	 * Truncate part of time at month
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public TimeUtil truncateAtMonth() {
		this.truncateTime(this, Calendar.MONTH);
		return this;
	}

	/**
	 * Truncate part of time at week
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public TimeUtil truncateAtWeek() {
		this.truncateTimeByWeek(this);
		return this;
	}

	/**
	 * Truncate part of time at date
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public TimeUtil truncateAtDate() {
		this.truncateTime(this, Calendar.DAY_OF_MONTH);
		return this;
	}

	/**
	 * Truncate part of time at hour
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public TimeUtil truncateAtHour() {
		this.truncateTime(this, Calendar.HOUR_OF_DAY);
		return this;
	}

	/**
	 * Truncate part of time at minute
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public TimeUtil truncateAtMinute() {
		this.truncateTime(this, Calendar.MINUTE);
		return this;
	}

	/**
	 * Truncate part of time at second
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public TimeUtil truncateAtSecond() {
		this.truncateTime(this, Calendar.SECOND);
		return this;
	}

	/**
	 * Truncate part of time specifed Calender field
	 * 
	 * @param time
	 *            Time to be truncated
	 * @param field
	 *            Calendar field: YEAR, MONTH, DAYS_OF_MONTH, HOUR, HOUR_OF_DAY,
	 *            MINUTE, SECOND
	 */
	protected void truncateTime(Calendar time, int field) {
		switch (field) {
		case Calendar.YEAR:
			time.set(Calendar.MONTH, 0);
		case Calendar.MONTH:
			time.set(Calendar.DAY_OF_MONTH, 1);
		case Calendar.DAY_OF_MONTH:
			time.set(Calendar.HOUR_OF_DAY, 0);
		case Calendar.HOUR:
		case Calendar.HOUR_OF_DAY:
			time.set(Calendar.MINUTE, 0);
		case Calendar.MINUTE:
			time.set(Calendar.SECOND, 0);
		case Calendar.SECOND:
			time.set(Calendar.MILLISECOND, 0);
		}
	}

	/**
	 * Week is different, so handle it seperately.
	 * 
	 * @param time
	 */
	protected void truncateTimeByWeek(Calendar time) {
		time.getTime();// This must be called, otherwise following statement
						// will not be useful. Maybe this is a bug.
		time.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		time.set(Calendar.HOUR_OF_DAY, 0);
		time.set(Calendar.MINUTE, 0);
		time.set(Calendar.SECOND, 0);
		time.set(Calendar.MILLISECOND, 0);
	}

	/**
	 * 
	 * @param time
	 *            <a href="http://my.oschina.net/u/556800" class="referer"
	 *            target="_blank">@return</a>
	 */
	public int getYearIntervalFrom(TimeUtil time) {
		if (time == null)
			return 0;
		return this.get(Calendar.YEAR) - time.get(Calendar.YEAR);
	}

	public int getYearIntervalTo(TimeUtil time) {
		return 0 - getYearIntervalFrom(time);
	}

	public int getMonthIntervalFrom(TimeUtil time) {
		int yearInterval = getYearIntervalFrom(time);
		return (this.get(Calendar.MONTH) - time.get(Calendar.MONTH)) + yearInterval * 12;
	}

	public int getMonthIntervalTo(TimeUtil time) {
		return 0 - getMonthIntervalFrom(time);
	}

	public int getWeekIntervalFrom(TimeUtil time) {
		if (time == null) {
			return 0;
		}
		Calendar s = time.clone().truncateAtWeek();
		Calendar e = this.clone().truncateAtWeek();
		long timeInMillis = e.getTimeInMillis() - s.getTimeInMillis();
		int weekInterval = (int) (timeInMillis / (7 * 24 * 60 * 60 * 1000));
		return weekInterval;
	}

	public int getWeekIntervalTo(TimeUtil time) {
		return 0 - getWeekIntervalFrom(time);
	}

	/**
	 * 
	 * @param time
	 *            <a href="http://my.oschina.net/u/556800" class="referer"
	 *            target="_blank">@return</a>
	 */
	public int getDayIntervalFrom(TimeUtil time) {
		if (time == null) {
			return 0;
		}
		Calendar s = time.clone().truncateAtDate();
		Calendar e = this.clone().truncateAtDate();
		long timeInMillis = e.getTimeInMillis() - s.getTimeInMillis();
		int millisOfDay = 24 * 60 * 60 * 1000;
		long intervalInDate = timeInMillis / millisOfDay;
		return (int) intervalInDate;
	}

	public int getDayIntervalTo(TimeUtil time) {
		return 0 - getDayIntervalFrom(time);
	}

	public int getHourIntervalFrom(TimeUtil time) {
		if (time == null) {
			return 0;
		}
		Calendar s = time.clone().truncateAtHour();
		Calendar e = this.clone().truncateAtHour();
		long timeInMillis = e.getTimeInMillis() - s.getTimeInMillis();
		long intervalInHour = timeInMillis / (60 * 60 * 1000);
		return (int) intervalInHour;
	}

	public int getHourIntervalTo(TimeUtil time) {
		return 0 - getHourIntervalFrom(time);
	}

	public int getMinuteIntervalFrom(TimeUtil time) {
		if (time == null) {
			return 0;
		}
		Calendar s = time.clone().truncateAtMinute();
		Calendar e = this.clone().truncateAtMinute();
		long timeInMillis = e.getTimeInMillis() - s.getTimeInMillis();
		long ret = timeInMillis / (60 * 1000);
		return (int) ret;
	}

	public int getMinuteIntervalTo(TimeUtil time) {
		return 0 - getMinuteIntervalFrom(time);
	}

	public int getSecondIntervalFrom(TimeUtil time) {
		if (time == null) {
			return 0;
		}
		Calendar s = time.clone().truncateAtSecond();
		Calendar e = this.clone().truncateAtSecond();
		long timeInMillis = e.getTimeInMillis() - s.getTimeInMillis();
		long ret = timeInMillis / 1000;
		return (int) ret;
	}

	public int getSecondIntervalTo(TimeUtil time) {
		return 0 - getSecondIntervalFrom(time);
	}

	/**
	 * Use thie method to set time instead of 'set' method of Calendar
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @param hour
	 * @param minute
	 * @param second
	 */
	public void setTime(int year, int month, int date, int hour, int minute, int second) {
		super.set(year, month, date, hour, minute, second);
	}

	/**
	 * Use thie method to set time instead of 'set' method of Calendar
	 * 
	 * @param year
	 * @param month
	 * @param date
	 */
	public void setTime(int year, int month, int date) {
		super.set(year, month - 1, date);
	}

	/**
	 * Get year of time.
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public int getYear() {
		return this.get(YEAR);
	}

	/**
	 * Set year of time.
	 * 
	 * @param year
	 */
	public void setYear(int year) {
		this.set(YEAR, year);
	}

	/**
	 * Get month of time, starts from 1, not 0.
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public int getMonth() {
		return this.get(MONTH) + 1;
	}

	/**
	 * Set month of time.
	 * 
	 * @param month
	 */
	public void setMonth(int month) {
		this.set(MONTH, month - 1);
	}

	/**
	 * Get day of time
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public int getDate() {
		return this.get(DATE);
	}

	/**
	 * Set day of time.
	 * 
	 * @param date
	 */
	public void setDate(int date) {
		this.set(DATE, date);
	}

	/**
	 * Get hour of time.
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public int getHour() {
		return this.get(HOUR_OF_DAY);
	}

	/**
	 * Set hour of time.
	 * 
	 * @param hour
	 */
	public void setHour(int hour) {
		this.set(HOUR_OF_DAY, hour);
	}

	/**
	 * Get minute of time.
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public int getMinute() {
		return this.get(MINUTE);
	}

	/**
	 * Set minute of time.
	 * 
	 * @param minute
	 */
	public void setMinute(int minute) {
		this.set(MINUTE, minute);
	}

	/**
	 * Get second of time.
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public int getSecond() {
		return this.get(SECOND);
	}

	/**
	 * Set second of time.
	 * 
	 * @param second
	 */
	public void setSecond(int second) {
		this.set(SECOND, second);
	}

	/**
	 * Get day of week.
	 *
	 * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">
	 * @return</a>
	 */
	public int getDayOfWeek() {
		return this.get(Calendar.DAY_OF_WEEK);
	}

	public int getDaysOfThisMonth() {
		return getDaysOfMonth(this);
	}

	public static int getDaysOfMonth(TimeUtil time) {
		int year = time.getYear();
		int month = time.getMonth();
		if (month == 2 && time.isLeapYear(year)) {
			return 29;
		}
		return DAYS_OF_MONTH[month];
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return this.getTime().toString();
	}

	@Override
	public int hashCode() {
		int hash = 7;
		return hash;
	}
}
