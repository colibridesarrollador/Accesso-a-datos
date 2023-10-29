package ejerciciosRepaso;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMSeries {

	private Document doc;

	/*
	 * Crear una clase llamada DomSeries.java, que usando el API de DOM realice los
	 * siguientes puntos partiendo del fichero XML: series.xml que se adjunta y se
	 * deberá guardar en la carpeta “files_series” dentro del proyecto:
	 * 
	 */
	public static void main(String[] args) {
		DOMSeries ds = new DOMSeries();
		ds.abrirYLeerSeries();
		ds.eliminarNombreSerieByAnio(2009);
	}

	/*
	 * a. Crear un método llamado abrirYLeerSeries que muestre por consola la
	 * información almacenada en el fichero series.xml.
	 */
	public void abrirYLeerSeries() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringComments(true);
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			doc = db.parse("series.xml");
			leerDoc();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void leerDoc() {
		Serie serie = new Serie();
		Node raiz = doc.getFirstChild();
		NodeList atributos = raiz.getChildNodes();
		Node tmp = null;

		for (int i = 0; i < atributos.getLength(); i++) {
			tmp = atributos.item(i);
			if (tmp.getNodeType() == Node.ELEMENT_NODE) {
				serie = procesarNodo(tmp);
				System.out.println(serie);
			}
		}
	}

	public Serie procesarNodo(Node atributos) {

		Serie serie = new Serie();

		if (atributos.getAttributes() != null) {
			if (atributos.getAttributes().getNamedItem("anioEstreno") != null)
				serie.setAnioEstreno(atributos.getAttributes().getNamedItem("anioEstreno").getNodeValue());
			if (atributos.getAttributes().getNamedItem("cadena") != null)
				serie.setCadena(atributos.getAttributes().getNamedItem("cadena").getNodeValue());
			if (atributos.getAttributes().getNamedItem("anioFin") != null)
				serie.setAnioFin(atributos.getAttributes().getNamedItem("anioFin").getNodeValue());

			NodeList etiquetas = atributos.getChildNodes();
			Node tmp = null;

			for (int i = 0; i < etiquetas.getLength(); i++) {
				tmp = etiquetas.item(i);
				if (tmp.getNodeType() == Node.ELEMENT_NODE) {
					if (tmp.getNodeName().equalsIgnoreCase("nombre"))
						serie.setNombre(tmp.getTextContent());
					else if (tmp.getNodeName().equalsIgnoreCase("productor"))
						serie.setProductor(tmp.getTextContent());
					else if (tmp.getNodeName().equalsIgnoreCase("protagonista"))
						serie.setProtagonista(tmp.getTextContent());
				}
			}
		}

		return serie;
	}

	/*
	 * b. Crear un método llamado eliminarNombreSerieByAnio que elimine el nombre de
	 * todas aquellas series cuyo año de finalización sea menor al año pasado como
	 * parámetro. En el caso de que se haya eliminado el nombre de alguna serie,
	 * generar un nuevo fichero “series_eliminadas.xml” con la nueva información,
	 * que se guardará en la carpeta “files_salidas_series”.
	 * 
	 */

	public void eliminarNombreSerieByAnio(int anio) {

		Node raiz = doc.getFirstChild();
		NodeList atributos = raiz.getChildNodes();
		Node tmp = null;
		boolean crearDocumento = false;
		boolean algunoEliminado = false;
		for (int i = 0; i < atributos.getLength(); i++) {
			tmp = atributos.item(i);
			if (tmp.getNodeType() == Node.ELEMENT_NODE) {
			algunoEliminado = buscarPorFecha(tmp, anio);
			} 
			if(algunoEliminado) {
				crearDocumento = true;
			}
		}

		if(crearDocumento) {
			crearDocumento();
		}
	}

	public boolean buscarPorFecha(Node atributos, int anio) {

		boolean eliminar = false;
		boolean eliminado = false;
		if (atributos.getAttributes() != null) {
			if (atributos.getAttributes().getNamedItem("anioEstreno") != null) {
				int anioPelicula = Integer
						.parseInt(atributos.getAttributes().getNamedItem("anioEstreno").getNodeValue());
				if (anioPelicula < anio) {
					eliminar = true;
				}
			}

			if(eliminar) {
				
				NodeList etiquetas = atributos.getChildNodes();
				Node tmp = null;
	
				for (int i = 0; i < etiquetas.getLength(); i++) {
					tmp = etiquetas.item(i);
					if (tmp.getNodeType() == Node.ELEMENT_NODE) {
						if (tmp.getNodeName().equalsIgnoreCase("nombre"))
							tmp.setTextContent("");
							eliminado = true;
					}
				}
			}
		}
		return eliminado;
	}

	public void crearDocumento() {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t;
		try {
			t = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new FileWriter("files_salidas_series/series_eliminadas.xml"));
			t.transform(source, result);
		} catch (TransformerException | IOException e) {
			e.printStackTrace();
		}
	}
}
