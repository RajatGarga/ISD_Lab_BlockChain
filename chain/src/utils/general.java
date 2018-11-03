package utils;

import java.sql.Timestamp;
import java.util.Date;

public class general {
	
	public static String getTimeStamp() {
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		return ts.toString();
	}
	public static String getGenTimeStamp() {
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		return ts.toString();
	}
}
