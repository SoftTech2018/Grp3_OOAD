package costa_kalundborg.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import costa_kalundborg.shared.BookingDTO;
import costa_kalundborg.shared.DALException;
import costa_kalundborg.shared.HyttePladsDTO;
import costa_kalundborg.shared.KundeDTO;
import costa_kalundborg.shared.LillePladsDTO;
import costa_kalundborg.shared.PladsDTO;
import costa_kalundborg.shared.StorPladsDTO;
import costa_kalundborg.shared.TeltPladsDTO;


public class DAO {	

	private Connector con;

	protected DAO() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		con = new Connector();
	}

	protected KundeDTO getCustomer(String cpr) throws DALException {
		try { 
			ResultSet rs = con.doQuery("SELECT * FROM kunde WHERE cpr = '" + cpr + "'");
			if (!rs.first()) 
				throw new DALException("Kunde " + cpr + " findes ikke");
			//kunde_navn, cpr, adresse, pCode, city, id
			KundeDTO k = new KundeDTO(rs.getString("kunde_navn"), rs.getString("cpr"), rs.getString("adresse"), rs.getString("pCode"), rs.getString("city"));
			k.setId(rs.getInt("kunde_id"));
			return k;
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	protected void createCustomer(KundeDTO c) throws DALException {
		try {
			con.doUpdate("INSERT INTO kunde(kunde_navn, cpr, adresse, pCode, city) VALUES ('" + c.getKundeNavn()
						+ "','" + c.getCpr() + "','" + c.getAdresse() + "'," + c.getpCode() + ",'" + c.getCity() + "')");			
		}catch (SQLException e) {throw new DALException(e); }
	}

	protected BookingDTO getBooking(int id) throws DALException {
		BookingDTO b;
		try { 
			ResultSet rs = con.doQuery("SELECT * FROM booking WHERE booking_id = " + id /*SQL statement med id'en den får medsendt*/);
			if (!rs.first()) 
				throw new DALException("Booking med id:  " + id + " findes ikke");
			b = new BookingDTO (rs.getString("start_date"), rs.getString("end_date"), rs.getString("status"), rs.getDouble("electric"), rs.getInt("dog"), rs.getInt("xtraPerson"), rs.getInt("camel"), rs.getInt("voksne"), rs.getInt("born"));				
		}catch (SQLException e) {
			throw new DALException(e); 
		} catch (Exception e) {
			throw new DALException(e);
		}
		return b; 
	}	

	protected BookingDTO createBooking(BookingDTO b, KundeDTO k, PladsDTO p) throws DALException {
		try{
			getCustomer(k.getCpr()); // Tjek om kunden findes
		} catch (DALException e) {
			createCustomer(k); // Hvis kunden ikke findes, oprettes denne
		} 
		try {
			String stm = "INSERT INTO booking(start_date, end_date, status, electric, dog, xtraPerson, camel, kunde_id, plads_id, voksne, born) VALUES('" 
					+ b.getStartDate() + "','" + b.getEndDate() + "','" + b.getStatus() + "'," + b.getElectric() + "," + b.getDog() + ","
					+ b.getXtraPerson() + "," + b.getCamel() + "," + getCustomer(k.getCpr()).getId() + "," + p.getPlads_id() + "," + b.getVoksne()
					+ "," + b.getBorn() + ")";
			System.out.println(stm);
			con.doUpdate(stm);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Kunne ikke oprette booking på plads " +p.getPlads_id());
		} 
//		return getBooking(con.doQuery("SELECT LAST_INSERT_ID() FROM booking").getInt("booking_id"));
		return null;
	}

	protected PladsDTO getPlads(int plads) throws DALException {
		PladsDTO p;
		try { 
			ResultSet rs = con.doQuery("SELECT * FROM plads WHERE plads_id = "+ plads/*SQL statement med pladsen den får medsendt*/);
			if (!rs.first()) {
				throw new DALException("Booking med id:  " + plads + " findes ikke");				
			}
			switch(rs.getString("type").toUpperCase()){
			case "LILLE":
				p = new LillePladsDTO(rs.getInt("plads_id"), rs.getDouble("price"), rs.getDouble("lowprice"));
			break;
			case "STOR":
				p = new StorPladsDTO(rs.getInt("plads_id"), rs.getDouble("price"), rs.getDouble("lowprice"));
			break;
			case "HYTTE":
				p = new HyttePladsDTO(rs.getInt("plads_id"), rs.getDouble("price"), rs.getDouble("lowprice"));
			break;
			case "TELT":
				p = new TeltPladsDTO(rs.getInt("plads_id"), rs.getDouble("price"), rs.getDouble("lowprice"));
			break;
			default:
				throw new DALException("Ukendt pladstype.");
			}			
		}		
		catch (Exception e) {
			throw new DALException(e); 
		}
		return p;
	}

	protected ArrayList<BookingDTO> getBookings(int plads) throws DALException {
		ArrayList<BookingDTO> list = new ArrayList<BookingDTO>();
		try	{
			ResultSet rs = con.doQuery("SELECT * FROM booking WHERE plads_id = " + plads /*Sql statement med pladsen den får medsendt*/);
			while (rs.next()) 
			{
				list.add(new BookingDTO (rs.getString("start_date"), rs.getString("end_date"), rs.getString("status"), rs.getDouble("electric"), rs.getInt("dog"), rs.getInt("xtraPerson"), rs.getInt("camel"), rs.getInt("voksne"), rs.getInt("born")));
			}
		}
		catch (Exception e) {
			throw new DALException(e); 
		}
		return list; 
	}

	protected ArrayList<PladsDTO> getPladser() throws DALException {
		ArrayList<PladsDTO> list = new ArrayList<PladsDTO>();
		try	{
			ResultSet rs = con.doQuery("SELECT * FROM plads"/* SQL statement for alle pladser*/);
			while (rs.next()){
				PladsDTO p;
				switch(rs.getString("type").toUpperCase()){
				case "LILLE":
					p = new LillePladsDTO(rs.getInt("plads_id"), rs.getDouble("price"), rs.getDouble("lowprice"));
				break;
				case "STOR":
					p = new StorPladsDTO(rs.getInt("plads_id"), rs.getDouble("price"), rs.getDouble("lowprice"));
				break;
				case "HYTTE":
					p = new HyttePladsDTO(rs.getInt("plads_id"), rs.getDouble("price"), rs.getDouble("lowprice"));
				break;
				case "TELT":
					p = new TeltPladsDTO(rs.getInt("plads_id"), rs.getDouble("price"), rs.getDouble("lowprice"));
				break;
				default:
					throw new DALException("Ukendt pladstype.");
				}
				list.add(p);
			}
		}
		catch (Exception e) {
			throw new DALException(e); 
		}
		return list;
	}

	protected ArrayList<BookingDTO> getBookings(PladsDTO plads) throws DALException {
		ArrayList<BookingDTO> list = new ArrayList<BookingDTO>();
		try	{
			ResultSet rs = con.doQuery("SELECT * from booking WHERE plads_id = " + plads.getPlads_id());
			while (rs.next())
			{
				list.add(new BookingDTO (rs.getString("start_date"), rs.getString("end_date"), rs.getString("status"), rs.getDouble("electric"), rs.getInt("dog"), rs.getInt("xtraPerson"), rs.getInt("camel"), rs.getInt("voksne"), rs.getInt("born")));
			}
		}
		catch (Exception e) {
			throw new DALException(e); 
		}
		return list;
	}
}