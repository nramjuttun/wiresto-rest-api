package com.wiresto;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.resteasy.plugins.spring.SpringContextLoaderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wiresto.jaxrs.filter.logging.RequestStartTimeFilter;

public class Main {
	
	private static final Logger log = LoggerFactory.getLogger(Main.class);	
	
	private static final int DEFAULT_HTTP_PORT = 5000;
	private static final String DEFAULT_HOST = "localhost";
	
	private static final String REST_PATH_ROOT_CONTEXT = "/wiresto";
	

	public static void main(String[] args) throws ServletException {		

		log.info("Building wiResto REST API on Undertow server....");
		
		int httpPort = getHttpPort();		 
		String host = getHost();
		
		Undertow server = Undertow.builder()
								.addHttpListener(httpPort, host)
								.setHandler(createServletHandler())
								.build();
		
		addShutDownHook(server);
		
		server.start();
		
		log.info("wiResto REST API Running on Undertow server @ http://"+host+":"+httpPort+getRootContext());

	}
	
	private static int getHttpPort(){
		String httpPortStr = System.getProperty("http.port");
		
		Integer value=null;
		try{
			value = Integer.valueOf(httpPortStr);
		}catch(NumberFormatException nfe){
			
		}
		
		return (value==null || value<0)?DEFAULT_HTTP_PORT:value;
	}
	
	private static String getHost(){
		return DEFAULT_HOST;
	}
	
	private static String getRootContext(){
		return REST_PATH_ROOT_CONTEXT;
	}
	
	private static HttpHandler createServletHandler() throws ServletException {
	   	DeploymentInfo servletBuilder = Servlets.deployment()    
	   			.setClassLoader(Main.class.getClassLoader())
    	        .setDeploymentName("wiresto-rest-api")
    	        .setContextPath(getRootContext())
    	        .addInitParameter("contextConfigLocation", "classpath:spring/application.xml")
    	        .addServlets(
    	                Servlets.servlet("Undertow Rest Service", HttpServletDispatcher.class)
                        .addMapping("/*")
    	        )
    	        
    	        .addFilter(Servlets.filter("requestStartTimeFilter", RequestStartTimeFilter.class))
    	        .addFilterUrlMapping("requestStartTimeFilter", "/*", DispatcherType.REQUEST)    	     
    	        
    	        .addListeners(
       	        		Servlets.listener(ResteasyBootstrap.class),
    	        		Servlets.listener(SpringContextLoaderListener.class)
    	        );

	   	
	   	DeploymentManager deploymentManager = Servlets.defaultContainer().addDeployment(servletBuilder);
        deploymentManager.deploy();		
	
        HttpHandler httpHandler = deploymentManager.start(); 
        
        log.info("Sucessfully created HTTP handler for servlet with root context path: " + getRootContext());
        
        return httpHandler;
	} 
	
	private static void addShutDownHook(Undertow server){		
		Runtime.getRuntime().addShutdownHook(new Thread(()->{server.stop();},
				Thread.currentThread().getClass().getCanonicalName()+ "_ShutdownHook"));		
	}

}
