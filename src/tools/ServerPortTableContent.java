package tools;

import javafx.beans.property.StringProperty;

public class ServerPortTableContent {

	private StringProperty port;
	private StringProperty server;
	private StringProperty isConnected;
	private StringProperty createdON;
	private StringProperty updatedON;

	// #########################################################################
	// ## Getter und Setter ####################################################
	// #########################################################################
	
	public ServerPortTableContent(StringProperty port, StringProperty server, StringProperty isConnected,
			StringProperty createdON, StringProperty updatedON) {
		super();
		this.port = port;
		this.server = server;
		this.isConnected = isConnected;
		this.createdON = createdON;
		this.updatedON = updatedON;
	}
	
	
	// << Port >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getPort() {
		return port.get();
	}
	public void setPort(String port) {
		this.port.set(port);
	}
	public StringProperty portProperty() {
		return port;
	}

	// << Server >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getServer() {
		return server.get();
	}
	public void setServer(String server) {
		this.server.set(server);
	}
	public StringProperty serverProperty() {
		return server;
	}
	// << isConnected >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getIsConnected() {
		return isConnected.get();
	}
	public void setIsConnected(String isConnected) {
		this.isConnected.set(isConnected);
	}
	public StringProperty isConnectedProperty() {
		return isConnected;
	}
	
	// << CreatedON >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getCreatedON() {
		return createdON.get();
	}

	public void setCreatedON(String createdON) {
		this.createdON.set(createdON);
	}
	public StringProperty createdONProperty() {
		return createdON;
	}
	
	// << UpdatedON >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getUpdatedON() {
		return updatedON.get();
	}
	public void setUpdatedON(String updatedON) {
		this.updatedON.set(updatedON);
	}
	public StringProperty updatedONProperty() {
		return updatedON;
	}
}
