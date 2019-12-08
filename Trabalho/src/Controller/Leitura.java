package Controller;

import Model.*;

//
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Leitura implements Serializable {

	private ArrayList<Linha> linhas = new ArrayList();
	private ArrayList<Editora> editoras = new ArrayList();
	private ArrayList<Autor> autores = new ArrayList();
	private ArrayList<TipoMaterial> tiposDeMaterial = new ArrayList();
	private ArrayList<LocalPublicacao> locais = new ArrayList();
	private ArrayList<TipoDivulgacao> tiposDeDivulgacao = new ArrayList();
	private ArrayList<Material> materiais = new ArrayList();
	private ArrayList<PalavraChave> palavras = new ArrayList();
	private ArrayList<Entidade> entidades = new ArrayList();
	private int linhaCorrente = 0;
	private static int contadorTeste = 0;

	public Leitura(File arquivo) throws IOException {
		FileReader leitor = new FileReader(arquivo);
		BufferedReader ler = new BufferedReader(leitor);
		String l;
		String[] newRow;
		try {
			while ((l = ler.readLine()) != null) {
				Linha novaLinha = new Linha(linhaCorrente++);
				newRow = (l.replaceAll(";", " ; ")).split(";");

				TipoMaterial novoTipoMat = new TipoMaterial(newRow[0].trim());
				novoTipoMat = adicionarNaLista(novoTipoMat);
				novaLinha.setTipoMateLinha(novoTipoMat);

				TipoDivulgacao novoTipoDiv = new TipoDivulgacao(newRow[1].trim());
				novoTipoDiv = adicionarNaLista(novoTipoDiv);
				novaLinha.setTipoDivulLinha(novoTipoDiv);

				Entidade novaEntidade = new Entidade(newRow[2],newRow[3].trim());
				novaEntidade = adicionarNaLista(novaEntidade);
				novaLinha.setEntidadeLinha(novaEntidade);

				Editora novaEditora = new Editora(newRow[11].trim());
				novaEditora = adicionarNaLista(novaEditora);
				novaLinha.setEditoraLinha(novaEditora);

				String[] autoresDaRow = newRow[4].split("[,|]");
				
				if (autoresDaRow.length > 0) {
					for (String autor : autoresDaRow) {
						Autor novoAutor = new Autor(autor.trim());
						novoAutor = adicionarNaLista(novoAutor);
						novaLinha.addAutor(novoAutor);
					}
				} else {
					Autor novoAutor = new Autor(newRow[4].trim());
					novoAutor = adicionarNaLista(novoAutor);
					novaLinha.addAutor(novoAutor);

				}

				String[] palavrasDaRow = newRow[6].split("[|,-]");
				
				if (palavrasDaRow.length > 0) {
					for (String palavra : palavrasDaRow) {
						PalavraChave novaPalavra = new PalavraChave(palavra.trim());
						novaPalavra = adicionarNaLista(novaPalavra);
						novaLinha.addPalavraChave(novaPalavra);
					}
				} else {
					PalavraChave novaPalavra = new PalavraChave(newRow[6].trim());
					novaPalavra = adicionarNaLista(novaPalavra);
					novaLinha.addPalavraChave(novaPalavra);

				}

				LocalPublicacao novoLocal = new LocalPublicacao(newRow[10].trim());
				novoLocal = adicionarNaLista(novoLocal);
				novaLinha.setLocalPubliLinha(novoLocal);

				Material novoMaterial = new Material(newRow[5].trim(), newRow[7].trim(), newRow[8].trim(), newRow[13].trim(), newRow[12].trim(), newRow[9].trim(),
						newRow[14].trim(), newRow[15].trim());
				novoMaterial = adicionarNaLista(novoMaterial);
				novaLinha.setMaterialLinha(novoMaterial);
				linhas.add(novaLinha);
				contadorTeste++;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	

	
	public void Substituir(Editora objectSource, Editora objectTarget) {
		for(Linha linha: linhas) {
			if(linha.getEditoraLinha().equals(objectTarget)) {
				linha.setEditoraLinha(objectSource);
			}
		}
	}
	
	public void Substituir(Entidade objectSource, Entidade objectTarget) {
		for(Linha linha: linhas) {
			if(linha.getEntidadeLinha().equals(objectTarget)) {
				linha.setEntidadeLinha(objectSource);
			}
		}
	}
	
	public void Substituir(LocalPublicacao objectSource, LocalPublicacao objectTarget) {
		for(Linha linha: linhas) {
			if(linha.getLocalPubliLinha().equals(objectTarget)) {			
				linha.setLocalPubliLinha(objectSource);
			}
		}
	}
	
	public void Substituir(Material objectSource, String titulo, String anoProducao, String anoPublicacao, String urlDisponivel, String nrPaginas,
			String edicao, String nrISBN, String nrISSN) {
		objectSource.setAnoProducao(anoProducao);
		objectSource.setAnoPublicacao(anoPublicacao);
		objectSource.setEdicao(edicao);
		objectSource.setNrISSN(nrISSN);
		objectSource.setNrISBN(nrISBN);
		objectSource.setNrPaginas(nrPaginas);
		objectSource.setTitulo(titulo);
		objectSource.setUrlDisponivel(urlDisponivel);
	}
	
	
	public void Substituir(PalavraChave objectSource, PalavraChave objectTarget) {
		for(Linha linha: linhas) {
			ArrayList<PalavraChave> a = new ArrayList();
			for(PalavraChave palavra: linha.getPalavrasChaveLinha()) {
				if(palavra.equals(objectTarget)){
					a.add(objectSource);
				}else{
					a.add(palavra);
				}
			}
			linha.setPalavrasChaveLinha(a);
		}
	}
	
	public void Substituir(TipoDivulgacao objectSource, TipoDivulgacao objectTarget) {
		for(Linha linha: linhas) {
			if(linha.getTipoDivulLinha().equals(objectTarget)) {
				linha.setTipoDivulLinha(objectSource);
			}
		}
	}
	
	public void Substituir(TipoMaterial objectSource, TipoMaterial objectTarget) {
		for(Linha linha: linhas) {
			if(linha.getTipoMateLinha().equals(objectTarget)) {
				linha.setTipoMateLinha(objectSource);
			}
		}
	}
	
	public void Substituir(Autor objectSource, Autor objectTarget) {	
		for(Linha linha: linhas) {
			ArrayList<Autor> a = new ArrayList();
			for(Autor autor: linha.getAutores()) {
				if(autor.equals(objectTarget)){
					a.add(objectSource);
				}else{
					a.add(autor);
				}
			}
			linha.setAutores(a);
		}
	}

	public TipoMaterial adicionarNaLista(TipoMaterial objeto) {
		String nomeObj = objeto.getTipoMaterial().toLowerCase();
		for (TipoMaterial obj : tiposDeMaterial) {
			if ((obj.getTipoMaterial().toLowerCase()).equals(nomeObj)) {
				objeto = obj;
			}
		}
		if (!tiposDeMaterial.contains(objeto)) {
			tiposDeMaterial.add(objeto);
		}
		return objeto;
	}

	public TipoDivulgacao adicionarNaLista(TipoDivulgacao objeto) {
		String nomeObj = objeto.getTipoDivulgacao().toLowerCase();
		for (TipoDivulgacao obj : tiposDeDivulgacao) {
			if ((obj.getTipoDivulgacao().toLowerCase()).equals(nomeObj)) {
				objeto = obj;
			}
		}
		if (!tiposDeDivulgacao.contains(objeto)) {
			tiposDeDivulgacao.add(objeto);
		}
		return objeto;
	}

	public Entidade adicionarNaLista(Entidade objeto) {
		String nomeObj = objeto.getEntidade().toLowerCase();
		for (Entidade obj : entidades) {
			if ((obj.getEntidade().toLowerCase()).equals(nomeObj)) {
				objeto = obj;
			}
		}
		if (!entidades.contains(objeto)) {
			entidades.add(objeto);
		}
		return objeto;
	}

	public Editora adicionarNaLista(Editora objeto) {
		String nomeObj = objeto.getEditora().toLowerCase();
		for (Editora obj : editoras) {
			if ((obj.getEditora().toLowerCase()).equals(nomeObj)) {
				objeto = obj;
			}
		}
		if (!editoras.contains(objeto)) {
			editoras.add(objeto);
		}
		return objeto;
	}

	public Autor adicionarNaLista(Autor objeto) {
		String nomeObj = objeto.getAutor().toLowerCase();
		for (Autor obj : autores) {
			if ((obj.getAutor().toLowerCase()).equals(nomeObj)) {
				objeto = obj;
			}
		}
		if (!autores.contains(objeto)) {
			autores.add(objeto);
		}
		return objeto;
	}

	public PalavraChave adicionarNaLista(PalavraChave objeto) {
		String nomeObj = objeto.getPalavraChave().toLowerCase();
		for (PalavraChave obj : palavras) {
			if ((obj.getPalavraChave().toLowerCase()).equals(nomeObj)) {
				objeto = obj;
			}
		}
		if (!palavras.contains(objeto)) {
			palavras.add(objeto);
		}
		return objeto;
	}

	public LocalPublicacao adicionarNaLista(LocalPublicacao objeto) {
		String nomeObj = objeto.getLocalPublicacao().toLowerCase();
		for (LocalPublicacao obj : locais) {
			if ((obj.getLocalPublicacao().toLowerCase()).equals(nomeObj)) {
				objeto = obj;
			}
		}
		if (!locais.contains(objeto)) {
			locais.add(objeto);
		}
		return objeto;
	}

	public Material adicionarNaLista(Material objeto) {
		String nomeObj = objeto.getTitulo().toLowerCase();
		for (Material obj : materiais) {
			if ((obj.getTitulo().toLowerCase()).equals(nomeObj)) {
				objeto = obj;
			}
		}
		if (!materiais.contains(objeto)) {
			materiais.add(objeto);
		}
		return objeto;
	}

	public ArrayList<Linha> getLinhas() {
		return linhas;
	}

	public void setLinhas(ArrayList<Linha> linhas) {
		this.linhas = linhas;
	}

	public ArrayList<Editora> getEditoras() {
		return editoras;
	}

	public void setEditoras(ArrayList<Editora> editoras) {
		this.editoras = editoras;
	}

	public ArrayList<Autor> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<Autor> autores) {
		this.autores = autores;
	}

	public ArrayList<TipoMaterial> getTiposDeMaterial() {
		return tiposDeMaterial;
	}

	public void setTiposDeMaterial(ArrayList<TipoMaterial> tiposDeMaterial) {
		this.tiposDeMaterial = tiposDeMaterial;
	}

	public ArrayList<LocalPublicacao> getLocais() {
		return locais;
	}

	public void setLocais(ArrayList<LocalPublicacao> locais) {
		this.locais = locais;
	}

	public ArrayList<TipoDivulgacao> getTiposDeDivulgacao() {
		return tiposDeDivulgacao;
	}

	public void setTiposDeDivilgacao(ArrayList<TipoDivulgacao> tiposDeDivilgacao) {
		this.tiposDeDivulgacao = tiposDeDivilgacao;
	}

	public ArrayList<Material> getMaterias() {
		return materiais;
	}

	public void setMaterias(ArrayList<Material> materias) {
		this.materiais = materias;
	}

	public ArrayList<PalavraChave> getPalavras() {
		return palavras;
	}

	public void setPalavras(ArrayList<PalavraChave> palavras) {
		this.palavras = palavras;
	}

	public ArrayList<Entidade> getEntidades() {
		return entidades;
	}

	public void setEntidades(ArrayList<Entidade> entidades) {
		this.entidades = entidades;
	}

	public int getLinhaCorrente() {
		return linhaCorrente;
	}

	public void setLinhaCorrente(int linhaCorrente) {
		this.linhaCorrente = linhaCorrente;
	}

}
