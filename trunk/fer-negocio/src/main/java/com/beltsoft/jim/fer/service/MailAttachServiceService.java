package com.beltsoft.jim.fer.service;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceClient;

@WebServiceClient(name = "MailAttachServiceService", wsdlLocation = "http://172.18.11.86:1880/MsMail/MailAttachServiceService?wsdl", targetNamespace = "http://service.mail.gob.se.mx/")
public class MailAttachServiceService extends Service {

	public final static URL WSDL_LOCATION;

	public final static QName SERVICE = new QName(
			"http://service.mail.gob.se.mx/", "MailAttachServiceService");
	public final static QName MsMailServiceFilePort = new QName(
			"http://service.mail.gob.se.mx/", "MsMailServiceFilePort");
	static {
		URL url = null;
		try {
			url = new URL(
					"http://172.18.11.86:1880/MsMail/MailAttachServiceService?wsdl");
		} catch (MalformedURLException e) {
			java.util.logging.Logger
					.getLogger(MailAttachServiceService.class.getName())
					.log(java.util.logging.Level.INFO,
							"Can not initialize the default wsdl from {0}",
							"http://172.18.11.86:1880/MsMail/MailAttachServiceService?wsdl");
		}
		WSDL_LOCATION = url;
	}

	public MailAttachServiceService(URL wsdlLocation) {
		super(wsdlLocation, SERVICE);
	}

	public MailAttachServiceService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public MailAttachServiceService() {
		super(WSDL_LOCATION, SERVICE);
	}

}