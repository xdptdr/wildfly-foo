package local.xd.wfoo.webservices;

import javax.jws.WebService;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.attachment.AttachmentMarshaller;
import javax.xml.bind.attachment.AttachmentUnmarshaller;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Holder;
import javax.xml.ws.Response;
import javax.xml.ws.wsaddressing.W3CEndpointReference;

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

	@SuppressWarnings("unused")
	private void foo() {
		Response<Integer> r = null;
		AsyncHandler<Integer> ah = null;
		AttachmentMarshaller am = null;
		AttachmentUnmarshaller au = null;
		Unmarshaller u = null;
		u.setAttachmentUnmarshaller(au);
		Marshaller m = null;
		m.setAttachmentMarshaller(am);
		W3CEndpointReference w3cer = null;
		
	}
}
