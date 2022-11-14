package businessLogic;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;

public class BusinessLogicFactory {

	static BLFacade appFacadeInterface;
	public BusinessLogicFactory() {

	}

	public static BLFacade createBusinessLogicObject(ConfigXML c) {

		try {
			if (c.isBusinessLogicLocal()) {

				DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
				appFacadeInterface=new BLFacadeImplementation(da);
			}

			else { 

				String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";

				URL url = new URL(serviceName);
				QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");

				Service service = Service.create(url, qname);

				appFacadeInterface = service.getPort(BLFacade.class);

			}

		} catch(Exception e) {
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}
		return appFacadeInterface;



	}
}
