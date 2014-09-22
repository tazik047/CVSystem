package ua.nure.pi.client;

import ua.nure.pi.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.DateLabel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainEntryPoint implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	
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
    rootPanel.setWidth("600px");
    rootPanel.setStyleName("#menu-left");
    
    uiManager = new UIManager(RootPanel.get("content"));
    
    Button btnNewButton = new Button("New button");
    btnNewButton.setFocus(true);
   
    btnNewButton.setText("Главная");
    btnNewButton.addStyleName("buttons");
    rootPanel.add(btnNewButton);
    btnNewButton.setSize("70px", "23px");
    
    Button btnNewButton_1 = new Button("New button");
    btnNewButton_1.setText("Вакансии");
    btnNewButton_1.addStyleName("buttons");
    rootPanel.add(btnNewButton_1);
    btnNewButton_1.setSize("80px", "23px");
    
    Button btnNewButton_2 = new Button("New button");
    btnNewButton_2.setText("Статистика");
    btnNewButton_2.addStyleName("buttons");
    rootPanel.add(btnNewButton_2);
    btnNewButton_2.setSize("97px", "23px");
    
    Button btnNewButton_3 = new Button("New button");
    btnNewButton_3.setText("Партнеры");
    btnNewButton_3.addStyleName("buttons");
    rootPanel.add(btnNewButton_3);
    btnNewButton_3.setSize("82px", "23px");
    
    Button btnNewButton_4 = new Button("New button");
    btnNewButton_4.setText("О нас");
    btnNewButton_4.addStyleName("buttons");
    rootPanel.add(btnNewButton_4);
    btnNewButton_4.setSize("60px", "23px");
    
    Button btnNewButton_5 = new Button("New button");
    btnNewButton_5.setText("Контакты");
    btnNewButton_5.addStyleName("buttons");
    rootPanel.add(btnNewButton_5);
    btnNewButton_5.setSize("82px", "23px");
    
    RootPanel addCV = RootPanel.get("AddCV");
    Button butAddCV = new Button("New button");
    butAddCV.setText("Добавить резюме");
    butAddCV.addStyleName("addCV");
    butAddCV.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			uiManager.setPanel(new RegistrationSimplePanel(mainService));
		}
	});
    if(addCV!=null)
    addCV.add(butAddCV);
    
    
    // Create the popup dialog box
    final DialogBox dialogBox = new DialogBox();
    dialogBox.setText("Remote Procedure Call");
    dialogBox.setAnimationEnabled(true);
    final Button closeButton = new Button("Close");
    // We can set the id of a widget by accessing its Element
    closeButton.getElement().setId("closeButton");
    final Label textToServerLabel = new Label();
    final HTML serverResponseLabel = new HTML();
    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");
    dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
    dialogVPanel.add(textToServerLabel);
    dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
    dialogVPanel.add(serverResponseLabel);
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
    dialogVPanel.add(closeButton);
    dialogBox.setWidget(dialogVPanel);

    // Add a handler to close the DialogBox

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler, KeyUpHandler {
      /**
       * Fired when the user clicks on the sendButton.
       */
      

      /**
       * Fired when the user types in the nameField.
       */
      public void onKeyUp(KeyUpEvent event) {
        
      }

      /**
       * Send the name from the nameField to the server and wait for a response.
       */
      private void sendNameToServer() {
        // First, we validate the input.
        
        
        // Then, we send the input to the server.
        }

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}
    }

    // Add a handler to send the name to the server
    MyHandler handler = new MyHandler();
  }
}
