package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.WorkExp;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.form.DynamicForm;

public class LanguageSimplePanel extends SimplePanel{
	
	private ArrayList<LanguageElementSimplePanel> languages;
	private DynamicForm form;
	private int countColor = -1;
	
	public LanguageSimplePanel() {
		VerticalPanel hp = new VerticalPanel();
		hp.setWidth("100%");
		final AbsolutePanel absP = new AbsolutePanel();
		
		languages = new ArrayList<LanguageElementSimplePanel>();
		form = new DynamicForm();  
        form.setIsGroup(true);  
        form.setGroupTitle("Языки");
        form.setNumCols(4);
        final VerticalPanel vp = new VerticalPanel();
        form.addChild(vp);
        Button btAdd = new Button("Добавить язык");
        hp.add(absP);
        hp.add(btAdd);
        btAdd.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				addLang(vp, absP);
				form.markForRedraw();
			}
		});
        setWidget(hp);
        form.markForRedraw();
        
	}
	
	private void addLang(final VerticalPanel vp, AbsolutePanel absP){
		/*if(languages.size()!=0)
			languages.get(languages.size()-1).addSeparator();
		else
			absP.add(form);
		final LanguageElementSimplePanel lang = new LanguageElementSimplePanel();
		final HorizontalPanel hp = new HorizontalPanel();
		Image imgDel = new Image("img/close.png", 0, 0, 16, 16);
		imgDel.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				vp.remove(hp);
				languages.remove(lang);
				if(languages.size()==0)
					form.removeFromParent();
				form.markForRedraw();
			}
		});
		hp.add(lang);
		hp.add(imgDel);
		hp.setCellVerticalAlignment(imgDel, HasVerticalAlignment.ALIGN_MIDDLE);
		vp.add(hp);
		languages.add(lang);*/
		
		if(languages.size() == 0)
			absP.add(form);
		final LanguageElementSimplePanel lang = new LanguageElementSimplePanel();
		countColor  = (++countColor)%2;
		if(countColor == 0){
			lang.setStyleName("stylePanel1");
		}
		else
			lang.setStyleName("stylePanel2");
		/*final HorizontalPanel hp = new HorizontalPanel();
		Image imgDel = new Image("img/close.png", 0, 0, 16, 16);*/
		lang.imgDel.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				vp.remove(lang);
				languages.remove(lang);
				if(languages.size()==0)
					form.removeFromParent();
				form.markForRedraw();
			}
		});
		/*hp.add(exp);
		hp.add(imgDel);
		hp.setCellVerticalAlignment(imgDel, HasVerticalAlignment.ALIGN_MIDDLE);*/
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
