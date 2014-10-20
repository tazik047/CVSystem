package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.entity.Education;
import ua.nure.pi.entity.WorkExp;
import ua.nure.pi.entity.typeOfDuration;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class EducationElementSimplePanel extends SimplePanel{
	
	private TextItem startYearText;
	private TextItem endYearText;
	private TextItem specialty;
	private TextItem nameOfInstitute;
	private VerticalPanel rootPanel;
	public Image imgDel;
	public DynamicForm controls;
	
	public EducationElementSimplePanel(){
		rootPanel = new VerticalPanel();
		HorizontalPanel upPanel = new HorizontalPanel();
		HorizontalPanel downPanel = new HorizontalPanel();
		VerticalPanel labelPanel = new VerticalPanel();
		VerticalPanel textPanel = new VerticalPanel();
		VerticalPanel startYear = new VerticalPanel();
		VerticalPanel endYear = new VerticalPanel();
		upPanel.setSpacing(5);
		rootPanel.setSpacing(5);

		
		imgDel = new Image("img/close.png", 0, 0, 30, 30);
		imgDel.setStyleName("imgDelStyle");
		imgDel.setTitle("Удалить образование");
		rootPanel.add(imgDel);
		setWidth("343px");
		labelPanel.setSpacing(5);
		textPanel.setSpacing(5);
		
		upPanel.setSpacing(10);
		
		startYearText = new TextItem("startYear", "Год начала");  
		startYearText.setWidth(70);  
		startYearText.setHint("");  
		startYearText.setShowHintInField(true);
		startYearText.setMask("####");
	    startYearText.setRequired(true);

		
	    endYearText = new TextItem("endYear", "Год окончания");  
	    endYearText.setWidth(70);  
	    endYearText.setHint("");  
	    endYearText.setShowHintInField(true);
	    endYearText.setMask("####");
	    endYearText.setRequired(true);
        
	    specialty = new TextItem("specialty", "Специальность");  
	    specialty.setWidth(200);  
	    specialty.setHint("");  
	    specialty.setShowHintInField(true);
	    specialty.setRequired(true);

	    nameOfInstitute = new TextItem("nameOfInstitute", "Место учёбы");  
	    nameOfInstitute.setWidth(200);  
	    nameOfInstitute.setHint("");  
	    nameOfInstitute.setShowHintInField(true);
	    nameOfInstitute.setRequired(true);
        controls = new DynamicForm();
        controls.setFields(startYearText, endYearText, specialty, nameOfInstitute);
        
        rootPanel.add(controls);
        controls.draw();
        controls.markForRedraw();
	    
        setWidget(rootPanel);
	}
	
	public Education getEducation() throws IllegalArgumentException{
		Education ed = new Education();
		int startYear = Integer.parseInt(startYearText.getValueAsString());
		int endYear = Integer.parseInt(endYearText.getValueAsString());
		
		ed.setStartYear(startYear);
		ed.setEndYear(endYear);		
		ed.setNameOfInstitution(nameOfInstitute.getValueAsString());
		ed.setSpecialty(specialty.getValueAsString());
		return ed;
	}
	
	public void addSeparator(){
		rootPanel.add(new HTML("<hr/>"));
	}
}
