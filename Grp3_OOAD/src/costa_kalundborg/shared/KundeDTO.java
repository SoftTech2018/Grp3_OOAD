package costa_kalundborg.shared;

import java.io.Serializable;

public class KundeDTO implements Serializable {
	
	private String kundeNavn;
	private String cpr;
	private String adresse;
	private String pCode;
	private String city;
	private int id;

	public KundeDTO(){
	}
	
	public KundeDTO(String kundeNavn, String cpr, String adresse, String pCode, String city) {
		this.kundeNavn = kundeNavn;
		this.cpr = kundeNavn;
		this.adresse = adresse;
		this.pCode = pCode;
		this.city = city;
		
	}
	//kunde_navn, cpr, adresse, pCode, city, id
	public String getKundeNavn() {
		return kundeNavn;
	}


	public void setKundeNavn(String kundeNavn) {
		this.kundeNavn = kundeNavn;
	}


	public String getCpr() {
		return cpr;
	}


	public void setCpr(String cpr) {
		this.cpr = cpr;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getpCode() {
		return pCode;
	}


	public void setpCode(String pCode) {
		this.pCode = pCode;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
