package tools;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JSONFileContentInAList {

	// ## Variablen ############################################################
	private JSONFileHandler jfh;

	// --> Listen für View -----------------------------------------------------
	private ArrayList<String> portList = new ArrayList<>();
	private ArrayList<String> portNameList = new ArrayList<>();
	private ArrayList<String> serverNameList = new ArrayList<>();
	private ArrayList<String> ipList = new ArrayList<>();
	private ArrayList<String> hostList = new ArrayList<>();
	private ArrayList<ArrayList<String>> connectArray;
	private ArrayList<String> portConnArray;
	private String serverName = "";
	private String portName = "";
	private String host = "";

	

	
	// #########################################################################
	// ## Initialisieren 													   #
	// #########################################################################
	public JSONFileContentInAList() {
		setPortServerValuesInAList();
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
			ArrayList<String> list) {
		for (int i = 0; i < array.size(); i++) {
			JsonObject temp = array.get(i).getAsJsonObject();
			JsonElement tempE = temp.get(key);
			String tempS = tempE.getAsString();
			list.add(tempS);
		}
	}
	/**
	 * Speichert folgende Werte aus den Server- und Port-Arrays in neue Listen.
	 * 
	 * <p>
	 * <b>Werte:</b>
	 * <ul>
	 * <li><b>Port als Integer</b></li>
	 * <li><b>Servername als String</b></li>
	 * <li><b>ServerIP als String</b></li>
	 * </ul>
	 * </p>
	 */
	private void setPortServerValuesInAList() {
		// die Port-Werte in neue Liste speichern
		saveValuesInArray(jfh.getPortsArray(), "port", portList);
		// die Port-Werte in neue Liste speichern
		saveValuesInArray(jfh.getPortsArray(), "name", portNameList);
		// Servername in neue List speichern
		saveValuesInArray(jfh.getServerArray(), "name", serverNameList);
		// IP-Adressen aus Server-Array in neue List speichern
		saveValuesInArray(jfh.getServerArray(), "ip", ipList);
		// Host-Adressen aus Server-Array in neue List speichern
		saveValuesInArray(jfh.getServerArray(), "host", hostList);
	}
	
	

	
	// #########################################################################
	// ## Getter und Setter ####################################################
	// #########################################################################
	// --> Daten für die View --------------------------------------------------
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public ArrayList<String> getHostList() {
		return hostList;
	}
	public void setHostList(ArrayList<String> hostList) {
		this.hostList = hostList;
	}
	public ArrayList<String> getIpList() {
		return ipList;
	}
	public ArrayList<String> getServerNameList() {
		return serverNameList;
	}
	public ArrayList<String> getPortList() {
		return portList;
	}
	public ArrayList<String> getPortNameList() {
		return portNameList;
	}
	public ArrayList<ArrayList<String>> getConnectArray() {
		return connectArray;
	}
	
	public ArrayList<String> getPortConnArray() {
		return portConnArray;
	}
	public void setPortConnArray(ArrayList<String> portConnArray) {
		this.portConnArray = portConnArray;
	}
	// --> Array Iteration -----------------------------------------------------

	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
	}
	

}
