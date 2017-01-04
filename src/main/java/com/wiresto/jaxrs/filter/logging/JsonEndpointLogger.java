package com.wiresto.jaxrs.filter.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.wiresto.jaxrs.message.gson.StandardGsonFactory;

public class JsonEndpointLogger implements EndpointLogger {
	
	private static final Gson gson = StandardGsonFactory.create();
	
	private final Logger logger;
	
	public JsonEndpointLogger(String loggerName){
		logger = LoggerFactory.getLogger(loggerName);
	}

	@Override
	public void log(LogEntry entry) {
		logger.info(gson.toJson(entry));		
	}

}
