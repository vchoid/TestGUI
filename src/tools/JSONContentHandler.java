package tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

//TODO syso -> löschen								
//TODO regEx -> bei add und edit Funktionen machen	
//TODO Funktionsnamen ggf. anpassen					
//TODO jUnit Tests schreiben 						!
//TODO Ecxeption Handling 							!

/**
 * 
 * Inhalte aus der Server_Ports.JSON wird zur Weiterverarbeitung vorbereitet.
 * 
 * Zusätzlich stellt die Klasse Methoden zum Schreiben, ändern und Löschen der
 * Daten bereit.</br>
 * 
 * <p>
 * <b>Methoden:</b>
 * <ul>
 * <li>Einen Porteintrag hinzufügen:
 * <b>{@link #addPort(String, String)}</b></li>
 * <li>Einen Porteintrag löschen: <b>{@link #deletePort(String)}</b></li>
 * <li>Einen Servereintrag via Host hinzufügen:
 * <b>{@link #addServerViaHost(String, String)}</b></li>
 * <li>Einen Servereintrag via IP hinzufügen:
 * <b>{@link #addServerViaIP(String, String)}</b></li>
 * <li>Einen Servereintrag bearbeiten:
 * </ul>
 * </p>
 * 
 * @author Christoph Kiank
 * @version 1.0.0
 */

public class JSONContentHandler extends JSONFileInitialisator {

	// ## Variablen ############################################################

	// --> json-Handling -------------------------------------------------------
	private JsonObject newPort = new JsonObject();
	private JsonObject newServer = new JsonObject();
	private JsonArray portsArray;
	private JsonArray serverArray;
	private int positionInArray;
	private JsonElement searchElement;
	private Boolean success = false;

	// #########################################################################
	// ## Prüfen auf validen Inhalt ############################################
	// #########################################################################

	/**
	 * Sucht einen Wert anhand des gesetzten Parameters im Array.
	 * 
	 * JSONArray wird durchlaufen, jeder Eintrag wird temporär gespeichert und
	 * überprüft ob der gesuchte Parameter sich im Array befindet. Wenn ja,
	 * speichere den Eintrag selbst mit der
	 * {@link #setSearchElement(JsonElement)}-Methode und die Position mit der
	 * {@link #setPositionInArray(int)}-Methode, wo sich der Eintrag im Array
	 * befindet und dann gib true zurück, ansonsten false.
	 * 
	 * 
	 * @param value
	 * @return
	 */
	private Boolean isValueInArray(JsonArray array, String key, String value) {
		JsonObject temp = new JsonObject();
		for (int i = 0; i < array.size(); i++) {
			temp = array.get(i).getAsJsonObject();
			JsonElement keyTemp = temp.get(key);
			if (keyTemp.getAsString().equalsIgnoreCase(value)) {
				// das ganze Element gesetzt
				setSearchElement(array.get(i));
				// die Position des Elements im Array gesetzt
				setPositionInArray(i);
				// TODO l�schen!
				System.out.print("#" + getPositionInArray() + " " + getSearchElement());
				return true;
			}
		}
		return false;
	}

	/**
	 * Prüft mit der {@link #isValueInArray(JsonArray, String, String)}-Methode, ob
	 * der Port bereits im Array vorhanden ist. Wenn ja dann gib ein true zurück.
	 * 
	 * @param name
	 * @param port
	 * @return
	 */
	private Boolean isPortAvailable(Port port) {
		if (isValueInArray(getPortsArray(), "port", "" + port.getPort())
				|| isValueInArray(getPortsArray(), "name", port.getName())) {
			setExcMessage("... bereits vorhanden");
			// TODO l�schen !
			System.out.print("\n  -> vorhanden");
			return true;
		}
		return false;

	}

