package Model;

import java.io.Serializable;

public class Material implements Serializable {
	
	private String titulo;
	private String anoProducao;
	private String anoPublicacao;
	private String urlDisponivel;
	private String nrPaginas;
	private String edicao;
	private String nrISBN;
	private String nrISSN;
	
	
	
	public Material(String titulo, String anoProducao, String anoPublicacao, String urlDisponivel, String nrPaginas,
			String edicao, String nrISBN, String nrISSN) {
		this.setTitulo(titulo);
		this.setAnoProducao(anoProducao);
		this.setAnoPublicacao(anoPublicacao);
		this.setUrlDisponivel(urlDisponivel);
		this.setNrPaginas(nrPaginas);
		this.setEdicao(edicao);
		this.setNrISBN(nrISBN);
		this.setNrISSN(nrISSN);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if (titulo == null) {
			return;
		}
		this.titulo = titulo;
	}

	public String getAnoProducao() {
		return anoProducao;
	}

	public void setAnoProducao(String anoProducao) {
		if (anoProducao == null) {
			return;
		}
		this.anoProducao = anoProducao;
	}

	public String getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(String anoPublicacao) {
		if (anoPublicacao == null) {
			return;
		}
		this.anoPublicacao = anoPublicacao;
	}

	public String getUrlDisponivel() {
		return urlDisponivel;
	}

	public void setUrlDisponivel(String urlDisponivel) {
		if (urlDisponivel == null) {
			return;
		}
		this.urlDisponivel = urlDisponivel;
	}

	public String getNrPaginas() {
		return nrPaginas;
	}

	public void setNrPaginas(String nrPaginas) {
		if (nrPaginas == null) {
			return;
		}
		this.nrPaginas = nrPaginas;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		if (edicao == null) {
			return;
		}
		this.edicao = edicao;
	}

	public String getNrISBN() {
		return nrISBN;
	}

	public void setNrISBN(String nrISBN) {
		if (nrISBN == null) {
			return;
		}
		this.nrISBN = nrISBN;
	}

	public String getNrISSN() {
		return nrISSN;
	}

	public void setNrISSN(String nrISSN) {
		if (nrISSN == null) {
			return;
		}
		this.nrISSN = nrISSN;
	}
	
	@Override
	public String toString() {
		return titulo;
	}
	
	
}





