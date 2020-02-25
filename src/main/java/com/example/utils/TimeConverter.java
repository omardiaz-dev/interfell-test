package com.example.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeConverter {
	
	
	private static String getTime(String timeZone, String time) {
 		String myTime="";
 		String preparedTimeZone="GMT" + timeZone + ":00";
 		
 		String myHour="";
    	Date date = null;
    	DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    	try {
			date = formatter.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	TimeZone tz = TimeZone.getTimeZone(preparedTimeZone);
    	SimpleDateFormat date_format_gmt = new SimpleDateFormat("HH:mm:ss");
	    date_format_gmt.setTimeZone(tz);
	    myTime= date_format_gmt.format(date);
    	
    	return myTime;
 	}
	
	
	public static String getTimeInJSON(String timeZone, String time) {
 		StringBuilder json = new StringBuilder();
 		json.append("{\"response\": {\"time\": \"");
 		json.append(getTime(timeZone, time));
 		json.append(",\"timezone\": \"utc\"}}");
 		
 		return json.toString();		
 	}

}
