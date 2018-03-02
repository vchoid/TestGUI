package tools;

public class EmptyPortServerTemplate {

	private final String portServerTemplate = "{\"server\":[],\"ports\":[]}";
	
	private String message;
	
	public String getPortServerTemplate() {
		System.out.print("Server_Ports.json ");
		setMessage("Server_Ports.json angelegt");
		return portServerTemplate;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
