package costa_kalundborg.shared;

import java.io.Serializable;
import java.util.Date;

public class BookingDTO implements Serializable{
	
	private Date start_date;
	private Date end_date;


	/**
	 * Tjekker om de tilsendte datoer overlapper med datoerne i bookingen. Returnerer true hvis datoerne overlapper.
	 * @param start_date
	 * @param end_date
	 * @return
	 * @throws Exception
	 */
	public boolean compareDate(Date start_date, Date end_date) throws Exception {
		if (start_date.compareTo(end_date) < 0)
			throw new Exception("Slut dato er før start dato.");
		
		if (this.start_date.compareTo(end_date)>=0)
			return false;
		else if (this.end_date.compareTo(start_date)>=0 )
			return false;
		return true;
	}

}
