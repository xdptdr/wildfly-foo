package local.xd.wfoo.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.MTOMFeature;

import local.xd.wfoo.webservices.i.WFooI;

@WebServlet("/helloworlddetails")
public class HelloWorldDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public class SOAPLoggingHandler implements SOAPHandler<SOAPMessageContext> {

		private PrintWriter writer;

		public SOAPLoggingHandler(PrintWriter writer) {
			this.writer = writer;
		}

		@Override
		public void close(MessageContext context) {
			writer.println("close");
		}

		@Override
		public boolean handleFault(SOAPMessageContext context) {
			writer.println("handleFault");
			return true;
		}

		@Override
		public boolean handleMessage(SOAPMessageContext context) {
			writer.println("handleMessage");

			SOAPMessage message = context.getMessage();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				message.writeTo(baos);
			} catch (SOAPException | IOException e) {
			}
			writer.println(new String(baos.toByteArray()));
			return true;
		}

		@Override
		public Set<QName> getHeaders() {
			writer.println("getHeaders");
			return null;
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		try {
			doWork(req, resp);
		} catch (SOAPException e) {
			throw new RuntimeException(e);
		}

	}

	private void doWork(HttpServletRequest req, HttpServletResponse resp) throws IOException, SOAPException {

		boolean mtom = req.getParameter("mtom") != null;

		resp.setContentType("text/plain;charset=UTF-8");
		PrintWriter writer = resp.getWriter();

		URL url = new URL("http://localhost:8080/foo/WFoo?wsdl");
		QName name = new QName("http://webservices.wfoo.xd.local/", "WFooService");
		Service s = null;
		if (!mtom) {
			s = Service.create(url, name, new MTOMFeature());
		} else {
			s = Service.create(url, name);
		}
		WFooI port = s.getPort(WFooI.class);

		BindingProvider bp = (BindingProvider) port;
		@SuppressWarnings("rawtypes")
		List<Handler> chain = bp.getBinding().getHandlerChain();
		chain.add(new SOAPLoggingHandler(writer));
		bp.getBinding().setHandlerChain(chain);

		writer.println(port.byteToString(new Holder<byte[]>("helloyoupi".getBytes())));

	}

}