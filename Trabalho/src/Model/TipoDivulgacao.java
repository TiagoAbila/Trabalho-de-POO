package Model;

import java.io.Serializable;

public class TipoDivulgacao implements Serializable {

	private String TipoDivulgacao;

	public TipoDivulgacao(String tipoDivulgacao) {
		this.setTipoDivulgacao(tipoDivulgacao);
	}

	public String getTipoDivulgacao() {
		return TipoDivulgacao;
	}

	public void setTipoDivulgacao(String tipoDivulgacao) {
		TipoDivulgacao = tipoDivulgacao;
	}
		
	@Override
	public String toString() {
		return  TipoDivulgacao;
	}
}
