package tools;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServerPortNamePropertyTable {

	private StringProperty serverName;
	private StringProperty portName;
	
	
	public String getServerName() {
		 return serverName.get();
	}
	public void setServernName(String serverName) {
		this.serverName = new SimpleStringProperty(serverName);
	}
	public StringProperty serverNameProperty() {
		return serverName;
	}
	
	
	public String getPortName() {
		return portName.get();
	}
	public void setPortName(String portName) {
		this.portName = new SimpleStringProperty(portName);
	}
	public StringProperty portNameProperty() {
		return portName;
	}
}
