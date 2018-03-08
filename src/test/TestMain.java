package test;

import tools.JSONFileContentHandler;
import tools.Port;
import tools.Server;

public class TestMain {
	public static void main(String[] args) {
		JSONFileContentHandler jf = new JSONFileContentHandler();
		jf.init();
		Port p = new Port("test");
		p.createPort("1234");
		jf.addPort(p);
//		
		Server s = new Server("Test");
		s.setIPcreateHost("222.222.222.255");
//		jf.addServer(s);
		
		Server sNeu = new Server("Test");
		sNeu.setIPcreateHost("222.222.222.0");
		jf.editServer(s, sNeu);
		
		
	}
}
