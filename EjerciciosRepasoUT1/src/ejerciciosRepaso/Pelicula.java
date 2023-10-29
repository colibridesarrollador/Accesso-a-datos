package ejerciciosRepaso;

public class Pelicula {

	private String nombre;
	private String director;
	private String oscars;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getOscars() {
		return oscars;
	}
	public void setOscars(String oscars) {
		this.oscars = oscars;
	}
	@Override
	public String toString() {
		return "Pelicula [nombre=" + nombre + ", director=" + director + ", oscars=" + oscars + "]";
	}
	
	
}

