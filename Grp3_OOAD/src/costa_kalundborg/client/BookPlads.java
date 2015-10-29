package costa_kalundborg.client;

import java.text.ParseException;
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

import costa_kalundborg.shared.BookingDTO;
import costa_kalundborg.shared.KundeDTO;
import costa_kalundborg.shared.PladsDTO;
import costa_kalundborg.shared.Status;

public class BookPlads extends Composite {
	
	private ServiceAsync service;
	private VerticalPanel vPane;
	private FlexTable ft;
	private TextBox name, address, zip, date1, date2, adult, child;
	private Button ok;
	private ListBox type;
	private int adults, children;
	private String expectedPattern = "dd/MM/yyyy";
	private String startDate, endDate;
	
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
		
		
		ok = new Button("Søg");
		ok.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event) {
				startDate = date1.getText();
				endDate = date2.getText();
				adults = Integer.parseInt(adult.getText());
				children = Integer.parseInt(child.getText());
				try {
					Status ledig = Status.CANCEL;
					service.checkBooking(new BookingDTO(startDate, endDate, ledig, 0.0, 0, 0, 0, adults, children, null, null), new AsyncCallback<List<PladsDTO>>(){

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
							ft.setWidget(5, 0, type);
						}
						
					});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		ft.setWidget(4, 0, ok);
//		
//		ft.setText(4, 0, "Beboelse");
//		type = new ListBox();
//		type.addItem("Telt");
//		type.addItem("Campingvogn");
//		type.addItem("2 pers. hytte - 12m2");
//		type.addItem("2 pers. hytte - 14m2");
//		type.addItem("4 pers. hytte - 22m2");
//		type.addItem("4-6 pers. luksushytte - 25m2");
//		type.addItem("4-6 pers. luksushytte+ - 25m2");
		
//		getResidenceType(type.getSelectedIndex());
		
//		ft.setWidget(4, 1, type);
		
		
//		ft.setText(0, 0, "Navn");
//		name = new TextBox();
//		ft.setWidget(0, 1, name);
//		
//		ft.setText(1, 0, "Adresse");
//		address = new TextBox();
//		ft.setWidget(1, 1, address);
//		
//		ft.setText(2, 0, "Postnummer");
//		zip = new TextBox();
//		ft.setWidget(2, 1, zip);
//		
//		ft.setText(3, 0, "Start dato");
//		zip = new TextBox();
//		ft.setWidget(3, 1, zip);
//		
//		ft.setText(4, 0, "Slut dato");
//		zip = new TextBox();
//		ft.setWidget(4, 1, zip);
//		
//		ft.setText(5, 0, "Telefon");
//		zip = new TextBox();
//		ft.setWidget(5, 1, zip);
//		
//		ft.setText(6, 0, "Email");
//		zip = new TextBox();
//		ft.setWidget(6, 1, zip);
		
		
		
		
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

