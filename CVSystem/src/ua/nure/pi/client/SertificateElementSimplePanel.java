package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import ua.nure.pi.entity.Sertificate;
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

public class SertificateElementSimplePanel extends SimplePanel{
	
	private TextItem desc;
	private TextBox nameOfInstitute;
	private VerticalPanel rootPanel;
	private TextItem yearField;
	public DynamicForm controls;
	
	public Image imgDel; 
	public SertificateElementSimplePanel(){
		rootPanel = new VerticalPanel();
		HorizontalPanel upPanel = new HorizontalPanel();
		HorizontalPanel downPanel = new HorizontalPanel();
		VerticalPanel labelPanel = new VerticalPanel();
		VerticalPanel textPanel = new VerticalPanel();
		upPanel.setSpacing(5);
		rootPanel.setSpacing(5);
		downPanel.add(labelPanel);
		downPanel.add(textPanel);
		imgDel = new Image("img/close.png", 0, 0, 30, 30);
		imgDel.setStyleName("imgDelStyle");
		imgDel.setTitle("Удалить сертификат");
		rootPanel.add(imgDel);
		
		setWidth("343px");
		labelPanel.setSpacing(5);
		textPanel.setSpacing(5);
		
		upPanel.setSpacing(10);
		
		controls = new DynamicForm();  
		controls.setStyleName("fixUpPanel");
		
  
        yearField = new TextItem("year", "Год получения");  
        yearField.setWidth(70);
        yearField.setHeight(18);
        yearField.setKeyPressFilter("[0-9.]");
        yearField.setTitleOrientation(TitleOrientation.LEFT);
        yearField.setRequired(true);
        
        desc = new TextItem("desc", "Описание");  
        desc.setWidth(200);
        desc.setHeight(18);
        desc.setKeyPressFilter("[0-9.]");
        desc.setTitleOrientation(TitleOrientation.LEFT);
        desc.setRequired(true);

        controls.setNumCols(1);        
        controls.setFields(yearField, desc);
		rootPanel.add(controls);
        controls.draw(); 
        controls.markForRedraw();


        setWidget(rootPanel);
        
	}
	
	public Sertificate getSertificate() throws IllegalArgumentException{
		Sertificate se = new Sertificate();
		int year = Integer.parseInt(yearField.getValueAsString());
		se.setSertificateName(desc.getValueAsString());
		se.setSertificateYear(year);
		
		return se;
	}
	
	public void addSeparator(){
		rootPanel.add(new HTML("<hr/>"));
	}
}
