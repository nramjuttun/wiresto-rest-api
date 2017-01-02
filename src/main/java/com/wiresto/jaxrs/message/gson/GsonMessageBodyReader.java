package com.wiresto.jaxrs.message.gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

@Provider
@Consumes({MediaType.APPLICATION_JSON})
public class GsonMessageBodyReader implements MessageBodyReader<Object>{
	
	@Inject
	private Gson gson;
	
	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public Object readFrom(Class<Object> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		
		JsonReader reader = new JsonReader(new InputStreamReader(entityStream));
		return gson.fromJson(reader, type);		
	}	
	

	public void setGson(Gson gson) {
		this.gson = gson;
	}


	
	
	
}
