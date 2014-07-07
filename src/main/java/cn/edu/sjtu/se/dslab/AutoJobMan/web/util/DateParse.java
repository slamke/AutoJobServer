package cn.edu.sjtu.se.dslab.AutoJobMan.web.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateParse {
	public Date string2Date(String dateString)
	{
		if(dateString == null || dateString.equals(""))
		{
			return null;
		}
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
	   			return format.parse(dateString+" 00:00:01");   		
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public Date string2Date2(String dateString)
	{
		if(dateString == null || dateString.equals(""))
		{
			return null;
		}
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
	   			return format.parse(dateString);   		
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public Calendar s2Date(String dateString) throws ParseException{
		if(dateString == null || dateString.equals(""))
		{
			return null;
		}
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date =format.parse(dateString+" 00:00:01");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	public Calendar s2Date2(String dateString) throws ParseException{
		if(dateString == null || dateString.equals(""))
		{
			return null;
		}
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date =format.parse(dateString);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	public String date2String(Date date)
	{
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return format.format(date).subSequence(0, 10).toString();
		}
		else {
			return null;
		}
	}
	
	public Date getCurrentDate() {
		Date curDate=new Date(System.currentTimeMillis());
		return curDate;
	}
	
	 public  int daysBetween(Date smdate,Date bdate) throws ParseException    
	    {    
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        smdate=sdf.parse(sdf.format(smdate));  
	        bdate=sdf.parse(sdf.format(bdate));  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(smdate);    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(bdate);    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	       return Integer.parseInt(String.valueOf(between_days));           
	    }   
}
