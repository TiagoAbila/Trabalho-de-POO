package Model;

import java.io.Serializable;

public class PalavraChave implements Serializable {

	private String palavraChave;

	public PalavraChave(String palavraChave) {
		this.setPalavraChave(palavraChave);
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		if (palavraChave == null) {
			return;
		}
		this.palavraChave = palavraChave;
	}
	
	@Override
	public String toString() {
		return  palavraChave;
	}
}
