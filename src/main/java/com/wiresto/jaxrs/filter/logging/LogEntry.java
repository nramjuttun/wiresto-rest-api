package com.wiresto.jaxrs.filter.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;

public class LogEntry {
	private Date startTime;
	private Date endTime;
	private String threadName;
	private String endpoint;
	private String operation;
	private String user;
	private Integer httpCode;
	private Map<String, String> parameters;
	private String exceptionType;
	private String exceptionMessage;
	private String exceptionStackTrace;

	public LogEntry withStartTime(Date startTime) {
		this.startTime = startTime;
		return this;
	}

	public LogEntry withEndTime(Date endTime) {
		this.endTime = endTime;
		return this;
	}

	public LogEntry withThreadName(String threadId) {
		this.threadName = threadId;
		return this;
	}

	public LogEntry withEndpoint(String endpoint) {
		this.endpoint = endpoint;
		return this;
	}

	public LogEntry withOperation(String operation) {
		this.operation = operation;
		return this;
	}

	public LogEntry withUser(String user) {
		this.user = user;
		return this;
	}

	public LogEntry withHttpCode(Integer httpCode) {
		this.httpCode = httpCode;
		return this;
	}
	
	public LogEntry withParameters(Map<String, String> parameters) {
		this.parameters = parameters;
		return this;
	}
	
	public LogEntry withExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
		return this;
	}


	public LogEntry withExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
		return this;
	}


	public LogEntry withExceptionStackTrace(String exceptionStackTrace) {
		this.exceptionStackTrace = exceptionStackTrace;
		return this;
	}	
	
	public LogEntry withExceptionStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));
		this.exceptionStackTrace =  sw.toString();	
		
		return this;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getThreadName() {
		return threadName;
	}
	public void setThreadName(String threadId) {
		this.threadName = threadId;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Integer getHttpCode() {
		return httpCode;
	}
	public void setHttpCode(Integer httpCode) {
		this.httpCode = httpCode;
	}	
	public Map<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getExceptionStackTrace() {
		return exceptionStackTrace;
	}

	public void setExceptionStackTrace(String exceptionStackTrace) {
		this.exceptionStackTrace = exceptionStackTrace;
	}
	
}

