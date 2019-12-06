package Model;

import java.io.Serializable;

public class Entidade implements Serializable {
	private String entidade;
	private String tipo;
	
	public Entidade(String entidade) {
		this.setEntidade(entidade);
	}
	
	public String getEntidade() {
		return entidade;
	}
	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
