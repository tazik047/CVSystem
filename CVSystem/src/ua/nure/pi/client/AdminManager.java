package ua.nure.pi.client;

import ua.nure.pi.entity.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AdminManager {

	private static RootPanel loginPanel;
	
	private static RootPanel adminPanel;
	
	private static AbsolutePanel profilePanel;
	
	private static Button main;
	
	private static MainServiceAsync mainService;
	
	private static UIManager uiManager;
	
	//private static User loginedUser;
	
	public static void start(final UIManager ui, final MainServiceAsync service, Button btnNewButton) {
		loginPanel = RootPanel.get("login");
		adminPanel = RootPanel.get("lifjhil");
		uiManager=ui;
		main = btnNewButton;
		mainService = service;
		loginPanel.add(new Label("Проверка входа в систему...."));
		mainService.checkLogined(new AsyncCallback<User>() {
			
			@Override
			public void onSuccess(User result) {
				loginPanel.clear();
				//loginedUser = result;
				if(result==null){
					createStartUI();
				}
				else if(result.isAdmin()){
					createProfilePanel(true);
					
				}
				else{
					createProfilePanel(false);
					loginPanel.add(new Label("Здравствуйте, компания"));
					
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				loginPanel.clear();
				loginPanel.add(new Label(caught.getLocalizedMessage()));
			}
		});
		
	    
	}

	public static void aftorizedAdmin() {
		RootPanel head = RootPanel.get("header");
		head.addStyleName("forAdminFix");
		adminPanel.setStyleName("adminPanel");
		Label text = new Label("Здравствуйте, администратор");
		
		loginPanel.add(text);
		setAdminFunc();
	}
	
	public static void exitFromAdmin() {
		RootPanel head = RootPanel.get("header");
		head.removeStyleName("forAdminFix");
		adminPanel.removeStyleName("adminPanel");
		createStartUI();
		main.click();
	}
	
	private static void createStartUI() {
		RootPanel head = RootPanel.get("header");
		head.removeStyleName("forAdminFix");
		adminPanel.removeStyleName("adminPanel");
		adminPanel.clear(true);
		loginPanel.clear(true);
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

	public static void createProfilePanel(boolean admin){
		loginPanel.clear();
		profilePanel = new AbsolutePanel();
		loginPanel.add(profilePanel);
		HorizontalPanel pan = new HorizontalPanel();
		pan.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		pan.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		Button edit = new Button("Редактировать профиль");
		Button exit = new Button("Выйти");
		profilePanel.add(pan);
		pan.add(new HTML("<div class=\"triangle\"></div><div class=\"triangle1\"></div>"));
		pan.add(edit);
		pan.add(exit);
		pan.setStyleName("profilePanel");
		exit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				mainService.logout(new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						createStartUI();
						main.click();
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getLocalizedMessage());
					}
				});
			}
		});
		
		edit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
			}
		});
		
		hideProfilePanel();
		
		if(admin){
			aftorizedAdmin();
		}
	}
	
	private static void setAdminFunc(){
		HorizontalPanel root = new HorizontalPanel();
		final Button getCVs = new Button("Получить списки резюме");
		getCVs.setStyleName("Buttons");
		root.add(getCVs);
		getCVs.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				getCVs.setFocus(false);
				if(!uiManager.isSetted(TablePanel.class.getName()))
					uiManager.setPanel(new TablePanel(mainService));
			}
		});
		
		adminPanel.add(root);
	}
	
	
	
	//////////////////// Осторожно, JavaScript code /////////////////////////
	public static native void hideProfilePanel() /*-{
	 $wnd.$('.profilePanel').hide();
	}-*/;
	
	public static native void showProfilePanel() /*-{
	 $wnd.$('.profilePanel').show();
	}-*/;
	
	public static native boolean isShow()/*-{
		return $wnd.$('.profilePanel').is(':visible');
	}-*/;
	
	////////////////////////////////////////////////////////////////////////
}
