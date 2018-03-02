package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tools.JSONFileContentInAList;
import tools.JSONFileHandler;
import tools.Port;
import tools.RegExValidator;
import tools.Server;

public class SampleController {

	private JSONFileHandler jf = new JSONFileHandler();
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
	private ChoiceBox serverChoiceBox;
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
	private ChoiceBox portChoiceBox;
	@FXML
	private Button delPortButton;

	// << Server >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

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

	public void saveServerEntry() {
		if (isSAddrFieldValid()) {
			jf.init();
			s = new Server(serverNameTField.getText());
			s.createServerViaIP(ipConcat);
			jf.addServer(s);
			messageLabel.setText(jf.getExcMessage());
		}
		clearServerField();
	}
	public void clearServerField() {
		serverNameTField.clear();
		sAddr1TField.clear();
		sAddr2TField.clear();
		sAddr3TField.clear();
		sAddr4TField.clear();
	}

	public void loadPortList(){
		
	}
	
	// << Port >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Überprüft ob gültige Werte in den Feldern geschrieben wurden.
	 * @return
	 */
	private boolean isPortFieldValid() {
		if (!portNameTField.getText().isEmpty()
				|| !portAddrTField.getText().isEmpty()) {
			if(rv.validateOnlyNumField(portAddrTField.getText())) {
			return true;
			}
		}
		return false;
	}
	public void clearPortField() {
		portNameTField.clear();
		portAddrTField.clear();
	}
	public void savePortEntry() {
		if (isPortFieldValid()) {
			jf.init();
			p = new Port(portNameTField.getText());
			p.createPort(Integer.parseInt(portAddrTField.getText()));
			jf.addPort(p);
			messageLabel.setText(jf.getExcMessage());
		}
		clearPortField();
	}

	

}
