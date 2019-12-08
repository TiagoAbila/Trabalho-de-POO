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
		if (editora == null) {
			return;
		}
		this.editora = editora;
	}
	
	@Override
	public String toString() {
		return  editora;
	}
}
