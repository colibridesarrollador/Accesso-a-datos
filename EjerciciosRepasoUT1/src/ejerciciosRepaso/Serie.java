package ejerciciosRepaso;

public class Serie {
	private String nombre;
	private String productor;
	private String protagonista;
	private String anioEstreno;
	private String cadena;
	private String anioFin;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getProductor() {
		return productor;
	}
	public void setProductor(String productor) {
		this.productor = productor;
	}
	public String getProtagonista() {
		return protagonista;
	}
	public void setProtagonista(String protagonista) {
		this.protagonista = protagonista;
	}
	public String getAnioEstreno() {
		return anioEstreno;
	}
	public void setAnioEstreno(String anioEstreno) {
		this.anioEstreno = anioEstreno;
	}
	public String getCadena() {
		return cadena;
	}
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}
	public String getAnioFin() {
		return anioFin;
	}
	public void setAnioFin(String anioFin) {
		this.anioFin = anioFin;
	}
	@Override
	public String toString() {
		return "Series [nombre=" + nombre + ", productor=" + productor + ", protagonista=" + protagonista
				+ ", anioEstreno=" + anioEstreno + ", cadena=" + cadena + ", anioFin=" + anioFin + "]";
	}

	
}
