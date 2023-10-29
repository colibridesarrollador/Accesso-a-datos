package ejerciciosRepaso;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMRepaso {

	private Document doc;
	/*
	 * Crear una clase llamada DomRepaso.java, que usando el API de DOM realice los
	 * siguientes puntos partiendo del fichero XML: jugadores.xml que se adjunta y
	 * se deberá guardar en la carpeta “files_origen” dentro del proyecto
	 */

	public static void main(String[] args) {

		DOMRepaso dr = new DOMRepaso();
		dr.abrirXML();
		dr.modificarRojiblancos();
		
	}
	/*
	 * a. Crear un método llamado abrirYLeerJugadores que muestre por consola la
	 * información almacenada en el fichero jugadores.xml.
	 * 
	 */

	public void abrirXML(){
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringComments(true);
		dbf.setIgnoringElementContentWhitespace(true);
		try {
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse("jugadores.xml");
			
			mostrarPorConsola();
		} catch (SAXException e) {				
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		
	}
	public void mostrarPorConsola() {
		Jugador jugador = null;
		Node raiz = doc.getFirstChild();
		NodeList atributos = raiz.getChildNodes();
		Node tmp = null;
		
		for (int i = 0; i < atributos.getLength(); i++) {
			tmp = atributos.item(i);
			if(tmp.getNodeType()==Node.ELEMENT_NODE) {
			jugador = procesarNodo(tmp);
			System.out.println(jugador);
			}
		}
	}
	
	public Jugador procesarNodo(Node atributos) {
		
		Jugador jugador = new Jugador();
		
		if(atributos.getAttributes() != null) {
			if(atributos.getAttributes().getNamedItem("equipo") != null)
				jugador.setEquipo(atributos.getAttributes().getNamedItem("equipo").getNodeValue());
			if(atributos.getAttributes().getNamedItem("seleccion") != null)
				jugador.setSeleccion(atributos.getAttributes().getNamedItem("seleccion").getNodeValue());
			if(atributos.getAttributes().getNamedItem("posicion") != null)
				jugador.setPosicion(atributos.getAttributes().getNamedItem("posicion").getNodeValue());
		}
			
		NodeList valores = atributos.getChildNodes();
		Node tmp = null;
		
		for (int i = 0; i < valores.getLength(); i++) {
			tmp = valores.item(i);
			if(tmp.getNodeType()==Node.ELEMENT_NODE) {
				if(tmp.getNodeName().equalsIgnoreCase("nombre"))
					jugador.setNombre(tmp.getTextContent());
				else if(tmp.getNodeName().equalsIgnoreCase("apellido"))
					jugador.setApellido(tmp.getTextContent());
				else if(tmp.getNodeName().equalsIgnoreCase("dorsal"))
					jugador.setDorsal(tmp.getTextContent());
			}
		}	
		
		return jugador;
	}
	/*
	 * Crear un método llamado modificarRojiblancos que modifique el equipo de todos
	 * los jugadores que pertenezcan al Atletico por el nuevo nombre de Atletico de
	 * Madrid. La información modificada se debe guardar en un nuevo fichero llamado
	 * “jugadores_modificados.xml”, que se guardará en la carpeta “files_salidas”.
	 */
	
	public void modificarRojiblancos() {
		
		Node raiz = doc.getFirstChild();
		NodeList atributos = raiz.getChildNodes();
		Node tmp = null;
		
		for (int i = 0; i < atributos.getLength(); i++) {
			tmp = atributos.item(i);
			if(tmp.getNodeType()==Node.ELEMENT_NODE)
				if(tmp.getAttributes().getNamedItem("equipo") != null)
					tmp.getAttributes().getNamedItem("equipo").setNodeValue("Atletico de Madrid");
		}
		guardarFichero();
	}
	public void guardarFichero() {
		
		TransformerFactory tf = TransformerFactory.newInstance();
		try {
			Transformer t = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new FileWriter("jugadores_modificados.xml"));
			t.transform(source, result);
		} catch (TransformerException | IOException e) {
			e.printStackTrace();
		}
		
	}
}
