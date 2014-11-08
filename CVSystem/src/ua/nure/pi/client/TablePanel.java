package ua.nure.pi.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.types.Alignment;  
import com.smartgwt.client.types.ListGridFieldType;  
import com.smartgwt.client.util.SC;  
import com.smartgwt.client.widgets.Canvas;  
import com.smartgwt.client.widgets.IButton;  
import com.smartgwt.client.widgets.ImgButton;  
import com.smartgwt.client.widgets.events.ClickEvent;  
import com.smartgwt.client.widgets.events.ClickHandler;  
import com.smartgwt.client.widgets.grid.ListGrid;  
import com.smartgwt.client.widgets.grid.ListGridField;  
import com.smartgwt.client.widgets.grid.ListGridRecord;  
import com.smartgwt.client.widgets.layout.HLayout;

import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.mssql.MSSqlStudentDAO;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Student;

public class TablePanel extends LoadingSimplePanel{
	private Collection<Student> students;
	
	public TablePanel(Collection<Student> students)
	{
		this.students = students;
		onModuleLoad();
	}
	
	
	 public TablePanel(MainServiceAsync mainService) {
		final TablePanel tab = this;
		mainService.getStudents(new AsyncCallback<Collection<Student>>() {
			
			@Override
			public void onSuccess(Collection<Student> result) {
				students=result;
				onModuleLoad();
				fireLoadEvent(tab);
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
	                            for(Student i :  students){
	                            	if(i.getStudentsId()==id){
	                            		cv.clear();
	                            		cv.add(new PrintSimplePanel(i));
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
	        ListGridField SurnameField = new ListGridField("surname", "ФИО");  
	        ListGridField show = new ListGridField("show", "Показать резюме");  
	        show.setAlign(Alignment.CENTER);  
	        CVGrid.setFields(idField, SurnameField, show);  
	        CVGrid.setCanResizeFields(true);
	        ListGridRecord[] recs = new ListGridRecord[students.size()];
	        int ind = 0;
	        for(Student i : students){
		    ListGridRecord rec = new ListGridRecord(); 
	        rec.setAttribute("idField", i.getStudentsId());
	        rec.setAttribute("surname", i.getSurname()+" " + i.getFirstname()+" "+i.getPatronymic());
	        rec.setAttribute("show", "");
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
	
