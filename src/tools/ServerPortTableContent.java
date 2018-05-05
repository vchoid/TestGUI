package tools;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServerPortTableContent {

	private StringProperty port;
	private StringProperty server;
	private StringProperty createdON;
	private StringProperty connection;
	private StringProperty updatedON;

	
	public ServerPortTableContent(ServerPortConnection sp, ServerPortConnectionQuery spQuery) {
		this.port = new SimpleStringProperty(sp.getPort());
		this.server = new SimpleStringProperty(sp.getServer());
		this.createdON = new SimpleStringProperty(sp.getCreatedON());
		this.connection = new SimpleStringProperty(spQuery.getIsConnected());
		this.updatedON = new SimpleStringProperty(spQuery.getUpdatedON());
	}
	
	// #########################################################################
	// ## Getter und Setter ####################################################
	// #########################################################################
	// << Port >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getPort() {
		return port.get();
	}
	// << Server >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getServer() {
		return server.get();
	}
	// << CreatedON >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getCreatedON() {
		return createdON.get();
	}
	// << connceted >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getConnection() {
		return connection.get();
	}

	// << updatedON >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getUpdatedON() {
		return updatedON.get();
	}
	
	
}
