package local.xd.wfoo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Response;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.wsaddressing.W3CEndpointReference;

import local.xd.wfoo.webservices.i.WFooI;

@WebServlet("/helloworld")
public class HelloWorldServlet extends HttpServlet {

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
		PrintWriter writer = resp.getWriter();
		writer.println("Hello world!");

		URL url = new URL("http://localhost:8080/foo/WFoo?wsdl");
		QName name = new QName("http://webservices.wfoo.xd.local/", "WFooService");
		Service s = Service.create(url, name);
		WFooI port = s.getPort(WFooI.class);
		writer.println(port.twice(8));

		HandlerResolver hr = s.getHandlerResolver();
		writer.println(objStr(hr));
		Executor e = s.getExecutor();
		writer.println(objStr(e));

		BindingProvider bp = (BindingProvider) port;
		W3CEndpointReference r = (W3CEndpointReference) bp.getEndpointReference();
		Map<String, Object> requestContext = bp.getRequestContext();
		Map<String, Object> responseContext = bp.getResponseContext();

		writer.println(objStr(r));
		for (String key : requestContext.keySet()) {
			writer.println("requestContext: " + key);
		}
		for (String key : responseContext.keySet()) {
			writer.println("responseContext: " + key);
		}
		Object soapVersion = responseContext.get("org.apache.cxf.binding.soap.SoapVersion");
		writer.println(objStr(soapVersion));

		QName portQname = new QName("http://i.webservices.wfoo.xd.local/", "WFooPort");

		Dispatch<SOAPMessage> dSoap = s.createDispatch(portQname, SOAPMessage.class, Mode.MESSAGE);

		MessageFactory mf = MessageFactory.newInstance();
		SOAPMessage msg = mf.createMessage();

		dSoap.invoke(msg);

		AsyncHandler<SOAPMessage> handler = new AsyncHandler<SOAPMessage>() {
			@Override
			public void handleResponse(Response<SOAPMessage> res) {

			}
		};

		Future<?> response = dSoap.invokeAsync(msg, handler);
		dSoap.invokeOneWay(msg);

		response.isDone();

	}

	private String objStr(Object o) {
		if (o == null) {
			return "n$";
		}
		if (o instanceof String) {
			return "s$" + o;
		}
		return "c$" + o.getClass().getCanonicalName();
	}

}