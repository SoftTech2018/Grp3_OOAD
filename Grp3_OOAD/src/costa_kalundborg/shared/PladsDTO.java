package costa_kalundborg.shared;

import java.io.Serializable;

public abstract class PladsDTO implements Serializable{

	private int plads_id;
	private double price, lowprice;
	
	public PladsDTO(){
	}

	public PladsDTO(int id, double price, double lowprice){
		this.plads_id = id;
		this.price = price;
		this.lowprice = lowprice;
	}

	public int getPlads_id() {
		return plads_id;
	}

	public void setPlads_id(int plads_id) {
		this.plads_id = plads_id;
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
