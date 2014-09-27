package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;

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

public class WorkExperinceElementSimplePanel extends SimplePanel{
	
	private TextBox yearText;
	private TextBox durationText;
	private ListBox durationType;
	private TextBox role;
	private TextBox nameOfInstitute;
	private VerticalPanel rootPanel;
	
	public WorkExperinceElementSimplePanel(){
		rootPanel = new VerticalPanel();
		HorizontalPanel upPanel = new HorizontalPanel();
		HorizontalPanel rolePanel = new HorizontalPanel();
		HorizontalPanel instPanel = new HorizontalPanel();
		VerticalPanel year = new VerticalPanel();
		VerticalPanel duration = new VerticalPanel();
		VerticalPanel type = new VerticalPanel();
		upPanel.setSpacing(5);
		rootPanel.setSpacing(5);
		rootPanel.add(upPanel);
		rootPanel.add(rolePanel);
		rootPanel.add(instPanel);
		upPanel.add(year);
		upPanel.add(duration);
		upPanel.add(type);
		setWidth("100%");
		
		upPanel.setSpacing(10);
		
		Label label = new Label("Год");
        label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        year.add(label);
        label.setSize("30%", "18px");
		
		yearText = new TextBox();
        year.add(yearText);
        yearText.setSize("30%", "18px");
        
        Label label_1 = new Label("Длительность");
        label_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        duration.add(label_1);
        label_1.setSize("80px", "18px");
        
        durationText = new TextBox();
        duration.add(durationText);
        durationText.setSize("70px", "18px");
        
        
        Label label_role = new Label("Должность");
        label_role.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rolePanel.add(label_role);
        label_role.setSize("80px", "18px");
        
        role = new TextBox();
        rolePanel.add(role);
        role.setSize("100%", "18px");
        
        Label label_place = new Label("Место работы");
        label_place.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        instPanel.add(label_place);
        //label_place.setHeight("18px");
        
        nameOfInstitute = new TextBox();
        instPanel.add(nameOfInstitute);
        nameOfInstitute.setSize("100%", "18px");
        
        Label label_3 = new Label(" ");
        label_3.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        type.add(label_3);
        label_3.setSize("80px", "18px");
        
        durationType = new ListBox();
        Collection<String> items = new ArrayList<String>();
        items.add("Недель");
        items.add("Месяцев");
        items.add("Лет");
        for(String i : items)
        	durationType.addItem(i);
        type.add(durationType);
        
        setWidget(rootPanel);
        durationType.setWidth("30%");
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
