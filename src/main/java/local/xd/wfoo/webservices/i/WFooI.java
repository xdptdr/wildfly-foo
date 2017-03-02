package local.xd.wfoo.webservices.i;

import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebService;
import javax.xml.ws.Holder;

@WebService
public interface WFooI {

	String hello();

	int addThree(@WebParam(name = "a", mode = Mode.IN) int a, @WebParam(name = "b", mode = Mode.OUT) Holder<Integer> b,
			@WebParam(name = "c", mode = Mode.INOUT) Holder<Integer> c);

	Integer twice(Integer a);
}
