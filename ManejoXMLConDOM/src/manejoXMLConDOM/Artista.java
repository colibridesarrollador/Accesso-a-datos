package manejoXMLConDOM;

public class Artista {
	private String nombre;
	private String apellido;
	private String ciudad;
	private String telefono;
	private String direccion;
	private String musica;
	
	
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public String getCiudad() {
		return ciudad;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public String getMusica() {
		return musica;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void setMusica(String musica) {
		this.musica = musica;
	}
	@Override
	public String toString() {
		return "Artista [nombre=" + nombre + ", apellido=" + apellido + ", ciudad=" + ciudad + ", telefono=" + telefono
				+ ", direccion=" + direccion + ", musica=" + musica + "]";
	}
	
	
}
