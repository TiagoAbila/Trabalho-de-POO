package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Linha implements Serializable {
	private ArrayList<Autor> autores = new ArrayList();
	private ArrayList<PalavraChave> palavrasChaveLinha = new ArrayList();
	private Editora editoraLinha;
	private LocalPublicacao localPubliLinha;
	private Material materialLinha;	
	private TipoDivulgacao tipoDivulLinha;
	private TipoMaterial tipoMateLinha;
	private Entidade entidadeLinha;
	int idLinha;
	
	
	
	public Linha(int idLinha) {
		this.setIdLinha(idLinha);
	}	

	public void addAutor(Autor autor) {
		this.autores.add(autor);
	}
	
	public ArrayList<Autor> getAutores(){
		return this.autores;
	}
	
	public void setAutores(ArrayList<Autor> autores) {
		this.autores = autores;
	}
	
	public Entidade getEntidadeLinha() {
		return entidadeLinha;
	}


	public void setEntidadeLinha(Entidade entidadeLinha) {
		this.entidadeLinha = entidadeLinha;
	}


	public int getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(int idLinha) {
		this.idLinha = idLinha;
	}

	public Editora getEditoraLinha() {
		return editoraLinha;
	}

	public void setEditoraLinha(Editora editoraLinha) {
		this.editoraLinha = editoraLinha;
	}

	public LocalPublicacao getLocalPubliLinha() {
		return localPubliLinha;
	}

	public void setLocalPubliLinha(LocalPublicacao localPubliLinha) {
		this.localPubliLinha = localPubliLinha;
	}

	public Material getMaterialLinha() {
		return materialLinha;
	}

	public void setMaterialLinha(Material materialLinha) {
		this.materialLinha = materialLinha;
	}

	public ArrayList<PalavraChave> getPalavrasChaveLinha() {
		return palavrasChaveLinha;
	}

	public void setPalavrasChaveLinha(ArrayList<PalavraChave> palavrasChaveLinha) {
		this.palavrasChaveLinha = palavrasChaveLinha;
	}
	
	public void addPalavraChave(PalavraChave palavra) {
		this.palavrasChaveLinha.add(palavra);
	}

	public TipoDivulgacao getTipoDivulLinha() {
		return tipoDivulLinha;
	}

	public void setTipoDivulLinha(TipoDivulgacao tipoDivulLinha) {
		this.tipoDivulLinha = tipoDivulLinha;
	}

	public TipoMaterial getTipoMateLinha() {
		return tipoMateLinha;
	}

	public void setTipoMateLinha(TipoMaterial tipoMateLinha) {
		this.tipoMateLinha = tipoMateLinha;
	}	
	
	
}
