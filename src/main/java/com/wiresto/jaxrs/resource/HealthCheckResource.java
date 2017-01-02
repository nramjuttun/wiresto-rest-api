package com.wiresto.jaxrs.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/health")
public class HealthCheckResource {
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})	
	public HealthStatus status(){

		HealthStatus hs = new HealthStatus();
		
		hs.setServerOK(true);
		hs.setDbConnectionOK(true);
		hs.setFileManagerOK(true);

		return hs;
	}
	
	private class HealthStatus{
		private boolean serverOK; 
		private boolean dbConnectionOK;
		private boolean fileManagerOK;
		
		public boolean isServerOK() {
			return serverOK;
		}
		public void setServerOK(boolean serverOK) {
			this.serverOK = serverOK;
		}
		public boolean isDbConnectionOK() {
			return dbConnectionOK;
		}
		public void setDbConnectionOK(boolean dbConnectionOK) {
			this.dbConnectionOK = dbConnectionOK;
		}
		public boolean isFileManagerOK() {
			return fileManagerOK;
		}
		public void setFileManagerOK(boolean fileManagerOK) {
			this.fileManagerOK = fileManagerOK;
		}		
		
	}

}
