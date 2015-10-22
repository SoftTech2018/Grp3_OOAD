package costa_kalundborg.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cdio.shared.RaavareDTO;

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

public class BookPlads extends Composite {
	
	private ServiceAsync service;
	private VerticalPanel vPane;
	private FlexTable ft;
	private TextBox name, address, zip, date1, date2, adult, child;
	private Button ok;
	private ListBox type;
	private String adults, children;
	private String expectedPattern = "dd/MM/yyyy";
	private Date startDate, endDate;
	
	private SimpleDateFormat formatter;
	
	public BookPlads(final ServiceAsync service){
		this.service = service;
		formatter = new SimpleDateFormat(expectedPattern);
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
		startDate = formatter.parse(date1.getText());
		
		ft.setText(1, 0, "Slut dato");
		date2 = new TextBox();
		ft.setWidget(1, 1, date2);
		endDate = formatter.parse(date2.getText());
		
		ft.setText(2, 0, "Antal voksne");
		adult = new TextBox();
		ft.setWidget(2, 1, adult);
		adults = adult.getText();
		
		ft.setText(3, 0, "Antal børn");
		child = new TextBox();
		ft.setWidget(3, 1, child);
		children = child.getText();
		
		ok = new Button("Søg");
		ok.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event) {
				service.checkBooking(new BookingDTO(startDate, endDate, null, null, null, null, null, adults, children, KundeDTO kunde, PladsDTO plads), new AsyncCallback<List<PladsDTO>>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(List<PladsDTO> result) {
						// TODO Auto-generated method stub
						
					}
					
				});
			}
			
		});
		ft.setWidget(4, 0, ok);
		
		ft.setText(4, 0, "Beboelse");
		type = new ListBox();
		type.addItem("Telt");
		type.addItem("Campingvogn");
		type.addItem("2 pers. hytte - 12m2");
		type.addItem("2 pers. hytte - 14m2");
		type.addItem("4 pers. hytte - 22m2");
		type.addItem("4-6 pers. luksushytte - 25m2");
		type.addItem("4-6 pers. luksushytte+ - 25m2");
		
//		getResidenceType(type.getSelectedIndex());
		
		ft.setWidget(4, 1, type);
		
		
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
	
//	private PladsDTO getResidenceType(int n) throws Exception{
//		
//		switch(n) {
//		case 0:
//			return new PladsDTO(0, "LILLE_TELT", 82.0, 87.0);
//		case 1:
//			return new PladsDTO(0, "STORT_TELT", 82.0, 87.0);
//		case 2:
//			return new PladsDTO(0, "LILLE_TELT", 82.0, 87.0);
//		case 3:
//			break;
//		case 4:
//			break;
//		case 5:
//			break;
//		case 6:
//			break;
//			
//			
//		}
//		
//		return null;
//	}

}
