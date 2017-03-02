package local.xd.wfoo.webservices.soapbinding;

import javax.jws.WebService;

import local.xd.wfoo.webservices.soapbinding.i.WSLDBI;

//@WebService(endpointInterface = "local.xd.wfoo.webservices.soapbinding.i.WSLDBI")
public class WSLDB implements WSLDBI {

	@Override
	public String hello() {
		return "Hello from " + this.getClass().getName() + " ! ";
	}

	@Override
	public int addThree(int a, int b, int c) {
		return a + b + c;
	}
}
