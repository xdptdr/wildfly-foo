package local.xd.wfoo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Holder;
import javax.xml.ws.Response;
import javax.xml.ws.Service;

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

		URL url = new URL("http://localhost:8080/foo/WFoo?wsdl");
		QName name = new QName("http://webservices.wfoo.xd.local/", "WFooService");
		Service s = Service.create(url, name);
		WFooI port = s.getPort(WFooI.class);
		writer.println(port.twice(new Holder<Integer>(2)));

	}

}