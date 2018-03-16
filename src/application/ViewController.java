package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tools.JSONFileContentInAList;
import tools.Port;
import tools.Server;
import tools.ServerPortTableContent;

public class ViewController implements Initializable {

	private JSONFileContentInAList jfList = new JSONFileContentInAList();

	
	// << Table Content >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@FXML
	private TableView<ServerPortTableContent> serverPortConTable;
	@FXML
	private TableColumn<ServerPortTableContent, String> server;
	@FXML
	private TableColumn<ServerPortTableContent, String> port;
	@FXML
	private TableColumn<ServerPortTableContent, String> createdON;
	@FXML
	private TableColumn<ServerPortTableContent, String> isConnected;
	@FXML
	private TableColumn<ServerPortTableContent, String> updatedON;
	
	private ObservableList<ServerPortTableContent> content;
	// << Server >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@FXML
	private TextField serverNameTField;
	@FXML
	private TextField ipAddr0TField;
	@FXML
	private TextField ipAddr1TField;
	@FXML
	private TextField ipAddr2TField;
	@FXML
	private TextField ipAddr3TField;
	@FXML
	private ComboBox<String> serverComboBox;
	@FXML
	private Label serverPH;

	private String ipTFieldConcat;
	private Server sOld;
	private Server sNew;
	private boolean isServerEditActiv;
	private boolean isServerChoiceMade;

	// << Port >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@FXML
	private TextField portNameTField;
	@FXML
	private TextField portAddrTField;
	@FXML
	private ComboBox<String> portComboBox;
	@FXML
	Label portPH;

	private Port pOld;
	private Port pNew;
	private boolean isPortEditActiv;
	private boolean isPortChoose;
	
	// << Exception Ausgabe >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@FXML
	private Label messageLabel;

