package local.xd.wfoo.webservices.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name="hello")
public class WFooException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public WFooException(String message) {
		super(message);
	}
}
