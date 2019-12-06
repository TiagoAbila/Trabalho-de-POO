package Model;

import java.io.Serializable;

public class Linha implements Serializable {
	private Autor autorLinha;
	private Editora editoraLinha;
	private LocalPublicacao localPubliLinha;
	private Material materialLinha;
	private PalavraChave palavraChaveLinha;
	private TipoDivulgacao tipoDivulLinha;
	private TipoMaterial tipoMateLinha;
	private Entidade entidadeLinha;
	int idLinha;
	
	
	
	public Linha(int idLinha) {
		this.setIdLinha(idLinha);
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

	public Autor getAutorLinha() {
		return autorLinha;
	}

	public void setAutorLinha(Autor autorLinha) {
		this.autorLinha = autorLinha;
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

	public PalavraChave getPalavraChaveLinha() {
		return palavraChaveLinha;
	}

	public void setPalavraChaveLinha(PalavraChave palavraChaveLinha) {
		this.palavraChaveLinha = palavraChaveLinha;
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
