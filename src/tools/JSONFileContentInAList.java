package tools;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JSONFileContentInAList {

	// #########################################################################
	// ## Variablen 														   #
	// #########################################################################
	
	private JSONFileContentHandler jfh = new JSONFileContentHandler();
	// --> Listen für View -----------------------------------------------------
	private ArrayList<String> portList = new ArrayList<>();
	private ArrayList<String> portNameList = new ArrayList<>();
	private ArrayList<String> serverNameList = new ArrayList<>();
	private ArrayList<String> ipList = new ArrayList<>();
	private ArrayList<String> hostList = new ArrayList<>();
	
	// #########################################################################
	// ## Initialisieren 													   #
	// #########################################################################
	
	public JSONFileContentInAList() {
		init();
	}
	
	public void init() {
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
			ArrayList<String> list) {
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
		saveValuesInArray(jfh.getServerArray(), "name", serverNameList);
		// IP-Adressen aus Server-Array in neue List speichern
		saveValuesInArray(jfh.getServerArray(), "ip", ipList);
		// Host-Adressen aus Server-Array in neue List speichern
		saveValuesInArray(jfh.getServerArray(), "host", hostList);
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
		saveValuesInArray(jfh.getPortsArray(), "port", portList);
		// die Port-Werte in neue Liste speichern
		saveValuesInArray(jfh.getPortsArray(), "name", portNameList);
	}

	
	// #########################################################################
	// ## Getter und Setter ####################################################
	// #########################################################################
	// --> Daten für die View --------------------------------------------------
	
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
	// --> Array Iteration -----------------------------------------------------

	

}
