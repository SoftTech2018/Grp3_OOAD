package costa_kalundborg.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import costa_kalundborg.shared.BookingDTO;
import costa_kalundborg.shared.Kunde;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface Service extends RemoteService {
	BookingDTO createBooking(Date d1, Date d2) throws Exception;
	Kunde getKunde(String cpr) throws Exception;
	BookingDTO getBooking(int id) throws Exception;
	void editBooking(int id) throws Exception;
	void deleteBooking(int id) throws Exception;
	void registerArrival(Kunde c) throws Exception;
	double checkout(Kunde c, int id) throws Exception;
	void rentCamel(Kunde c, int number) throws Exception;
	void addElectricUsage(int id, double amount) throws Exception;
}
