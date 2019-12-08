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

    public ArrayList<String> nomesAutoresArray() {
		ArrayList<String> nomesAutores = new ArrayList();
		
		for(Autor autor: autores) {
			nomesAutores.add(autor.getAutor());
		}
		return nomesAutores;
	}
    
    public ArrayList<String> palavrasChaveArray() {
		ArrayList<String> palavrasChave = new ArrayList();
		
		for(PalavraChave palavra: palavrasChaveLinha) {
			palavrasChave.add(palavra.getPalavraChave());
		}
		return palavrasChave;
	}
	
	public void addAutor(Autor autor) {
		this.autores.add(autor);
	}
	
	public ArrayList<Autor> getAutores(){
		return this.autores;
	}
	
	public void setAutores(ArrayList<Autor> autores) {
		if (autores == null) {
			return;
		}
		this.autores = autores;
	}
	
	public Entidade getEntidadeLinha() {
		return entidadeLinha;
	}


	public void setEntidadeLinha(Entidade entidadeLinha) {
		if (entidadeLinha == null) {
			return;
		}
		this.entidadeLinha = entidadeLinha;
	}


	public int getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(int idLinha) {
		if (idLinha <= -1) {
			return;
		}
		this.idLinha = idLinha;
	}

	public Editora getEditoraLinha() {
		return editoraLinha;
	}

	public void setEditoraLinha(Editora editoraLinha) {
		if (editoraLinha == null) {
			return;
		}
		this.editoraLinha = editoraLinha;
	}

	public LocalPublicacao getLocalPubliLinha() {
		return localPubliLinha;
	}

	public void setLocalPubliLinha(LocalPublicacao localPubliLinha) {
		if (localPubliLinha == null) {
			return;
		}
		this.localPubliLinha = localPubliLinha;
	}

	public Material getMaterialLinha() {
		return materialLinha;
	}

	public void setMaterialLinha(Material materialLinha) {
		if (materialLinha == null) {
			return;
		}
		this.materialLinha = materialLinha;
	}

	public ArrayList<PalavraChave> getPalavrasChaveLinha() {
		return palavrasChaveLinha;
	}

	public void setPalavrasChaveLinha(ArrayList<PalavraChave> palavrasChaveLinha) {
		if (palavrasChaveLinha == null) {
			return;
		}
		this.palavrasChaveLinha = palavrasChaveLinha;
	}
	
	public void addPalavraChave(PalavraChave palavra) {
		this.palavrasChaveLinha.add(palavra);
	}

	public TipoDivulgacao getTipoDivulLinha() {
		return tipoDivulLinha;
	}

	public void setTipoDivulLinha(TipoDivulgacao tipoDivulLinha) {
		if (tipoDivulLinha == null) {
			return;
		}
		this.tipoDivulLinha = tipoDivulLinha;
	}

	public TipoMaterial getTipoMateLinha() {
		return tipoMateLinha;
	}

	public void setTipoMateLinha(TipoMaterial tipoMateLinha) {
		if (tipoMateLinha == null) {
			return;
		}
		this.tipoMateLinha = tipoMateLinha;
	}	
	
	
}
