package tools;

public class EmptyPortServerConfigTemplate {

	private final String portServerTemplate = "{\"connection\":[]}";
	
	private String message;
	
	public String getPortServerTemplate() {
		System.out.print("Server_PortsConfig.json ");
		setMessage("Server_PortsConfig.json angelegt");
		return portServerTemplate;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
