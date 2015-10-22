package costa_kalundborg.server;

import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import costa_kalundborg.client.Service;
import costa_kalundborg.shared.BookingDTO;
import costa_kalundborg.shared.Kunde;

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
	public BookingDTO createBooking(Date d1, Date d2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kunde getKunde(String cpr) throws Exception {
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
	public void registerArrival(Kunde c) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double checkout(Kunde c, int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void rentCamel(Kunde c, int number) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addElectricUsage(int id, double amount) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
