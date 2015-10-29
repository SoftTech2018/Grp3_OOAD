package costa_kalundborg.shared;

import java.io.Serializable;

public class CampingpasDTO implements Serializable{
	
	private int kunde_id;
	private String cpas;
	
	public CampingpasDTO (int kunde_id, String cpas) {
		this.kunde_id = kunde_id;
		this.cpas = cpas;
	}

	public int getKunde_id() {
		return kunde_id;
	}

	public void setKunde_id(int kunde_id) {
		this.kunde_id = kunde_id;
	}

	public String getCpas() {
		return cpas;
	}

	public void setCpas(String cpas) {
		this.cpas = cpas;
	}
}
