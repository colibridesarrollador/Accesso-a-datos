package manejoXMLConDOM;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ArtistaDOM {

	private Document doc;
	
	public static void main(String[] args) {

	}

	/*
	 * 1. Un método que cree un objeto Document igual al fichero artistas.xml,
	 * usando DOM.
	 */
	public void crearDocumentoXMLArtistasDOM() {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			this.doc = db.newDocument();
			
			Element raiz = this.doc.createElement("Artistas");//crea la raiz
			this.doc.appendChild(raiz);//une la raiz
			
			//Element artista =
			
			
			
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
