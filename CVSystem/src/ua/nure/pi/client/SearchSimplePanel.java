package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;

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
	
	public SearchSimplePanel(MainServiceAsync mainService) {
		this.mainService = mainService;
		isReady = true;
		//TODO: создать поиск
		HorizontalPanel rootPanel = new HorizontalPanel();
		
		final VerticalPanel filters = new VerticalPanel();
        
		final ListGrid purposeGrid = new ListGrid();  
		purposeGrid.setWidth(180);  
		purposeGrid.setHeight(200);  
		purposeGrid.setShowAllRecords(true);  
		purposeGrid.setSelectionType(SelectionStyle.SIMPLE);  
		purposeGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);  
		
        ListGridField nameField = new ListGridField("purposeName", "Желаемая должность");
        purposeGrid.setFields(nameField);
        
        Record[] purposes = new Record[12];
        
        //findButton.setIcon("silk/printer.png");          
       
        
        //purposeGrid.setData(data);
        
        filters.add(purposeGrid);        
        
        FlexTable results = new FlexTable();
		results.setHeight("500px");
		results.setWidth("800px");

        //results.add(findButton);
       	
        
                
        
    	final ArrayList<String> opt = new ArrayList<String>();
    	opt.add("Посредственно");
    	opt.add("Хорошо");
    	opt.add("Отлично");
    	
        final IButton findButton = new IButton("Искать");  


        mainService.getProgramLanguages(new AsyncCallback<Collection<ProgramLanguage>>() {

        	@Override
        	public void onSuccess(Collection<ProgramLanguage> result) {
        	HashMap<String, String> map = new HashMap<String, String>();
        	for(ProgramLanguage pl : result){
        	map.put(String.valueOf(pl.getId()), pl.getTitle());
        	}
        	MultiSelect ms = new MultiSelect(map, opt);
        	filters.add(ms);
        	ms.draw();
        	ms.setWidth(200);
        	ms.setHeight(200);
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
        	MultiSelect ms1 = new MultiSelect(map1, opt);
        	filters.add(ms1);
        	ms1.draw();
        	ms1.setWidth(200);
        	ms1.setHeight(150);
            findButton.draw();

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
        
        setWidget(rootPanel);


	}
	
}
