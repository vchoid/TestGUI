package tools;

/**
 * 
 * @author Christoph Kiank
 * @version 0.0.1
 */
public class Port {

	private String name;
	private String port;
	private RegExValidator rv = new RegExValidator();

	public Port(String name) {
		super();
		this.name = name;
	}

	/**
	 * Überprüfen ob der Port nur aus 5 Zahlen besteht, Wenn ja, dann speichere den
	 * Port und gibt true zurück, ansonsten false:
	 * 
	 * @param name
	 * @param port
	 * @return
	 */
	public boolean createValidPort(String port) {
		if (rv.validateOnlyNumField(port)) {
			this.port = port;
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
