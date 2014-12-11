package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import ua.nure.pi.entity.WorkExp;
import ua.nure.pi.entity.typeOfDuration;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;  
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;  
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.ReadOnlyDisplayAppearance;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class WorkExperinceElementSimplePanel extends SimplePanel{
	
	private TextItem yearField;
	private TextItem durationField;
	private SelectItem durTypeField;
	private TextItem role;
	private TextItem nameOfInstitute;
	private VerticalPanel rootPanel;
	public DynamicForm controls;
	public CheckboxItem currentWork;
	
	public Image imgDel; 
	public WorkExperinceElementSimplePanel(){
		rootPanel = new VerticalPanel();
		rootPanel.setSpacing(5);
		imgDel = new Image("img/close.png", 0, 0, 30, 30);
		imgDel.setStyleName("imgDelStyle");
		imgDel.setTitle("Удалить опыт работы");
		rootPanel.add(imgDel);
		
		setWidth("343px");
		setHeight("178px");
		
		
		controls = new DynamicForm();
		
  
        yearField = new TextItem("year", "Год начала");  
        yearField.setWidth(70);
        yearField.setHeight(18);
        yearField.setMask("####");
        yearField.setRequired(true);
        
        currentWork = new CheckboxItem ();
        currentWork.setName("isWorking");
        currentWork.setTitle("Работаю по сей день");
        
        durationField = new TextItem("duration", "Продолжительность");  
        durationField.setWidth(70);
        durationField.setHeight(18);
        durationField.setRequired(true);
        durationField.setKeyPressFilter("[0-9.]");
                
        LinkedHashMap<typeOfDuration, String> valueMap = new LinkedHashMap<typeOfDuration, String>();
        valueMap.put(typeOfDuration.WEEK, "недель");
        valueMap.put(typeOfDuration.MONTH, "месяцев");
        valueMap.put(typeOfDuration.YEAR, "лет");
        durTypeField = new SelectItem("type","Тип");
        durTypeField.setValueMap(valueMap);
        durTypeField.setWidth(100);
        durTypeField.setDefaultValue(typeOfDuration.WEEK);
                
        
		  
        role = new TextItem("role", "Должность");  
        role.setWidth(183);
        role.setHeight(18);
        role.setRequired(true);
        role.setTitleOrientation(TitleOrientation.LEFT);

        
        nameOfInstitute = new TextItem("nameOfInstitute", "Место работы");  
        nameOfInstitute.setWidth(183);
        nameOfInstitute.setHeight(18);
        nameOfInstitute.setRequired(true);
        nameOfInstitute.setTitleOrientation(TitleOrientation.LEFT);

		//controls.setStyleName("fixUpPanel");
        controls.setNumCols(1);
        
        currentWork.addChangedHandler(new ChangedHandler() {  
            public void onChanged(ChangedEvent event) {  
            	if (durationField.isDisabled()) {
            		durationField.enable();
            		durTypeField.enable();
                }
            	else {
            		durationField.disable();
            		durTypeField.disable();
            	}
            	durationField.setRequired(!currentWork.getValueAsBoolean());
            }  
        });  

        controls.setFields(yearField, currentWork, durationField, durTypeField, role, nameOfInstitute);
        controls.setRequiredTitleSuffix("");
        controls.setTitleSuffix("");
        rootPanel.add(controls);
        controls.markForRedraw();

        setWidget(rootPanel);
        
	}
	
	
	
	public WorkExp getWorkExp() throws IllegalArgumentException{
		WorkExp we = new WorkExp();
		int year = Integer.parseInt(yearField.getValueAsString());
		
		we.setStartYear(year);
		if(!currentWork.getValueAsBoolean()){
			int durat = Integer.parseInt(durationField.getValueAsString());
			we.setDuration(durat);
			we.setTypeOfDuration((typeOfDuration)durTypeField.getValue());
			we.setIsNow(false);
		}
		else{
			we.setIsNow(true);
		}
		we.setNameOfInstitution(nameOfInstitute.getValueAsString());
		we.setRole(role.getValueAsString());
		return we;
	}
	
	
	public void addSeparator(){
		rootPanel.add(new HTML("<hr/>"));
	}
}
