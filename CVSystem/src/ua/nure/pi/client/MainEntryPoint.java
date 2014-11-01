package ua.nure.pi.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainEntryPoint implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */

	
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final MainServiceAsync mainService = (MainServiceAsync) GWT
			.create(MainService.class);

	private UIManager uiManager;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

    // Add the nameField and sendButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    RootPanel rootPanel = RootPanel.get("buttons");
    rootPanel.setWidth("650px");
    rootPanel.setStyleName("#menu-left");
    
    uiManager = new UIManager(RootPanel.get("content"));
    
    Button btnNewButton = new Button("New button");
    btnNewButton.setStyleName("buttons");
    btnNewButton.setFocus(true);
   
    btnNewButton.setText("Главная");
    btnNewButton.addStyleName("buttons");
    btnNewButton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			uiManager.setPanel(new MainStaticPanel(mainService));
			//Window.Location.reload();
		}
	});
    
    rootPanel.add(btnNewButton);
   // btnNewButton.setSize("70px", "23px");
    Button btnNewButton_4 = new Button("New button");
    btnNewButton_4.setStyleName("buttons");
    btnNewButton_4.setText("О нас");
    btnNewButton_4.addStyleName("buttons");
    rootPanel.add(btnNewButton_4);
   // btnNewButton_4.setSize("70px", "23px");
    
    btnNewButton_4.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			uiManager.setPanel(new AboutUsStaticPanel(mainService));
		}
	});
        
   
    Button butAddCV = new Button("New button");
    butAddCV.setText("Добавить резюме");
    butAddCV.addStyleName("buttons");
    butAddCV.setStyleName("buttons");
    rootPanel.add(butAddCV);
    butAddCV.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			uiManager.setPanel(new RegistrationSimplePanel(mainService));
		}
	});

    Button btnNewButton_3 = new Button("New button");
    btnNewButton_3.setStyleName("buttons");
    btnNewButton_3.setText("Партнеры");
    btnNewButton_3.addStyleName("buttons");
    rootPanel.add(btnNewButton_3);
    
    
    Button btnNewButton_1 = new Button("New button");
    btnNewButton_1.setStyleName("buttons");
    btnNewButton_1.setText("Списки резюме");
    btnNewButton_1.addStyleName("buttons");
    rootPanel.add(btnNewButton_1);
  //  btnNewButton_1.setSize("80px", "23px");
    
   // btnNewButton_2.setSize("97px", "23px");
    
   // btnNewButton_3.setSize("82px", "23px");
    

    Button btnNewButton_2 = new Button("New button");
    btnNewButton_2.setStyleName("buttons");
    btnNewButton_2.setText("Статистика");
    btnNewButton_2.addStyleName("buttons");
    rootPanel.add(btnNewButton_2);

    
    uiManager.setPanel(new MainStaticPanel(mainService));
  }
}
