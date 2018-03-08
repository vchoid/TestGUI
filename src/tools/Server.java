package tools;

import java.net.InetAddress;
import java.net.UnknownHostException;

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

	public Server(String name) {
		super();
		this.name = name;
	}
	/**
	 * Zum Server-Objekt eine IP-Adresse hinzuf�gen. Der Host wird automatisch
	 * ermittelt und hinzugef�gt.
	 * 
	 * @param ip
	 * @return
	 */
	public Server setIPcreateHost(String ip) {
		try {
			inet = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			// TODO l�schen
			System.out.print(" -> ungültige IP-Adresse");
		}
		this.ip = ip;
		this.host = inet.getHostName();
		return this;
	}

	/**
	 * Macht aus den 4 einzelnen Teilen einer Ip eine einzige IP-Adresse.
	 * 
	 * @param ipArr
	 * @return String ip
	 */
	public String makeIpFromArray(String[] ipArr) {
		return ipArr[0] + "." + ipArr[1] + "." + ipArr[2] + "." + ipArr[3];
	}

	/**
	 * Verteilt die einzelnen Teile einer IP-Adresse auf ein String-Array.
	 * 
	 * @param ip
	 * @return String[] ip
	 */
	public String[] makeArrayFromIp(String ip) {
		return ip.split("\\.", 4);
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
