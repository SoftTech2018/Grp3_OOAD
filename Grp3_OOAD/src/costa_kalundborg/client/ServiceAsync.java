package costa_kalundborg.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import costa_kalundborg.shared.BookingDTO;
import costa_kalundborg.shared.KundeDTO;
import costa_kalundborg.shared.PladsDTO;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ServiceAsync {
	void checkBooking(BookingDTO booking, AsyncCallback<ArrayList<PladsDTO>> callback);
	void createBooking(BookingDTO booking, KundeDTO kunde, PladsDTO plads, AsyncCallback<BookingDTO> callback);
	void getKunde(String cpr, AsyncCallback<KundeDTO> callback);
	void getBooking(int id, AsyncCallback<BookingDTO> callback);
	void getPrice(PladsDTO p, BookingDTO booking, AsyncCallback<Double> callback);
	void editBooking(int id, AsyncCallback<Void> callback);
	void deleteBooking(int id, AsyncCallback<Void> callback);
	void registerArrival(KundeDTO c, AsyncCallback<Void> callback);
	void checkout(KundeDTO c, int id, AsyncCallback<Double> callback);
	void rentCamel(KundeDTO c, int number, AsyncCallback<Void> callback);
	void addElectricUsage(int id, double amount, AsyncCallback<Void> callback);
}
