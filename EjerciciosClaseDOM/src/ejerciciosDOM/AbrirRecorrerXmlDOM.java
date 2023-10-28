package ejerciciosDOM;

import java.io.File;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

//import org.apache.xml.serialize.XMLSerializer;
//import org.apache.xml.serialize.OutputFormat;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AbrirRecorrerXmlDOM {
	
	public Document abrirXMLDOM(File fichero) throws Exception {
		Document doc = null;
		
		try {
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // Creamos un objeto de la clase DocumentBuilderFactory
			
			dbf.setIgnoringComments(true); // Indicamos que el modelo DOM debe ignorar los comentarios que aparezcan en el XML
			dbf.setIgnoringElementContentWhitespace(true); // Indicamos que el modelo DOM debe ignorar los espacios en blanco del XML
			
			DocumentBuilder db = dbf.newDocumentBuilder(); // Se crea un objeto DocumentBuilder para cargar en �l la estructura de �rbol DOM a partir del XML seleccionado
			
			doc = db.parse(fichero); // Interpreta (parsea) el documento XML (fichero) y genera el DOM equivalente
			return doc;
			
		}
		
		catch (Exception e){
			e.printStackTrace();
			throw e;
			
		}
		
	}
	
	public String recorreroDOMyMostrar(Document doc) throws Exception {
		String datos_nodo [] = null;
		String salida = "";
		Node nodo;
		
		try {
			Node raiz = doc.getFirstChild(); // Obtiene el nodo principal del arbol generado por DOM (el nodo raiz)
			NodeList listaNodosRaiz = raiz.getChildNodes(); // Obtiene una lista de nodos con todos los nodos hijo del ra�z
			
			for (int i = 0; i < listaNodosRaiz.getLength(); i++) {
				nodo = listaNodosRaiz.item(i);
				if (nodo.getNodeType() == Node.ELEMENT_NODE) { // Es un nodo elemento de Libro
					datos_nodo = procesarNodoLibro(nodo);
					salida=salida + "\n " + "Publicado en: " + datos_nodo[0];
					salida=salida + "\n " + "El t�tulo es: " + datos_nodo[1];
					salida=salida + "\n " + "El autor es: " + datos_nodo[2] + "\n ";
				}
			}
			return salida;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
			
	}
	
	public String [] procesarNodoLibro(Node nodo) {
		String datos[]= new String[3];
		Node nodo_tmp = null;
		int contador = 1;
		
		datos[0]=nodo.getAttributes().item(0).getNodeValue(); // Obtenemos el valor del atributo "Publicado en:"
		
		NodeList nodos_hijos = nodo.getChildNodes();
		for (int i = 0; i<nodos_hijos.getLength(); i++) {
			nodo_tmp = nodos_hijos.item(i);
			if (nodo_tmp.getNodeType() == Node.ELEMENT_NODE) {
				datos[contador] = nodo_tmp.getChildNodes().item(0).getNodeValue(); // Para obtener el valor del nodo T�tulo y Autor, accedemos al nodo #text
				contador ++;
			}
		}
		
		return datos;
	}
	
	public static void main(String[] args) throws Exception {
		
		AbrirRecorrerXmlDOM doc_dom = new AbrirRecorrerXmlDOM();
		File fichero = new File("libroscompleto.xml");
		Document doc = null;
		doc = doc_dom.abrirXMLDOM(fichero);
		System.out.println("Proceso abrir fichero DOM desde fichero XML terminado");
		
		String salida = doc_dom.recorreroDOMyMostrar(doc);
		System.out.println(salida);
		System.out.println();
		System.out.println("Proceso recorrer y mostrar documento DOM desde fichero XML terminado");
		
	}

}
