package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tools.JSONFileHandler;
import tools.Port;
import tools.Server;

public class SampleController implements Initializable{
	
	private JSONFileHandler jf = new JSONFileHandler();
	private Port p;
	private Server s;
	
	@FXML
	private Button savePortBtn;
	@FXML
	private Button cancelPortBtn;
	@FXML
	private TextField portNameTField;
	@FXML
	private TextField portAddrTField;
	
	@FXML
	private Button saveServerBtn;
	@FXML
	private Button cancelServerBtn;
	@FXML
	private TextField serverNameTField;
	@FXML
	private TextField serverAddrTField;
	
	public void savePortEntry() {
		p = new Port(portNameTField.getText());
		p.createPort(Integer.parseInt(portAddrTField.getText()));
		jf.addPort(p);
		p = null;
	}
	
	public void saveServerEntry() {
		s = new Server(serverNameTField.getText());
		s.createServerViaIP(serverAddrTField.getText());
		jf.addServer(s);
		s = null;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		jf.init();
		
	}
	
}
