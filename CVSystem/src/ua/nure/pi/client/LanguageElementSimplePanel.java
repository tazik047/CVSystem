package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import ua.nure.pi.entity.Language;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment.VerticalAlignmentConstant;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;  
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler; 


public class LanguageElementSimplePanel extends SimplePanel{
	
	private ListBox langBox;
	private int levelType;
	private VerticalPanel rootPanel;
	public Image imgDel;
	
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
		
		imgDel = new Image("img/close.png", 0, 0, 16, 16);
		imgDel.setStyleName("imgDelStyle");
		imgDel.setTitle("Удалить опыт работы");
		rootPanel.add(imgDel);
		
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
        styleMap.put(0, "Начальный");  
        styleMap.put(1, "Средний");  
        styleMap.put(2, "Высокий");  
          
        RadioGroupItem style = new RadioGroupItem();
        style.setCellStyle("Fiix");
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
        controls.setWidth(150);
        controls.setStyleName("fixForm");
        controls.setFields(style);
        controls.setHeight(110);
        controls.markForRedraw();
        level.add(controls);
        
        setWidget(rootPanel);
	}
	
	public Language getLanguage() throws IllegalArgumentException{
		Language lang = new Language();
		return lang;
	}
}
