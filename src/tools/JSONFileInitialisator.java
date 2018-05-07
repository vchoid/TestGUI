package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
	private File f;
	private BufferedReader reader;
	private FileInputStream input;
	private BufferedWriter writer;
	private FileOutputStream out;
	// --> Content-Handling ----------------------------------------------------
	private JsonObject jsonObj;
	private JsonArray settingsArray;
	private JsonArray portsArray;
	private JsonArray serverArray;
	private JsonArray queryArray;

	// --> Message-Handling ----------------------------------------------------
	private String excMessage;

	// #########################################################################
	// ## Initialisieren #######################################################
	// #########################################################################
	/**
	 * Initialisiert die Datei mit der {@link #parseFileAsJSONObject()}-Methode.
	 * Bereitet den Inhalt zur Weiterverabeitung mit folgenden Methoden auf.
	 * <p>
	 * <b>Methoden:</b>
	 * <ul>
	 * <li>Speichert Port und Server in jeweils ein JSONArray:
	 * <b>{@link #setValuesInAList()}</b></li>
	 * </ul>
	 * </p>
	 */
	public void init() {
		readFromFileParseFileAsJSONObject(getFile().toString());
		setValuesInAList();
	}

	public void setFile(File f) {
		this.f = f;
	}

	/**
	 * Ließt Datei ein und speichern den Inhalt in ein {@link JsonObject}}.
	 */
	public void readFromFileParseFileAsJSONObject(String file) {
		// Datei über einen Stream einlesen
		try {
			input = new FileInputStream(f);
			reader = new BufferedReader(new InputStreamReader(input));
			// Datei als JSON-Objekt einlesen
			setJsonObj(gson.fromJson(reader, JsonObject.class));
			reader.close();
			System.out.println("reader ...geschlossen!");
		} catch (IOException e) {
		}
	}

	/**
	 * Schreibt Inhalt(Parameter content) in der JSON-Datei und schließt den Writer.
	 */
	public void writeInFile(String content) {
		try {
			out = new FileOutputStream(getFile());
			writer = new BufferedWriter(new OutputStreamWriter(out));
			writer.write(content);
			// TODO löschen!
			setExcMessage(" ...gespeichert");
			System.out.println("...gespeichert");
			writer.close();
			System.out.println("writer ...geschlossen!");
		} catch (IOException e) {
			setExcMessage(e.toString());
		}
	}

	/**
	 * Speichert Werte aus den Server- und Port-Arrays aus der JSON-Datei in Arrays.
	 * 
	 */
	private void setValuesInAList() {
		// Settings-Array aus JSONFile speichern
		setSettingsArray(getJsonObj().getAsJsonArray("settings"));
		// Ports-Array aus JSONFile speichern
		setPortsArray(getJsonObj().getAsJsonArray("ports"));
		// Server-Array aus JSONFile speichern
		setServerArray(getJsonObj().getAsJsonArray("server"));
		// Query_array aus JSONFile speichern
		setQueryArray(getJsonObj().getAsJsonArray("querys"));
	}
	// #########################################################################
	// ## Getter und Setter ####################################################
	// #########################################################################

	// --> File-Handling -------------------------------------------------------
	public FileInputStream getInput() {
		return input;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public FileOutputStream getOut() {
		return out;
	}

	public BufferedWriter getWriter() {
		return writer;
	}

	// --> JSON-Handling -------------------------------------------------------
	public JsonObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JsonObject json) {
		this.jsonObj = json;
	}

	// --> Datei-Handling ------------------------------------------------------
	public File getFile() {
		return f;
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

	public JsonArray getSettingsArray() {
		return settingsArray;
	}

	public void setSettingsArray(JsonArray settingsArray) {
		this.settingsArray = settingsArray;
	}

	public JsonArray getQueryArray() {
		return queryArray;
	}

	public void setQueryArray(JsonArray queryArray) {
		this.queryArray = queryArray;
	}

	// --> Message-Handling ----------------------------------------------------
	public String getExcMessage() {
		return excMessage;
	}

	public void setExcMessage(String excMessage) {
		this.excMessage = excMessage;
	}
}
