package com.pcms.core.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 时间操作工具类
 * @author Administrator
 *
 */
public class DateUtil {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒",Locale.SIMPLIFIED_CHINESE);
	private static final SimpleDateFormat shortFormat = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
	private static final SimpleDateFormat timeFormats = new SimpleDateFormat("HHmmss");
	private static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	//1
	public static final String FORMAT = "yyyyMMdd";
	//2
	public static final String FORMAT_BIAS = "yyyy/MM/dd";
	//3
	public static final String FORMAT_LAND = "yyyy-MM-dd";
	//4
	public static final String FORMAT_SCAPE_HMS = "yyyy-MM-dd HH:mm:ss";
	//5
	public static final String FORMAT_HMS = "yyyyMMddHHmmss";
	//6
	public static final String FORMAT_SCAPE_HMSS = "yyyy-MM-dd HH:mm:ss.sss";
	//7
	public static final String FORMAT_HOUR_MINUTE = "HH:mm";
	
	public static final int SERIAL_HOUR_MINUTE = 7;
	//8
	public static final String FORMAT_YEAR_MONTH = "yyyy-MM";
	//9
	public static final String FORMAT_SCAPE_HM = "yyyy-MM-dd HH:mm";
	//10
	public static final String FORMAT_YEAR = "yyyy";

	public static Map<String, String> dateForMatString = new HashMap(8);
	public static Map<String, SimpleDateFormat> dateForMatObject = new HashMap(8);

	static {
		dateForMatString.put("1", FORMAT);
		dateForMatString.put("2", FORMAT_BIAS);
		dateForMatString.put("3", FORMAT_LAND);
		dateForMatString.put("4", FORMAT_SCAPE_HMS);
		dateForMatString.put("5", FORMAT_HMS);
		dateForMatString.put("6", FORMAT_SCAPE_HMSS);
		dateForMatString.put("7", FORMAT_HOUR_MINUTE);
		dateForMatString.put("8", FORMAT_YEAR_MONTH);
		dateForMatString.put("9", FORMAT_SCAPE_HM);
		dateForMatString.put("10", FORMAT_YEAR);
	}
	public static String getDate(Date currentTime) throws ParseException{
		
		   String dateString = dateFormat.format(currentTime);
		   return dateString;
	}
	
	public static String getShortDate(Date currentTime) throws ParseException{
		
		   String dateString = shortFormat.format(currentTime);
		   return dateString;
	}
	public static String getYear(Date currentTime) throws ParseException{
		   String dateString = yearFormat.format(currentTime);
		   return dateString;
	}
	
	public static String getHourMis(Date currentTime) throws ParseException{
		
		   String dateString = timeFormat.format(currentTime);
		   return dateString;
	}
	public static String getHourMisSS(Date currentTime) throws ParseException{
		
		   String dateString = timeFormats.format(currentTime);
		   return dateString;
	}
	/**
	 * 根据传入的字符串长度自动判断，解析成date类型
	 * @param dateString
	 * @return
	 */
	public static Date stringToDate(String dateString) {
		if (dateString == null || "".equals(dateString)) {
			return null;
		} else {
			return parseDate(dateString);
		}
	}
	
	/**
	 * String转化Date
	 * 
	 * @param dateString
	 * @param formatNum
	 * @return
	 */
	public static Date parseDate(String dateString) {
		Date date = null; 
		if ("".equals(dateString)) {
			return null;
		}
		try {
			if (dateString.length() == FORMAT_SCAPE_HMSS.length()) {
				date = dateFormat(6).parse(dateString);
			}
			if (dateString.length() == FORMAT_SCAPE_HMS.length()) {
				date = dateFormat(4).parse(dateString);
			}
			if (dateString.length() == FORMAT_SCAPE_HM.length()) {
				date = dateFormat(9).parse(dateString);
			}
			if (dateString.length() == FORMAT_LAND.length()) {
				date = dateFormat(3).parse(dateString);
			}
			if (dateString.length() == FORMAT.length()) {
				date = dateFormat(1).parse(dateString);
			}
		} catch (ParseException e) {
			// throw new RuntimeException("在将String类型转化为Date型发生错误。");
			return null;
		}
		return date;
	}
	
	/**
	 * 实例SimpleDateFormat
	 * 
	 * @param formatNum
	 * @return
	 */
	private static SimpleDateFormat dateFormat(int formatNum) {
		String key = formatNum + "";
		if (dateForMatObject.get(key) != null) {
			return dateForMatObject.get(key);
		} else {
			String formatString = dateForMatString.get(key);
			SimpleDateFormat f = new SimpleDateFormat(formatString);
			dateForMatObject.put(key, f);
			return f;
		}
	}
	/**
	 * 
	 * 比较一个指定的时间是否在指定的范围内，如果在返回true
	 * @param appointDate
	 * @param startDate
	 * @param endDate
	 * @return
	 * 
	 */
	public static boolean checkDateInBetweenDays(Date appointDate, Date startDate, Date endDate) {
		//比结束时间大
		if (compareDate(appointDate, endDate) < 0) {
			return false;
		}

		//比开始时间小
		if (compareDate(appointDate, startDate) > 0) {
			return false;
		}
		return true;
	}
    
	public static boolean checkEndDateDays(Date appointDate,  Date endDate) {
		//比结束时间大
		if (compareDate(appointDate, endDate) < 0) {
			return false;
		} 
		return true;
	}
    
