package tools;

import date.DateStamp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServerPortConnectionQuery {

	private DateStamp ds = new DateStamp();
	private String isConnected;
	private String updatedON;

	public ServerPortConnectionQuery(String isConnected) {
		this.isConnected = isConnected;
		this.updatedON = ds.getDateTimeStamp();
	}

	// #########################################################################
	// ## Getter und Setter ####################################################
	// #########################################################################
	// << isConnected >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getIsConnected() {
		return isConnected;
	}
	public void setIsConnected(String isConnected) {
		this.isConnected = isConnected;
	}
	// << UpdatedON >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getUpdatedON() {
		return updatedON;
	}
	
}
