package costa_kalundborg.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Plads implements Serializable{

	private int plads_id;
	private Type type;
	private double price;
	private ArrayList<Booking> bookings;

	private enum Type {
		LILLE_TELT,
		STOR_TELT,
		HYTTE,
		LUKSUS_HYTTE };

		public Plads(int id, String type, double price) throws Exception{
			this.plads_id = id;
			this.price = price;
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

		public Plads(int id, String type, double price, ArrayList<Booking> bookings) throws Exception{
			this.plads_id = id;
			this.price = price;
			this.bookings = bookings;

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
		 * Tjekker om pladsen er ledig i en given tidsperiode
		 * @param start_date
		 * @param end_date
		 * @return
		 */
		public boolean checkAvailability(Date start_date, Date end_date){
			for (Booking booking : bookings){
				if (booking.compareDate(start_date, end_date))
					return false;
			}
			return true;
		}
		
		/**
		 * Tilknytter en booking til pladsen
		 * @param b
		 */
		public void book(Booking b){
			this.bookings.add(b);
		}

}
