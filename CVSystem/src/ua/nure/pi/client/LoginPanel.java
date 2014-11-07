package ua.nure.pi.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HTML;

public class LoginPanel extends LoadingSimplePanel {
	
	public LoginPanel(final MainServiceAsync mainService, final Button mainTab) {
		clear();
		isReady=true;
		VerticalPanel root = new VerticalPanel();
		root.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		root.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		root.setSize("100%", "60%");
		final Label err = new Label();
		err.setStyleName("error");
		final TextBox textBox = new TextBox();
		
		final PasswordTextBox passwordTextBox = new PasswordTextBox();
		passwordTextBox.setSize("170", "25");
		
		final Button btnNewButton = new Button("Войти");
		
		add(root);
		
		class LoginEvents implements KeyUpHandler,ClickHandler{
			
			@Override
			public void onClick(ClickEvent event) {
				login();
			}

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
					login();
				}
			}

			private void login() {
				btnNewButton.setEnabled(false);
				err.setText("");
				mainService.login(textBox.getValue(), passwordTextBox.getValue(), new AsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean result) {
						if(result){
							AdminManager.createProfilePanel(true);
							mainTab.click();
						}
						else{
							err.setText("Неправильно введен логин или пароль.");
							passwordTextBox.setValue("");
						}
						btnNewButton.setEnabled(true);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						err.setText(caught.getLocalizedMessage());
						btnNewButton.setEnabled(true);
					}
				});
				
			}
			
		}
		
		LoginEvents loginEvents = new LoginEvents();
		
		textBox.addKeyUpHandler(loginEvents);
		passwordTextBox.addKeyUpHandler(loginEvents);
		btnNewButton.addClickHandler(loginEvents);
		
		
		FlexTable flexTable = new FlexTable();
		root.add(flexTable);
		for(int i=0;i<5;i++){
			flexTable.insertRow(i);
			flexTable.insertCell(i, 0);
			flexTable.insertCell(i, 1);
		}
		flexTable.getFlexCellFormatter().setColSpan(0, 0, 2);
		flexTable.setWidget(0, 0, new HTML("<h1>Авторизация</h1>", true));
		flexTable.setText(1,0,"Логин");
		flexTable.setWidget(1, 1, textBox);
		textBox.setSize("170", "25");
		flexTable.setText(2, 0,"Пароль");
		flexTable.setWidget(2, 1, passwordTextBox);
		flexTable.setWidget(3, 0, err);
		flexTable.setText(4, 0, "");
		flexTable.setWidget(4, 1, btnNewButton);
		flexTable.getCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		textBox.setFocus(true);
		textBox.setTabIndex(1);
		passwordTextBox.setTabIndex(2);
		btnNewButton.setTabIndex(3);
		flexTable.getFlexCellFormatter().setColSpan(3, 0, 2);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
	}
	
	
}
