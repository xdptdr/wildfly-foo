package local.xd.wfoo.webservices.soapbinding;

import javax.jws.WebService;

import local.xd.wfoo.webservices.soapbinding.i.WSLRBI;

@WebService(endpointInterface = "local.xd.wfoo.webservices.soapbinding.i.WSLRBI")
public class WSLRB implements WSLRBI {

	@Override
	public String hello() {
		return "Hello from " + this.getClass().getName() + " ! ";
	}

	@Override
	public int addThree(int a, int b, int c) {
		return a + b + c;
	}
}