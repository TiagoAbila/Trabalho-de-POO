package Model;
//
import java.time.LocalDate;
import java.util.ArrayList;

public class Material {
	
	private int linha;
	private String titulo;
	private String anoProducao;
	private String anoPublicacao;
	private String urlDisponivel;
	private int nrPaginas;
	private String edicao;
	private String nrISBN;
	private String nrISSN;
	private ArrayList<Autor> autores = new ArrayList();
	private ArrayList<PalavraChave> palavrasChave = new ArrayList();
	
	public Material(int linha) {
		this.setLinha(linha);
	}	
	
	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAnoProducao() {
		return anoProducao;
	}

	public void setAnoProducao(String anoProducao) {
		this.anoProducao = anoProducao;
	}

	public String getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public String getUrlDisponivel() {
		return urlDisponivel;
	}

	public void setUrlDisponivel(String urlDisponivel) {
		this.urlDisponivel = urlDisponivel;
	}

	public int getNrPaginas() {
		return nrPaginas;
	}

	public void setNrPaginas(int nrPaginas) {
		this.nrPaginas = nrPaginas;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public String getNrISBN() {
		return nrISBN;
	}

	public void setNrISBN(String nrISBN) {
		this.nrISBN = nrISBN;
	}

	public String getNrISSN() {
		return nrISSN;
	}

	public void setNrISSN(String nrISSN) {
		this.nrISSN = nrISSN;
	}
	
	public void addAutor(Autor novo) {
		if (novo == null) {
			throw new IllegalArgumentException("Autor inválido.");
		}
		this.autores.add(novo);
	}
	
	public void addProjeto(PalavraChave novo) {
		if (novo == null) {
			throw new IllegalArgumentException("Palavra Chave inválida");
		}
		this.palavrasChave.add(novo);
	}
	
	
	
}
