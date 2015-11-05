package costa_kalundborg.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import costa_kalundborg.shared.BookingDTO;
import costa_kalundborg.shared.KundeDTO;
import costa_kalundborg.shared.PladsDTO;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ServiceAsync {
	void checkBooking(BookingDTO booking, AsyncCallback<List<PladsDTO>> callback) throws Exception;
	void createBooking(BookingDTO booking, KundeDTO kunde, PladsDTO plads, AsyncCallback<BookingDTO> callback) throws Exception;
	void getKunde(String cpr, AsyncCallback<KundeDTO> callback) throws Exception;
	void getBooking(int id, AsyncCallback<BookingDTO> callback) throws Exception;
	void editBooking(int id, AsyncCallback<Void> callback) throws Exception;
	void deleteBooking(int id, AsyncCallback<Void> callback) throws Exception;
	void registerArrival(KundeDTO c, AsyncCallback<Void> callback) throws Exception;
	void checkout(KundeDTO c, int id, AsyncCallback<Double> callback) throws Exception;
	void rentCamel(KundeDTO c, int number, AsyncCallback<Void> callback) throws Exception;
	void addElectricUsage(int id, double amount, AsyncCallback<Void> callback) throws Exception;
}
