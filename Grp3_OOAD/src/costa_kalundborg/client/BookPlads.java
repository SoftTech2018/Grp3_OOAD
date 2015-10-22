package costa_kalundborg.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BookPlads extends Composite {
	
	private VerticalPanel vPane;
	private FlexTable ft;
	private TextBox name, address, zip;
	private Button ok;
	
	public BookPlads(){
		vPane = new VerticalPanel();
		initWidget(vPane);
		run();
	}
	
	private void run(){
		vPane.clear();
		
		ft = new FlexTable();
		
		ft.setText(0, 0, "Name");
		name = new TextBox();
		ft.setWidget(0, 1, name);
		
		ft.setText(1, 0, "Address");
		address = new TextBox();
		ft.setWidget(1, 1, address);
		
		ft.setText(2, 0, "Zip code");
		zip = new TextBox();
		ft.setWidget(2, 1, zip);
		
		ok = new Button("Book");
		ok.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Booking saved");
				//Her skal være et service kald til serveren und so weiter.
			}
			
		});
		ft.setWidget(3, 0, ok);
		
		vPane.add(ft);
	}

}
