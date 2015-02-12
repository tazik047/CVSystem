package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Purpose;
import ua.nure.pi.entity.CV;

import com.google.gwt.dev.util.collect.HashSet;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
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
	final IButton moreButton;
	final VerticalPanel filters;
	Collection<ProgramLanguage> ts = null;
	Collection<Language> ls = null;
	Collection<Purpose> ps = null;
	int curr;

	

	public SearchSimplePanel(final MainServiceAsync mainService) {
		this.mainService = mainService;
		isReady = true;
		//TODO: создать поиск
		HorizontalPanel rootPanel = new HorizontalPanel();
		filters = new VerticalPanel();
        filters.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        curr = 0;
		
        final FlexTable results = new FlexTable();
		results.setHeight("500px");
		results.setWidth("800px");

		
		moreButton = new IButton();
		moreButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
		        mainService.searchCV(ls, ts, ps, curr, curr+12, new AsyncCallback<Collection<CV>>() {

		        	@Override
		        	public void onSuccess(Collection<CV> result) {
		        	results.remove(moreButton);
		        	int i = 0;
		        	results.clear();
	        		for(CV cv : result) {
	        		results.setWidget(i / 5, i % 5, new PrintSmallSimplePanel(cv));
	        		i++;
		        	}
	        		results.add(moreButton);
		        	}
		        	@Override
		        	public void onFailure(Throwable caught) {
		        	setWidget(new Label(caught.getMessage()));
		        	}
		 
		        	});


			}
        	
        });

        //results.add(findButton);
       	
        
                
        
    	final ArrayList<String> opt = new ArrayList<String>();
    	opt.add("Посредственно");
    	opt.add("Хорошо");
    	opt.add("Отлично");
    	
        findButton = new IButton("Искать"); 
        findButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				HashMap<Integer, Collection<String>> techs = mstech.getValues();
				HashMap<Integer, Collection<String>> langs = mslang.getValues();
				HashMap<Integer, Collection<String>> purps = mspurp.getValues();
				ts = getTechsFromMap(techs);
				ls = getLangsFromMap(langs);
				ps = getPurpsFromMap(purps);
				curr = 0;
		        mainService.searchCV(ls, ts, ps, curr, curr+12, new AsyncCallback<Collection<CV>>() {

		        	@Override
		        	public void onSuccess(Collection<CV> result) {
		        	Label l = new Label();
		        	int i = 0;
		        	results.clear();
	        		for(CV cv : result) {
	        		results.setWidget(i / 5, i % 5, new PrintSmallSimplePanel(cv));
	        		i++;
		        	}
		        	}
		        	@Override
		        	public void onFailure(Throwable caught) {
		        	setWidget(new Label(caught.getMessage()));
		        	}
		 
		        	});


			}
        	
        });

        mainService.getPurposes(new AsyncCallback<Collection<Purpose>>() {

        	@Override
        	public void onSuccess(Collection<Purpose> result) {
        	HashMap<String, String> map = new HashMap<String, String>();
        	for(Purpose prp : result){
        	map.put(String.valueOf(prp.getId()), prp.getTitle());
        	}
        	mspurp = new MultiSelect(map, "Цель работы");
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
        	flaglang = true;
        	tryDraw();
        	}

        	@Override
        	public void onFailure(Throwable caught) {
        	setWidget(new Label(caught.getMessage()));
        	}
        	});
        
        mainService.searchCV(null, null, null, 0, 12, new AsyncCallback<Collection<CV>>() {

        	@Override
        	public void onSuccess(Collection<CV> result) {
        	Label l = new Label();
        	int i = 0;
    		for(CV cv : result) {
    		results.setWidget(i / 5, i % 5, new PrintSmallSimplePanel(cv));
    		i++;
        	}
    		results.add(moreButton);
        	}
        	@Override
        	public void onFailure(Throwable caught) {
        	setWidget(new Label(caught.getMessage()));
        	}
 
        	});
        
        findButton.setAlign(Alignment.CENTER);
        findButton.setWidth(120);  
        
        
        rootPanel.add(filters);
        rootPanel.add(results);
        
        setWidget(rootPanel);


	}
	
	private void tryDraw() {
        
        if (flaglang && flagpurp && flagtech) {
        	filters.add(mspurp);
        	mspurp.draw();
        	mspurp.setWidth(200);
        	mspurp.setHeight(200);
        	mspurp.setStyleName("filterPanel");
        	filters.add(mstech);
        	mstech.draw();
        	mstech.setWidth(200);
        	mstech.setHeight(200);
        	mstech.setStyleName("filterPanel");
        	filters.add(mslang);
        	mslang.draw();
        	mslang.setWidth(200);
        	mslang.setHeight(150);
        	mslang.setStyleName("filterPanel");
            filters.add(findButton);
        	findButton.draw();
        }
        
	}
	
	private Collection<Language> getLangsFromMap(HashMap<Integer, Collection<String>> hash) {
		Collection<Language> ls = new ArrayList<Language>();
		for (Entry<Integer, Collection<String>> entry : hash.entrySet()) {
		    for (String s : entry.getValue()) {
			Language curr = new Language();
		    curr.setLevel(entry.getKey());
		    curr.setId(Long.parseLong(s));
		    ls.add(curr);
		    }
		}
		return ls;
	}
	private Collection<ProgramLanguage> getTechsFromMap(HashMap<Integer, Collection<String>> hash) {
		Collection<ProgramLanguage> ls = new ArrayList<ProgramLanguage>();
		for (Entry<Integer, Collection<String>> entry : hash.entrySet()) {
		    for (String s : entry.getValue()) {
		    ProgramLanguage curr = new ProgramLanguage();
		    curr.setLevel(entry.getKey());
		    curr.setId(Long.parseLong(s));
		    ls.add(curr);
		    }
		}
		return ls;
	}
	private Collection<Purpose> getPurpsFromMap(HashMap<Integer, Collection<String>> hash) {
		Collection<Purpose> ls = new ArrayList<Purpose>();
		for (Entry<Integer, Collection<String>> entry : hash.entrySet()) {
		    for (String s : entry.getValue()) {
		    Purpose curr = new Purpose();
		    curr.setId(Long.parseLong(s));
		    ls.add(curr);
		    }
		}
		return ls;
	}
	
}
