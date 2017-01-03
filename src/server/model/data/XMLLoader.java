package server.model.data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLLoader {
	private String path = "files/users.xml";
	private Document doc;

	public Map<String, String> load() throws Exception {
		Map<String, String> usersAndPasswords = new HashMap<>();
		buildDocumentFromFile();
		NodeList nodes = doc.getElementsByTagName("user");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			NodeList nodeChildren = n.getChildNodes();
			String[] credentials = getCredentialsFromUserNode(nodeChildren);
			if (credentials != null && credentials[0] != null && !credentials[0].equals("") && credentials[1] != null
					&& credentials[1].equals("")) {
				usersAndPasswords.put(credentials[0], credentials[1]);
			}
		}
		return usersAndPasswords;
	}

	private void buildDocumentFromFile() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		//String sDoc = SimpleFileReader.readFile(path);
		this.doc = builder.parse(path);
		this.doc.getDocumentElement().normalize();
	}

	private String[] getCredentialsFromUserNode(NodeList userNodeChildren) {
		String[] result = new String[2];
		Function<Node, String> f = (node) -> {
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element el = (Element) node;
				return el.getTextContent().trim();
			}
			return null;
		};
		result[0] = f.apply(userNodeChildren.item(0));
		result[1] = f.apply(userNodeChildren.item(1));
		return result;
	}

}
