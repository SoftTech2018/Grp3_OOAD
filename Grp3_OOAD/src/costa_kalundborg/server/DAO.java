package costa_kalundborg.server;

import java.sql.ResultSet;
import java.sql.SQLException;

import costa_kalundborg.shared.DALException;

import costa_kalundborg.shared.Booking;
import costa_kalundborg.shared.KundeDTO;


public class DAO {
	
	public DAO() {
		
	}
	
	public static KundeDTO getCustomer(String cpr) {
		ResultSet rs = Connector.doQuery(/*SQL statement med cpr'en den får medsendt*/);
		try { 
			if (!rs.first()) throw new DALException("Kunde " + cpr + " findes ikke");
				//kunde_navn, cpr, adresse, pCode, city, id
				return new KundeDTO (rs.getString("kunde_navn"), rs.getString("cpr"), rs.getString("adresse"), rs.getString("pCode"), rs.getString("city"));				
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
	
	public static ResultSet getBooking(int id) {
		ResultSet rs = Connector.doQuery(/*SQL statement med id'en den får medsendt*/);
		return rs; /*Resultat fra database Booking*/
	}
	
	public static void createBooking(Booking b) {
		Connector.doUpdate(/*SQL statement med Booking-objektet den får medsendt*/);
	}
	
	public static getPlace(int id) {
		ResultSet rs = Connector.doQuery(/*SQL statement med id'en den får medsendt*/);
		return rs; /*Resultat fra database Booking*/
	}
}
