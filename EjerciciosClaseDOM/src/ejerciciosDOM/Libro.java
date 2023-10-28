package ejerciciosDOM;

public class Libro {
	private String publicadoEn = null;
	private String titulo = null;
	private String autor = null;
	
	public Libro (String publicadoEn, String titulo, String autor) {
		this.publicadoEn = publicadoEn;
		this.titulo = titulo;
		this.autor = autor;
	}
	
	public String getPublicadoEn() {
		return publicadoEn;
	}

	public void setPublicadoEn(String publicadoEn) {
		this.publicadoEn = publicadoEn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return "Libro [publicadoEn=" + publicadoEn + ", titulo=" + titulo + ", autor=" + autor + "]";
	}

}
