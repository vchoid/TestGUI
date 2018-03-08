package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tools.JSONFileContentInAList;
import tools.Port;
import tools.RegExValidator;
import tools.Server;

public class SampleController implements Initializable {

	private JSONFileContentInAList jfList = new JSONFileContentInAList();
	private RegExValidator rv = new RegExValidator();

	// << Exception Ausgabe >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@FXML
	private Label messageLabel;
	// << Server >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@FXML
	private TextField serverNameTField;
	@FXML
	private TextField ipAddr1TField;
	@FXML
	private TextField ipAddr2TField;
	@FXML
	private TextField ipAddr3TField;
	@FXML
	private TextField ipAddr4TField;
	@FXML
	private ChoiceBox<String> serverChoiceBox;

	private Server sOld;
	private Server sNew;
	private String serverName;
	private String ipTFieldConcat;
	private String[] ipPartsAdd;
	private String[] ipPartsEdit;
	private String ipEditConcat;

	private String ip;
	private String[] ipArr;

	private boolean isServerEditActiv;
	private boolean isServerChoiceBoxActiv;

	// << Port >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@FXML
	private TextField portNameTField;
	@FXML
	private TextField portAddrTField;
	@FXML
	private ChoiceBox<String> portChoiceBox;
	private boolean isPortEditActiv;

	private Port pOld;
	private Port pNew;

	// #########################################################################
	// ## Init #################################################################
	// #########################################################################
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		jfList.init();
		updatePortList();
		updateServerList();
	}

	// #########################################################################
	// ## Port #################################################################
	// #########################################################################
	/**
	 * Überprüft ob gültige Werte in den Feldern geschrieben wurden.
	 * 
	 * @return
	 */
	private boolean isPortFieldNotEmptyAndValid() {
		if (!portNameTField.getText().isEmpty() || !portAddrTField.getText().isEmpty()) {
			if (rv.validateOnlyNumField(portAddrTField.getText())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Leert die Eingabefelder
	 */
	public void clearPortField() {
		portNameTField.clear();
		portAddrTField.clear();
		setPortEditActiv(false);
	}

	/**
	 * Aktualisiert den Inhalt der ChoiceBox für die Ports.
	 */
	public void updatePortList() {
		jfList.savePortValuesInAList();
		portChoiceBox.setItems(jfList.getPortNameList());
	}

	// << Delete >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * Löscht einen Port aus der JSON-Datei
	 */
	public void delportEntry() {
		jfList.deletePort(portChoiceBox.getValue());
		setPortEditActiv(false);
		clearPortField();
		updatePortList();
	}

	// << Edit >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * Holt den zu bearbeitenden Port und fügt ihn ihn die Eingabemaske ein.
	 * Anschließend wird der Status Bearbeiten auf true gesetzt,
	 */
	public void editPortEntry() {
		pOld = new Port(portChoiceBox.getValue());
		pOld.createPort(jfList.searchValueByName(jfList.getPortsArray(), pOld.getName(), "port"));
		portNameTField.setText(pOld.getName());
		portAddrTField.setText(pOld.getPort());
		setPortEditActiv(true);
	}

	// << Add >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * Überprüft vor dem Einfügen eines neuen Ports ob die Felder gefüllt sind und
	 * der Inhalt auch der regEx entspricht. Wenn Ja, dann hole die Werte aus den
	 * Felder und erzeuge ein neues Port-Objekt. Initialisiere dent
	 * ContentFileHandler. Überprüfe, ob es der Status auf Bearbeiten gesetzt wurde.
	 * Wenn Ja, dann lösche den alten Port und füge den neuen Port in die Datei.
	 * Wenn Nein, dann füge nur den eingebenen Port hinzu. Gib eine Nachricht aus
	 * und mache eine Update der PortChoiceBox. Leere alle Felder.
	 */
	public void savePortEntry() {
		if (isPortFieldNotEmptyAndValid()) {
			pNew = new Port(portNameTField.getText());
			pNew.createPort(portAddrTField.getText());
			jfList.init();
			if (isPortEditActiv) {
				jfList.deletePort(pOld.getName());
			}
			jfList.addPort(pNew);
			System.out.println(jfList.getPortsArray());
			messageLabel.setText(jfList.getExcMessage());
			updatePortList();
		}
		clearPortField();
	}
	// #########################################################################
	// ## Server ###############################################################
	// #########################################################################

	/**
	 * Überprüft ob gültige Werte in den Feldern geschrieben wurden.
	 * 
	 * @return true wenn Feld nicht leer und gültige IP-Adresse
	 */
	private boolean isSAddrFieldValid() {
		if (!serverNameTField.getText().isEmpty() || !ipAddr1TField.getText().isEmpty()
				|| !ipAddr2TField.getText().isEmpty() || !ipAddr3TField.getText().isEmpty()
				|| !ipAddr4TField.getText().isEmpty()) {
			ipTFieldConcat = ipAddr1TField.getText() + "." + ipAddr2TField.getText() + "." + ipAddr3TField.getText()
					+ "." + ipAddr4TField.getText();
			if (rv.validateIP(ipTFieldConcat)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Leere alle Server-Eingabefelder.
	 */
	public void clearServerField() {
		serverNameTField.clear();
		ipAddr1TField.clear();
		ipAddr2TField.clear();
		ipAddr3TField.clear();
		ipAddr4TField.clear();
	}
	/**
	 * Aktualisiere den Inhalt der Server-ChoiceBox.
	 */
	public void updateServerList() {
		jfList.saveServerValuesInAList();
		serverChoiceBox.setItems(jfList.getServerNameList());
	}

	/**
	 * Lösche einen Server aus der JSON-Datei.
	 */
	// << Delete >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void delServerEntry() {
		jfList.deleteServer(serverChoiceBox.getValue());
		updateServerList();
	}

	// << Edit >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	

	public void editServerEntry() {
		
	}

	// << Add >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void saveServerEntry() {
		if (isSAddrFieldValid()) {
			sNew = new Server(serverNameTField.getText());
			sNew.setIPcreateHost(ipTFieldConcat);
			jfList.init();
			System.out.println(isServerChoiceBoxActiv() && isServerEditActiv());
			if (isServerChoiceBoxActiv() && isServerEditActiv()) {
				editServerEntry();
				setServerChoiceBoxActiv(false);
				setServerEditActiv(false);
			}
			jfList.addServer(sNew);
			updateServerList();
			System.out.println(jfList.getServerArray());
			messageLabel.setText(jfList.getExcMessage());
		}
		clearServerField();
	}

	// #########################################################################
	// ## Getter und Setter ####################################################
	// #########################################################################

	public boolean isPortEditActiv() {
		return isPortEditActiv;
	}

	public void setPortEditActiv(boolean editButtonWasPressed) {
		this.isPortEditActiv = editButtonWasPressed;
	}

	public boolean isServerEditActiv() {
		return isServerEditActiv;
	}

	public boolean isServerChoiceBoxActiv() {
		return isServerChoiceBoxActiv;
	}

	public void setServerChoiceBoxActiv(boolean isServerChoiceBoxActiv) {
		this.isServerChoiceBoxActiv = isServerChoiceBoxActiv;
	}

	public void setServerEditActiv(boolean isServerEditActiv) {
		this.isServerEditActiv = isServerEditActiv;
	}
}
