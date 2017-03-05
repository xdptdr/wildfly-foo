package local.xd.wfoo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.soap.MTOMFeature;

@WebServlet("/soapmessage")
public class SoapMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		try {
			doWork(req, resp);
		} catch (SOAPException e) {
			throw new RuntimeException(e);
		}

	}

	private void doWork(HttpServletRequest req, HttpServletResponse resp) throws IOException, SOAPException {
		resp.setContentType("text/plain;charset=UTF-8");

		URL url = new URL("http://localhost:8080/foo/WFoo?wsdl");
		QName name = new QName("http://webservices.wfoo.xd.local/", "WFooService");
		Service s = Service.create(url, name, new MTOMFeature());

		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage message = mf.createMessage();
		Dispatch<SOAPMessage> dispatch = s.createDispatch(name, SOAPMessage.class, Mode.PAYLOAD);
		SOAPMessage r = dispatch.invoke(message);
		r.writeTo(resp.getOutputStream());
	}

}