package com.wiresto.audit;

import java.io.Serializable;
import java.util.Date;

public class MethodStatsEntry implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String className;

	private Date startTime;
	
	private Date endTime;	
	
	private long durationMS;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public long getDurationMS() {
		return durationMS;
	}

	public void setDurationMS(long durationMS) {
		this.durationMS = durationMS;
	}
	
	

}
