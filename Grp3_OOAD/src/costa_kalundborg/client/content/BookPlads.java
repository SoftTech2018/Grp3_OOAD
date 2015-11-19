package costa_kalundborg.client.content;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import costa_kalundborg.client.Menu;
import costa_kalundborg.client.Service;
import costa_kalundborg.client.ServiceAsync;
import costa_kalundborg.shared.BookingDTO;
import costa_kalundborg.shared.FieldVerifier;
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
		Label header = new Label();
		header.setText("Opret booking");
		header.setStyleName("FlexTable-Header");
		vPane.add(header);
		
		ft = new FlexTable();
		
		ok = new Button("Søg");
		ok.setStyleName("Button-Ret");
		ok.setEnabled(false);

		ft.setText(0, 0, "Start dato");
		date1 = new TextBox();
		date1.addKeyUpHandler(new KeyUpHandler(){
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.checkDate(date1.getText())){
					ok.setEnabled(false);
					date1.setStyleName("TextBox-Error");
				} else {
					ok.setEnabled(true);
					date1.setStyleName("TextBox");
				}
			}
		});
		ft.setWidget(0, 1, date1);

		ft.setText(1, 0, "Slut dato");
		date2 = new TextBox();
		date2.addKeyUpHandler(new KeyUpHandler(){
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.checkDate(date1.getText())){
					ok.setEnabled(false);
					date2.setStyleName("TextBox-Error");
				} else {
					ok.setEnabled(true);
					date2.setStyleName("TextBox");
				}
			}
		});
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

		ft.setWidget(6, 0, ok);

		vPane.add(ft);
		ok.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				booking.setStartDate(date1.getText());
				booking.setEndDate(date2.getText());
				booking.setStatus("BOOKET");
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

							final Button soeg = new Button("Søg");
							cpr = new TextBox();
							cpr.addKeyUpHandler(new KeyUpHandler(){
								@Override
								public void onKeyUp(KeyUpEvent event) {
									if (!FieldVerifier.isValidCpr(cpr.getText())){
										soeg.setEnabled(false);
										book.setEnabled(false);
										cpr.setStyleName("TextBox-Error");
									} else {
										soeg.setEnabled(true);
										book.setEnabled(true);
										cpr.setStyleName("TextBox");
									}
								}
							});
							ft2.setText(3, 0, "cpr");
							ft2.setWidget(3, 1, cpr);
							
							soeg.setStyleName("Button-Ret");
							ft2.setWidget(3, 2, soeg);
							soeg.addClickHandler(new ClickHandler(){
								@Override
								public void onClick(ClickEvent event) {
									Menu.service.getKunde(cpr.getText(), new AsyncCallback<KundeDTO>(){

										@Override
										public void onFailure(Throwable caught) {
											Window.alert(caught.getMessage());											
										}

										@Override
										public void onSuccess(KundeDTO result) {
											ft2.clearCell(3, 2);
											cpr.setReadOnly(true);
											firstName.setText(result.getKundeNavn());
											firstName.setReadOnly(true);
											street.setText(result.getAdresse());
											street.setReadOnly(true);
											zip.setText(result.getpCode());
											zip.setReadOnly(true);
											town.setText(result.getCity());
											town.setReadOnly(true);
											phone.setText(Integer.toString(result.getTlf()));
											phone.setReadOnly(true);
											email.setText(result.getEmail());
											email.setReadOnly(true);
										}
									});
								}
							});
							
							firstName = new TextBox();
							ft2.setText(4, 0, "Navn");
							ft2.setWidget(4, 1, firstName);

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
									int tlf;
									try {
										tlf = Integer.valueOf(phone.getText());
									} catch (NumberFormatException e){
										tlf = 00000000;
									}
									KundeDTO kunde = new KundeDTO(firstName.getText(), cpr.getText(), street.getText(), zip.getText(), town.getText(), tlf, email.getText());

									String[] pladsNr = type.getItemText(type.getSelectedIndex()).split(" ");
									pladsId = Integer.valueOf(pladsNr[0]);
									PladsDTO plads = new LillePladsDTO();
									plads.setPlads_id(pladsId);
									try {
										Menu.service.createBooking(booking, kunde, plads, new AsyncCallback<BookingDTO>(){

											@Override
											public void onFailure(Throwable caught) {
												Window.alert(caught.getMessage() + " - plads_id: " + pladsId);
											}

											@Override
											public void onSuccess(BookingDTO result) {
												vPane.clear();
												FlexTable ft3 = new FlexTable();
												ft3.setText(0, 0, "Booking oprettet");
												ft3.getRowFormatter().addStyleName(0, "FlexTable-Header");

												ft3.setText(1, 0, "Booking id:");
												ft3.setText(1, 1, Integer.toString(result.getBooking_id()));

												ft3.setText(2, 0, "Plads id:");
												ft3.setText(2, 1, Integer.toString(pladsId));

												ft3.setText(3, 0, "Ankomst dato:");
												ft3.setText(3, 1, result.getStartDate());

												ft3.setText(4, 0, "Slut dato:");
												ft3.setText(4, 1, result.getEndDate());

												ft3.setText(5, 0, "Status:");
												ft3.setText(5, 1, result.getStatus().toString());
												
												Button forfra = new Button("Ny booking");
												forfra.setStyleName("Button-Ret");
												forfra.addClickHandler(new ClickHandler(){
													@Override
													public void onClick(ClickEvent event) {
														run();
													}
												});
												
												ft3.setWidget(6, 1, forfra);
												vPane.add(ft3);
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