package ejerciciosDOM;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//import org.apache.xml.serialize.XMLSerializer;
//import org.apache.xml.serialize.OutputFormat;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class CrearTratarXmlDOM {
	
	//Declaraci�n de constantes
	private static String LIBROS = "Libros";
	private static String LIBRO = "Libro";
	private static String TITULO = "Titulo";
	private static String AUTOR = "Autor";
	private static String PUBLICADO_EN = "publicado_en";
	
	/*-----------------------------------------------------------------*/
	/*--------------- 1� Parte: Se crea el fichero XML ----------------*/
	/*-----------------------------------------------------------------*/
	
	public Document crearXMLDocument() throws ParserConfigurationException, DOMException {

		Document doc = null;
		Element raizElement = null;
		Element libro = null;
		
		try {
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
			
			raizElement = doc.createElement(LIBROS); // Creamos el elemento raiz
			doc.appendChild(raizElement); //A�adimos la raiz
			
			libro = crearNodeLibro(doc, new Libro("1840", "El Capote", "Nikolai Gogol")); // Creamos los 3 nodos Libro y los a�adimos
			raizElement.appendChild(libro);

			libro = crearNodeLibro(doc, new Libro("2008", "El Sanador de Caballo", "Gonzalo Giner"));
			raizElement.appendChild(libro);

			libro = crearNodeLibro(doc, new Libro("1981", "El Nombre de la Rosa", "Umberto Eco"));
			raizElement.appendChild(libro);
			
		} catch (ParserConfigurationException | DOMException e) {
			e.printStackTrace();
			throw e;
		}
		return doc;
	}
	
	private Element crearNodeLibro(Document doc, Libro libro) throws DOMException{
		// Libro
		Element nodeLibro = null;
		try {
			nodeLibro = doc.createElement(LIBRO);

			// atributo del elemento Libro
			Attr attr = doc.createAttribute(PUBLICADO_EN);
			attr.setValue(libro.getPublicadoEn());
			nodeLibro.setAttributeNode(attr);

			// valor del elemento Titulo
			Element titulo = doc.createElement(TITULO);
			Node node_text_titulo = doc.createTextNode(libro.getTitulo());
			titulo.appendChild(node_text_titulo);
			nodeLibro.appendChild(titulo);

			// valor del elemento Autor
			Element autor = doc.createElement(AUTOR);
			Node node_text_autor = doc.createTextNode(libro.getAutor());
			autor.appendChild(node_text_autor);
			nodeLibro.appendChild(autor);
			
		} catch (DOMException e) {
			e.printStackTrace();
			throw e;
		}

		return nodeLibro;
	}
	
	public void domToFile(Document doc, String nombreFichero) throws TransformerFactoryConfigurationError, TransformerException, IOException {
		try {
			//Transform the XML Source to a Result.
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");
			DOMSource source = new DOMSource(doc);
			
			File file = new File("files/" + nombreFichero);
			if (!file.exists()) {
				file.createNewFile();
			}
			
			StreamResult result = new StreamResult(file);	 
			transformer.transform(source, result);
		}
		catch(TransformerFactoryConfigurationError | TransformerException | IOException e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
	/*----------------------------------------------------------------------------*/
	/*--------------- 2� Parte: Tratamos los datos del XML creado ----------------*/
	/*----------------------------------------------------------------------------*/
	
	public void modificarDOMPorTitulo(Document doc, String titulOriginal, String tituloNuevo) throws DOMException{		
		Node ntemp  = null;
		Node node;
		NodeList nodos;
		
		try{
					
			Node raiz = doc.getFirstChild(); // Sacamos la raiz del Document pasado, es decir, el primer nodo del arbol
			NodeList nodelist = raiz.getChildNodes(); // Obtiene una lista de nodos con todos los nodos hijo del ra�z

			for (int i = 0; i < nodelist.getLength(); i++){
				node = nodelist.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE){ // Es un nodo elemento de Libro										
					nodos = node.getChildNodes();
					
					for (int j = 0; j < nodos.getLength(); j++){
						ntemp = nodos.item(j);

					    if(ntemp.getNodeType() == Node.ELEMENT_NODE){
					    	if (ntemp.getNodeName().equals(TITULO) && ntemp.getTextContent().equals(titulOriginal)){
					    		ntemp.setTextContent(tituloNuevo); 	//para obtener el valor con el t�tulo y autor se accede al nodo #text
					    	}
						}
					}
				}			
			}		
		}
		
		catch(DOMException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void eliminarLibro(Document doc, String titulo) throws DOMException {
		Node ntemp;
		Node nparent;
		
		try {
			
			Node raiz = doc.getFirstChild(); // Sacamos la raiz del Document pasado, es decir, el primer nodo del arbol
			NodeList nodelist = doc.getElementsByTagName(TITULO); // Obtenemos todos los elementos del arbol cuyo nombre sea Titulo, incluida la raiz
			
			for (int i = 0; i < nodelist.getLength(); i++) {
				ntemp = nodelist.item(i);
				if (ntemp instanceof Element) {
					if (titulo.equals(ntemp.getTextContent())) {
						nparent = ntemp.getParentNode();
						raiz.removeChild(nparent);  // Elimino el nodo libro, cuyo valor coincide con el titulo pasado como parametro, 
													//desde el nodo padre, es decir, desde la raiz
					}
				}
			}	
		}
		
		catch (DOMException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		
		try {
			
			// ------------------- Probamos la 1� parte -----------------------
			//creamos un arbol dom;
			CrearTratarXmlDOM createDom = new CrearTratarXmlDOM();
			Document doc = createDom.crearXMLDocument();
			System.out.println("1.1 El arbol ha sido creado y guardado en un Document");
			
			//guardamos en un XML el arbol DOM creado en el metodo anterior
			createDom.domToFile(doc, "libroscompleto_new.xml");
			System.out.println("1.2 El documento XML ha sido creado a partir de su arbol DOM");
			
			createDom.modificarDOMPorTitulo(doc, "El Capote", "el nuevo capote");
			createDom.domToFile(doc, "libroscompleto_modificado.xml");
			
			createDom.eliminarLibro(doc, "El Sanador de Caballo");
			createDom.domToFile(doc, "libroscompleto_eliminado.xml");
			
		} 
		catch (ParserConfigurationException | DOMException | TransformerFactoryConfigurationError | TransformerException | IOException e) {
			e.printStackTrace();
			throw e;
		} 
	}
}