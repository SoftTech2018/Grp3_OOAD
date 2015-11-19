package costa_kalundborg.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import costa_kalundborg.client.content.BookPlads;

public class Menu extends Composite{
	
	public static ServiceAsync service;
	private HorizontalPanel hPane;
	private VerticalPanel vPaneMenu, vPaneCon, header, footer, site;
	private FlexTable ftMenu, ftCon;
	
	public Menu(final ServiceAsync service){
		Menu.service = service;
		site = new VerticalPanel();
		hPane = new HorizontalPanel();
		vPaneMenu = new VerticalPanel();
		vPaneCon = new VerticalPanel();
		header = new VerticalPanel();
		footer = new VerticalPanel();
		header.setStyleName("Header");
		footer.setStyleName("Footer");
		site.add(header);
		site.add(hPane);
		hPane.add(vPaneMenu);
		hPane.add(vPaneCon);
		site.add(footer);
		initWidget(site);
		vPaneCon.add(new BookPlads());
		run();
	}
	
	private void run(){
		ftMenu = new FlexTable();
		ftMenu.setStyleName("Menu");
		ftCon = new FlexTable();
		ftCon.setStyleName("Content");
		Label txt = new Label();
		txt.setStyleName("Header-Text");
		txt.setText("Costa Kalundborg");
		header.add(txt);
		
		Label txt2 = new Label();
		txt2.setStyleName("Footer-Text");
		txt2.setText("Et bookingssystem udviklet af Gruppe 3");
		footer.add(txt2);
		
		Button plads = new Button("Book plads");
		plads.setStyleName("Button-Ret");
		plads.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				vPaneCon.clear();
				vPaneCon.add(new BookPlads());
			}	
		});
		Button editBook = new Button("Rediger booking");
		editBook.setStyleName("Button-Ret");
		Button tjekUd = new Button("Tjek ud");
		tjekUd.setStyleName("Button-Ret");
		Button sletBooking = new Button("Slet booking");
		sletBooking.setStyleName("Button-Ret");
		Button ankomst = new Button("Registrer ankomst");
		ankomst.setStyleName("Button-Ret");
		Button kamel = new Button("Leje af kamel");
		kamel.setStyleName("Button-Ret");
		Button strom = new Button("Registrer strøm");
		strom.setStyleName("Button-Ret");
		
		ftMenu.setWidget(0, 0, plads);
		ftMenu.setWidget(1, 0, ankomst);
		ftMenu.setWidget(2, 0, editBook);
		ftMenu.setWidget(3, 0, tjekUd);
		ftMenu.setWidget(4, 0, sletBooking);
		ftMenu.setWidget(5, 0, kamel);
		ftMenu.setWidget(6, 0, strom);
		
		
//		ftCon.setText(0, 0, "CONTENT");
		
		vPaneMenu.add(ftMenu);
		vPaneCon.add(ftCon);
		
	}
	
}
