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

public class JSONFileConnConfigINI {

	// --> Datei-Handling ------------------------------------------------------
	private Gson gson = new Gson();
	private final static String FILE = System.getProperty("user.dir")
			+ "/src/resources/Server_Port_ConnectionConfig.JSON";
	private BufferedReader reader;
	private FileInputStream input;
	private BufferedWriter writer;
	private FileOutputStream out;
	// --> Content-Handling ----------------------------------------------------
	private JsonObject jsonObj;
	private EmptyPortServerConfigTemplate emptyPSTemplate = new EmptyPortServerConfigTemplate();
	private JsonArray allConnJArray;
	private JsonArray oneConnJArray;

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
	 * <b>{@link #setPortServerValuesInAList()}</b></li>
	 * </ul>
	 * </p>
	 */
	public void init() {
		readFromFileParseFileAsJSONObject(getFile());
	}

	/**
	 * Ließt Datei ein und speichern den Inhalt in ein {@link JsonObject}}.
	 */
	public void readFromFileParseFileAsJSONObject(String file) {
		// Datei über einen Stream einlesen
		try {
			input = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(input));
			// Datei als JSON-Objekt einlesen
			setJsonObj(gson.fromJson(reader, JsonObject.class));
			setAllConnJArray(getJsonObj().getAsJsonArray("connections"));
			reader.close();
			System.out.println("reader ...geschlossen!");
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
	 */
	public void writeInFile(String content) {
		try {
			out = new FileOutputStream(getFile());
			writer = new BufferedWriter(new OutputStreamWriter(out));
			writer.write(content);
			// TODO l�schen!
			setExcMessage(" ...gespeichert");
			System.out.println("...gespeichert");
			writer.close();
			System.out.println("writer ...geschlossen!");
		} catch (IOException e) {
			setExcMessage(e.toString());
		}
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
	public static String getFile() {
		return FILE;
	}
	// --> Array-Handling ------------------------------------------------------
	public JsonArray getAllConnJArray() {
		return allConnJArray;
	}
	public void setAllConnJArray(JsonArray allConnJArray) {
		this.allConnJArray = allConnJArray;
	}
	// --> Message-Handling ----------------------------------------------------
	public String getExcMessage() {
		return excMessage;
	}
	public void setExcMessage(String excMessage) {
		this.excMessage = excMessage;
	}
	
}
