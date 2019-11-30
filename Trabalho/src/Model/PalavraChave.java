package Model;
//
public class PalavraChave {

	private String palavraChave;
	private int linha;
	
	public PalavraChave(int linha) {
		this.setLinha(linha);
	}
	
	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}
	
	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}
}
