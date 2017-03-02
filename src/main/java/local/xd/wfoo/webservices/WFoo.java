package local.xd.wfoo.webservices;

import javax.jws.WebService;
import javax.xml.ws.Holder;

import local.xd.wfoo.webservices.exceptions.WFooException;
import local.xd.wfoo.webservices.i.WFooI;

@WebService(endpointInterface = "local.xd.wfoo.webservices.i.WFooI")
public class WFoo implements WFooI {

	@Override
	public String hello() {
		return "Hello from " + this.getClass().getName() + " ! ";
	}

	@Override
	public int addThree(int a, Holder<Integer> b, Holder<Integer> c) {

		if (a == 0 && b.value == 0 && c.value == 0) {
			throw new WFooException("I don't want to compute 0");
		}
		return a + b.value + c.value;
	}

	@Override
	public Integer twice(Integer a) {
		if (a == null) {
			return null;
		}
		return 2 * a;
	}
}
