package test;

import tools.JSONFileHandler;
import tools.Port;
import tools.Server;

public class TestMain {
	public static void main(String[] args) {
		JSONFileHandler jf = new JSONFileHandler();
//		
//		Port p = new Port("test");
//		p.createPort(1234);
//		jf.addPort(p);
		
		Server s = new Server("Tesrt");
		s.createServerViaIP("222.222.222.256");
	}
}
