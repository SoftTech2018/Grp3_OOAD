package costa_kalundborg.client.content;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import costa_kalundborg.client.Menu;
import costa_kalundborg.client.Service;
import costa_kalundborg.client.ServiceAsync;
import costa_kalundborg.shared.BookingDTO;
import costa_kalundborg.shared.HyttePladsDTO;
import costa_kalundborg.shared.KundeDTO;
import costa_kalundborg.shared.LillePladsDTO;
import costa_kalundborg.shared.PladsDTO;
import costa_kalundborg.shared.StorPladsDTO;
import costa_kalundborg.shared.TeltPladsDTO;

public class BookPlads extends Composite {

	private VerticalPanel vPane;
	private FlexTable ft, ft2;
	private TextBox firstName, cpr, street, zip, town, email, phone, date1, date2, adult, child, xPers, dog;
	private Button ok, book;
	private ListBox type;
	private int pladsId;
	private final BookingDTO booking = new BookingDTO();

	public BookPlads(){
		vPane = new VerticalPanel();
		initWidget(vPane);
		run();
	}

	private void run(){
		vPane.clear();
		ft = new FlexTable();

		ft.setText(0, 0, "Start dato");
		date1 = new TextBox();
		ft.setWidget(0, 1, date1);

		ft.setText(1, 0, "Slut dato");
		date2 = new TextBox();
		ft.setWidget(1, 1, date2);

		ft.setText(2, 0, "Antal voksne");
		adult = new TextBox();
		ft.setWidget(2, 1, adult);

		ft.setText(3, 0, "Antal børn");
		child = new TextBox();
		ft.setWidget(3, 1, child);

		ft.setText(4, 0, "Antal ekstra personer");
		xPers = new TextBox();
		ft.setWidget(4, 1, xPers);

		ft.setText(5, 0, "Antal hunde");
		dog = new TextBox();
		ft.setWidget(5, 1, dog);

		ok = new Button("Søg");
		ok.setStyleName("Button-Ret");
		ft.setWidget(6, 0, ok);

		vPane.add(ft);
		ok.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				booking.setStartDate(date1.getText());
				booking.setEndDate(date2.getText());
				booking.setStatus("CANCEL");
				try { booking.setVoksne(Integer.parseInt(adult.getText()));
				}catch (NumberFormatException e){
				} try {	booking.setBorn(Integer.parseInt(child.getText()));
				}catch (NumberFormatException e){
				} try {	booking.setXtraPerson(Integer.parseInt(xPers.getText()));
				}catch (NumberFormatException e){
				} try{ booking.setDog(Integer.parseInt(dog.getText()));
				}catch (NumberFormatException e){}
				try {
					Menu.service.checkBooking(booking, new AsyncCallback<ArrayList<PladsDTO>>(){

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Ingen ledige pladser");
							run();
						}

						@Override
						public void onSuccess(ArrayList<PladsDTO> result) {
							ft2 = new FlexTable();
							vPane.clear();
							vPane.add(ft2);
							type = new ListBox();
							for (PladsDTO i : result){
								type.addItem(getDescription(i));
							}
							ft2.setText(1, 0, "Vælg plads:");
							ft2.setWidget(1, 1, type);

							firstName = new TextBox();
							ft2.setText(3, 0, "Navn");
							ft2.setWidget(3, 1, firstName);

							cpr = new TextBox();
							ft2.setText(4, 0, "cpr");
							ft2.setWidget(4, 1, cpr);

							street = new TextBox();
							ft2.setText(5, 0, "Gade/vej & Hus nr.");
							ft2.setWidget(5, 1, street);

							zip = new TextBox();
							ft2.setText(6, 0, "Postnummer");
							ft2.setWidget(6, 1, zip);

							town = new TextBox();
							ft2.setText(7, 0, "By");
							ft2.setWidget(7, 1, town);

							email = new TextBox();
							ft2.setText(8, 0, "Email");
							ft2.setWidget(8, 1, email);

							phone = new TextBox();
							ft2.setText(9, 0, "Telefon nr.");
							ft2.setWidget(9, 1, phone);

							book = new Button();
							book.setText("Book");
							book.setStyleName("Button-Ret");
							ft2.setWidget(10, 0, book);
							book.addClickHandler(new ClickHandler(){

								@Override
								public void onClick(ClickEvent event) {
									KundeDTO kunde = new KundeDTO(firstName.getText(), cpr.getText(), street.getText(), zip.getText(), town.getText());
									
									String[] pladsNr = type.getItemText(type.getSelectedIndex()).split(" ");
									pladsId = Integer.valueOf(pladsNr[0]);
									PladsDTO plads = new LillePladsDTO();
									plads.setPlads_id(pladsId);
									try {
										Menu.service.createBooking(booking, kunde, plads, new AsyncCallback<BookingDTO>(){

											@Override
											public void onFailure(Throwable caught) {
												Window.alert(caught.getMessage() + pladsId);
											}

											@Override
											public void onSuccess(BookingDTO result) {
												Window.alert("Booking oprettet på plads: " + pladsId);
												run();
											}

										});
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
						}
					});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	private String getDescription(PladsDTO p){
		String desc = "";
		desc = Integer.toString(p.getPlads_id());
		if (p instanceof StorPladsDTO){
			desc += " : Stor campingvognsplads, pris: " + p.getPrice();
		}else if(p instanceof LillePladsDTO){
			desc += " : Lille campingvognsplads, pris: " + p.getPrice();
		} else if(p instanceof TeltPladsDTO){
			desc += " : Teltplads";
		} else if (p instanceof HyttePladsDTO){
			desc += " : Hytte, pris: " + p.getPrice();
		}
		return desc;
	}
}