package com.wiresto.jaxrs.filter.logging;

import java.util.Date;

public class RequestStartTimeContext {
	
	private static ThreadLocal<Date> threadLocal = new ThreadLocal<Date>();
	
	public static void start()
	{
		threadLocal.set(new Date());
	}
	
	public static Date getStartTime()
	{
		return (Date)threadLocal.get();
		
	}
	
	public static void end()
	{
		threadLocal.remove();
		
	}

}
