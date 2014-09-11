package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;  
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;  
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourcePasswordField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.validator.CustomValidator;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.Canvas;  
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RegistrationEntryPoint implements EntryPoint {
	private final RegistrationServiceAsync RegistrationService = GWT
			.create(RegistrationService.class);


	public void onModuleLoad() {

	    // Add the nameField and sendButton to the RootPanel
	    // Use RootPanel.get() to get the entire body element
	    RootPanel rootPanel = RootPanel.get("content");
	    
	    TabPanel tabPanel = new TabPanel();
	    rootPanel.add(tabPanel, 21, 10);
	    tabPanel.setSize("761px", "370px");
	    
	    AbsolutePanel step1panel = new AbsolutePanel();
	    tabPanel.add(step1panel, "Шаг 1", false);
	    step1panel.setSize("162px", "342px");
	    
	    AbsolutePanel step2panel = new AbsolutePanel();
	    tabPanel.add(step2panel, "Шаг 2", false);
	    step2panel.setSize("5cm", "3cm");
	    
	    AbsolutePanel step3panel = new AbsolutePanel();
	    tabPanel.add(step3panel, "Шаг 3", false);
	    step3panel.setSize("5cm", "338px");
	    
	    //Step 1
	    
        DataSource dataSource = new DataSource();  
        
        DataSourceTextField loginField = new DataSourceTextField("login", "login", 50, true);  
        DataSourceTextField emailField = new DataSourceTextField("email", "Email", 100, true);  
  
        RegExpValidator emailValidator = new RegExpValidator();  
        emailValidator.setErrorMessage("Invalid email address");  
        emailValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");  
          
        emailField.setValidators(emailValidator);  
  
        DataSourcePasswordField passwordField = new DataSourcePasswordField("password", "Password", 20, true);  
  
        dataSource.setFields(loginField, emailField, passwordField);  
  
        final DynamicForm form = new DynamicForm();  
        form.setDataSource(dataSource);  
        form.setUseAllDataSourceFields(true);  
  
        HeaderItem header = new HeaderItem();  
        header.setDefaultValue("Registration Form");  
  
        PasswordItem passwordItem = new PasswordItem();  
        passwordItem.setName("password");  
  
        PasswordItem passwordItem2 = new PasswordItem();  
        passwordItem2.setName("password2");  
        passwordItem2.setTitle("Password Again");  
        passwordItem2.setRequired(true);  
        passwordItem2.setLength(20);  
  
        MatchesFieldValidator matchesValidator = new MatchesFieldValidator();  
        matchesValidator.setOtherField("password");  
        matchesValidator.setErrorMessage("Passwords do not match");          
        passwordItem2.setValidators(matchesValidator);  
  
        CheckboxItem acceptItem = new CheckboxItem();  
        acceptItem.setName("acceptTerms");  
        acceptItem.setTitle("I accept the terms of use.");  
        acceptItem.setDefaultValue(false);  
        CustomValidator isTrueValidator = new CustomValidator() {  
  
            @Override  
            protected boolean condition(Object value) {  
                if (new Boolean(true).equals(value)) return true;  
                return false;  
            }  
              
        };  
        isTrueValidator.setErrorMessage("You must accept the terms of use to continue");  
        acceptItem.setValidators(isTrueValidator);  
        acceptItem.setWidth(150);  
  
        ButtonItem validateItem = new ButtonItem();  
        validateItem.setTitle("Next step");  
        validateItem.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
                form.validate(false);  
            }  
        });  
  
        form.setFields(header, passwordItem, passwordItem2, acceptItem, validateItem);  
          
        form.setValue("login", "Bob");  
        form.setValue("email", "bob@.com");  
        form.setValue("password", "sekrit");  
        form.setValue("password2", "fatfinger");      
              
        form.draw();  
    }  

}