	// #########################################################################
	// ## Init #################################################################
	// #########################################################################
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fillContent();
		jfList.init();
		updatePortList();
		updateServerList();
	}

	public void editAreaDefault() {
		clearPortField();
		clearServerField();
	}
	// #########################################################################
	// ## Port #################################################################
	// #########################################################################
	public void disablePortPH() {
		portPH.setVisible(false);
	}
	public void enablePortPH() {
		portPH.setVisible(true);
	}
	/**
	 * Überprüft ob die die Felder nicht leer sind. Wenn ja dann lege einen
	 * neuen Portnamen an. Lege einen einen neuen Port an, welcher beim erzeugen
	 * validiert wird und ein true oder false zurück gibt. Bei false leere die
	 * Eingabefelder
	 * 
	 * @return
	 */
	private boolean isPortFieldNotEmptyAndValid() {
		if (!portNameTField.getText().isEmpty()
				|| !portAddrTField.getText().isEmpty()) {
			pNew = new Port(portNameTField.getText());
			return pNew.createValidPort(portAddrTField.getText());
		}
		clearPortField();
		return false;
	}

	/**
	 * Leert die Eingabefelder
	 */
	public void clearPortField() {
		portNameTField.clear();
		portAddrTField.clear();
		enablePortPH();
		updatePortList();
		setPortFieldEditable(true);
	}

	/**
	 * Aktualisiert den Inhalt der ChoiceBox für die Ports.
	 */
	private void updatePortList() {
		jfList.savePortValuesInAList();
		portComboBox.setItems(jfList.getPortNameList());
		isPortEditActiv = false;
	}

	public void addPortValuesFromComboToTFields() {
		pOld = new Port(portComboBox.getValue());
		pOld.createValidPort(jfList.searchValueByName(jfList.getPortsArray(),
				pOld.getName(), "port"));
		portNameTField.setText(pOld.getName());
		portAddrTField.setText(pOld.getPort());
		isPortChoose = true;
		setPortFieldEditable(false);
	}
	
	public void setPortFieldEditable(boolean bool) {
		portNameTField.setDisable(!bool);
		portAddrTField.setDisable(!bool);
		
	}
	// << Delete >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * Löscht einen Port aus der JSON-Datei
	 */
	public void delportEntry() {
		if (isPortChoose) {
			jfList.deletePort(portComboBox.getValue());
		}
		clearPortField();
	}

	// << Edit >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * Holt den zu bearbeitenden Port und fügt ihn ihn die Eingabemaske ein.
	 * Anschließend wird der Status Bearbeiten auf true gesetzt,
	 */
	public void editPortEntry() {
		if (isPortChoose) {
			isPortEditActiv = true;
			setPortFieldEditable(true);
		}
	}

	// << Add >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * Überprüft vor dem Einfügen eines neuen Ports ob die Felder gefüllt sind
	 * und der Inhalt auch der regEx entspricht. Wenn ja, dann hole die Werte
	 * aus den Felder und erzeuge ein neues Port-Objekt. Initialisiere dent
	 * ContentFileHandler. Überprüfe, ob es der Status auf Bearbeiten gesetzt
	 * wurde. Wenn ja, dann lösche den alten Port und füge den neuen Port in die
	 * Datei. Wenn nein, dann füge nur den eingebenen Port hinzu. Gib eine
	 * Nachricht aus und mache eine Update der PortChoiceBox. Leere alle Felder.
	 */
	public void savePortEntry() {
		if (isPortFieldNotEmptyAndValid()) {
			jfList.init();
			if (isPortEditActiv) {
				jfList.deletePort(pOld.getName());
			}
			jfList.addPort(pNew);
			messageLabel.setText(jfList.getExcMessage());
		} else {
			messageLabel.setText("Ungültiger Port");
		}
		clearPortField();
	}
	// #########################################################################
	// ## Server ###############################################################
	// #########################################################################
	public void disableServerPH() {
		serverPH.setVisible(false);
	}
	public void enableServerPH() {
		serverPH.setVisible(true);
	}
	/**
	 * Überprüft ob in allen Eingabefeldern Werte geschrieben wurden. Wenn ja,
	 * erzeuge einen neuen Servernamen, hole den Text aus dem Eingabefeld. Füge
	 * alle Eingabefelder für die IP-Adresse zusammen zu einer IP. Erstelle eine
	 * neue IP. Wenn es sich um eine valide IP handelt gibt diese zurück und ein
	 * true. Automatisch wird ein Ip-Array erzeugt. Bei false leere alle Felder.
	 * 
	 * @return true wenn Feld nicht leer und gültige IP-Adresse
	 */
	private boolean isSAddrFieldValid() {
		if (!serverNameTField.getText().isEmpty()
				|| !ipAddr0TField.getText().isEmpty()
				|| !ipAddr1TField.getText().isEmpty()
				|| !ipAddr2TField.getText().isEmpty()
				|| !ipAddr3TField.getText().isEmpty()) {
			sNew = new Server(serverNameTField.getText());
			ipTFieldConcat = ipAddr0TField.getText() + "."
					+ ipAddr1TField.getText() + "." + ipAddr2TField.getText()
					+ "." + ipAddr3TField.getText();
			return sNew.createValidIpWithArrayGetHost(ipTFieldConcat);
		}
		clearServerField();
		return false;
	}

	/**
	 * Leere alle Server-Eingabefelder.
	 */
	public void clearServerField() {
		serverNameTField.clear();
		ipAddr0TField.clear();
		ipAddr1TField.clear();
		ipAddr2TField.clear();
		ipAddr3TField.clear();
		updateServerList();
		enableServerPH();
	}

	/**
	 * Aktualisiere den Inhalt der Server-ChoiceBox.
	 */
	private void updateServerList() {
		jfList.saveServerValuesInAList();
		serverComboBox.setItems(jfList.getServerNameList());
		isServerEditActiv = false;
	}

	public void setServerChoiceMade() {
		isServerChoiceMade = true;
	}
	// << Delete >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * Lösche einen Server aus der JSON-Datei.
	 */
	public void delServerEntry() {
		if (isServerChoiceMade) {
			jfList.deleteServer(serverComboBox.getValue());
		}
		clearServerField();
	}

	// << Edit >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void editServerEntry() {
		if (isServerChoiceMade) {
			sOld = new Server(serverComboBox.getValue());
			sOld.createValidIpWithArrayGetHost(jfList.searchValueByName(
					jfList.getServerArray(), sOld.getName(), "ip"));
			serverNameTField.setText(sOld.getName());
			ipAddr0TField.setText(sOld.getIpArr()[0]);
			ipAddr1TField.setText(sOld.getIpArr()[1]);
			ipAddr2TField.setText(sOld.getIpArr()[2]);
			ipAddr3TField.setText(sOld.getIpArr()[3]);
			isServerEditActiv = true;
		}
	}

	// << Add >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void saveServerEntry() {
		if (isSAddrFieldValid()) {
			jfList.init();
			if (isServerEditActiv) {
				jfList.deleteServer(sOld.getName());
			}
			jfList.addServer(sNew);
			messageLabel.setText(jfList.getExcMessage());
		} else {
			messageLabel.setText("Ungültige IP-Adresse");
		}
		clearServerField();
	}
	// #########################################################################
	// ## Table ################################################################
	// #########################################################################
	public void fillContent() {
		server.setCellValueFactory(new PropertyValueFactory<ServerPortTableContent, String>("server"));
		port.setCellValueFactory(new PropertyValueFactory<ServerPortTableContent, String>("port"));
		isConnected.setCellValueFactory(new PropertyValueFactory<ServerPortTableContent, String>("isConnected"));
		createdON.setCellValueFactory(new PropertyValueFactory<ServerPortTableContent, String>("createdON"));
		updatedON.setCellValueFactory(new PropertyValueFactory<ServerPortTableContent, String>("updatedON"));
		content = FXCollections.observableArrayList(
				new ServerPortTableContent("Tomcat", "PreProd", "true", "03-12-2018", "03-14-2018"),
				new ServerPortTableContent("Remote", "Staging", "false", "03-12-2018", "03-12-2018"),
				new ServerPortTableContent("FirstSpirit", "Local", "true", "03-10-2018", "03-11-2018"),
				new ServerPortTableContent("Tomcat", "Auslieferung", "true", "03-09-2018", "03-12-2018"),
				new ServerPortTableContent("Tomcat", "PreProd", "true", "03-12-2018", "03-14-2018"),
				new ServerPortTableContent("Remote", "Staging", "false", "03-12-2018", "03-12-2018"),
				new ServerPortTableContent("FirstSpirit", "Local", "true", "03-10-2018", "03-11-2018"),
				new ServerPortTableContent("Tomcat", "Auslieferung", "true", "03-09-2018", "03-12-2018"),
				new ServerPortTableContent("Tomcat", "PreProd", "true", "03-12-2018", "03-14-2018"),
				new ServerPortTableContent("Remote", "Staging", "false", "03-12-2018", "03-12-2018"),
				new ServerPortTableContent("FirstSpirit", "Local", "true", "03-10-2018", "03-11-2018"),
				new ServerPortTableContent("Tomcat", "Auslieferung", "true", "03-09-2018", "03-12-2018"),
				new ServerPortTableContent("Tomcat", "PreProd", "true", "03-12-2018", "03-14-2018"),
				new ServerPortTableContent("Remote", "Staging", "false", "03-12-2018", "03-12-2018"),
				new ServerPortTableContent("FirstSpirit", "Local", "true", "03-10-2018", "03-11-2018"),
				new ServerPortTableContent("Tomcat", "Auslieferung", "true", "03-09-2018", "03-12-2018"),
				new ServerPortTableContent("Tomcat", "PreProd", "true", "03-12-2018", "03-14-2018"),
				new ServerPortTableContent("Remote", "Staging", "false", "03-12-2018", "03-12-2018"),
				new ServerPortTableContent("FirstSpirit", "Local", "true", "03-10-2018", "03-11-2018"),
				new ServerPortTableContent("Tomcat", "Auslieferung", "true", "03-09-2018", "03-12-2018")
				);
		serverPortConTable.setItems(content);
	}
}
