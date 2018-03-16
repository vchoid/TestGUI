package tools;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServerPortTableContent {

	private StringProperty port;
	private StringProperty server;
	private StringProperty createdON;
	

	
	public ServerPortTableContent(String port, String server, String isConnected,
			String createdON, String updatedON) {
		this.port = new SimpleStringProperty(port);
		this.server = new SimpleStringProperty(server);
		this.createdON = new SimpleStringProperty(createdON);
	}
	
	// #########################################################################
	// ## Getter und Setter ####################################################
	// #########################################################################
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
	
}
