package com.wiresto.jaxrs.filter.logging.access;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import com.wiresto.jaxrs.filter.logging.EndpointLogger;
import com.wiresto.jaxrs.filter.logging.LogEntry;
import com.wiresto.jaxrs.filter.logging.RequestStartTimeContext;

/**
 * Container response filter to log request/response details (e.g. user, http code, query params, etc..) around endpoints that are accessed.
 *
 */
@Provider
public class AccessLoggingFilter implements ContainerResponseFilter{
	
	@Context
	private ResourceInfo resourceInfo;	

	@Context
	private SecurityContext securityContext;		
	
	private EndpointLogger accessEndpointLogger;
	
	public AccessLoggingFilter(EndpointLogger accessEndpointLogger){
		this.accessEndpointLogger = accessEndpointLogger;
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

		Principal principal = securityContext.getUserPrincipal();
		String userName = (principal != null)? principal.getName(): null;
		
		Map<String, String> parameters = new HashMap<String, String>();
		MultivaluedMap<String, String> multiMap = requestContext.getUriInfo().getQueryParameters();
		for(String key: multiMap.keySet()) {
			parameters.put(key, multiMap.getFirst(key));
		}
		
		LogEntry entry = new LogEntry()
			.withEndpoint(requestContext.getUriInfo().getPath())
			.withEndTime(new Date())
			.withHttpCode(responseContext.getStatus())			
			.withOperation(requestContext.getMethod())
			.withParameters(parameters)
			.withStartTime(RequestStartTimeContext.getStartTime())
			.withThreadName(Thread.currentThread().getName())
			.withUser(userName);
		
		
		accessEndpointLogger.log(entry);		
	}

}
