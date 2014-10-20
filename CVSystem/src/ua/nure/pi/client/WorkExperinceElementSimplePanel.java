package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import ua.nure.pi.entity.WorkExp;
import ua.nure.pi.entity.typeOfDuration;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.ReadOnlyDisplayAppearance;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class WorkExperinceElementSimplePanel extends SimplePanel{
	
	private TextBox yearText;
	private TextBox durationText;
	private ListBox durationType;
	private TextBox role;
	private TextBox nameOfInstitute;
	private VerticalPanel rootPanel;
	public DynamicForm controls;
	
	public Image imgDel; 
	public WorkExperinceElementSimplePanel(){
		rootPanel = new VerticalPanel();
		HorizontalPanel upPanel = new HorizontalPanel();
		HorizontalPanel downPanel = new HorizontalPanel();
		VerticalPanel labelPanel = new VerticalPanel();
		VerticalPanel textPanel = new VerticalPanel();
		upPanel.setSpacing(5);
		rootPanel.setSpacing(5);
		downPanel.add(labelPanel);
		downPanel.add(textPanel);
		imgDel = new Image("img/close.png", 0, 0, 16, 16);
		imgDel.setStyleName("imgDelStyle");
		imgDel.setTitle("Удалить опыт работы");
		rootPanel.add(imgDel);
		
		setWidth("343px");
		labelPanel.setSpacing(5);
		textPanel.setSpacing(5);
		
		upPanel.setSpacing(10);
		
		controls = new DynamicForm();  
		controls.setStyleName("fixUpPanel");
		
  
        TextItem yearField = new TextItem("year", "Год");  
        yearField.setWidth(70);
        yearField.setHeight(18);
        yearField.setMask("####");
        yearField.setKeyPressFilter("[0-9.]");
        yearField.setRequired(true);
        
        TextItem durationField = new TextItem("duration", "Длительность");  
        durationField.setWidth(70);
        durationField.setHeight(18);
        durationField.setRequired(true);
        durationField.setKeyPressFilter("[0-9.]");
        
        LinkedHashMap<typeOfDuration, String> valueMap = new LinkedHashMap<typeOfDuration, String>();
        valueMap.put(typeOfDuration.WEEK, "недель");
        valueMap.put(typeOfDuration.MONTH, "месяцев");
        valueMap.put(typeOfDuration.YEAR, "лет");
        SelectItem durTypeField = new SelectItem("type","Тип");
        durTypeField.setValueMap(valueMap);
        durTypeField.setWidth(100);
                
        
		  
        TextItem role = new TextItem("role", "Должность");  
        role.setWidth(200);
        role.setHeight(18);
        role.setKeyPressFilter("[0-9.]");
        role.setRequired(true);
        role.setTitleOrientation(TitleOrientation.LEFT);

        
        TextItem nameOfInstitute = new TextItem("nameOfInstitute", "Место работы");  
        nameOfInstitute.setWidth(200);
        nameOfInstitute.setHeight(18);
        nameOfInstitute.setKeyPressFilter("[0-9.]");;
        nameOfInstitute.setRequired(true);
        nameOfInstitute.setTitleOrientation(TitleOrientation.LEFT);

		controls.setStyleName("fixUpPanel");

        controls.setFields(yearField, durationField, durTypeField, role, nameOfInstitute);

        
        rootPanel.add(controls);
        controls.draw();
        controls.markForRedraw();

        setWidget(rootPanel);
        
	}
	
	public WorkExp getWorkExp() throws IllegalArgumentException{
		WorkExp we = new WorkExp();
		int year = Integer.parseInt(yearText.getText());
		int durat = Integer.parseInt(durationText.getText());
		
		we.setStartYear(year);
		we.setDuration(durat);
		
		typeOfDuration types = null;
		switch(durationType.getSelectedIndex()){
			case 0:
				types = typeOfDuration.WEEK;
				break;
			case 1:
				types = typeOfDuration.MONTH;
				break;
			case 2:
				types = typeOfDuration.YEAR;
				break;
		}
		we.setTypeOfDuration(types);
		
		we.setNameOfInstitution(nameOfInstitute.getText());
		we.setRole(role.getText());
		return we;
	}
	
	public void addSeparator(){
		rootPanel.add(new HTML("<hr/>"));
	}
}
