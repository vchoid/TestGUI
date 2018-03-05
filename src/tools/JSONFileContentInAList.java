package tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JSONFileContentInAList extends JSONFileContentHandler{

	// #########################################################################
	// ## Variablen 														   #
	// #########################################################################
	
	// --> Listen für View -----------------------------------------------------
	private ObservableList<String> portList = FXCollections.observableArrayList();
	private ObservableList<String> portNameList = FXCollections.observableArrayList();
	private ObservableList<String> serverNameList =FXCollections.observableArrayList();
	private ObservableList<String> ipList = FXCollections.observableArrayList();
	private ObservableList<String> hostList = FXCollections.observableArrayList();
	
	// #########################################################################
	// ## Initialisieren 													   #
	// #########################################################################
	
	public JSONFileContentInAList() {
//		saveLists();
	}
	
	public void saveLists() {
		saveServerValuesInAList();
		savePortValuesInAList();
	}
	// #########################################################################
	// ## Daten als für View												   #
	// #########################################################################

	/**
	 * Holt Werte eines Arrays über den Key und speichert diese in eine neue
	 * ArrayList.
	 * 
	 * @param array
	 * @param key
	 * @param list
	 */
	private void saveValuesInArray(JsonArray array, String key,
			ObservableList<String> list) {
		for (int i = 0; i < array.size(); i++) {
			JsonObject temp = array.get(i).getAsJsonObject();
			JsonElement tempE = temp.get(key);
			String tempS = tempE.getAsString();
			list.add(tempS);
		}
	}
	/**
	 * Speichert folgende Werte aus den Server-Arrays in neue Listen.
	 * 
	 * <p>
	 * <b>Werte:</b>
	 * <ul>
	 * <li><b>Servername als String</b></li>
	 * <li><b>IP-Adressen als String</b></li>
	 * <li><b>Host als String</b></li>
	 * </ul>
	 * </p>
	 */
	public void saveServerValuesInAList() {
		// Servername in neue List speichern
		saveValuesInArray(getServerArray(), "name", serverNameList);
		// IP-Adressen aus Server-Array in neue List speichern
		saveValuesInArray(getServerArray(), "ip", ipList);
		// Host-Adressen aus Server-Array in neue List speichern
		saveValuesInArray(getServerArray(), "host", hostList);
	}
	/**
	 * Speichert folgende Werte aus den Port-Arrays in neue Listen.
	 * 
	 * <p>
	 * <b>Werte:</b>
	 * <ul>
	 * <li><b>Portname als String</b></li>
	 * <li><b>Port als Integer</b></li>
	 * </ul>
	 * </p>
	 */
	public void savePortValuesInAList() {
		// die Port-Werte in neue Liste speichern
		saveValuesInArray(getPortsArray(), "port", portList);
		// die Port-Werte in neue Liste speichern
		saveValuesInArray(getPortsArray(), "name", portNameList);
	}

	
	// #########################################################################
	// ## Getter und Setter ####################################################
	// #########################################################################
	// --> Daten für die View --------------------------------------------------
	
	public ObservableList<String> getHostList() {
		return hostList;
	}
	public void setHostList(ObservableList<String> hostList) {
		this.hostList = hostList;
	}
	public ObservableList<String> getIpList() {
		return ipList;
	}
	public ObservableList<String> getServerNameList() {
		return serverNameList;
	}
	public ObservableList<String> getPortList() {
		return portList;
	}
	public ObservableList<String> getPortNameList() {
		return portNameList;
	}
	// --> Array Iteration -----------------------------------------------------

	

}
