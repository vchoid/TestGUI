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
import tools.JSONFileHandler;
import tools.Port;
import tools.Server;

public class SampleController implements Initializable {

	private JSONFileHandler jf = new JSONFileHandler();
	private Port p;
	private Server s;

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

	private boolean isSAddrFieldValid() {
		if (serverNameTField.getText().isEmpty()
				|| sAddr1TField.getText().isEmpty()
				|| sAddr2TField.getText().isEmpty()
				|| sAddr3TField.getText().isEmpty()
				|| sAddr4TField.getText().isEmpty()) {
			messageLabel.setText(jf.getExcMessage());
			return false;
		}

		messageLabel.setText(jf.getExcMessage());
		return true;
	}

	public void saveServerEntry() {

		if (isSAddrFieldValid()) {
			jf.init();
			String ipConcat = sAddr1TField.getText() + "."
					+ sAddr2TField.getText() + "." + sAddr3TField.getText()
					+ "." + sAddr4TField.getText();
			s = new Server(serverNameTField.getText());
			s.createServerViaIP(ipConcat);
			jf.addServer(s);

		}
		cancelServerEntry();
	}
	public void cancelServerEntry() {
		serverNameTField.clear();
		sAddr1TField.clear();
		sAddr2TField.clear();
		sAddr3TField.clear();
		sAddr4TField.clear();
		messageLabel.setText(jf.getExcMessage());
	}

	// << Port >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	private boolean isPortFieldValid() {
		if (portNameTField.getText().isEmpty()
				|| portAddrTField.getText().isEmpty()) {
			messageLabel.setText(jf.getExcMessage());
			return false;
		}
		messageLabel.setText(jf.getExcMessage());
		return true;
	}
	public void cancelPortEntry() {
		portNameTField.clear();
		portAddrTField.clear();
		messageLabel.setText(jf.getExcMessage());
	}
	public void savePortEntry() {
		if (isPortFieldValid()) {
			jf.init();
			p = new Port(portNameTField.getText());
			p.createPort(Integer.parseInt(portAddrTField.getText()));
			jf.addPort(p);
		}
		cancelPortEntry();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
