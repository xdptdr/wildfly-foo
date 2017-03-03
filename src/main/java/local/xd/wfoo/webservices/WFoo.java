package local.xd.wfoo.webservices;

import javax.jws.WebService;
import javax.xml.bind.JAXBElement;
import javax.xml.ws.Holder;

import local.xd.wfoo.webservices.i.WFooI;

@WebService(endpointInterface = "local.xd.wfoo.webservices.i.WFooI")
public class WFoo implements WFooI {

	@Override
	public int twice(Holder<Integer> a) {

		return 2 * a.value;
	}

}
