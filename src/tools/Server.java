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
	private String[] ipArr;
	private InetAddress inet;
	
	private RegExValidator rv = new RegExValidator();

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
	public boolean createValidIpWithArrayGetHost(String ip) {
		if(rv.validateIP(ip)) {
			try {
				inet = InetAddress.getByName(ip);
				this.ip = ip;
				makeArrayFromIp(ip);
				this.host = inet.getHostName();
				
			} catch (UnknownHostException e) {
				// TODO löschen
				System.out.print(" -> ungültige IP-Adresse");
			}
			return true;
		}
		return false;
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
	private String[] makeArrayFromIp(String ip) {
		return this.ipArr = ip.split("\\.", 4);
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

	public String[] getIpArr() {
		return ipArr;
	}
	public void setIpArr(String[] ipArr) {
		this.ipArr = ipArr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
