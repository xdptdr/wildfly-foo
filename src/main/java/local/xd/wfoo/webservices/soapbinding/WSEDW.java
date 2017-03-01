package local.xd.wfoo.webservices.soapbinding;

import javax.jws.WebService;

import local.xd.wfoo.webservices.soapbinding.i.WSEDWI;

@WebService(endpointInterface = "local.xd.wfoo.webservices.soapbinding.i.WSEDWI")
public class WSEDW implements WSEDWI {

	@Override
	public String hello() {
		return "Hello from " + this.getClass().getName() + " ! ";
	}

	@Override
	public int addThree(int a, int b, int c) {
		return a + b + c;
	}
}
