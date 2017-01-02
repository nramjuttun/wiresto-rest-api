package com.wiresto.jaxrs.message.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StandardGsonFactory {
	
	public static Gson create() {
		return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
	}
}
