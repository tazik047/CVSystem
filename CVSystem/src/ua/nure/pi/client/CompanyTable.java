package ua.nure.pi.client;

import java.util.Collection;

import ua.nure.pi.entity.Company;
import ua.nure.pi.entity.Student;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.SimplePanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;



public class CompanyTable extends SimplePanel{
	
	MainServiceAsync mainService;
	
	public CompanyTable(Collection<Company> companies, MainServiceAsync main, boolean needActivate){
		mainService = main;
		VLayout root = new VLayout();
		root.addChild(new CompanyListGrid(companies, needActivate));
		setWidth("100%");
		/*root.setCellHorizontalAlignment(CVGrid,HasHorizontalAlignment.ALIGN_CENTER);
	    root.setCellHorizontalAlignment(cv,HasHorizontalAlignment.ALIGN_CENTER);
	    root.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);*/
	    root.setWidth("100%");
		setWidget(root);
	}

	private class CompanyListGrid extends ListGrid {		
		private Collection<Company> companies;
		
		
		@Override  
        protected String getBaseStyle(ListGridRecord record, int rowNum, int colNum) { 
			if(record.getAttribute("activate").equals("Активировано")) {  
                return "activated";
            } else {  
                return super.getBaseStyle(record, rowNum, colNum);  
            }  
        }
		
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
	                    for(Company i :  companies){
	                    	if(i.getId()==id){
	                    		Window.alert(i.getName());
	                    	}
	                    }
	                }  
	            });  
	            return button;  
	        } else if (fieldName.equals("activate") && !record.getAttribute("activate").equals("Активировано")) {  
	            final IButton button = new IButton();  
	            button.setHeight(18);  
	            button.setWidth(80);                      
	            button.setTitle("Активировать");  
	            final Canvas temp = this;
	            button.addClickHandler(new ClickHandler() {  
	                public void onClick(ClickEvent event) {  
	                    int id  = Integer.parseInt(record.getAttribute("idField"));
	                    for(Company i :  companies){
	                    	if(i.getId()==id){
	                    		button.disable();
	                    		mainService.activateCompany(i, new AsyncCallback<Void>() {
									
									@Override
									public void onSuccess(Void result) {
										Window.alert("ОК");
			                    		button.markForDestroy();
			                    		record.setAttribute("activate", "Активировано");
			                    		temp.markForRedraw();
									}
									
									@Override
									public void onFailure(Throwable caught) {
										Window.alert(caught.getLocalizedMessage());
										button.enable();
									}
								});
	                    	}
	                    }
	                }  
	            });  
	            return button;  
	        }
			else {  
	            return null;  
	        }
		}
		
		public CompanyListGrid(Collection<Company> companies, boolean activate){
			this.companies = companies;
			setShowRecordComponents(true);          
	        setShowRecordComponentsByCell(true);  
	        setCanRemoveRecords(false);  
	  
	        setWidth(550);  
	        setHeight(224);  
	        setShowAllRecords(true);  
	        ListGridField idField = new ListGridField("idField", "ID");  
	        ListGridField SurnameField = new ListGridField("title", "Название");  
	        ListGridField show = new ListGridField("show", "Показать подробную информацию");
	        show.setAlign(Alignment.CENTER);
	        if(activate){
	        	ListGridField active = new ListGridField("activate", "Активировать компанию");
	        	active.setAlign(Alignment.CENTER);
	        	setFields(idField, SurnameField, show, active);
	        }
	        else
	        	setFields(idField, SurnameField, show);
	        setCanResizeFields(true);
	        ListGridRecord[] recs = new ListGridRecord[companies.size()];
	        int ind = 0;
	        for(Company i : companies){
		    ListGridRecord rec = new ListGridRecord(); 
	        rec.setAttribute("idField", i.getId());
	        rec.setAttribute("title", i.getName());
	        rec.setAttribute("show", "");
	        if(activate)
		        rec.setAttribute("activate", "");
	        recs[ind++]=rec;
	        }
	        setRecords(recs);
	        markForRedraw(); 
		}
	}
}
