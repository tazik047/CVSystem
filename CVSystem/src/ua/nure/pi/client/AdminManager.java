package ua.nure.pi.client;

import ua.nure.pi.entity.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class AdminManager {

	private static RootPanel loginPanel;
	
	private static RootPanel adminPanel;
	
	private static Button main;
	
	private static MainServiceAsync mainService;
	
	public static void start(final UIManager uiManager, final MainServiceAsync service, Button btnNewButton) {
		loginPanel = RootPanel.get("login");
		adminPanel = RootPanel.get("lifjhil");
		main = btnNewButton;
		mainService = service;
		loginPanel.add(new Label("проверка входа в систему...."));
		mainService.checkLogined(new AsyncCallback<User>() {
			
			@Override
			public void onSuccess(User result) {
				loginPanel.clear();
				if(result==null){
					final Button loginBtn = new Button("Войти");
				    loginBtn.setStyleName("buttons");
				    loginPanel.add(loginBtn);
				    loginBtn.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							loginBtn.setFocus(false);
							if(!uiManager.isSetted(LoginPanel.class.getName()))
								uiManager.setPanel(new LoginPanel(mainService, main));
						}
					});
				}
				else if(result.isAdmin()){
					aftorizedAdmin();
				}
				else{
					loginPanel.add(new Label("Здрайствуйте, компания"));
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				loginPanel.clear();
				loginPanel.add(new Label(caught.getLocalizedMessage()));
			}
		});
		
	    
	}

	public static void aftorizedAdmin() {
		RootPanel head = RootPanel.get("header");
		head.addStyleName("forAdminFix");
		adminPanel.setStyleName("adminPanel");
		loginPanel.clear();
		loginPanel.add(new Label("Здравствуйте, администратор"));
	}
}
