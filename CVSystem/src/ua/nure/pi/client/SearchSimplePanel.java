package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Purpose;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class SearchSimplePanel extends LoadingSimplePanel {

	MainServiceAsync mainService;
	boolean flagpurp;
	boolean flaglang;
	boolean flagtech;
	MultiSelect mspurp;
	MultiSelect mstech;
	MultiSelect mslang;
	final IButton findButton;

	public SearchSimplePanel(MainServiceAsync mainService) {
		this.mainService = mainService;
		isReady = true;
		//TODO: создать поиск
		HorizontalPanel rootPanel = new HorizontalPanel();
		
		final VerticalPanel filters = new VerticalPanel();
        
        FlexTable results = new FlexTable();
		results.setHeight("500px");
		results.setWidth("800px");

        //results.add(findButton);
       	
        
                
        
    	final ArrayList<String> opt = new ArrayList<String>();
    	opt.add("Посредственно");
    	opt.add("Хорошо");
    	opt.add("Отлично");
    	
        findButton = new IButton("Искать");  

        mainService.getPurposes(new AsyncCallback<Collection<Purpose>>() {

        	@Override
        	public void onSuccess(Collection<Purpose> result) {
        	HashMap<String, String> map = new HashMap<String, String>();
        	for(Purpose prp : result){
        	map.put(String.valueOf(prp.getId()), prp.getTitle());
        	}
        	mspurp = new MultiSelect(map, "Цель работы");
        	filters.add(mspurp);
        	mspurp.setWidth(200);
        	mspurp.setHeight(200);
        	flagpurp = true;
        	tryDraw();
        	}

        	@Override
        	public void onFailure(Throwable caught) {
        	setWidget(new Label(caught.getMessage()));
        	}
        	});
        


        mainService.getProgramLanguages(new AsyncCallback<Collection<ProgramLanguage>>() {

        	@Override
        	public void onSuccess(Collection<ProgramLanguage> result) {
        	HashMap<String, String> map = new HashMap<String, String>();
        	for(ProgramLanguage pl : result){
        	map.put(String.valueOf(pl.getId()), pl.getTitle());
        	}
        	mstech = new MultiSelect(map, opt, "Владение технологиями");
        	filters.add(mstech);
        	mstech.setWidth(200);
        	mstech.setHeight(200);
        	flagtech = true;
        	tryDraw();
        	}

        	@Override
        	public void onFailure(Throwable caught) {
        	setWidget(new Label(caught.getMessage()));
        	}
        	});
        
        mainService.getLanguages(new AsyncCallback<Collection<Language>>() {

        	@Override
        	public void onSuccess(Collection<Language> result1) {
        	HashMap<String, String> map1 = new HashMap<String, String>();
        	for(Language lang : result1){
        	map1.put(String.valueOf(lang.getId()), lang.getTitle());
        	}
        	mslang = new MultiSelect(map1, opt, "Владение языками");
        	filters.add(mslang);
        	mslang.setWidth(200);
        	mslang.setHeight(150);
        	flaglang = true;
        	tryDraw();
        	}

        	@Override
        	public void onFailure(Throwable caught) {
        	setWidget(new Label(caught.getMessage()));
        	}
        	});
        findButton.setAlign(Alignment.RIGHT);
        findButton.setWidth(120);  

        filters.add(findButton);
        
        rootPanel.add(filters);
        rootPanel.add(results);
        tryDraw();
        
        setWidget(rootPanel);


	}
	
	private void tryDraw() {
        
        if (flaglang && flagpurp && flagtech) {
        	findButton.draw();
        	mspurp.draw();
        	mstech.draw();
        	mslang.draw();
        }
        
	}
	
}
