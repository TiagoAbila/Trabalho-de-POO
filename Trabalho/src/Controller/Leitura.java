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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Leitura {

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

				TipoMaterial novoTipoMat = new TipoMaterial(newRow[0]);
				novoTipoMat = adicionarNaLista(novoTipoMat);
				novaLinha.setTipoMateLinha(novoTipoMat);

				TipoDivulgacao novoTipoDiv = new TipoDivulgacao(newRow[1]);
				novoTipoDiv = adicionarNaLista(novoTipoDiv);
				novaLinha.setTipoDivulLinha(novoTipoDiv);

				Entidade novaEntidade = new Entidade(newRow[2]);
				novaEntidade = adicionarNaLista(novaEntidade);
				novaLinha.setEntidadeLinha(novaEntidade);

				Editora novaEditora = new Editora(newRow[11]);
				novaEditora = adicionarNaLista(novaEditora);
				novaLinha.setEditoraLinha(novaEditora);

				String[] autoresDaRow = newRow[4].split(",");
				if (autoresDaRow.length > 0) {
					for (String autor : autoresDaRow) {
						Autor novoAutor = new Autor(autor);
						novoAutor = adicionarNaLista(novoAutor);
						novaLinha.addAutor(novoAutor);
					}
				} else {
					Autor novoAutor = new Autor(newRow[4]);
					novoAutor = adicionarNaLista(novoAutor);
					novaLinha.addAutor(novoAutor);

				}

				PalavraChave novaPalavra = new PalavraChave(newRow[6]);
				novaPalavra = adicionarNaLista(novaPalavra);
				novaLinha.setPalavraChaveLinha(novaPalavra);

				LocalPublicacao novoLocal = new LocalPublicacao(newRow[10]);
				novoLocal = adicionarNaLista(novoLocal);
				novaLinha.setLocalPubliLinha(novoLocal);

				Material novoMaterial = new Material(newRow[5], newRow[7], newRow[8], newRow[13], newRow[12], newRow[9],
						newRow[14], newRow[15]);
				novoMaterial = adicionarNaLista(novoMaterial);
				novaLinha.setMaterialLinha(novoMaterial);
				linhas.add(novaLinha);
				contadorTeste++;
			}
		} catch (Exception e) {
			System.out.println("" + contadorTeste);
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
	
	public void Substituir(Material objectSource, Material objectTarget) {
		for(Linha linha: linhas) {
			if(linha.getMaterialLinha().equals(objectTarget)) {
				linha.setMaterialLinha(objectSource);
			}
		}
	}
	
	public void Substituir(PalavraChave objectSource, PalavraChave objectTarget) {
		for(Linha linha: linhas) {
			if(linha.getPalavraChaveLinha().equals(objectTarget)) {
				linha.setPalavraChaveLinha(objectSource);
			}
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
		ArrayList<Autor> a = new ArrayList();
		for(Linha linha: linhas) {
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
	

	public int getContadorTeste() {
		return this.contadorTeste;
	}

	public String teste() {
		StringBuilder montador = new StringBuilder();

		montador.append("Linhas: \n");
		for (Linha obj : linhas) {
			montador.append(obj.toString() + "\n");
		}
		montador.append(" \n");
		montador.append("Tipos de Material: \n");
		for (TipoMaterial obj : tiposDeMaterial) {
			montador.append(obj.getTipoMaterial() + "\n");
		}
		montador.append(" \n");
		montador.append("Tipos de Divulgacao: \n");
		for (TipoDivulgacao obj : tiposDeDivulgacao) {
			montador.append(obj.getTipoDivulgacao() + "\n");
		}
		montador.append(" \n");
		montador.append("Entidades: \n");
		for (Entidade obj : entidades) {
			montador.append(obj.getEntidade() + "\n");
		}
		montador.append(" \n");
		montador.append("Editoras: \n");
		for (Editora obj : editoras) {
			montador.append(obj.getEditora() + "\n");
		}
		montador.append(" \n");
		montador.append("Autor: \n");
		for (Autor obj : autores) {
			montador.append(obj.getAutor() + "\n");
		}
		montador.append(" \n");
		montador.append("Palavras Chaves: \n");
		for (PalavraChave obj : palavras) {
			montador.append(obj.getPalavraChave() + "\n");
		}
		montador.append(" \n");
		montador.append("Locais: \n");
		for (LocalPublicacao obj : locais) {
			montador.append(obj.getLocalPublicacao() + "\n");
		}
		montador.append(" \n");
		montador.append("Materiais: \n");
		for (Material obj : materiais) {
			montador.append(obj.getTitulo() + ",");
			montador.append(obj.getAnoProducao() + ",");
			montador.append(obj.getAnoPublicacao() + ",");
			montador.append(obj.getEdicao() + ",");
			montador.append(obj.getNrPaginas() + ",");
			montador.append(obj.getUrlDisponivel() + ",");
			montador.append(obj.getNrISBN() + ",");
			montador.append(obj.getNrISSN() + ",");
			// montador.append(obj.);
			montador.append("\n");
			montador.append(" \n");
		}
		return montador.toString();
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
