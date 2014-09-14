package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.util.Name;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
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
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SectionItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.CustomValidator;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.IMenuButton;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.Canvas;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;  
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
	    
	    Label labelGroup = new Label("Выберите группу");
	    rootPanel.add(labelGroup, 84, 20);
	    labelGroup.setSize("145px", "18px");

	    // Menu for faculty/field
        Menu menu = new Menu();  
        
        menu.setShowShadow(true);  
        menu.setShadowDepth(10);  
  
        // Сделать заполнение из БД, запрос на факультеты и группы, им принадлежащие.
        
        MenuItem CS = new MenuItem("Компьютерных наук");  
  
        MenuItem RT = new MenuItem("Радиотехники");  
  
    
        Menu CSSubMenu = new Menu();  
        MenuItem SE131 = new MenuItem("ПИ-13-1");  
        MenuItem SE132 = new MenuItem("ПИ-13-2");  
        MenuItem SE133 = new MenuItem("ПИ-13-3");  
        CSSubMenu.setItems(SE131, SE132, SE133);  
  
        CS.setSubmenu(CSSubMenu);  
  
        menu.setItems(CS, RT);
        
        IMenuButton menuButton = new IMenuButton("Факультет", menu);  
        menuButton.setWidth(100);  
        /*
        HStack layout = new HStack();  
        layout.setWidth100();  
        layout.setMembers(menuButton);  
        layout.draw();  	 
        */
        rootPanel.add(menuButton, 84, 55);
        
        Label labelSurname = new Label("Фамилия");
        labelSurname.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(labelSurname, 84, 86);
        labelSurname.setSize("145px", "18px");
        
        final TextBox SurnametextBox = new TextBox();
        rootPanel.add(SurnametextBox, 84, 123);
        SurnametextBox.setSize("170px", "22px");
        
        Label labelName = new Label("Имя");
        labelName.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(labelName, 84, 181);
        labelName.setSize("145px", "18px");
        
        final TextBox NametextBox = new TextBox();
        rootPanel.add(NametextBox, 84, 219);
        NametextBox.setSize("170px", "22px");
        
        Label labelAddress = new Label("Домашний адрес");
        labelAddress.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(labelAddress, 485, 20);
        labelAddress.setSize("186px", "18px");
        
        final TextBox AddresstextBox = new TextBox();
        rootPanel.add(AddresstextBox, 485, 57);
        AddresstextBox.setSize("170px", "22px");
        
        final Label Phonelabel = new Label("Мобильный телефон");
        Phonelabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(Phonelabel, 485, 115);
        Phonelabel.setSize("186px", "18px");
        
        final TextBox PhonetextBox = new TextBox();
        rootPanel.add(PhonetextBox, 485, 153);
        PhonetextBox.setSize("170px", "22px");
        
        Label labelEmail = new Label("Email");
        labelEmail.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(labelEmail, 354, 216);
        labelEmail.setSize("186px", "18px");
        
        TextBox EmailtextBox = new TextBox();
        rootPanel.add(EmailtextBox, 354, 253);
        EmailtextBox.setSize("170px", "22px");
        
        Label labelSkype = new Label("Skype");
        labelSkype.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(labelSkype, 563, 216);
        labelSkype.setSize("186px", "18px");
        
        TextBox SkypetextBox = new TextBox();
        rootPanel.add(SkypetextBox, 563, 253);
        SkypetextBox.setSize("170px", "22px");
        
        Button validation = new Button("Проверить");
        validation.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {
        	public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        		if (NametextBox.getText().length()>50 || NametextBox.getText().length() == 0)
        			NametextBox.setStyleName("invalid");
        		else 
        			NametextBox.setStyleName("valid");
        		if (SurnametextBox.getText().length()>50 || SurnametextBox.getText().length() == 0)
        			SurnametextBox.setStyleName("invalid");
        		else 
        			SurnametextBox.setStyleName("valid");
        		if (AddresstextBox.getText().length()>100 || AddresstextBox.getText().length() == 0)
        			AddresstextBox.setStyleName("invalid");
        		else 
        			AddresstextBox.setStyleName("valid");
        		if (PhonetextBox.getText().length()>13 || PhonetextBox.getText().toCharArray()[0]!='+')
        			PhonetextBox.setStyleName("invalid");
        		else 
        			PhonetextBox.setStyleName("valid");
        	}
        });
        rootPanel.add(validation, 80, 287);
        
        		
    }  
}
