package recorrerYMostrarDOM;
	

public class Artista {
	
	private String nombre;
	private String apellidos;
	private String ciudad;
	private String telefono;
	private String direccion;
	private String musica;
	
	public Artista() {
		
	}
	
	public Artista(String nombre, String apellidos, String ciudad, String telefono, String direccion, String musica) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.direccion = direccion;
		this.musica = musica;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getMusica() {
		return musica;
	}
	public void setMusica(String musica) {
		this.musica = musica;
	}
	@Override
	public String toString() {
		return "Artista [nombre=" + nombre + ", apellidos=" + apellidos + ", ciudad=" + ciudad + ", telefono="
				+ telefono + ", direccion=" + direccion + ", musica=" + musica + "]";
	}
	
	
}
