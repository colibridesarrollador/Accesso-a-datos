package ejerciciosDOM;

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
import org.w3c.dom.Element;


public class CrearTratarXmlDOM {

	private Document doc;
	
	public static void main(String[] args) {
		
		CrearTratarXmlDOM cd = new CrearTratarXmlDOM();
		cd.crearDocumento();
	}
	public void crearDocumento() {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
		try {
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.newDocument();
			insertarInformacion();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	public void insertarInformacion() {
		
		
		Libro libro1 = new Libro("1840","El capote","Nikolai Gogol");
		Libro libro2 = new Libro("2008","El Sanador de Caballo","Gonzalo Giner");
		Libro libro3 = new Libro("1981","Nombre de la Rosa","Umberto Eco");
		
		//NODO RAIZ
		Element libros = doc.createElement("Libros");
		libros.setAttribute("xmlns:xsi","www.w3.org/2001/XMLSchema-instance");
		doc.appendChild(libros);
		
		agregarNodo(libro1,libros);
		agregarNodo(libro2,libros);
		agregarNodo(libro3,libros);
			
		pasarADocumento();
		
	}
	public void agregarNodo(Libro libro,Element raiz) {
		
		//NODO CONTENEDOR
		Element contenedor = doc.createElement("Libro");//nodo contenedor
		
		//ATRIBUTOS
		if(libro.getPublicadoEn() != null)
			contenedor.setAttribute("Publicado_en", libro.getPublicadoEn());//atributos
		
		//ETIQUETAS
		Element titulo = doc.createElement("Titulo");
		titulo.appendChild(doc.createTextNode(libro.getTitulo()));
		contenedor.appendChild(titulo);//enlaza con el contenedor
		
		
		Element autor = doc.createElement("Autor");
		autor.appendChild(doc.createTextNode(libro.getAutor()));
		contenedor.appendChild(autor);
		
		raiz.appendChild(contenedor);
	}
	public void pasarADocumento() {
		try {
			
			TransformerFactory tf = TransformerFactory.newDefaultInstance();
			Transformer t = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new FileWriter("libros.xml"));
			t.transform(source, result);
			
		} catch (TransformerException | IOException e) {
			e.printStackTrace();
		}
		
		
	}
}