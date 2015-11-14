package costa_kalundborg.shared;

import java.io.Serializable;
import java.util.Calendar;

public class BookingDTO implements Serializable{

	private String startDate, endDate;
	private Status status;
	private double electric;
	private int dog, voksne, born, xtraPerson, camel, booking_id;

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

	public void setStatus(String status) {
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
			this.status = Status.CANCEL;
		}
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

	public int getBooking_id() {
		// TODO Auto-generated method stub
		return booking_id;
	}
	
	public void setBooking_id(int id){
		this.booking_id = id;
	}

}
