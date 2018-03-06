package test;

import tools.JSONFileContentHandler;

public class TestMain {
	public static void main(String[] args) {
		JSONFileContentHandler jf = new JSONFileContentHandler();
		
//		Port p = new Port("test");
//		p.createPort(1234);
//		jf.addPort(p);
//		
//		Server s = new Server("Tesrt");
//		s.createServerViaIP("222.222.222.255");
//		jf.addServer(s);
		
		jf.searchValueByName(jf.getPortsArray(), "Tomcat", "port");
		
	}
}
