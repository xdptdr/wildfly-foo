package local.xd.wfoo.webservices.soapbinding.i;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebParam.Mode;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

//@WebService
@SOAPBinding(use = Use.ENCODED, style = Style.RPC, parameterStyle = ParameterStyle.BARE)
public interface WSERBI {

	@WebResult(name = "helloWebResult", partName = "helloPart")
	String hello();

	int addThree(@WebParam(name = "a", mode = Mode.IN, partName="aPart") int a, @WebParam(name = "b", mode = Mode.OUT) int b,
			@WebParam(name = "c", mode = Mode.INOUT) int c);
}
