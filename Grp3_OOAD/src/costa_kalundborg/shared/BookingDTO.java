package costa_kalundborg.shared;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BookingDTO implements Serializable{

	private String startDate;
	private String endDate;
	private Status status;
	private double electric;
	private int dog;
	private int voksne;
	private int born;
	private int xtraPerson;
	private int camel;
	private KundeDTO kunde;
	private PladsDTO plads;

	public BookingDTO(){
	}

	public BookingDTO(String startDate, String endDate, String status, double electric, int dog, int xtraPerson, int camel, int voksne, int born) throws Exception{
		this.startDate = startDate;
		this.endDate = endDate;
		switch(status.toUpperCase()){
		case "BOOKET":
			this.status = Status.BOOKET;
			break;
		case "CANCEL":
			this.status = Status.CANCEL;
			break;
		case "AFHOLDT":
			this.status = Status.AFHOLDT;
			break;
		case "AKTIV":
			this.status = Status.AKTIV;
			break;
		default:
			throw new Exception("Ukendt status på booking.");
		}
		this.electric = electric;
		this.dog = dog;
		this.xtraPerson = xtraPerson;
		this.camel = camel;
		this.voksne = voksne;
		this.born = born;
	}



	/**
	 * Returnerer prisen for den pÃ¥gÃ¦ldende booking.
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
	 * Returnerer true hvis bookingen er i hÃ¸jsÃ¦son
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

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
