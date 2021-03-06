package ua.nure.pi.client;

import ua.nure.pi.entity.Company;
import ua.nure.pi.entity.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;

public class RegistrationCompanySimplePanel extends LoadingSimplePanel {

	MainServiceAsync mainService;
	Button toMainPage;
	public TextItem nameOfCompany;
	public TextItem login;
	public PasswordItem password;
	public PasswordItem password2;
	public TextItem PhonetextBox;
	public TextItem Skypetextbox;
    public TextItem Emailtextbox;
    public TextItem nameOfReliable;
    public TextItem phoneOfReliable;
    
    public Label label;

	public DynamicForm mainForm;
	public StaticTextItem nameHint;
	public StaticTextItem loginHint;
	public StaticTextItem passwordHint;
	public StaticTextItem phoneHint;
	public StaticTextItem skypeHint;
	public StaticTextItem emailHint;
	public StaticTextItem nameOfRelHint;
	public StaticTextItem phoneOfRelHint;
	
	public int generalWidth = 300;
	
	public RegistrationCompanySimplePanel(MainServiceAsync main, Button main2) {
		isReady = true;
		mainService = main;
		
		toMainPage = main2;
		clear();
		onModuleLoad();
	}
	
	public void onModuleLoad() {

	    
		
		VerticalPanel rootPanel = new VerticalPanel();
	    mainForm = new DynamicForm();
	    mainForm.setNumCols(1);
	    mainForm.setTitleSuffix("");
	    mainForm.setRequiredTitleSuffix("");
	    mainForm.setRequiredRightTitleSuffix("");
	    mainForm.setZIndex(10);
	    rootPanel.setWidth("100%");
	    
	    
        label = new Label();  
        label.setHeight(60);  
        //label.setPadding(10);  
        label.setAlign(Alignment.LEFT);  
        label.setValign(VerticalAlignment.CENTER);  
        label.setWrap(false);  
        label.setContents("Регистрация профиля компании");  
        label.setStyleName("title");
        label.draw();  
        
	    mainForm.setStyleName("mainForm");
	    
	    rootPanel.add(label);	

	    rootPanel.add(mainForm);
	    	    
        
        nameOfCompany = new TextItem("name", "Название компании");  
        nameOfCompany.setWidth(generalWidth);  
        nameOfCompany.setHint("Введите название компании");  
        nameOfCompany.setShowHintInField(true);
	    nameOfCompany.setRequired(true);
	    nameHint = new StaticTextItem();
	    nameHint.setShowTitle(false);
	    nameHint.setValue("Например, OOO \"Apple\"");
	    
        login = new TextItem("login", "Ваш логин");  
        login.setWidth(generalWidth);  
        login.setHint("Введите ваш логин");  
        login.setShowHintInField(true);
        login.setRequired(true);
        loginHint = new StaticTextItem();
        loginHint.setShowTitle(false);
        loginHint.setValue("Например, apple_1");
        
        password = new PasswordItem("password", "Ваш пароль");  
        password.setWidth(generalWidth);  
        password.setRequired(true);
        
        passwordHint = new StaticTextItem();
        passwordHint.setShowTitle(false);
        passwordHint.setValue("Например, password_1");
        
        password2 = new PasswordItem("password2", "Ваш пароль еще раз");  
        password2.setWidth(generalWidth);  
        password2.setShowHintInField(true);
        password2.setRequired(true);
        
        RegExpValidator emailValidator = new RegExpValidator();  
        emailValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");  
        
        Emailtextbox = new TextItem("email", "Email");  
        Emailtextbox.setWidth(generalWidth);  
        Emailtextbox.setHint("Введите email"); 
        Emailtextbox.setShowHintInField(true);
        Emailtextbox.setValidators(emailValidator);
	    emailHint = new StaticTextItem();
	    emailHint.setShowTitle(false);
	    emailHint.setValue("Например, example@gmail.com");
	    Emailtextbox.setRequired(true);

        PhonetextBox = new TextItem("phone", "Контакнтый телефон");  
        PhonetextBox.setWidth(generalWidth);  
        PhonetextBox.setShowHintInField(true);
        PhonetextBox.setHint("Введите контактный телефон"); 
        PhonetextBox.setMask("+38(###) ###-####");
        PhonetextBox.setRequired(false);
	    phoneHint = new StaticTextItem();
	    phoneHint.setShowTitle(false);
	    phoneHint.setValue("Например, +38(050)145-8872");

        Skypetextbox = new TextItem("skype", "Skype");  
        Skypetextbox.setWidth(generalWidth);  
        Skypetextbox.setHint("Введите skype"); 
        Skypetextbox.setShowHintInField(true);
        Skypetextbox.setRequired(false);
	    skypeHint = new StaticTextItem();
	    skypeHint.setShowTitle(false);
	    skypeHint.setValue("Например, skypelogin");
	    
        nameOfReliable = new TextItem("nameOfReliable", "Ответственное лицо");  
        nameOfReliable.setWidth(generalWidth);  
        nameOfReliable.setHint("Введите имя и отчество ответственного лица"); 
        nameOfReliable.setShowHintInField(true);
        nameOfReliable.setRequired(true);
	    nameOfRelHint = new StaticTextItem();
	    nameOfRelHint.setShowTitle(false);
	    nameOfRelHint.setValue("Например, Стив");
	    
        phoneOfReliable = new TextItem("phoneOfReliable", "Контактный телефон ответственного лица");  
        phoneOfReliable.setWidth(generalWidth);  
        phoneOfReliable.setShowHintInField(true);
        phoneOfReliable.setHint("Введите контактный телефон ответственного лица"); 
        phoneOfReliable.setMask("+38(###) ###-####");
        phoneOfReliable.setRequired(true);
        phoneOfRelHint = new StaticTextItem();
	    phoneOfRelHint.setShowTitle(false);
	    phoneOfRelHint.setValue("Например, +38(050)145-8872");
       
	    
	    Button commit = new Button("Зарегистрироваться");
        commit.addClickHandler(new ClickHandler() {
        	
            public void onClick(ClickEvent event) {
            	Boolean b = mainForm.validate();
            	Company comp = CollectData();
            	if(b) {
            	    mainService.insertCompany(comp, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getLocalizedMessage());							
						}

						@Override
						public void onSuccess(Void result) {
							Window.alert("Ваши данный сохранены и в скором времени пройдут проверку администратором.");							
						}

            	});
        	}

        }
        });
        nameHint.setCellStyle("hint");
        loginHint.setCellStyle("hint");
        passwordHint.setCellStyle("hint");
        emailHint.setCellStyle("hint");
	    phoneHint.setCellStyle("hint");
	    skypeHint.setCellStyle("hint");
	    nameOfRelHint.setCellStyle("hint");
	    phoneOfRelHint.setCellStyle("hint");
	    
	    mainForm.setCellPadding(5);
	    
        mainForm.setTitleOrientation(TitleOrientation.TOP);
	    mainForm.setFields(nameOfCompany, nameHint, login, loginHint, password, password2,passwordHint, Emailtextbox, emailHint,
	    		PhonetextBox, phoneHint, Skypetextbox, skypeHint, nameOfReliable, nameOfRelHint, phoneOfReliable, phoneOfRelHint);
	    mainForm.markForRedraw();
	    rootPanel.add(commit);
	    
	    commit.setStyleName("commit");
	    
	    rootPanel.setSpacing(15);

	    add(rootPanel);
	    

	    
	}
	
	public Company CollectData() {
		Company c = new Company();
		c.setName(nameOfCompany.getValueAsString().trim().replaceAll("[\\s]{2,}", " "));
		c.setNameOfReliable(nameOfReliable.getValueAsString().trim().replaceAll("[\\s]{2,}", " "));
		c.setEmail(Emailtextbox.getValueAsString().trim().replaceAll("[\\s]{2,}", " "));
		c.setPhone(PhonetextBox.getValueAsString().trim().replaceAll("[\\s]{2,}", " "));
		c.setPhoneOfReliable(phoneOfReliable.getValueAsString().trim().replaceAll("[\\s]{2,}", " "));
		try {
		c.setSkype(Skypetextbox.getValueAsString().trim().replaceAll("[\\s]{2,}", " "));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		User u = new User();
		u.setLogin(login.getValueAsString().trim().replaceAll("[\\s]{2,}", " "));
		u.setPassword(password.getValueAsString());
		c.setUser(u);
		
		return c;
	}
}
