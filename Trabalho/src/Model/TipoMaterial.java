package Model;

import java.io.Serializable;

public class TipoMaterial implements Serializable {
	private String tipoMaterial;

	public TipoMaterial(String tipoMaterial) {
		this.setTipoMaterial(tipoMaterial);
	}

	public String getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(String tipoMaterial) {
		if (tipoMaterial == null) {
			return;
		}
		this.tipoMaterial = tipoMaterial;
	}

	@Override
	public String toString() {
		return tipoMaterial;
	}
}