	/**
	 * Prüft mit der {@link #isValueInArray(JsonArray, String, String)}-Methode, ob
	 * der Server bereits im Array vorhanden ist. Wenn ja dann gib ein true zurück.
	 * 
	 * @param name
	 * @param ipOrHost
	 * @return
	 */
	private Boolean isServerAvailable(Server server) {
		if (isValueInArray(getServerArray(), "ip", server.getIp())
				|| isValueInArray(getServerArray(), "name", server.getName())
				|| isValueInArray(getServerArray(), "host", server.getHost())) {
			setExcMessage("... bereits vorhanden");
			// TODO l�schen !
			System.out.print("\n  -> vorhanden");
			return true;
		}
		return false;
	}
//TODO Methoden machen
	private Boolean isQueryAvailable() {
		return true;
		
	}
	// #########################################################################
	// ## search-Methode #######################################################
	// #########################################################################
	/**
	 * Gibt einen Wert(getValueDoYouNeed) eines Array anhand des Wertes
	 * 'name'(byNameValue) zurück.
	 * 
	 * @param searchFromArray
	 * @param byNameValue
	 * @param getValueDoYouNeed
	 * @return
	 */
	public String searchValueByName(JsonArray searchFromArray, String byNameValue, String getValueDoYouNeed) {
		for (int i = 0; i < searchFromArray.size(); i++) {
			JsonObject temp = searchFromArray.get(i).getAsJsonObject();
			JsonElement keyTemp = temp.get("name");
			if (keyTemp.getAsString().equalsIgnoreCase(byNameValue)) {
				JsonObject tempObj = (JsonObject) searchFromArray.get(i);
				String val = tempObj.get(getValueDoYouNeed).toString().replace("\"", "");
				return val;
			}
		}
		return "";
	}

	// #########################################################################
	// ## add-Methode ##########################################################
	// #########################################################################
	/**
	 * Fügt ein neue Objekt in ein Array.
	 * 
	 * @param array
	 * @param newObject
	 */
	private void addNewObjectInArray(JsonArray array, JsonObject newObject) {
		// Objekt in Array anfügen
		array.add(newObject);
	}

	/**
	 * Holt das JSONObjekt mit der {@link #getJsonObj()}-Methode und schreibt mit
	 * der {@link #writeInFile(String)}-Methode das neue Objekt.
	 * 
	 * @param array
	 * @param key
	 */
	private void addNewArrayInJSONFile(JsonArray array, String key) {
		// Werte in Object anfügen
		getJsonObj().add(key, array);
		// verändertes Objekt als String in Datei schreiben
		writeInFile(getJsonObj().toString());
	}

	/**
	 * Fügt mit der {@link #addNewObjectInArray(JsonArray, JsonObject)}-Methode das
	 * Objekt in das Array ein und schreibt mit
	 * der{@link #addNewArrayInJSONFile(JsonArray, String)}-Methode das Objekt in
	 * die Datei.
	 * 
	 * 
	 * @param array
	 * @param newObject
	 * @param key
	 */
	private void addObjectInArrayAndWriteInFile(JsonArray array, JsonObject newObject, String key) {
		System.out.print(newObject);
		addNewObjectInArray(array, newObject);
		// TODO l�schen!
		System.out.print(" -> hinzugefügt zum Array");
		addNewArrayInJSONFile(array, key);
		// TODO
		// closeMethode
	}

	// --> Port ------------------------------------------------------------
	/**
	 * Fügt zwei Key-Value Paare für das PortsArray hinzu.
	 * 
	 * @param port
	 */
	private void addPortValues(Port port) {
		System.out.println("\n++++++++++++++ HINZUFÜGEN ++++++++++++++");
		newPort.addProperty("name", port.getName());
		newPort.addProperty("port", port.getPort());
	}

	/**
	 * Ein Port in die Server_Ports.JSON-Datei schreiben
	 * 
	 * {@link #addPortValues(Port)} fügt Werte dem Port-Objekt hinzu.
	 * 
	 * überprüft mit der {@link #isPortAvailable(Port)}-Methode, ob die Werte
	 * bereits in dem Port-Objekt vorhanden sind. Wenn nicht, werden die neue Werte
	 * mit der
	 * {@link #addObjectInArrayAndWriteInFile(JsonArray, JsonObject, String)}-Methode
	 * dem Array hinzugefüt und in die Datei geschrieben.
	 *
	 * 
	 * @param port
	 */
	public void addPort(Port port) {
		// neues Objekt mit zwei Key-Value-Paare anlegen
		addPortValues(port);
		// prüfen ob bereits vorhanden
		if (!isPortAvailable(port)) {
			// neuen validen Wert schreiben
			addObjectInArrayAndWriteInFile(getPortsArray(), newPort, "ports");
		}
	}

	// --> Server ----------------------------------------------------------
	/**
	 * Fügt drei Key-Value Paare für das Server-Array hinzu.
	 * 
	 * @param server
	 */
	private void addServerValues(Server server) {
		System.out.println("\n++++++++++++++ HINZUFüGEN ++++++++++++++");
		newServer.addProperty("name", server.getName());
		newServer.addProperty("host", server.getHost());
		newServer.addProperty("ip", server.getIp());
	}

