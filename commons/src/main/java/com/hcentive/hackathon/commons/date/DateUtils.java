package com.hcentive.hackathon.commons.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.FastDateFormat;

/**
 * @author Nitin.Gupta
 * 
 */
public class DateUtils {

	private static Map<String, FastDateFormat> fdfMap = new HashMap<String, FastDateFormat>(
			0);

	// private static final FastDateFormat FDF = FastDateFormat.getInstance();

	public static String format(Date date, String format) {

		FastDateFormat fdf;

		if (fdfMap.containsKey(format)) {
			fdf = fdfMap.get(format);
		} else {
			fdf = FastDateFormat.getInstance(format);
			fdfMap.put(format, fdf);
		}

		return fdf.format(date);
	}

	/**
	 * returns difference in minutes from the current system date.
	 * 
	 * @param date
	 * @return
	 */
	public static final long diffInMinutes(Date date) {
		return diff(date, Calendar.MINUTE);
	}

	/**
	 * returns difference in seconds from the current system date.
	 * 
	 * @param date
	 * @return
	 */
	public static final long diffInSecs(Date date) {
		return diff(date, Calendar.SECOND);
	}
	
	public static final long diffInDays(Date to, Date from) {
		long diffinSecs = diff(to, from, Calendar.SECOND);
	
		long diffInDays = TimeUnit.DAYS.convert(diffinSecs, TimeUnit.SECONDS);
		
		return diffInDays;
	}
	
	public static String currentDateToString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(Calendar.getInstance().getTime());
	}

	public static final Date parse(String dateStr, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}
	
	private static final long diff(Date to, Date from, int unit) {
		if (to == null)
			throw new IllegalArgumentException("to date cannot be null");

		long diffInMillis = to.getTime() - from.getTime();

		if (unit == Calendar.MINUTE) {
			return (diffInMillis / 1000) / 60;
		} else if (unit == Calendar.SECOND) {
			return (diffInMillis / 1000);
		} else {
			return diffInMillis;
		}

	}
	
	private static final long diff(Date date, int unit) {
		if (date == null)
			throw new IllegalArgumentException("date canno be null");

		Date sysDate = Calendar.getInstance().getTime();
		
		return diff(sysDate, date, unit);
	}
}
