package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JSONFileInitialisator {

	// --> Datei-Handling ------------------------------------------------------
	private Gson gson = new Gson();
	private final static String FILE = System.getProperty("user.dir")
			+ "/src/resources/Server_Ports.JSON";
	private BufferedReader reader;
	private FileInputStream input;

	private JsonObject jsonObj;

	private EmptyPortServerTemplate emptyPSTemplate = new EmptyPortServerTemplate();

	private BufferedWriter writer;
	private FileOutputStream out;

	private JsonArray portsArray;
	private JsonArray serverArray;

	private String excMessage;
	
	public void init() {
		parseFileAsJSONObject();
		setPortServerValuesInAList();
	}
	
	/**
	 * Ließt Datei ein und speichern den Inhalt in ein {@link JsonObject}}.
	 */
	private void parseFileAsJSONObject() {
		// Datei über einen Stream einlesen
		try {
			input = new FileInputStream(getFile());
			reader = new BufferedReader(new InputStreamReader(input));
			// Datei als JSON-Objekt einlesen
			setJsonObj(gson.fromJson(reader, JsonObject.class));
			reader.close();
		} catch (IOException e) {
			addEmptyJsonFileTemplate();
			init();
		}
	}

	/**
	 * Vorlageninhalt für leere JSON-Datei.
	 */
	private void addEmptyJsonFileTemplate() {
		writeInFile(emptyPSTemplate.getPortServerTemplate());
		setExcMessage(emptyPSTemplate.getMessage());
	}
	/**
	 * Schreibt Inhalt(Parameter content) in der JSON-Datei und schließt den
	 * Writer.
	 * 
	 * @param content
	 */
	private void writeInFile(String content) {
		try {
			out = new FileOutputStream(getFile());
			writer = new BufferedWriter(new OutputStreamWriter(out));
			writer.write(content);
			// TODO l�schen!
			setExcMessage("... gespeichert in Datei.");
			System.out.println("... gespeichert in Datei.");
			writer.close();
			System.out.println(" >> 'writer' geschlossen!");
		} catch (IOException e) {
			setExcMessage(e.toString());
		}
	}

	/**
	 * Speichert Werte aus den Server- und Port-Arrays aus der JSON-Datei in
	 * Arrays.
	 * 
	 */
	private void setPortServerValuesInAList() {
		// Ports-Array aus JSONFile speichern
		setPortsArray(getJsonObj().getAsJsonArray("ports"));
		// Server-Array aus JSONFile speichern
		setServerArray(getJsonObj().getAsJsonArray("server"));

	}

	// #########################################################################
	// ## Getter und Setter ####################################################
	// #########################################################################

	// --> JSON-Handling -------------------------------------------------------
	public JsonObject getJsonObj() {
		return jsonObj;
	}
	public void setJsonObj(JsonObject json) {
		this.jsonObj = json;
	}
	// --> Datei-Handling ------------------------------------------------------
	public static String getFile() {
		return FILE;
	}
	// --> Array-Handling ------------------------------------------------------
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
	// --> Message-Handling ----------------------------------------------------
	public String getExcMessage() {
		return excMessage;
	}
	public void setExcMessage(String excMessage) {
		this.excMessage = excMessage;
	}
}
