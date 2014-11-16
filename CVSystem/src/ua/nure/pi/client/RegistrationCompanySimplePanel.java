package ua.nure.pi.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.TitleOrientation;
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


	public DynamicForm mainForm;
	public StaticTextItem nameHint;
	public StaticTextItem loginHint;
	public StaticTextItem passwordHint;
	public StaticTextItem phoneHint;
	public StaticTextItem skypeHint;
	public StaticTextItem emailHint;

	
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
	    rootPanel.add(mainForm);
	    
        
        nameOfCompany = new TextItem("name", "Название компании");  
        nameOfCompany.setWidth(300);  
        nameOfCompany.setHint("Введите название компании");  
        nameOfCompany.setShowHintInField(true);
        nameOfCompany.setMask(">C<CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
	    nameOfCompany.setRequired(true);
	    nameHint = new StaticTextItem();
	    nameHint.setShowTitle(false);
	    nameHint.setValue("Например, OOO \"Apple\"");
	    
        login = new TextItem("login", "Ваш логин");  
        login.setWidth(300);  
        login.setHint("Введите ваш логин");  
        login.setShowHintInField(true);
        login.setMask(">C<CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
        login.setRequired(true);
        loginHint = new StaticTextItem();
        loginHint.setShowTitle(false);
        loginHint.setValue("Например, apple_1");
        
        password = new PasswordItem("password", "Ваш пароль");  
        password.setWidth(300);  
        password.setRequired(true);
        
        passwordHint = new StaticTextItem();
        passwordHint.setShowTitle(false);
        passwordHint.setValue("Например, password_1");
        
        password2 = new PasswordItem("password2", "Ваш пароль еще раз");  
        password2.setWidth(300);  
        password2.setShowHintInField(true);
        password2.setRequired(true);
        
        RegExpValidator emailValidator = new RegExpValidator();  
        emailValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");  
        
        Emailtextbox = new TextItem("email", "Email");  
        Emailtextbox.setWidth(300);  
        Emailtextbox.setHint("Введите email"); 
        Emailtextbox.setShowHintInField(true);
        Emailtextbox.setValidators(emailValidator);
	    emailHint = new StaticTextItem();
	    emailHint.setShowTitle(false);
	    emailHint.setValue("Например, example@gmail.com");
	    Emailtextbox.setRequired(true);

        PhonetextBox = new TextItem("phone", "Контакнтый телефон");  
        PhonetextBox.setWidth(300);  
        PhonetextBox.setShowHintInField(true);
        PhonetextBox.setHint("Введите контактный телефон"); 
        PhonetextBox.setMask("+38(###) ###-####");
        PhonetextBox.setRequired(false);
	    phoneHint = new StaticTextItem();
	    phoneHint.setShowTitle(false);
	    phoneHint.setValue("Например, +38(050)145-8872");

                
        Skypetextbox = new TextItem("skype", "Skype");  
        Skypetextbox.setWidth(300);  
        Skypetextbox.setHint("Введите skype"); 
        Skypetextbox.setShowHintInField(true);
        Skypetextbox.setRequired(false);
	    skypeHint = new StaticTextItem();
	    skypeHint.setShowTitle(false);
	    skypeHint.setValue("Например, skypelogin");
	    
	    

	    
	    mainForm.setCellPadding(5);
	    
        mainForm.setTitleOrientation(TitleOrientation.TOP);
	    mainForm.setFields(nameOfCompany, nameHint, login, loginHint, password, passwordHint, password2,Emailtextbox, emailHint,
	    		PhonetextBox, phoneHint, Skypetextbox, skypeHint);
	    mainForm.markForRedraw();
	    add(rootPanel);
	    
	}
}
