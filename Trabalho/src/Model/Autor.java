package Model;
//
public class Autor {

	private String autor;
    private int linha;
    
    public Autor(int linha) {
    	this.setLinha(linha);
    }
    
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}
}
