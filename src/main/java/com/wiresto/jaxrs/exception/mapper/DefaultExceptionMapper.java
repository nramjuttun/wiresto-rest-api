package com.wiresto.jaxrs.exception.mapper;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.google.gson.JsonSyntaxException;
import com.wiresto.jaxrs.filter.logging.EndpointLogger;
import com.wiresto.jaxrs.filter.logging.LogEntry;
import com.wiresto.jaxrs.filter.logging.RequestStartTimeContext;

/**
 * Generic mapper to translate exceptions to JAX-RS response.
 * 
 * Returned response will be of standard form {code: int, msg: string}
 * <br>
 * Client triggred errors such as invalid request, etc.. will return a 4XX HTTP response code
 * <br>
 * Unexpected exceptions will be logged in an error.log and returna 500 HTTP response code
 */

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Exception> {	
	
	@Context
	private ResourceInfo resourceInfo;
	
	@Context
	private Request request;
	
	@Context
	private UriInfo uriInfo;
	
	@Context
	private SecurityContext securityContext;	
	
	private EndpointLogger errorEndpointLogger;
	
	public DefaultExceptionMapper(EndpointLogger errorEndpointLogger){
		this.errorEndpointLogger = errorEndpointLogger;
	}

	@Override
	public Response toResponse(Exception exception) {
		try {
			Response response = handle(exception);
			if(response != null) {
				return response;
			} else {
				return Response.serverError().build();
			}
		} catch(Throwable t) {
			return Response.serverError().build();
		}
	}

	private Response handle(Exception exception){
		
		if(exception instanceof NotFoundException){
			Status status = Status.NOT_FOUND;
			return getResponse(status, status.getStatusCode(), "Resource not found");			
		}
		
		if(exception instanceof NotAllowedException){		
			Status status = Status.METHOD_NOT_ALLOWED;
			return getResponse(status, status.getStatusCode(), "Method not supported");			
		}
		
		if(exception instanceof NotSupportedException){		
			Status status = Status.UNSUPPORTED_MEDIA_TYPE;
			return getResponse(status, status.getStatusCode(), "Media type not supported or invalid");			
		}
		
		if(exception instanceof JsonSyntaxException){	
			Status status = Status.BAD_REQUEST;
			return getResponse(status, status.getStatusCode(), "JSON payload is invalid");		
		}
		
		handleInternalError(exception);
		
		return null;
		
	}
	
	private class ErrorResponse{
		private int code;
		private String msg;
		
		private ErrorResponse(int code, String msg){
			this.code = code;
			this.msg = msg;
		}
	}
	
	private Response getResponse(Status status, int code, String msg){
		return Response.status(status)
				.entity(new ErrorResponse(code, msg))
				.type(MediaType.APPLICATION_JSON_TYPE)					
				.build();
	}
	
	private Response handleInternalError(Exception ex){
		Status status = Status.INTERNAL_SERVER_ERROR;
		errorEndpointLogger.log(getEntry(ex, status));
		return getResponse(status, status.getStatusCode(), "Internal error occurred. Contact admin.");		
	}
	
	private LogEntry getEntry(Throwable t, Status status) {
		Principal principal = securityContext.getUserPrincipal();
		String userName = (principal != null)? principal.getName(): null;
		
		Map<String, String> parameters = new HashMap<String, String>();
		MultivaluedMap<String, String> multiMap = uriInfo.getQueryParameters();
		for(String key: multiMap.keySet()) {
			parameters.put(key, multiMap.getFirst(key));
		}
		
		return new LogEntry()
			.withEndpoint(uriInfo.getPath())
			.withEndTime(new Date())
			.withHttpCode(status.getStatusCode())
			.withOperation(request.getMethod())			
			.withParameters(parameters)
			.withStartTime(RequestStartTimeContext.getStartTime())
			.withThreadName(Thread.currentThread().getName())
			.withUser(userName)
			.withExceptionType(t.getClass().getCanonicalName())
			.withExceptionMessage(t.getMessage())
			.withExceptionStackTrace(t);		
	}
}
