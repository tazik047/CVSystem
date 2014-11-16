package ua.nure.pi.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class RegistrationCompanySimplePanel extends LoadingSimplePanel {

	MainServiceAsync mainService;
	Button toMainPage;
	public TextItem nameOfCompany;
	public DynamicForm mainForm;
	public StaticTextItem nameHint;
	
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
	    nameHint = new StaticTextItem();
	    nameHint.setShowTitle(false);
	    nameHint.setValue("Например, OOO \"Apple\"");
	    nameOfCompany.setRequired(true);
	    
	    mainForm.setFields(nameHint,nameOfCompany);
	    mainForm.markForRedraw();
	    add(rootPanel);
	    
	}
}
