package ua.nure.pi.client;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
		
		VerticalPanel filters = new VerticalPanel();
        
		final ListGrid purposeGrid = new ListGrid();  
		purposeGrid.setWidth(180);  
		purposeGrid.setHeight(200);  
		purposeGrid.setShowAllRecords(true);  
		purposeGrid.setSelectionType(SelectionStyle.SIMPLE);  
		purposeGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);  
		
        ListGridField nameField = new ListGridField("purposeName", "Желаемая должность");
        purposeGrid.setFields(nameField);
        
        Record[] purposes = new Record[12];
        
        final IButton findButton = new IButton("Искать");  
        
        findButton.setAlign(Alignment.RIGHT);
        findButton.setWidth(120);  
        findButton.draw();
        //findButton.setIcon("silk/printer.png");          
       
        
        //purposeGrid.setData(data);
        
        filters.add(purposeGrid);
        filters.add(findButton);
        
        
        FlexTable results = new FlexTable();
		results.setHeight("500px");
		results.setWidth("800px");

        //results.add(findButton);
       	
        
        
        rootPanel.add(filters);
        rootPanel.add(results);
        
        setWidget(rootPanel);
	}
	
}
