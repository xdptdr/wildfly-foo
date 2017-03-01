package local.xd.wfoo.webservices;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

import local.xd.wfoo.webservices.i.WFooI;

@WebServiceClient(name = "youpi", targetNamespace = "floppy", wsdlLocation = "hello")
public class WSC extends Service {

	public WSC() throws MalformedURLException {
		super(new URL("hello"), new QName("localPart"));
	}

	public WSC(WebServiceFeature... features) throws MalformedURLException {
		super(new URL("hello"), new QName("localPart"), features);
	}

	public WSC(URL url) {
		super(url, new QName("localPart"));
	}

	public WSC(URL url, WebServiceFeature... features) {
		super(url, new QName("localPart"), features);
	}

	public WSC(URL url, QName qname) {
		super(url, qname);
	}

	public WSC(URL url, QName qname, WebServiceFeature... features) {
		super(url, qname, features);
	}

	@WebEndpoint(name = "foobaryoupi")
	public WFooI getFooBar() throws WebServiceException {
		return this.getPort(new QName("foobaryoupi"), WFooI.class);
	}

	@WebEndpoint(name = "foobaryoupi")
	public WFooI getFooBar(WebServiceFeature... features) throws WebServiceException {
		return this.getPort(new QName("foobaryoupi"), WFooI.class, features);
	}

}