	/**
	 * 比较两个日期的相差毫秒数,如果开始日期比结束日期早，则返回正数，否则返回负数。
	 * 
	 * @param start
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @return
	 */
	public static long compareDate(Date start, Date end) {
		long temp = 0;
		Calendar starts = Calendar.getInstance();
		Calendar ends = Calendar.getInstance();
		starts.setTime(start);
		ends.setTime(end);
		temp = ends.getTime().getTime() - starts.getTime().getTime();
		return temp;
	}
	
	 
	public static Date getNewDate() {
		return new Date();
	}
	 /**
     * 根据起始日期和间隔时间计算结束日期
     *
     * @param sDate开始时间
     *
     * @param days间隔时间
     *
     * @return 结束时间
     * */
    public static Date calculateEndDate(Date sDate, int days)
    {
        //将开始时间赋给日历实例
        Calendar sCalendar = Calendar.getInstance();
        sCalendar.setTime(sDate);
        //计算结束时间
        sCalendar.add(Calendar.DATE, days);
        //返回Date类型结束时间
        return sCalendar.getTime();
    }
    /**
     * 返回当时间的前一天
     * 
     */
    public static String getBeforeDate(int day){
    	Timestamp thisTime = new Timestamp(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(thisTime);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, day); // 设置为前一天
		Date dateend =calendar.getTime();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String str=format.format(dateend);
		String newStr =str.replaceAll("-", "");	
    	
		return newStr;
    }
     
    /**
     * 计算两个日期的时间间隔
     *
     * @param sDate开始时间
     *
     * @param eDate结束时间
     *
     * @param type间隔类型("Y/y"--年  "M/m"--月  "D/d"--日)
     *
     * @return interval时间间隔
     * */
    public static int calInterval(Date sDate, Date eDate, String type)
    {
        //时间间隔，初始为0
        int interval = 0;
         
        /*比较两个日期的大小，如果开始日期更大，则交换两个日期*/
        //标志两个日期是否交换过
        boolean reversed = false;
        if(compareDate(sDate, eDate) > 0)
        {
            Date dTest = sDate;
            sDate = eDate;
            eDate = dTest;
            //修改交换标志
            reversed = true;
        }
         
        /*将两个日期赋给日历实例，并获取年、月、日相关字段值*/
        Calendar sCalendar = Calendar.getInstance();
        sCalendar.setTime(sDate);
        int sYears = sCalendar.get(Calendar.YEAR);
        int sMonths = sCalendar.get(Calendar.MONTH);
        int sDays = sCalendar.get(Calendar.DAY_OF_YEAR);
         
        Calendar eCalendar = Calendar.getInstance();
        eCalendar.setTime(eDate);
        int eYears = eCalendar.get(Calendar.YEAR);
        int eMonths = eCalendar.get(Calendar.MONTH);
        int eDays = eCalendar.get(Calendar.DAY_OF_YEAR);
         
        //年
        if(cTrim(type).equals("Y") || cTrim(type).equals("y"))
        {
            interval = eYears - sYears;
            if(eMonths < sMonths)
            {
                --interval;
            }
        }
        //月
        else if(cTrim(type).equals("M") || cTrim(type).equals("m"))
        {
            interval = 12 * (eYears - sYears);
            interval += (eMonths - sMonths);
        }
        //日
        else if(cTrim(type).equals("D") || cTrim(type).equals("d"))
        {
            interval = 365 * (eYears - sYears);
            interval += (eDays - sDays);
            //除去闰年天数
            while(sYears < eYears)
            {
                if(isLeapYear(sYears))
                {
                    --interval;
                }
                ++sYears;
            }
        }
        //如果开始日期更大，则返回负值
        if(reversed)
        {
            interval = -interval;
        }
        //返回计算结果
        return interval;
    }
    
    /**
     * 判定某个年份是否是闰年
     *
     * @param year待判定的年份
     *
     * @return 判定结果
     * */
    private static boolean isLeapYear(int year)
    {
        return (year%400 == 0 || (year%4 == 0 && year%100 != 0));
    }
    
    /**
     * 当前时间毫秒数
     * @return
     */
    public static long randomDate(){
    	return System.currentTimeMillis();
    }
    /**
     * 转化为string类型
     * @return
     */
    public static String dateFormate(Date date){
    	SimpleDateFormat ffff = new SimpleDateFormat(FORMAT_SCAPE_HMS);
    	String time = ffff.format(date);
    	return time;
    }
    /**
    *
    * 字符串去除两头空格，如果为空，则返回""，如果不空，则返回该字符串去掉前后空格
    *
    * @param tStr输入字符串
    *
    * @return 如果为空，则返回""，如果不空，则返回该字符串去掉前后空格
    *
    */
    public static String cTrim(String tStr)
    {
        String ttStr = "";
        if (tStr == null)
        {}
        else
        {
            ttStr = tStr.trim();
        }
        return ttStr;
    }
    
    public static Date getDateAfterNDays(Date currentTime,int n){
        return  new java.util.Date((currentTime.getTime() + n * 24 * 60 * 60 * 1000));
    }
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat ffff = new SimpleDateFormat(FORMAT_SCAPE_HMS);
		System.out.println(ffff.format(new Date()));;
	}
	
	/** 
	*字符串的日期格式的计算 
	*/  
	    public static int daysBetween(String smdate,String bdate) throws ParseException{  
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(sdf.parse(smdate));    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(sdf.parse(bdate));    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	       return Integer.parseInt(String.valueOf(between_days));     
	    } 
	
}
