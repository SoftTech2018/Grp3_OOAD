package costa_kalundborg.client;

import com.google.gwt.dev.asm.Label;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Menu extends Composite{
	
	private ServiceAsync service;
	private HorizontalPanel hPane;
	private VerticalPanel vPaneMenu, vPaneCon;
	private FlexTable ftMenu, ftCon;
	
	public Menu(final ServiceAsync service){
		hPane = new HorizontalPanel();
		vPaneMenu = new VerticalPanel();
		vPaneCon = new VerticalPanel();
		hPane.add(vPaneMenu);
		hPane.add(vPaneCon);
		initWidget(hPane);
		run();
	}
	
	private void run(){
		ftMenu = new FlexTable();
		ftCon = new FlexTable();
		
		Button plads = new Button("Book plads");
		plads.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				vPaneCon.clear();
				vPaneCon.add(new BookPlads());
			}
			
		});
		Button kamel = new Button("Book kamel");
		Button hund = new Button("Book hund");
		Button telt = new Button("Book telt");
		
		ftMenu.setWidget(0, 0, plads);
		ftMenu.setWidget(1, 0, kamel);
		ftMenu.setWidget(2, 0, hund);
		ftMenu.setWidget(3, 0, telt);
		
		
		ftCon.setText(0, 0, "CONTENT");
		
		vPaneMenu.add(ftMenu);
		vPaneCon.add(ftCon);
		
	}
	
}
