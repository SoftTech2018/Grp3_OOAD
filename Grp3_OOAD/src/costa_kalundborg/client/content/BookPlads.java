package costa_kalundborg.client.content;

import java.util.List;

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

import costa_kalundborg.client.ServiceAsync;
import costa_kalundborg.shared.BookingDTO;
import costa_kalundborg.shared.KundeDTO;
import costa_kalundborg.shared.LillePladsDTO;
import costa_kalundborg.shared.PladsDTO;
import costa_kalundborg.shared.Status;

public class BookPlads extends Composite {
	
	private ServiceAsync service;
	private VerticalPanel vPane;
	private FlexTable ft;
	private TextBox firstName, cpr, street, housenr, zip, town, email, phone, credit, date1, date2, adult, child, xPers, dog;
	private Button ok, vælg, book;
	private ListBox type;
	private int adults, children, pladsId, dogg, xPerss;
	private String expectedPattern = "dd/MM/yyyy";
	private String startDate, endDate, kundenavn, kundeCpr, adresse, postnummer, by;
	
	public BookPlads(final ServiceAsync service){
		this.service = service;
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
		ok.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event) {

				startDate = date1.getText();
				endDate = date2.getText();
				adults = Integer.parseInt(adult.getText());
				children = Integer.parseInt(child.getText());
				xPerss = Integer.parseInt(xPers.getText());
				dogg = Integer.parseInt(dog.getText());
				
				try {
					Status ledig = Status.CANCEL;
					service.checkBooking(new BookingDTO(startDate, endDate, "ledig", 0.0, 0, 0, 0, adults, children), new AsyncCallback<List<PladsDTO>>(){

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Ingen ledige pladser");
							run();
						}

						@Override
						public void onSuccess(List<PladsDTO> result) {
							type = new ListBox();
							for (PladsDTO i : result){
								type.addItem(getDescription(i.getPlads_id()));
							}
							ft.setWidget(7, 1, type);
							vælg = new Button();
							vælg.setText("Vælg");
							ft.setWidget(8, 0, vælg);
							
							vælg.addClickHandler(new ClickHandler(){

								@Override
								public void onClick(ClickEvent event) {
									// TODO Auto-generated method stub
									firstName = new TextBox();
									ft.setText(9, 0, "Navn");
									ft.setWidget(9, 1, firstName);
									
									
									cpr = new TextBox();
									ft.setText(10, 0, "cpr");
									ft.setWidget(10, 1, cpr);
									
									street = new TextBox();
									ft.setText(11, 0, "Gade/vej");
									ft.setWidget(11, 1, street);
									
									housenr = new TextBox();
									ft.setText(12, 0, "Hus nr.");
									ft.setWidget(12, 1, housenr);
									
									zip = new TextBox();
									ft.setText(13, 0, "Postnummer");
									ft.setWidget(13, 1, zip);
									
									town = new TextBox();
									ft.setText(14, 0, "By");
									ft.setWidget(14, 1, town);
									
									email = new TextBox();
									ft.setText(15, 0, "Email");
									ft.setWidget(15, 1, email);
									
									phone = new TextBox();
									ft.setText(16, 0, "Telefon nr.");
									ft.setWidget(16, 1, phone);
									
									credit = new TextBox();
									ft.setText(17, 0, "Kreditkort");
									ft.setWidget(17, 1, credit);
									
									book = new Button();
									book.setText("Book");
									ft.setWidget(18, 0, book);
									book.addClickHandler(new ClickHandler(){
										
										
										@Override
										public void onClick(ClickEvent event) {
											// TODO Auto-generated method stub
											kundenavn = firstName.getText();
											kundeCpr = cpr.getText();
											adresse = street.getText()+" " +housenr.getText();
											postnummer = zip.getText();
											by = town.getText();
											pladsId = type.getSelectedIndex()+1;
											PladsDTO pls = new LillePladsDTO();
											pls.setPlads_id(pladsId);
											try {
												service.createBooking(new BookingDTO(startDate, endDate, "ledig", 0.0, dogg, xPerss, 0, adults, children), new KundeDTO(kundenavn, kundeCpr, adresse, postnummer, by), pls, new AsyncCallback<BookingDTO>(){

													@Override
													public void onFailure(
															Throwable caught) {
														Window.alert("Fejl i oprettelse");
													}

													@Override
													public void onSuccess(
															BookingDTO result) {
														Window.alert("Booking oprettet");
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
							
							
						}
						
					});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		ft.setWidget(6, 0, ok);
		vPane.add(ft);
	}

	private String getDescription(int n){
		
		switch(n) {
		case 1:
			return new String("1, 'Lille camping', 400.00, 350.00");
		case 2:
			return new String("2, 'Lille camping', 400.00, 350.00");
		case 3:
			return new String("3, 'Lille camping', 400.00, 350.00");
		case 4:
			return new String("4, 'Stor camping', 740.00, 350.00");
		case 5:
			return new String("5, 'Stor camping', 740.00, 350.00");
		case 6:
			return new String("6, 'Stor hytte', 980.00, 350.00");
		case 7:
			return new String("7, 'Stor hytte', 980.00, 350.00");
		case 8:
			return new String("8, 'Lille hytte', 680.00, 350.00");
		case 9:
			return new String("9, 'Lille hytte', 680.00, 350.00");		
		}
		
		return null;
	}

}

