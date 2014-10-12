package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.entity.Language;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickEvent;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickHandler;

public class LanguageSimplePanel extends SimplePanel{
	
	private ArrayList<LanguageElementSimplePanel> languages;
	private SectionStack sectionStack;
	private int countColor = -1;
	private int pixelCount = 26;
	private VerticalPanel root;
	private boolean expend = true;
	
	public LanguageSimplePanel() {
		sectionStack = new SectionStack();  
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
  
        sectionStack.setWidth("355px");
        sectionStack.setHeight(String.valueOf(pixelCount)+"px");
        SectionStackSection section1 = new SectionStackSection("Языки");
        sectionStack.addSection(section1);
		
		root = new VerticalPanel();
		root.setWidth("100%");
		final AbsolutePanel absP = new AbsolutePanel();
		
		languages = new ArrayList<LanguageElementSimplePanel>();
		DynamicForm form = new DynamicForm();
		section1.addItem(form);
        form.setNumCols(4);
        final VerticalPanel vp = new VerticalPanel();
        form.addChild(vp);
        final Button btAdd = new Button("Добавить язык");
        btAdd.addStyleName("panel-startAddButton");
        btAdd.setTitle("Добавить язык");
        root.add(absP);
        root.add(btAdd);
        root.setCellHorizontalAlignment(btAdd, HasHorizontalAlignment.ALIGN_LEFT);
        btAdd.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				addLang(vp, absP, btAdd);
				sectionStack.markForRedraw();
			}
		});
        
        sectionStack.addSectionHeaderClickHandler(new SectionHeaderClickHandler() {
			
			@Override
			public void onSectionHeaderClick(SectionHeaderClickEvent event) {
				if(expend)
					sectionStack.setHeight(String.valueOf("26px"));
				else
					sectionStack.setHeight(String.valueOf(pixelCount)+"px");
				btAdd.setVisible(!expend);
				sectionStack.markForRedraw();
				expend = !expend; 
			}
		});
        setWidget(root);
        form.markForRedraw();
        
	}
	
	private void addLang(final VerticalPanel vp, final AbsolutePanel absP, final Button btAdd){		
		if(languages.size() == 0){
			absP.add(sectionStack);
			root.remove(btAdd);
			absP.add(btAdd);
			btAdd.removeStyleName("panel-startAddButton");
			btAdd.addStyleName("panel-newAddButton");
						
		}
		final LanguageElementSimplePanel lang = new LanguageElementSimplePanel();
		pixelCount+=168;
		sectionStack.setHeight(String.valueOf(pixelCount)+"px");
		countColor  = (++countColor)%2;
		if(countColor == 0){
			lang.setStyleName("stylePanel1");
		}
		else
			lang.setStyleName("stylePanel2");
		lang.imgDel.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				vp.remove(lang);
				languages.remove(lang);
				pixelCount-=168;
				sectionStack.setHeight(String.valueOf(pixelCount)+"px");
				if(languages.size()==0){
					sectionStack.removeFromParent();
					absP.remove(btAdd);
					root.add(btAdd);
					btAdd.removeStyleName("panel-newAddButton");
					btAdd.addStyleName("panel-startAddButton");
				}
				sectionStack.markForRedraw();
			}
		});
		vp.add(lang);
		languages.add(lang);
	}

	public Collection<Language> getLanguages() throws IllegalArgumentException{
		Collection<Language> studentLanguages = new ArrayList<Language>();
		for(LanguageElementSimplePanel we : languages)
			studentLanguages.add(we.getLanguage());
		return studentLanguages;
	}
}
