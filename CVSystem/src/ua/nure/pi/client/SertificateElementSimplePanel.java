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
	
	private TextBox yearText;
	private TextBox durationText;
	private ListBox durationType;
	private TextBox desc;
	private TextBox nameOfInstitute;
	private VerticalPanel rootPanel;
	private TextItem yearField;
	
	public Image imgDel; 
	public SertificateElementSimplePanel(){
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
		imgDel.setTitle("Удалить сертификат");
		rootPanel.add(imgDel);
		
		setWidth("343px");
		labelPanel.setSpacing(5);
		textPanel.setSpacing(5);
		
		upPanel.setSpacing(10);
		
		DynamicForm form = new DynamicForm();  
		form.setStyleName("fixUpPanel");
		
  
        yearField = new TextItem("year", "Год получения");  
        yearField.setWidth(150);
        yearField.setHeight(18);
        yearField.setKeyPressFilter("[0-9.]");
                
        form.setFields(yearField);
        form.setNumCols(3);
        form.setTitleOrientation(TitleOrientation.TOP);
        form.draw(); 

		upPanel.add(form);
        
        
        Label label_desc = new Label("Описание");
        label_desc.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        labelPanel.add(label_desc);
        label_desc.setSize("80px", "18px");
        
        desc = new TextBox();
        textPanel.add(desc);
        desc.setSize("100%", "18px");
        
        setWidget(rootPanel);
        
	}
	
	public Sertificate getSertificate() throws IllegalArgumentException{
		Sertificate se = new Sertificate();
		int year = Integer.parseInt(yearField.getValueAsString());
		se.setSertificateName(desc.getText());
		se.setSertificateYear(year);
		
		return se;
	}
	
	public void addSeparator(){
		rootPanel.add(new HTML("<hr/>"));
	}
}