	/**
	 * Ein Server in die Server_Ports.JSON-Datei schrieben
	 * 
	 * {@link #addServerValues(Server)} fügt Werte dem Server-Objekt hinzu.
	 * 
	 * überprüft mit der {@link #isServerAvailable(Server)}-Methode, ob die Werte
	 * bereits in dem Server-Objekt vorhanden sind. Wenn nicht, werden die neue
	 * Werte mit der
	 * {@link #addObjectInArrayAndWriteInFile(JsonArray, JsonObject, String)}-Methode
	 * dem Array hinzugefügt und in die Datei geschrieben.
	 * 
	 * @param server
	 */
	public void addServer(Server server) {
		// neues Objekt mit drei Key-Value-Paare anlegen
		addServerValues(server);
		// prüfen ob bereits vorhanden
		if (!isServerAvailable(server)) {
			// neuen validen Wert schreiben
			addObjectInArrayAndWriteInFile(getServerArray(), newServer, "server");
		}
	}

	// #########################################################################
	// ## delete Methoden ######################################################
	// #########################################################################
	/**
	 * Löscht einen Wert aus einem Array. überprüft mit der
	 * {@link #isValueInArray(JsonArray, String, String)}-Methode, ob der Wert im
	 * Array ist, wenn ja wird der Eintrag über die
	 * {@link #getPositionInArray()}-Methode ermittelt und aus dem Array entfernt.
	 * Der Wert Success wird auf true gesetzt.
	 * 
	 * @param value
	 * @param array
	 */
	private void removeValueFromArray(String value, JsonArray array) {
		System.out.println("\n+++++++++++++++ LÖSCHEN ++++++++++++++++");
		if (isValueInArray(array, "name", value)) {
			try {
				array.remove(getPositionInArray());
				// TODO l�schen!
				System.out.print("  -> wurde aus Array gel�scht");
				setSuccess(true);
			} catch (Exception e) {
			}
		} else {
			System.out.print(value);
			System.out.println("  -> nicht im Array vorhanden");
			setSuccess(false);
		}
	}

	/**
	 * Entfernt Werte aus einem Array mit der
	 * {@link #removeValueFromArray(String, JsonArray)}-Methode. Mit der
	 * {@link #getSuccess()}-Methode, wird überpr�ft, ob die
	 * {@link #removeValueFromArray(String, JsonArray)}-Methode erfolgreich war.
	 * Wenn ja wird das neue Array mit der
	 * {@link #addNewArrayInJSONFile(JsonArray, String)}-Methode in die Datei
	 * geschrieben.
	 * 
	 * @param array
	 * @param arrayInFile
	 * @param value
	 */
	private void deleteValuesFromArray(JsonArray array, String arrayInFile, String value) {
		removeValueFromArray(value, array);
		if (getSuccess()) {
			addNewArrayInJSONFile(array, arrayInFile);
		}
	}

	/**
	 * Löscht ein Port anhand des Parameters mit der
	 * {@link #deleteValuesFromArray(JsonArray, String, String)}-Methode.
	 * 
	 * @param name
	 */
	public void deletePort(String portName) {
		deleteValuesFromArray(getPortsArray(), "ports", portName);
		setExcMessage("... gelöscht");
	}

	/**
	 * Löscht ein Server anhand des Parameters mit der
	 * {@link #deleteValuesFromArray(JsonArray, String, String)}-Methode..
	 * 
	 * @param name
	 */
	public void deleteServer(String serverName) {
		deleteValuesFromArray(getServerArray(), "server", serverName);
		setExcMessage("... gelöscht");
	}

	// #########################################################################
	// ## Getter und Setter ####################################################
	// #########################################################################
	// --> Array-Handling ------------------------------------------------------

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public JsonElement getSearchElement() {
		return searchElement;
	}

	public void setSearchElement(JsonElement searchElement) {
		this.searchElement = searchElement;
	}

	public JsonArray getPortsArray() {
		return portsArray;
	}

	public void setPortsArray(JsonArray portsArray) {
		this.portsArray = portsArray;
	}

	public JsonArray getServerArray() {
		return serverArray;
	}

	public void setServerArray(JsonArray serverArray) {
		this.serverArray = serverArray;
	}

	public int getPositionInArray() {
		return positionInArray;
	}

	public void setPositionInArray(int positionInArray) {
		this.positionInArray = positionInArray;
	}

}
