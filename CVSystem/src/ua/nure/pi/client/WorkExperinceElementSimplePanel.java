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
	
	public Image imgDel; 
	public WorkExperinceElementSimplePanel(){
		rootPanel = new VerticalPanel();
		HorizontalPanel upPanel = new HorizontalPanel();
		HorizontalPanel downPanel = new HorizontalPanel();
		VerticalPanel labelPanel = new VerticalPanel();
		VerticalPanel textPanel = new VerticalPanel();
		upPanel.setSpacing(5);
		rootPanel.setSpacing(5);
		rootPanel.add(upPanel);
		rootPanel.add(downPanel);
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
		
		DynamicForm form = new DynamicForm();  
		form.setStyleName("fixUpPanel");
		
  
        TextItem yearField = new TextItem("year", "Год");  
        yearField.setWidth(70);
        yearField.setHeight(18);
        yearField.setKeyPressFilter("[0-9.]");
        
        TextItem durationField = new TextItem("duration", "Длительность");  
        durationField.setWidth(70);
        durationField.setHeight(18);
        durationField.setKeyPressFilter("[0-9.]");
        
        LinkedHashMap<typeOfDuration, String> valueMap = new LinkedHashMap<typeOfDuration, String>();
        valueMap.put(typeOfDuration.WEEK, "недель");
        valueMap.put(typeOfDuration.MONTH, "месяцев");
        valueMap.put(typeOfDuration.YEAR, "лет");
        SelectItem durTypeField = new SelectItem("type","Тип");
        //durTypeField.setReadOnlyDisplay(ReadOnlyDisplayAppearance.STATIC);
        durTypeField.setValueMap(valueMap);
        durTypeField.setWidth(100);
        form.setNumCols(3);
        form.setFields(yearField, durationField, durTypeField);
        form.setTitleOrientation(TitleOrientation.TOP);
        form.draw(); 

		upPanel.add(form);
        
        
        Label label_role = new Label("Должность");
        label_role.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        labelPanel.add(label_role);
        label_role.setSize("80px", "18px");
        
        role = new TextBox();
        textPanel.add(role);
        role.setSize("100%", "18px");
        
        Label label_place = new Label("Место работы");
        label_place.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        labelPanel.add(label_place);
        //label_place.setHeight("18px");
        
        nameOfInstitute = new TextBox();
        textPanel.add(nameOfInstitute);
        nameOfInstitute.setSize("100%", "18px");
        
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
		return we;
	}
	
	public void addSeparator(){
		rootPanel.add(new HTML("<hr/>"));
	}
}
