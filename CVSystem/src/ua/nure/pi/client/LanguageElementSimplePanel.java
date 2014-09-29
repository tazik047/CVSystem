package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import ua.nure.pi.entity.WorkExp;
import ua.nure.pi.entity.typeOfDuration;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment.VerticalAlignmentConstant;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;  
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler; 


public class LanguageElementSimplePanel extends SimplePanel{
	
	private ListBox langBox;
	private int levelType;
	private VerticalPanel rootPanel;
	
	public LanguageElementSimplePanel(){
		rootPanel = new VerticalPanel();
		HorizontalPanel upPanel = new HorizontalPanel();
		VerticalPanel lang = new VerticalPanel();
		VerticalPanel level = new VerticalPanel();
		
		upPanel.setSpacing(5);
		rootPanel.setSpacing(5);
		rootPanel.add(upPanel);
		upPanel.add(lang);
		upPanel.add(level);
		
		level.setWidth("200px");
		
		upPanel.setCellVerticalAlignment(lang, HasVerticalAlignment.ALIGN_MIDDLE);
		setWidth("343px");
		
		upPanel.setSpacing(10);
		
		Label label = new Label("Язык");
        label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        lang.add(label);
        label.setSize("70px", "18px");
		
		langBox = new ListBox();
        lang.add(langBox);
        langBox.setSize("70px", "18px");
        
        Label label_1 = new Label("Уровень знаний");
        label_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        level.add(label_1);
        label_1.setSize("150px", "18px");
        
        
        
        LinkedHashMap<Integer, String> styleMap = new LinkedHashMap<Integer, String>();  
        styleMap.put(0, "Начинающий");  
        styleMap.put(1, "Базовый уровень");  
        styleMap.put(2, "Средний уровень");  
        styleMap.put(3, "Продвинутый уровень");
        styleMap.put(4, "Свободное владение");  
          
        RadioGroupItem style = new RadioGroupItem();  
        style.setCellStyle("fixRadio");
        style.setDefaultValue(0);  
        style.setShowTitle(false);
        style.setValueMap(styleMap);
        style.addChangedHandler(new ChangedHandler() {  
            public void onChanged(ChangedEvent event) {  
                levelType = Integer.parseInt(event.getValue().toString());  
            }  
        });
        style.setWidth(250);
          
        DynamicForm controls = new DynamicForm();
        controls.setStyleName("fixForm");
        controls.setFields(style);
        controls.setHeight(110);
        controls.markForRedraw();
        level.add(controls);
        
        setWidget(rootPanel);
	}
	
	public WorkExp getWorkExp() throws IllegalArgumentException{
		WorkExp we = new WorkExp();
		return we;
	}
	
	public void addSeparator(){
		rootPanel.add(new HTML("<hr/>"));
	}
}
