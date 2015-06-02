package ua.nure.pi.client;

import ua.nure.pi.entity.User;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
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
import com.google.gwt.user.client.ui.Widget;

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
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(5);
		loginPanel.add(hp);
		final Button loginBtn = new Button("Войти");
	    loginBtn.setStyleName("buttons");
	    loginBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				loginBtn.setFocus(false);
				if(!uiManager.isSetted(LoginPanel.class.getName()))
					uiManager.setPanel(new LoginPanel(mainService, main));
			}
		});
	    final Button reg = new Button("Зарегистрироваться");
	    reg.setStyleName("buttons");
	    reg.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				reg.setFocus(false);
				if(!uiManager.isSetted(RegistrationCompanySimplePanel.class.getName()))
					uiManager.setPanel(new RegistrationCompanySimplePanel(mainService, main));
			}
		});
	    hp.add(loginBtn);
	    hp.add(reg);
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
		if(!admin){
			Button fav = new Button("Закладки");
			fav.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					if(!uiManager.isSetted(FavoritesSimplePanel.class.getName()))
						uiManager.setPanel(new FavoritesSimplePanel(mainService));
				}
			});
			pan.add(fav);
			pan.setSpacing(10);
			pan.setWidth("350px");
		}
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
		else{
			aftorizedCompany();
		}
	}
	
	private static void aftorizedCompany() {
		loginPanel.add(new Label("Здравствуйте, компания"));
	}

	private static void setAdminFunc(){
		createMenu();

		Element item = DOM.getElementById("menu1");
		item.setInnerText("Получить списки резюме");
		Event.sinkEvents(item, Event.ONCLICK);
	    Event.setEventListener(item, new EventListener() {

	        @Override
	        public void onBrowserEvent(Event event) {
	             if(Event.ONCLICK == event.getTypeInt()) {
	            	 if(!uiManager.isSetted(TablePanel.class.getName()))
	            		 uiManager.setPanel(new TablePanel(mainService));
	             }

	        }
	    });
		
		item = DOM.getElementById("menu2");
		item.setInnerText("Работа с БД");
		
		item = DOM.getElementById("menu2.1");
		item.setInnerText("Факультеты");
		Event.sinkEvents(item, Event.ONCLICK);
	    Event.setEventListener(item, new EventListener() {

	        @Override
	        public void onBrowserEvent(Event event) {
	             if(Event.ONCLICK == event.getTypeInt()) {
	            	 if(!uiManager.isSetted(AddFacultiesCanvas.class.getName()))
	 					uiManager.setPanel(new AddFacultiesCanvas(mainService));
	             }

	        }
	    });
	    
	    item = DOM.getElementById("menu2.2");
		item.setInnerText("Технологии");
		
		item = DOM.getElementById("menu2.2.1");
		item.setInnerText("Объединение технологий");
		Event.sinkEvents(item, Event.ONCLICK);
	    Event.setEventListener(item, new EventListener() {

	        @Override
	        public void onBrowserEvent(Event event) {
	             if(Event.ONCLICK == event.getTypeInt()) {
	            	 if(!uiManager.isSetted(MergeProgramLanguagesSimplePanel.class.getName()))
	 					uiManager.setPanel(new MergeProgramLanguagesSimplePanel(mainService));
	             }

	        }
	    });
	    
	    item = DOM.getElementById("menu2.2.2");
		item.setInnerText("Разбиение технологий");
		Event.sinkEvents(item, Event.ONCLICK);
	    Event.setEventListener(item, new EventListener() {

	        @Override
	        public void onBrowserEvent(Event event) {
	             if(Event.ONCLICK == event.getTypeInt()) {
	            	 if(!uiManager.isSetted(DivideProgramLanguageSimplePanel.class.getName()))
	 					uiManager.setPanel(new DivideProgramLanguageSimplePanel(mainService));
	             }

	        }
	    });
		
		item = DOM.getElementById("menu3");
		item.setInnerText("Компании");
		
		item = DOM.getElementById("menu3.1");
		item.setInnerText("Все компании");
		Event.sinkEvents(item, Event.ONCLICK);
		Event.setEventListener(item, new EventListener() {

	        @Override
	        public void onBrowserEvent(Event event) {
	             if(Event.ONCLICK == event.getTypeInt()) {
	            	 if(!uiManager.isSetted(CompanyPanel.class.getName()))
	            		 uiManager.setPanel(new CompanyPanel(true, mainService));
	             }

	        }
	    });
		
		item = DOM.getElementById("menu3.2");
		item.setInnerText("Активация");
		Event.sinkEvents(item, Event.ONCLICK);
		Event.setEventListener(item, new EventListener() {

	        @Override
	        public void onBrowserEvent(Event event) {
	             if(Event.ONCLICK == event.getTypeInt()) {
	            	 if(!uiManager.isSetted(CompanyPanel.class.getName()))
	            		 uiManager.setPanel(new CompanyPanel(false, mainService));
	             }

	        }
	    });

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
	
	private static native void createMenu() /*-{
		var res = '<div id = "cssmenu"><ul>';
		res+='<li><div id="menu1"></div></li>'+
			 '<li class="has-sub"><div id="menu2"></div>'+
				'<ul>'+
					'<li><div id="menu2.1"></div></li>'+
					'<li class="has-sub"><div id="menu2.2"></div>'+
						'<ul>'+
							'<li><div id="menu2.2.1"></div></li>'+
							'<li><div id="menu2.2.2"></div></li>'+
						'</ul>'+
					'</li>'+
				'</ul>'+
			 '</li>'+
			 '<li class="has-sub"><div id="menu3"></div>' +
				 '<ul>' +
				 	'<li><div id="menu3.1"></div></li>'+
				 	'<li><div id="menu3.2"></div></li>'+
				 '</ul>' +
			 '</li>';
			
		res+='</ul></div>';
		
		$wnd.$('#lifjhil').prepend(res);
		
		var link  = $wnd.document.createElement('link');
	    link.rel  = 'stylesheet';
	    link.type = 'text/css';
	    link.href = 'css/cssmenu.css';
	    $wnd.document.getElementsByTagName('head')[0].appendChild(link);
	    
		var script = $wnd.document.createElement("SCRIPT");
		script.src = "js/cssmenu.js";
		$wnd.document.getElementsByTagName("HEAD")[0].appendChild(script);
	}-*/;
	
	////////////////////////////////////////////////////////////////////////
}
