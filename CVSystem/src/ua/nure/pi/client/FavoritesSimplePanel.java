package ua.nure.pi.client;

import java.util.Collection;

import ua.nure.pi.entity.Favorite;
import ua.nure.pi.entity.Student;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class FavoritesSimplePanel extends LoadingSimplePanel {
	
	Collection<Favorite> favorites;
	
	public FavoritesSimplePanel(MainServiceAsync mainService) {
		mainService.getFavorite(new AsyncCallback<Collection<Favorite>>() {
			
			@Override
			public void onSuccess(Collection<Favorite> result) {
				favorites = result;
				onModuleLoad();
				fireLoadEvent(FavoritesSimplePanel.this);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getLocalizedMessage());
			}
		});
	}
	
	public void onModuleLoad() {
		clear();
	  	VerticalPanel root = new VerticalPanel();
	  	final SimplePanel cv = new SimplePanel();
        final ListGrid CVGrid = new ListGrid(){
        	@Override  
            protected Canvas createRecordComponent(final ListGridRecord record, final Integer colNum) {  
        		String fieldName = this.getFieldName(colNum);
        		if (fieldName.equals("show")) {  
                    IButton button = new IButton();  
                    button.setHeight(18);  
                    button.setWidth(65);                      
                    button.setTitle("Просмотреть");  
                    button.addClickHandler(new ClickHandler() {  
                        public void onClick(ClickEvent event) {  
                            int id  = Integer.parseInt(record.getAttribute("idField"));
                            for(Favorite i :  favorites){
                            	if(i.getStudent().getStudentsId()==id){
                            		cv.clear();
                            		cv.add(new PrintSimplePanel(i.getStudent()));
                            	}
                            }
                        }  
                    });  
                    return button;  
                } else {  
                    return null;  
        	}
        };  
        
        };
        CVGrid.setShowRecordComponents(true);          
        CVGrid.setShowRecordComponentsByCell(true);  
        CVGrid.setCanRemoveRecords(false);  
  
        CVGrid.setWidth(550);  
        CVGrid.setHeight(224);  
        CVGrid.setShowAllRecords(true);  
        ListGridField idField = new ListGridField("idField", "ID");  
        idField.setHidden(true);
        ListGridField SurnameField = new ListGridField("surname", "ФИО");  
        ListGridField show = new ListGridField("show", "Показать резюме");
        ListGridField group = new ListGridField("date", "Дата сохранения");
        show.setAlign(Alignment.CENTER);  
        CVGrid.setFields(idField, SurnameField, show, group);  
        CVGrid.setCanResizeFields(true);
        ListGridRecord[] recs = new ListGridRecord[favorites.size()];
        int ind = 0;
        for(Favorite i : favorites){
	    ListGridRecord rec = new ListGridRecord(); 
        rec.setAttribute("idField", i.getStudent().getStudentsId());
        rec.setAttribute("surname", i.getStudent().getSurname()+" " + i.getStudent().getFirstname());
        rec.setAttribute("show", "");
        rec.setAttribute("date", i.getDate());
        recs[ind++]=rec;
        }
        CVGrid.setRecords(recs);
        CVGrid.markForRedraw(); 
        root.add(CVGrid);
        root.add(cv);
       add(root);
       setWidth("100%");
       root.setCellHorizontalAlignment(CVGrid,HasHorizontalAlignment.ALIGN_CENTER);
       root.setCellHorizontalAlignment(cv,HasHorizontalAlignment.ALIGN_CENTER);
       root.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
       root.setWidth("100%");
        
 }

}
