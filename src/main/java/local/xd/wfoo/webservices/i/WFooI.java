package local.xd.wfoo.webservices.i;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Action;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.soap.MTOM;

@WebService
@SOAPBinding
@MTOM
public interface WFooI {

	@WebMethod
	@WebResult
	@Action
	@RequestWrapper
	@ResponseWrapper
	int twice(@WebParam(name = "a") Holder<Integer> a);

	@WebMethod
	@WebResult
	String byteToString(@WebParam(name = "p") Holder<byte[]> p);

}
