package com.wiresto.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.wiresto.jaxrs.message.gson.StandardGsonFactory;

/**
 * Simple audit that logs method stats to file in JSON format
 *
 */
public class JsonMethodStatsAudit implements MethodStatsAudit {
	
	private static final Gson gson = StandardGsonFactory.create();
	
	private final Logger logger;
	
	private JsonMethodStatsAudit(String loggerName){
		logger = LoggerFactory.getLogger(loggerName);
	}

	@Override
	public void log(MethodStatsEntry entry) {
		logger.info(gson.toJson(entry));		
	}

}
