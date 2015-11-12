package costa_kalundborg.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import costa_kalundborg.shared.BookingDTO;
import costa_kalundborg.shared.DALException;
import costa_kalundborg.shared.KundeDTO;
import costa_kalundborg.shared.PladsDTO;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface Service extends RemoteService {
	ArrayList<PladsDTO> checkBooking(BookingDTO booking) throws DALException;
	BookingDTO createBooking(BookingDTO booking, KundeDTO kunde, PladsDTO plads) throws DALException;
	KundeDTO getKunde(String cpr) throws DALException;
	BookingDTO getBooking(int id) throws DALException;
	double getPrice(PladsDTO p, BookingDTO booking) throws DALException;
	void editBooking(int id) throws DALException;
	void deleteBooking(int id) throws DALException;
	void registerArrival(KundeDTO c) throws DALException;
	double checkout(KundeDTO c, int id) throws DALException;
	void rentCamel(KundeDTO c, int number) throws DALException;
	void addElectricUsage(int id, double amount) throws DALException;
}
