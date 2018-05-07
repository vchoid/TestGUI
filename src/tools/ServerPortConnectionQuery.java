package tools;

import java.io.IOException;
import java.net.Socket;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import date.DateStamp;

public class ServerPortConnectionQuery {

	private Socket socket;
	private Service<Object> ncService;

	private DateStamp ds = new DateStamp();
	private String ip;
	private int port;
	private String isConnected;
	private String updatedON;

	public ServerPortConnectionQuery(String ip, String port) {

		setPort(Integer.parseInt(port));
		boolean isConnected = openSocket(ip, getPort());
		setIsConnected(String.valueOf(isConnected));
		setUpdatedON(ds.getDateTimeStamp());
		closeSocket();

	}

	// TODO Thread machen!!
	/**
	 * Startet die Verbindung von Server und Port.
	 * 
	 * @throws InterruptedException
	 */
	public boolean openSocket(String ip, int port) {

		try {
			setSocket(new Socket(ip, port));
			return true;
		} catch (IOException e) {
			System.out.println("mhn");
			return false;
		}
	}

	/**
	 * Schließt die Verbindung zum Server.
	 */
	public void closeSocket() {
		try {
			getSocket().close();
		} catch (IOException e) {
		}
	}

	// #########################################################################
	// ## Getter und Setter ####################################################
	// #########################################################################

	// << isConnected >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getIsConnected() {
		return isConnected;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setIsConnected(String isConnected) {
		this.isConnected = isConnected;
	}

	// << UpdatedON >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public String getUpdatedON() {
		return updatedON;
	}

	public void setUpdatedON(String updatedON) {
		this.updatedON = updatedON;
	}

	// --> Socket --------------------------------------------------------------
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
