package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
	private Port p;
	private Server s;
	private String ipConcat;

	// << Exception Ausgabe >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@FXML
	private Label messageLabel;
	// << Server >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@FXML
	private Button saveServerBtn;
	@FXML
	private Button cancelServerBtn;
	@FXML
	private TextField serverNameTField;
	@FXML
	private TextField sAddr1TField;
	@FXML
	private TextField sAddr2TField;
	@FXML
	private TextField sAddr3TField;
	@FXML
	private TextField sAddr4TField;
	@FXML
	private ChoiceBox<String> serverChoiceBox;
	@FXML
	private Button delServerButton;
	// << Port >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@FXML
	private Button savePortBtn;
	@FXML
	private Button cancelPortBtn;
	@FXML
	private TextField portNameTField;
	@FXML
	private TextField portAddrTField;
	@FXML
	private ChoiceBox<String> portChoiceBox;
	@FXML
	private Button delPortButton;

	// #########################################################################
	// ## Port #################################################################
	// #########################################################################

	/**
	 * Überprüft ob gültige Werte in den Feldern geschrieben wurden.
	 * 
	 * @return true wenn Feld nicht leer und gültige IP-Adresse
	 */
	private boolean isSAddrFieldValid() {
		if (!serverNameTField.getText().isEmpty()
				|| !sAddr1TField.getText().isEmpty()
				|| !sAddr2TField.getText().isEmpty()
				|| !sAddr3TField.getText().isEmpty()
				|| !sAddr4TField.getText().isEmpty()) {
			ipConcat = sAddr1TField.getText() + "." + sAddr2TField.getText()
					+ "." + sAddr3TField.getText() + "."
					+ sAddr4TField.getText();
			if (rv.validateIP(ipConcat)) {
				return true;
			}
		}
		return false;
	}
	public void clearServerField() {
		serverNameTField.clear();
		sAddr1TField.clear();
		sAddr2TField.clear();
		sAddr3TField.clear();
		sAddr4TField.clear();
	}
	public void updateServerList() {
		jfList.saveServerValuesInAList();;
		serverChoiceBox.setItems(jfList.getServerNameList());
	}

	// << Delete >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void delServerEntry() {
		jfList.deleteServer(serverChoiceBox.getValue());
		updateServerList();
	}
	// << Edit >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void editServerEntry() {
		serverNameTField.setText(serverChoiceBox.getValue());
	}
	// << Add >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void saveServerEntry() {
		if (isSAddrFieldValid()) {
			s = new Server(serverNameTField.getText());
			s.createServerViaIP(ipConcat);
			jfList.init();
			jfList.addServer(s);
			updateServerList();
			System.out.println(jfList.getServerArray());
			messageLabel.setText(jfList.getExcMessage());
		}
		clearServerField();
	}
	// #########################################################################
	// ## Port #################################################################
	// #########################################################################

	/**
	 * Überprüft ob gültige Werte in den Feldern geschrieben wurden.
	 * 
	 * @return
	 */
	private boolean isPortFieldValid() {
		if (!portNameTField.getText().isEmpty()
				|| !portAddrTField.getText().isEmpty()) {
			if (rv.validateOnlyNumField(portAddrTField.getText())) {
				return true;
			}
		}
		return false;
	}
	
	public void clearPortField() {
		portNameTField.clear();
		portAddrTField.clear();
	}
	
	public void updatePortList() {
		jfList.savePortValuesInAList();;
		portChoiceBox.setItems(jfList.getPortNameList());
	}
	
	// << Delete >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void delportEntry() {
		jfList.deletePort(portChoiceBox.getValue());
		updatePortList();
	}
	// << Edit >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void editPortEntry() {
		String name = portChoiceBox.getValue();
		portNameTField.setText(name);
		String port = jfList.searchValueByName(jfList.getPortsArray(), name, "port");
		portAddrTField.setText(port);
	}
	// << Add >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void savePortEntry() {
		if (isPortFieldValid()) {
			p = new Port(portNameTField.getText());
			p.createPort(Integer.parseInt(portAddrTField.getText()));
			jfList.init();
			jfList.addPort(p);
			updatePortList();
			System.out.println(jfList.getPortsArray());
			messageLabel.setText(jfList.getExcMessage());
		}
		clearPortField();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		jfList.init();
		updatePortList();
		updateServerList();
	}

}
