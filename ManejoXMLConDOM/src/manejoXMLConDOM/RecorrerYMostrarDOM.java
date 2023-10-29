package manejoXMLConDOM;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RecorrerYMostrarDOM {

	private Document doc;

	public static void main(String[] args) {
		RecorrerYMostrarDOM rym = new RecorrerYMostrarDOM();
		rym.abrirXML(new File("artistas.xml"));
		rym.mostrarXML();
	}

	/*
	 * 1. Un método que reciba el fichero que se adjunta en esta tarea
	 * (artistas.xml) y lo abra para crear el árbol DOM.
	 */
	public void abrirXML(File fichero) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringComments(true);
		dbf.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			try {
				this.doc = db.parse(fichero);
			} catch (SAXException e) {				
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 2. Crear otro método que muestre por pantalla la información de cada uno de
	 * los artistas.
	 */
	public void mostrarXML() {
		
		Artista artista;
		
		Node raiz = this.doc.getFirstChild();
		NodeList atributos = raiz.getChildNodes();
		Node tmp = null;
		
		for (int i = 0; i < atributos.getLength(); i++) {
			tmp = atributos.item(i);
			if(tmp.getNodeType()==Node.ELEMENT_NODE) {
				artista = procesarNodo(tmp);
				System.out.println(artista);
			}
		}
		
	}
	public Artista procesarNodo(Node atributos) {
		
		Artista artista = new Artista();
		
		if(atributos.getAttributes() != null) {
			
			if(atributos.getAttributes().getNamedItem("DIRECCION") != null) {
				artista.setDireccion(atributos.getAttributes().getNamedItem("DIRECCION").getNodeValue());
			}
			
			if(atributos.getAttributes().getNamedItem("TELEFONO") != null) {
				artista.setTelefono(atributos.getAttributes().getNamedItem("TELEFONO").getNodeValue());
			}
			
			if(atributos.getAttributes().getNamedItem("MUSICA") != null) {
				artista.setMusica(atributos.getAttributes().getNamedItem("MUSICA").getNodeValue());
			}
		}
		
		Node tmp = null;
		NodeList valores = atributos.getChildNodes();
		
		for (int i = 0; i < valores.getLength(); i++) {
			tmp = valores.item(i);
			if(tmp.getNodeType() == Node.ELEMENT_NODE) {
				if(tmp.getNodeName().equalsIgnoreCase("nombre"))
					artista.setNombre(tmp.getTextContent());
				else if(tmp.getNodeName().equalsIgnoreCase("apellidos"))
					artista.setApellido(tmp.getTextContent());
				else if(tmp.getNodeName().equalsIgnoreCase("ciudad"))
					artista.setCiudad(tmp.getTextContent());
			}
		}
		
		
		return artista;
	}
}
