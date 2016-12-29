package client;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Login {

	String nomeIns, pwdIns;
	String nomeXML, pwdXML;
	int n = 0;

	public Login(String nomeUtente, String password) {
		nomeIns = nomeUtente;
		pwdIns = password;
	}

	public int accesso() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse("files/Login.xml");

			NodeList nList = doc.getElementsByTagName("persona");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					nomeXML = eElement.getElementsByTagName("cognome").item(0).getTextContent()+'.'+ eElement.getElementsByTagName("nome").item(0).getTextContent();
					pwdXML = eElement.getElementsByTagName("password").item(0).getTextContent();

					if (nomeIns.equals(nomeXML) && pwdIns.equals(pwdXML)) {
						n = 1;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return n;
	}
}
