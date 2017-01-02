package com.wiresto.jaxrs.message.gson;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

@Provider
@Produces({MediaType.APPLICATION_JSON})
public class GsonMessageBodyWriter implements MessageBodyWriter<Object>{
	
	@Inject
	private Gson gson;
	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType,	Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public long getSize(Object t, Class<?> type, Type genericType,	Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(Object entity, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
		
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(entityStream, "UTF-8"));	
		gson.toJson(entity, type, writer);		
		writer.flush();		
	}	
	
	public void setGson(Gson gson) {
		this.gson = gson;
	}
	
	
	
}
