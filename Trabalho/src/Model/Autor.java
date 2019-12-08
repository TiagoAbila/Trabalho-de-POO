package Model;

import java.io.Serializable;

public class Autor implements Serializable {

	private String autor;

	public Autor(String autor) {
		this.setAutor(autor);
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		if (autor == null) {
			autor = " ";
		}
		this.autor = autor;
	}
	
	@Override
	public String toString() {
		return  autor;
	}
}
