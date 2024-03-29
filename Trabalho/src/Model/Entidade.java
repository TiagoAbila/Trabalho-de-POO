package Model;

import java.io.Serializable;

public class Entidade implements Serializable {
	private String entidade;
	private String tipo;
	
	public Entidade(String entidade, String tipo) {
		this.setEntidade(entidade);
		this.setTipo(tipo);
	}
	
	public String getEntidade() {
		return entidade;
	}
	public void setEntidade(String entidade) {
		if (entidade == null) {
			entidade = " ";
		}
		this.entidade = entidade;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		if (tipo == null) {
			tipo = " ";
		}
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return  entidade;
	}	
}