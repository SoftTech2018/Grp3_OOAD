package costa_kalundborg.shared;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BookingDTO implements Serializable{

	private Date start_date;
	private Date end_date;
	private Status status;
	private double electric;
	private int dog;
	private int voksne;
	private int born;
	private int xtraPerson;
	private int camel;
	private KundeDTO kunde;
	private PladsDTO plads;

	private enum Status{
		BOOKET,
		CANCEL,
		AFHOLDT,
		AKTIV
	}

	public BookingDTO(){
	}

	public BookingDTO(Date start_date, Date end_date, String status, double electric, int dog, int xtraPerson, int camel, int voksne, int born, KundeDTO kunde, PladsDTO plads) throws Exception{
		this.start_date = start_date;
		this.end_date = end_date;
		switch(status){
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
		this.kunde = kunde;
		this.plads = plads;	
		this.voksne = voksne;
		this.born = born;
	}

	/**
	 * Tjekker om de tilsendte datoer overlapper med datoerne i bookingen. Returnerer true hvis datoerne overlapper.
	 * @param start_date
	 * @param end_date
	 * @return
	 * @throws Exception
	 */
	public boolean compareDate(Date start_date, Date end_date) throws Exception {
		if (start_date.compareTo(end_date) < 0)
			throw new Exception("Slut dato er før start dato.");

		if (this.start_date.compareTo(end_date)>=0)
			return false;
		else if (this.end_date.compareTo(start_date)>=0 )
			return false;
		return true;
	}

	/**
	 * Returnerer prisen for den pågældende booking.
	 * @return
	 */
	public double getPrice(){
		double price = 0;
		long days = getDifferenceDays(start_date, end_date);

		if (!highSeason()){
			price += days * plads.getLowprice();
			price += dog * 10 * days;
			price += xtraPerson * 100 * days;
			price += camelPrice();
			price += electric * 3.75;
			price += voksne * 82 * days;
			price += born * 42 * days;
		} else {
			price += days * plads.getPrice();
			price += dog * 10 * days;
			price += xtraPerson * 100 * days;
			price += camelPrice();
			price += electric * 3.75;
			price += voksne * 87 * days;
			price += born * 49 * days;
		}

		return price;
	}

	/**
	 * Returnerer true hvis bookingen er i højsæson
	 * @return
	 */
	public boolean highSeason(){
		// to be implemented med start_date og end_date
		return false;
	}

	public long getDifferenceDays(Date d1, Date d2) {
		long diff = d2.getTime() - d1.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public double camelPrice(){
		// TO DO
		return 0;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
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
