package costa_kalundborg.server;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import cdio.server.DAL.Connector;
import cdio.shared.UserDTO;
import costa_kalundborg.shared.BookingDTO;
import costa_kalundborg.shared.DALException;
import costa_kalundborg.shared.Booking;
import costa_kalundborg.shared.KundeDTO;
import costa_kalundborg.shared.PladsDTO;


public class DAO {	

	public DAO() {
		
	}
	
	public static KundeDTO getCustomer(String cpr) {
		ResultSet rs = Connector.doQuery(/*SQL statement med cpr'en den får medsendt*/);
		try { 
			if (!rs.first()) throw new DALException("Kunde " + cpr + " findes ikke");
				//kunde_navn, cpr, adresse, pCode, city, id
				return new KundeDTO (rs.getInt("id"), rs.getString("kunde_navn"), rs.getString("cpr"), rs.getString("adresse"), rs.getString("pCode"), rs.getString("city"));				
		}
		catch (SQLException e) {throw new DALException(e); }
	}
	
	public static void createCustomer(KundeDTO c) {
		Connector.doUpdate(/*SQL statement med Kunde-objektet den får medsendt*/);
//		c.getKundeNavn()
//		c.getCpr()
//		c.getAdresse()
//		c.getpCode()
//		c.getCity()
		
	}
	
	public static BookingDTO getBooking(int id) {
		ResultSet rs = Connector.doQuery(/*SQL statement med id'en den får medsendt*/);
		BookingDTO b;
		try { 
			if (!rs.first()) throw new DALException("Booking med id:  " + id + " findes ikke");
				//start_date, end_date, status, electric, dog, xtraPerson, camel, kunde_id, plads_id
				b = new BookingDTO (getDate(rs.getString("start_date")), getDate(rs.getString("end_date")), getStatus(rs.getString("status")), rs.getString("electric"), rs.getInt("dog"), rs.getInt("xtraPerson"), rs.getInt("camel"), rs.getInt("kunde_id"), rs.getInt("plads_id"));				
				
		}		
		catch (SQLException e) {throw new DALException(e); }
		return b; /*Resultat fra database Booking*/
	}
	
		public static String getDate(String date) {
//			String day = date.substring(0, 2);
//			String month = date.substring(3, 4);
//			String year = date.substring(6, 9);
//			
//			int dayInt = Integer.parseInt(day);
//			int monthInt = Integer.parseInt(month);
//			int yearInt = Integer.parseInt(year);
//			
//			Calendar dateReturn = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
//			dateReturn.set(yearInt, monthInt, dayInt);
			return date;
			
		}
		
		public static String getStatus(String status) {
			return status;
		}
	
	public static void createBooking(BookingDTO b) {
		Connector.doUpdate(/*SQL statement med Booking-objektet den får medsendt*/);
	}
	
	public static PladsDTO getPlads(int plads) {
		ResultSet rs = Connector.doQuery(/*SQL statement med id'en den får medsendt*/);
		PladsDTO p;
		try { 
			if (!rs.first()) throw new DALException("Booking med id:  " + plads + " findes ikke");
				//start_date, end_date, status, electric, dog, xtraPerson, camel, kunde_id, plads_id
				p = new PladsDTO (rs.getInt("plads_id"), rs.getString("type"), rs.getDouble("price"), rs.getDouble("lowprice"), getBookings(plads));				
				
		}		
		catch (SQLException e) {throw new DALException(e); }
		return p; /*Resultat fra database Booking*/
	}
	
	public static ArrayList<BookingDTO> getBookings(int plads) {
		ArrayList<BookingDTO> list = new ArrayList<BookingDTO>();
		ResultSet rs = Connector.doQuery(/*Sql statement med id'en den får medsendt*/);
		try
		{
			while (rs.next()) 
			{
				list.add(new BookingDTO (getDate(rs.getString("start_date")), getDate(rs.getString("end_date")), getStatus(rs.getString("status")), rs.getString("electric"), rs.getInt("dog"), rs.getInt("xtraPerson"), rs.getInt("camel"), rs.getInt("kunde_id"), rs.getInt("plads_id"));
			}
		}
		catch (SQLException e) {throw new DALException(e); }
		return list; 
	}
	
}
