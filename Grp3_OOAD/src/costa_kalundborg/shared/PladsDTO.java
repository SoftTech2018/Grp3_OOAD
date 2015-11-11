package costa_kalundborg.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class PladsDTO implements Serializable{

	private int plads_id;
	private PladsType type;
	private double price, lowprice;

//	private enum Type {
//		LILLE_TELT,
//		STOR_TELT,
//		HYTTE,
//		LUKSUS_HYTTE }

	public PladsDTO(){
	}

	public PladsDTO(int id, String type, double price, double lowprice) throws Exception{
		this.plads_id = id;
		this.price = price;
		this.lowprice = lowprice;
		switch(type){
		case "LILLE_TELT":
			this.type = PladsType.LILLE_TELT;
			break;
		case "STOR_TELT":
			this.type = PladsType.STOR_TELT;
			break;
		case "HYTTE":
			this.type = PladsType.HYTTE;
			break;
		case "LUKSUS_HYTTE":
			this.type = PladsType.LUKSUS_HYTTE;
			break;
		default:
			throw new Exception("Ukendt pladstype!");
		}
	}

	public int getPlads_id() {
		return plads_id;
	}

	public void setPlads_id(int plads_id) {
		this.plads_id = plads_id;
	}

	public String getType() {
		return type.toString();
	}

	public void setType(String type) throws Exception {
		switch(type){
		case "LILLE_TELT":
			this.type = PladsType.LILLE_TELT;
			break;
		case "STOR_TELT":
			this.type = PladsType.STOR_TELT;
			break;
		case "HYTTE":
			this.type = PladsType.HYTTE;
			break;
		case "LUKSUS_HYTTE":
			this.type = PladsType.LUKSUS_HYTTE;
			break;
		default:
			throw new Exception("Ukendt pladstype!");
		}
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getLowprice() {
		return lowprice;
	}

	public void setLowprice(double lowprice) {
		this.lowprice = lowprice;
	}

}
