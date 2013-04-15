import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Main {
	
	public static void main(String [] args) throws SAXException, IOException{	
		TestClasses tc = new TestClasses();
		//tc.testWordNetSimilarity();
		//tc.printAllSynonyms("Segmented Worm");
		tc.getAllSynHypo("slowworm");
		
		List <String> hyps = new ArrayList <String>(){{add("hyps");}};
		//tc.getOnlyHyponyms("reptile");
		//System.out.println(tc.hyps.size());
		//tc.testWordNetStemming("Anseriformes");
		
		/*
		Document test = null;
		File fXmlFile = new File("tests/test.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Element result_root_element = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();	
			//creating the resulting xml file
			test = dBuilder.parse(fXmlFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		appendXmlFragment(dBuilder, test.getFirstChild(), "<test>x</test>");
	    */
	}
	
	public static void appendXmlFragment(
		      DocumentBuilder docBuilder, Node parent,
		      String fragment) throws IOException, SAXException {
		    Document doc = parent.getOwnerDocument();
		    Node fragmentNode = docBuilder.parse(
		        new InputSource(new StringReader(fragment)))
		        .getDocumentElement();
		    fragmentNode = doc.importNode(fragmentNode, true);
		    parent.appendChild(fragmentNode);
		  }
}
