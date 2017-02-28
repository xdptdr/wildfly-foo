package local.xd.wfoo.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "Foo", serviceName = "SFoo", portName = "PFoo", targetNamespace = "http://foospace")
public class WFoo {

	@WebMethod
	public String hello() {
		return "Hello from WFoo ! ";
	}
}
