package local.xd.wfoo.webservices.i;

import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import local.xd.wfoo.webservices.exceptions.WFooException;

@WebService
@SOAPBinding(use = Use.ENCODED, style = Style.DOCUMENT, parameterStyle = ParameterStyle.BARE)
public interface WFooI {

	@WebResult
	String hello();

	@WebResult
	@Action(input = "inputAction", output = "outputAction", fault = {
			@FaultAction(className = WFooException.class, value = "faultAction") })
	@RequestWrapper
	@ResponseWrapper
	// @XmlAttachmentRef
	// @XmlList
	// @XmlMimeType(value="text/plain")
	// @XmlJavaTypeAdapter()
	int addThree(@WebParam(name = "a", mode = Mode.IN) int a, @WebParam(name = "b", mode = Mode.OUT) Holder<Integer> b,
			@WebParam(name = "c", mode = Mode.INOUT) Holder<Integer> c);
}
