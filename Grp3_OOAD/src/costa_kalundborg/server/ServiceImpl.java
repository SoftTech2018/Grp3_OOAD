package costa_kalundborg.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import costa_kalundborg.client.Service;
import costa_kalundborg.shared.BookingDTO;
import costa_kalundborg.shared.KundeDTO;
import costa_kalundborg.shared.PladsDTO;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ServiceImpl extends RemoteServiceServlet implements Service {

	private DAO dao;

	public ServiceImpl(){
		try {
			dao = new DAO();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//
	//	public String greetServer(String input) throws IllegalArgumentException {
	//		// Verify that the input is valid. 
	//		if (!FieldVerifier.isValidName(input)) {
	//			// If the input is not valid, throw an IllegalArgumentException back to
	//			// the client.
	//			throw new IllegalArgumentException("Name must be at least 4 characters long");
	//		}
	//
	//		String serverInfo = getServletContext().getServerInfo();
	//		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
	//
	//		// Escape data from the client to avoid cross-site script vulnerabilities.
	//		input = escapeHtml(input);
	//		userAgent = escapeHtml(userAgent);
	//
	//		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
	//				+ userAgent;
	//	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	@Override
	public ArrayList<PladsDTO> checkBooking(BookingDTO booking) throws Exception {
		Calendar start = getCalendar(booking.getStartDate());
		Calendar end = getCalendar(booking.getEndDate());
		ArrayList<PladsDTO> listAll = dao.getPladser();
		ArrayList<PladsDTO> list = new ArrayList<PladsDTO>();
		boolean available;

		for(PladsDTO plads : listAll){
			available = true;
			for(BookingDTO book : dao.getBookings(plads)){
				if(available){
					available = dateClear(getCalendar(book.getStartDate()), getCalendar(book.getEndDate()), start, end);
				} else {
					break;
				}
			}
			if(available){
				list.add(plads);
			}
		}
		return list;
	}

	
	/**
	 * Returnerer prisen for den pÃ¥gÃ¦ldende booking.
	 * @return
	 */
	public double getPrice(PladsDTO p, BookingDTO booking){
		double price = 0;
		int days = countDays(getCalendar(booking.getStartDate()), getCalendar(booking.getEndDate()));
		
		if (!highSeason(booking)){
			price += days * p.getLowprice();
			price += booking.getDog() * 10 * days;
			price += booking.getXtraPerson() * 100 * days;
			price += camelPrice(booking);
			price += booking.getElectric() * 3.75;
			price += booking.getVoksne() * 82 * days;
			price += booking.getBorn() * 42 * days;
		} else {
			price += days * p.getPrice();
			price += booking.getDog() * 10 * days;
			price += booking.getXtraPerson() * 100 * days;
			price += camelPrice(booking);
			price += booking.getElectric() * 3.75;
			price += booking.getVoksne() * 87 * days;
			price += booking.getBorn() * 49 * days;
		}

		return price;
	}

	/**
	 * Finder antal dage mellem to datoer
	 * @param from
	 * @param to
	 * @return
	 */
	private int countDays(Calendar from, Calendar to) {
		if (from == null || to == null)
			return 0;
		// Set the time part to 0
		from.set(Calendar.MILLISECOND, 0);
		from.set(Calendar.SECOND, 0);
		from.set(Calendar.MINUTE, 0);
		from.set(Calendar.HOUR_OF_DAY, 0);
		to.set(Calendar.MILLISECOND, 0);
		to.set(Calendar.SECOND, 0);
		to.set(Calendar.MINUTE, 0);
		to.set(Calendar.HOUR_OF_DAY, 0);
		int nbJours = 0;
		for (Calendar c = from ; c.before(to) ; c.add(Calendar.DATE, +1)){
			nbJours++;
		}
		for (Calendar c = from ; c.after(to) ; c.add(Calendar.DATE, -1)){
			nbJours--;
		}
		return nbJours;
	}
	
	private Calendar getCalendar(String date){
		Calendar output = Calendar.getInstance();
		output.set(Integer.valueOf(date.substring(6)), Integer.valueOf(date.substring(3,5)), Integer.valueOf(date.substring(0, 2)));
		return output;
	}

	/**
	 * Returnerer true hvis bookingen er i hÃ¸jsÃ¦son. Hvis en af datoerne er i højsæsonen betragtes hele bookingen som i højsæson
	 * @return
	 */
	private boolean highSeason(BookingDTO booking){
		String year = booking.getEndDate().substring(6);
		Calendar highStart = getCalendar("12-06-"+year);
		Calendar highEnd = getCalendar("16-08-"+year);
		
		// Tjek om slutdato er i højsæsonen
		if(getCalendar(booking.getEndDate()).compareTo(highEnd) <= 0 && getCalendar(booking.getEndDate()).compareTo(highStart) >=0){
			return true;			
		}
		// Tjek om startdato er i højsæsonen
		if(getCalendar(booking.getStartDate()).compareTo(highEnd) <= 0 && getCalendar(booking.getStartDate()).compareTo(highStart) >=0){
			return true;			
		}
		return false;
	}

	private double camelPrice(BookingDTO booking){
		if (booking.getCamel() <5){
			return booking.getCamel() * 50;
		} else {
			int foo = booking.getCamel()%5;
			int bar = booking.getCamel()/5;
			return (foo*50) + bar * 200;
		}
	}

	/**
	 * Tjekker om de tilsendte datoer overlapper med datoerne i bookingen. Returnerer true hvis datoerne overlapper.
	 * @param start_Calendar
	 * @param end_Calendar
	 * @return
	 * @throws Exception
	 */
	private boolean dateClear(Calendar startDateBook, Calendar endDateBook, Calendar startDateBooking, Calendar endDateBooking) throws Exception {		
		if (endDateBooking.compareTo(startDateBook)<0 || startDateBooking.compareTo(endDateBook)>0)
			return true;
		else {
			return false;
		}
	}

	@Override
	public BookingDTO createBooking(BookingDTO booking, KundeDTO kunde, PladsDTO plads) throws Exception {
		return dao.createBooking(booking, kunde, plads);
	}

	@Override
	public KundeDTO getKunde(String cpr) throws Exception {
		return dao.getCustomer(cpr);
	}

	@Override
	public BookingDTO getBooking(int id) throws Exception {
		return dao.getBooking(id);
	}

	@Override
	public void editBooking(int id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBooking(int id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerArrival(KundeDTO c) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public double checkout(KundeDTO c, int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void rentCamel(KundeDTO c, int number) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void addElectricUsage(int id, double amount) throws Exception {
		// TODO Auto-generated method stub

	}
}
