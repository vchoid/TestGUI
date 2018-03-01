package test;

import tools.JSONFileHandler;
import tools.Port;

public class TestMain {
	public static void main(String[] args) {
		JSONFileHandler jf = new JSONFileHandler();
		
		Port p = new Port("test");
		p.createPort(1234);
		jf.addPort(p);
	}
}
