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
		this.localPublicacao = localPublicacao;
	}

}
