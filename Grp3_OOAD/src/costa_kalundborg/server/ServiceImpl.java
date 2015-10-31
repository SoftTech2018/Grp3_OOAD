package costa_kalundborg.server;

import java.awt.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
		dao = new DAO();
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
			for(BookingDTO book : plads.getBookings()){
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
	
	private Calendar getCalendar(String date){
		Calendar output = Calendar.getInstance();
		output.set(Integer.valueOf(date.substring(6)), Integer.valueOf(date.substring(3,5)), Integer.valueOf(date.substring(0, 2)));
		return output;
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
	public BookingDTO createBooking(BookingDTO booking) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KundeDTO getKunde(String cpr) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookingDTO getBooking(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
