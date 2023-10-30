package recorrerYMostrarDOM;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.crypto.dsig.Transform;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CrearArtistaDOM {

	private Document doc;

	/*
	 * Todos los ficheros generados estarán dentro de una subcarpeta del propio
	 * proyecto de nombre "files". Crear un código limpio y comentado. La entrega
	 * estará compuesta por la carpeta del proyecto de Eclipse con el código fuente.
	 */

	public static void main(String[] args) {
		CrearArtistaDOM ca = new CrearArtistaDOM();
		ca.crearArtistaDOM();
		ca.modificarArtista("Sergio", "Manuel");
		ca.modificarArtistaDireccionDOM("Manuel", "CALLE AMAPOLA");
		ca.eliminarArtista("POP");
	}

	/*
	 * 1. Un método que cree un objeto Document igual al fichero artistas.xml,
	 * usando DOM
	 */
	public void crearArtistaDOM() {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			doc = db.newDocument();
			System.out.println("Plantilla creada.");
			crearNodos();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void crearNodos() {

		Element raiz = doc.createElement("Artistas");
		raiz.setAttribute("xmlns:xsi", "www.w3.org/2001/XMLSchema-instance");
		doc.appendChild(raiz);

		Element contenedor1 = doc.createElement("Artista");
		crearNodo(contenedor1, new Artista("Sergio", "Dalma", "123456789", null, "CALLE BURGOS", null));
		raiz.appendChild(contenedor1);

		Element contenedor2 = doc.createElement("Artista");
		crearNodo(contenedor2, new Artista("David", "Bisbal", "Almeria", "987654123", null, null));
		raiz.appendChild(contenedor2);

		Element contenedor3 = doc.createElement("Artista");
		crearNodo(contenedor3, new Artista("Alboran", "Malaga", null, "87342121", "CALLE Lugo", "POP"));
		raiz.appendChild(contenedor3);

	}

	public void crearNodo(Element contenedor, Artista artista) {

		contenedor.setAttribute("Telefono", artista.getTelefono());
		contenedor.setAttribute("DIRECCION", artista.getDireccion());
		contenedor.setAttribute("Musica", artista.getMusica());

		Element nombre = doc.createElement("Nombre");
		nombre.setTextContent(artista.getNombre());
		contenedor.appendChild(nombre);

		Element apellidos = doc.createElement("Apellidos");
		apellidos.setTextContent(artista.getApellidos());
		contenedor.appendChild(apellidos);

		Element ciudad = doc.createElement("Ciudad");
		ciudad.setTextContent(artista.getCiudad());
		contenedor.appendChild(ciudad);

	}

	public void crearFichero(File fichero) {

		TransformerFactory tf = TransformerFactory.newInstance();
		DOMSource source = new DOMSource(doc);
		StreamResult result;
		try {
			result = new StreamResult(new FileWriter(fichero));
			Transformer t = tf.newTransformer();
			t.transform(source, result);
			System.out.println("Fichero creado.");
		} catch (IOException | TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	/*
	 * . Un método (modificarArtistasDOM), al que se le pase el nombre de un artista
	 * y cambie el nombre del artista dado por un nuevo nombre que también se pasará
	 * como parámetro. Tras modificar el nombre del artista, el Document modificado
	 * se guardará en un nuevo fichero llamado artistas_modificado.xml.
	 * 
	 */

	public void modificarArtista(String nombreActual, String nombreNuevo) {
		Node raiz = doc.getFirstChild();
		NodeList atributos = raiz.getChildNodes();
		Node tmp = null;

		for (int i = 0; i < atributos.getLength(); i++) {
			tmp = atributos.item(i);
			if (tmp.getNodeType() == Node.ELEMENT_NODE) {
				buscarNombre(tmp, nombreActual, nombreNuevo);
			}
		}
		crearFichero(new File("files/artistas_modificados.xml"));
	}

	public void buscarNombre(Node atributos, String nombreActual, String nombreNuevo) {
		NodeList valores = atributos.getChildNodes();
		Node tmp = null;

		for (int i = 0; i < valores.getLength(); i++) {
			tmp = valores.item(i);
			if (tmp.getNodeType() == Node.ELEMENT_NODE) {
				if (tmp.getNodeName().equalsIgnoreCase("nombre")) {
					if (tmp.getTextContent().equals(nombreActual))
						tmp.setTextContent(nombreNuevo);
				}
			}
		}
	}

	/*
	 * Un método (modificarArtistaDireccionDOM) que modifique el nombre de la
	 * dirección de un artista por la nueva dirección dada, a partir del nombre del
	 * artista. Guardarlo como artista_direccion_nueva.xml.
	 */

	public void modificarArtistaDireccionDOM(String nombre, String nuevaDireccion) {
		Node raiz = doc.getFirstChild();
		NodeList atributos = raiz.getChildNodes();
		Node tmp = null;

		for (int i = 0; i < atributos.getLength(); i++) {
			tmp = atributos.item(i);
			if (tmp.getNodeType() == Node.ELEMENT_NODE) {
				buscarDireccion(tmp, nombre, nuevaDireccion);
			}
		}
		crearFichero(new File("files/artista_direccion_nueva.xml"));
	}

	public void buscarDireccion(Node atributos, String nombre, String direccionNueva) {

		NodeList valores = atributos.getChildNodes();
		Node tmp = null;

		for (int i = 0; i < valores.getLength(); i++) {
			tmp = valores.item(i);
			if (tmp.getNodeType() == Node.ELEMENT_NODE) {
				if (tmp.getNodeName().equalsIgnoreCase("nombre")) {
					if (tmp.getTextContent().equalsIgnoreCase(nombre)) {
						if (atributos.getAttributes().getNamedItem("DIRECCION") != null) {
							atributos.getAttributes().getNamedItem("DIRECCION").setNodeValue(direccionNueva);
							System.out.println("Dirección modificada.");
						}
					}
				}
			}
		}
	}

	/*
	 * Un método (eliminarArtista), pasando como parámetro de búsqueda el tipo de
	 * música del artista. El método debe eliminar todos aquellos artistas que
	 * tengan como tipo de música la indicada por parámetro. Solo si se ha eliminado
	 * algún artista, el nuevo XML se guarda en un nuevo fichero llamado
	 * artistas_eliminados.xml.
	 */
	public void eliminarArtista(String musica) {

		Node raiz = doc.getFirstChild();
		NodeList contenedor = raiz.getChildNodes();
		Node atributos = null;

		for (int i = 0; i < contenedor.getLength(); i++) {
			atributos = contenedor.item(i);
			if (atributos.getNodeType() == Node.ELEMENT_NODE) {
				if(atributos.getAttributes() != null) {
					if(atributos.getAttributes().getNamedItem("Musica") != null) {
						if(atributos.getAttributes().getNamedItem("Musica").getNodeValue().equalsIgnoreCase(musica)) {
							raiz.removeChild(atributos);
							System.out.println("Nodo con elemnto musica '"+musica+"' eliminado.");
							crearFichero(new File("files/artistas_eliminados.xml"));
						}
					}
				}
			}
		}
	}
	
}
