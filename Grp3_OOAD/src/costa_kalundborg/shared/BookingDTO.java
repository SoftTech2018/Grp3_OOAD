package costa_kalundborg.shared;

import java.io.Serializable;
import java.util.Calendar;

public class BookingDTO implements Serializable{

	private String startDate, endDate;
	private Status status;
	private double electric;
	private int dog, voksne, born, xtraPerson, camel;

	public BookingDTO(){}

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
	public double getPrice(PladsDTO p){
		double price = 0;
		int days = countDays(getCalendar(startDate), getCalendar(endDate));
		
		if (!highSeason()){
			price += days * p.getLowprice();
			price += dog * 10 * days;
			price += xtraPerson * 100 * days;
			price += camelPrice();
			price += electric * 3.75;
			price += voksne * 82 * days;
			price += born * 42 * days;
		} else {
			price += days * p.getPrice();
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
	 * Finder antal dage mellem to datoer
	 * @param from
	 * @param to
	 * @return
	 */
	private int countDays(Calendar from, Calendar to) {
		if (from == null || to == null)
			return 0;
		// Set the time part to 0
		from.set(Calendar.MILLISECOND, 0);
		from.set(Calendar.SECOND, 0);
		from.set(Calendar.MINUTE, 0);
		from.set(Calendar.HOUR_OF_DAY, 0);
		to.set(Calendar.MILLISECOND, 0);
		to.set(Calendar.SECOND, 0);
		to.set(Calendar.MINUTE, 0);
		to.set(Calendar.HOUR_OF_DAY, 0);
		int nbJours = 0;
		for (Calendar c = from ; c.before(to) ; c.add(Calendar.DATE, +1)){
			nbJours++;
		}
		for (Calendar c = from ; c.after(to) ; c.add(Calendar.DATE, -1)){
			nbJours--;
		}
		return nbJours;
	}
	
	private Calendar getCalendar(String date){
		Calendar output = Calendar.getInstance();
		output.set(Integer.valueOf(date.substring(6)), Integer.valueOf(date.substring(3,5)), Integer.valueOf(date.substring(0, 2)));
		return output;
	}

	/**
	 * Returnerer true hvis bookingen er i hÃ¸jsÃ¦son. Hvis en af datoerne er i højsæsonen betragtes hele bookingen som i højsæson
	 * @return
	 */
	private boolean highSeason(){
		String year = endDate.substring(6);
		Calendar highStart = getCalendar("12-06-"+year);
		Calendar highEnd = getCalendar("16-08-"+year);
		
		// Tjek om slutdato er i højsæsonen
		if(getCalendar(endDate).compareTo(highEnd) <= 0 && getCalendar(endDate).compareTo(highStart) >=0){
			return true;			
		}
		// Tjek om startdato er i højsæsonen
		if(getCalendar(startDate).compareTo(highEnd) <= 0 && getCalendar(startDate).compareTo(highStart) >=0){
			return true;			
		}
		return false;
	}

	public double camelPrice(){
		if (camel <5){
			return camel * 50;
		} else {
			int foo = camel%5;
			int bar = camel/5;
			return (foo*50) + bar * 200;
		}
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
