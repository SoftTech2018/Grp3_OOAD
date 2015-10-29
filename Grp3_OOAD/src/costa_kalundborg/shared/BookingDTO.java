package costa_kalundborg.shared;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BookingDTO implements Serializable{

	private String start_Calendar;
	private String end_Calendar;
	private Status status;
	private double electric;
	private int dog;
	private int voksne;
	private int born;
	private int xtraPerson;
	private int camel;
	private KundeDTO kunde;
	private PladsDTO plads;

//	private enum Status implements Serializable, IsSerializable{
//		BOOKET,
//		CANCEL,
//		AFHOLDT,
//		AKTIV
//	}

	public BookingDTO(){
	}

	public BookingDTO(String start_Calendar, String end_Calendar, Status status, double electric, int dog, int xtraPerson, int camel, int voksne, int born, KundeDTO kunde, PladsDTO plads) throws Exception{
		this.start_Calendar = start_Calendar;
		this.end_Calendar = end_Calendar;
		switch(status.getStatus()){
		case BOOKET:
			this.status = Status.BOOKET;
			break;
		case CANCEL:
			this.status = Status.CANCEL;
			break;
		case AFHOLDT:
			this.status = Status.AFHOLDT;
			break;
		case AKTIV:
			this.status = Status.AKTIV;
			break;
		default:
			throw new Exception("Ukendt status på booking.");
		}
		this.electric = electric;
		this.dog = dog;
		this.xtraPerson = xtraPerson;
		this.camel = camel;
		this.kunde = kunde;
		this.plads = plads;	
		this.voksne = voksne;
		this.born = born;
	}

	/**
	 * Tjekker om de tilsendte datoer overlapper med datoerne i bookingen. Returnerer true hvis datoerne overlapper.
	 * @param start_Calendar
	 * @param end_Calendar
	 * @return
	 * @throws Exception
	 */
//	public boolean compareCalendar(String start_Calendar, String end_Calendar) throws Exception {
//		if (start_Calendar.compareTo(end_Calendar) < 0)
//			throw new Exception("Slut dato er før start dato.");
//
//		if (this.start_Calendar.compareTo(end_Calendar)>=0)
//			return false;
//		else if (this.end_Calendar.compareTo(start_Calendar)>=0 )
//			return false;
//		return true;
//	}

	/**
	 * Returnerer prisen for den pågældende booking.
	 * @return
	 */
//	public double getPrice(){
//		double price = 0;
//		long days = getDifferenceDays(start_Calendar, end_Calendar);
//
//		if (!highSeason()){
//			price += days * plads.getLowprice();
//			price += dog * 10 * days;
//			price += xtraPerson * 100 * days;
//			price += camelPrice();
//			price += electric * 3.75;
//			price += voksne * 82 * days;
//			price += born * 42 * days;
//		} else {
//			price += days * plads.getPrice();
//			price += dog * 10 * days;
//			price += xtraPerson * 100 * days;
//			price += camelPrice();
//			price += electric * 3.75;
//			price += voksne * 87 * days;
//			price += born * 49 * days;
//		}
//
//		return price;
//	}

	/**
	 * Returnerer true hvis bookingen er i højsæson
	 * @return
	 */
	public boolean highSeason(){
		// to be implemented med start_Calendar og end_Calendar
		return false;
	}

//	public long getDifferenceDays(Calendar d1, Calendar d2) {
//		long diff = d2.compareTo(d1);
//		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
//	}

	public double camelPrice(){
		// TO DO
		return 0;
	}

//	public Calendar getStart_Calendar() {
//		return start_Calendar;
//	}
//
//	public void setStart_Calendar(Calendar start_Calendar) {
//		this.start_Calendar = start_Calendar;
//	}
//
//	public Calendar getEnd_Calendar() {
//		return end_Calendar;
//	}
//
//	public void setEnd_Calendar(Calendar end_Calendar) {
//		this.end_Calendar = end_Calendar;
//	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getElectric() {
		return electric;
	}

	public void setElectric(double elictric) {
		this.electric = elictric;
	}

	public int getDog() {
		return dog;
	}

	public void setDog(int dog) {
		this.dog = dog;
	}

	public int getXtraPerson() {
		return xtraPerson;
	}

	public void setXtraPerson(int xtraPerson) {
		this.xtraPerson = xtraPerson;
	}

	public int getCamel() {
		return camel;
	}

	public void setCamel(int camel) {
		this.camel = camel;
	}

	public KundeDTO getKunde() {
		return kunde;
	}

	public void setKunde(KundeDTO kunde) {
		this.kunde = kunde;
	}

	public PladsDTO getPlads() {
		return plads;
	}

	public void setPlads(PladsDTO plads) {
		this.plads = plads;
	}

	public int getVoksne() {
		return voksne;
	}

	public void setVoksne(int voksne) {
		this.voksne = voksne;
	}

	public int getBorn() {
		return born;
	}

	public void setBorn(int born) {
		this.born = born;
	}

}
