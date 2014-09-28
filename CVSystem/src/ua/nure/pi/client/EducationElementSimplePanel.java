package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.entity.Education;
import ua.nure.pi.entity.WorkExp;
import ua.nure.pi.entity.typeOfDuration;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EducationElementSimplePanel extends SimplePanel{
	
	private TextBox startYearText;
	private TextBox endYearText;
	private TextBox specialty;
	private TextBox nameOfInstitute;
	private VerticalPanel rootPanel;
	
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
		rootPanel.add(upPanel);
		rootPanel.add(downPanel);
		downPanel.add(labelPanel);
		downPanel.add(textPanel);
		upPanel.add(startYear);
		upPanel.add(endYear);
		setWidth("343px");
		labelPanel.setSpacing(5);
		
		upPanel.setSpacing(10);
		
		Label label = new Label("Год начала");
        label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        startYear.add(label);
        label.setSize("70px", "18px");
		
        startYearText = new TextBox();
        startYear.add(startYearText);
        startYearText.setSize("70px", "18px");
        
        Label label_1 = new Label("Год окончания");
        label_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        endYear.add(label_1);
        label_1.setSize("80px", "18px");
        
        endYearText = new TextBox();
        endYear.add(endYearText);
        endYearText.setSize("70px", "18px");
        
        
        Label label_role = new Label("Специальность");
        label_role.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        labelPanel.add(label_role);
        label_role.setSize("80px", "18px");
        
        specialty = new TextBox();
        textPanel.add(specialty);
        specialty.setSize("100%", "18px");
        
        Label label_place = new Label("Место учёбы");
        label_place.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        labelPanel.add(label_place);
        //label_place.setHeight("18px");
        
        nameOfInstitute = new TextBox();
        textPanel.add(nameOfInstitute);
        nameOfInstitute.setSize("100%", "18px");
                        
        setWidget(rootPanel);
	}
	
	public Education getEducation() throws IllegalArgumentException{
		Education ed = new Education();
		int startYear = Integer.parseInt(startYearText.getText());
		int endYear = Integer.parseInt(endYearText.getText());
		
		ed.setStartYear(startYear);
		ed.setEndYear(endYear);		
		return ed;
	}
	
	public void addSeparator(){
		rootPanel.add(new HTML("<hr/>"));
	}
}
