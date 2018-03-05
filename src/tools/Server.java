package tools;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ein Server-Objekt anlegen. Entweder mit der IP-Adresse oder der Host-Adresse.
 * Der jeweils fehlende Wert wird automatisch erganzt.
 * 
 * @author Christoph Kiank
 * @version 1.0.0
 */
public class Server {

	private String name;
	private String host;
	private String ip;
	private InetAddress inet;
	private String excMessage;

	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	private Pattern pattern;
	private Matcher matcher;

	public Server(String name) {
		super();
		this.name = name;
	}
	public boolean validate(final String ip) {
		pattern = Pattern.compile(IPADDRESS_PATTERN);
		matcher = pattern.matcher(ip);
		return matcher.matches();
	}

	/**
	 * Zum Server-Objekt eine IP-Adresse hinzufügen. Der Host wird automatisch
	 * ermittelt und hinzugefügt.
	 * 
	 * @param ip
	 * @return
	 */
	public Server createServerViaIP(String ip) {
		try {
			inet = InetAddress.getByName(ip);
			this.host = inet.getHostName();
			this.ip = ip;
		} catch (UnknownHostException e) {
			// wird mit der validate()-Methode überprüft
		}
		return this;
	}
	/**
	 * Zum Server-Objekt ein Host-Adresse hinzuf�gen. Die IP wird automatisch
	 * ermittelt und hinzugefügt.
	 * 
	 * @param host
	 * @return
	 */
	public Server createServerViaHost(String host) {
		try {
			inet = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			// TODO löschen
			System.out.print(" -> Unbekannter Host");
		}
		this.host = host;
		this.ip = inet.getHostAddress();
		return this;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getExcMessage() {
		return excMessage;
	}
	public void setExcMessage(String excMessage) {
		this.excMessage = excMessage;
	}
}
