package costa_kalundborg.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class PladsDTO implements Serializable{

	private int plads_id;
	private Type type;
	private double price, lowprice;
	private ArrayList<BookingDTO> bookings;

	private enum Type {
		LILLE_TELT,
		STOR_TELT,
		HYTTE,
		LUKSUS_HYTTE }

	public PladsDTO(){

	}

	public PladsDTO(int id, String type, double price, double lowprice) throws Exception{
		this.plads_id = id;
		this.price = price;
		this.lowprice = lowprice;
		switch(type){
		case "LILLE_TELT":
			this.type = Type.LILLE_TELT;
			break;
		case "STOR_TELT":
			this.type = Type.STOR_TELT;
			break;
		case "HYTTE":
			this.type = Type.HYTTE;
			break;
		case "LUKSUS_HYTTE":
			this.type = Type.LUKSUS_HYTTE;
			break;
		default:
			throw new Exception("Ukendt pladstype!");
		}
	}

	public PladsDTO(int id, String type, double price, double lowprice, ArrayList<BookingDTO> bookings) throws Exception{
		this.plads_id = id;
		this.price = price;
		this.bookings = bookings;
		this.lowprice = lowprice;
		switch(type){
		case "LILLE_TELT":
			this.type = Type.LILLE_TELT;
			break;
		case "STOR_TELT":
			this.type = Type.STOR_TELT;
			break;
		case "HYTTE":
			this.type = Type.HYTTE;
			break;
		case "LUKSUS_HYTTE":
			this.type = Type.LUKSUS_HYTTE;
			break;
		default:
			throw new Exception("Ukendt pladstype!");
		}
	}

	/**
	 * Tjekker om pladsen er ledig i en given tidsperiode. Returnerer true hvis pladsen er ledig.
	 * @param start_date
	 * @param end_date
	 * @return
	 * @throws Exception 
	 */
	//		public boolean checkAvailability(String start_date, String end_date) throws Exception{
	//			for (BookingDTO booking : bookings){
	//				if (booking.compareCalendar(start_date, end_date))
	//					return false;
	//			}
	//			return true;
	//		}

	/**
	 * Tilknytter en booking til pladsen
	 * @param b
	 */
	public void book(BookingDTO b){
		this.bookings.add(b);
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
			this.type = Type.LILLE_TELT;
			break;
		case "STOR_TELT":
			this.type = Type.STOR_TELT;
			break;
		case "HYTTE":
			this.type = Type.HYTTE;
			break;
		case "LUKSUS_HYTTE":
			this.type = Type.LUKSUS_HYTTE;
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

	public ArrayList<BookingDTO> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<BookingDTO> bookings) {
		this.bookings = bookings;
	}

	public double getLowprice() {
		return lowprice;
	}

	public void setLowprice(double lowprice) {
		this.lowprice = lowprice;
	}

}
