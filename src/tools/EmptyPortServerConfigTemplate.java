package tools;

public class EmptyPortServerConfigTemplate {

	private final String portServerTemplate = "{\"connectionConfig\":[],\"ports\":[]}";
	
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
