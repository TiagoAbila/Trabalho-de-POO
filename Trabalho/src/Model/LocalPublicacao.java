package Model;

import java.io.Serializable;

public class LocalPublicacao implements Serializable {
	private String localPublicacao;

	public LocalPublicacao(String localPublicacao) {
		this.setLocalPublicacao(localPublicacao);
	}

	public String getLocalPublicacao() {
		return localPublicacao;
	}

	public void setLocalPublicacao(String localPublicacao) {
		if (localPublicacao == null) {
			localPublicacao = " ";
		}
		this.localPublicacao = localPublicacao;
	}
	
	@Override
	public String toString() {
		return  localPublicacao;
	}

}
