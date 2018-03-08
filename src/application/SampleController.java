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
	
	private Server s;
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
	private boolean isPortChoiceBoxActiv;

	private Port pOld;
	private Port pNew;
	private String portName;
	private String portAddr;
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
			ipTFieldConcat = ipAddr1TField.getText() + "." + ipAddr2TField.getText() + "." + ipAddr3TField.getText() + "."
					+ ipAddr4TField.getText();
			if (rv.validateIP(ipTFieldConcat)) {
				return true;
			}
		}
		return false;
	}
	public void clearServerField() {
		serverNameTField.clear();
		ipAddr1TField.clear();
		ipAddr2TField.clear();
		ipAddr3TField.clear();
		ipAddr4TField.clear();
	}
	public void updateServerList() {
		jfList.saveServerValuesInAList();
		serverChoiceBox.setItems(jfList.getServerNameList());
	}
	// << Delete >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void delServerEntry() {
		jfList.deleteServer(serverChoiceBox.getValue());
		updateServerList();
	}
	// << Edit >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void getServerChoiceBoxValues() {
		serverName = serverChoiceBox.getValue();
		serverNameTField.setText(serverName);
		ipArr = jfList.makeArrayFromIp(jfList.searchValueByName(jfList.getServerArray(), serverName, "ip"));
		ipAddr1TField.setText(ipArr[0]);
		ipAddr2TField.setText(ipArr[1]);
		ipAddr3TField.setText(ipArr[2]);
		ipAddr4TField.setText(ipArr[3]);
		setServerEditActiv(true);
		System.out.println(isServerEditActiv);
	}
	public void activateServerEdit() {
		setServerChoiceBoxActiv(true);
		System.out.println(isServerChoiceBoxActiv);
	}
	public void editServerEntry() {
//		jfList.editServerName(serverName, serverNameTField.getText());
		System.out.println(ipArr[0]);
		String[] t = jfList.makeArrayFromIp(serverNameTField.getText());
		System.out.println(t[0]);
//		jfList.editServerIP(ipArr, jfList.makeArrayFromIp(serverNameTField.getText()));
	}
	// << Add >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void saveServerEntry() {
		if (isSAddrFieldValid()) {
			s = new Server(serverNameTField.getText());
			s.setIPcreateHost(ipTFieldConcat);
			jfList.init();
			System.out.println(isServerChoiceBoxActiv() && isServerEditActiv());
			if(isServerChoiceBoxActiv() && isServerEditActiv()) {
				editServerEntry();
				setServerChoiceBoxActiv(false);
				setServerEditActiv(false);
			}
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
	private boolean isPortFieldNotEmptyAndValid() {
		if (!portNameTField.getText().isEmpty() || !portAddrTField.getText().isEmpty()) {
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
		jfList.savePortValuesInAList();
		portChoiceBox.setItems(jfList.getPortNameList());
	}

	// << Delete >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void delportEntry() {
		jfList.deletePort(portChoiceBox.getValue());
		setPortEditActiv(false);
		clearPortField();
		updatePortList();
	}

	// << Edit >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void getPortChoiceBoxValues() {
		portName = portChoiceBox.getValue();
		portNameTField.setText(portName);
		portAddr = jfList.searchValueByName(jfList.getPortsArray(), portName, "port");
		portAddrTField.setText(portAddr);
		setPortEditActiv(true);
	}

	public void editPortEntry() {
		pOld = new Port(portName);
		pOld.createPort(portAddr);
		pNew = new Port(portNameTField.getText());
		pNew.createPort(portAddrTField.getText());
		jfList.editPort(pOld, pNew);
	}

	public void activatePortEdit() {
		setPortChoiceBoxActiv(true);
	}

	// << Add >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void savePortEntry() {
		if (isPortFieldNotEmptyAndValid()) {
			pNew = new Port(portNameTField.getText());
			pNew.createPort(portAddrTField.getText());
			jfList.init();
			if (isPortEditActiv() && isPortChoiceBoxActiv()) {
				editPortEntry();
				setPortEditActiv(false);
				setPortChoiceBoxActiv(false);
			} else {
				jfList.addPort(pNew);
				System.out.println(jfList.getPortsArray());
				messageLabel.setText(jfList.getExcMessage());
			}
			updatePortList();
		}
		clearPortField();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		jfList.init();
		updatePortList();
		updateServerList();
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

	public boolean isPortChoiceBoxActiv() {
		return isPortChoiceBoxActiv;
	}

	public void setPortChoiceBoxActiv(boolean isChoiceBoxActiv) {
		this.isPortChoiceBoxActiv = isChoiceBoxActiv;
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
