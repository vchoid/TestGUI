package tools;

import date.DateStamp;

public class ServerPortConnection {

	private DateStamp ds = new DateStamp();
	private String server;
	private String port;
	private String createdON;

	public ServerPortConnection(String server, String port) {
		this.server = server;
		this.port = port;
		this.createdON = ds.getDateTimeStamp();
	}
	
	// #########################################################################
	// ## Getter und Setter ####################################################
	// #########################################################################

	// << Server >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	
	
	// << Port >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	

	// << Date >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getCreatedON() {
		return createdON;
	}
	public void setCreatedON(String createdON) {
		this.createdON = createdON;
	}
	 
}
