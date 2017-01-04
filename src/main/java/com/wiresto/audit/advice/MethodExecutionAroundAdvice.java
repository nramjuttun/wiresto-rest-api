package com.wiresto.audit.advice;

import java.lang.reflect.Method;
import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.wiresto.audit.MethodStatsAudit;
import com.wiresto.audit.MethodStatsEntry;

/**
 * Simple around advice that audits method details.  The logging is delagated to an audit object. It could be a DB audit or file-based audit or something else.
 *
 */
public class MethodExecutionAroundAdvice implements MethodInterceptor{	
	
	private MethodStatsAudit audit;
	
	public MethodExecutionAroundAdvice(MethodStatsAudit audit){
		this.audit = audit;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {	
		
		Date startTime = new Date();
		
		try {
			// proceed to original method call
			Object result = invocation.proceed();
			
			// log method stats
			log(invocation.getMethod(), startTime, new Date());
			
			//important!
			return result;

		} catch (Throwable e) {
			throw e;
		}
		
	}
	
	private void log(Method m, Date startTime, Date endTime){
		MethodStatsEntry entry = new MethodStatsEntry();
		entry.setName(m.getName());
		entry.setClassName(m.getDeclaringClass().getName());
		entry.setStartTime(startTime);
		entry.setEndTime(endTime);
		entry.setDurationMS(endTime.getTime() - startTime.getTime());
		
		audit.log(entry);
	}

}
