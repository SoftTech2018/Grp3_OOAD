package costa_kalundborg.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import costa_kalundborg.shared.Booking;
import costa_kalundborg.shared.Kunde;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
	void createBooking(Date d1, Date d2, AsyncCallback<Booking> callback) throws Exception;
	void getKunde(String cpr, AsyncCallback<Kunde> callback) throws Exception;
	void getBooking(int id, AsyncCallback<Booking> callback) throws Exception;
	void editBooking(int id, AsyncCallback<Void> callback) throws Exception;
	void deleteBooking(int id, AsyncCallback<Void> callback) throws Exception;
	void registerArrival(Kunde c, AsyncCallback<Void> callback) throws Exception;
	void checkout(Kunde c, int id, AsyncCallback<Double> callback) throws Exception;
	void rentCamel(Kunde c, int number, AsyncCallback<Void> callback) throws Exception;
	void addElectricUsage(int id, double amount, AsyncCallback<Void> callback) throws Exception;
}
