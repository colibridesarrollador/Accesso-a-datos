package ejerciciosRepaso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.naming.spi.ObjectFactoryBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONRepaso {
	
	private Peliculas misPeliculas;

	/*
	 * Crear una clase llamada JsonRepaso.java que usando el API de JSON cree un
	 * método leerFicheroJson que devuelva un objeto JAVA con el contenido del
	 * fichero peliculas.json que se adjunta y se deberá guardar en la carpeta
	 * “files_origen” dentro del proyecto, y muestre por pantalla la
	 * información del objeto JAVA devuelto.
	 */
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		JSONRepaso jr = new JSONRepaso();
		jr.leerFicheroJson(new File("peliculas.json"));

	}

	public void leerFicheroJson(File fichero) throws JsonParseException, JsonMappingException, IOException {
		 
		ObjectMapper mapper = new ObjectMapper();
		misPeliculas =  mapper.readValue(new File("peliculas.json"), Peliculas.class);
		
		for (int i = 0; i < misPeliculas.peliculas.size(); i++) {
			System.out.println(misPeliculas.peliculas.get(i));
		}
		
		
		this.guardarEnFilesOrigen();
	}
	public void guardarEnFilesOrigen() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		FileWriter fw = null;
		try {
			String json = mapper.writeValueAsString(misPeliculas);
			fw = new FileWriter("files_origen/peliculas.json");
			fw.write(json+"\r\n");
			fw.flush();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fw != null)
			fw.close();	
		}
		
	}
}
