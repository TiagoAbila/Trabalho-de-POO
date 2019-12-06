package Model;

import java.io.Serializable;

public class Editora implements Serializable {
	private String editora;

	public Editora(String editora) {
		this.setEditora(editora);
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}
}
