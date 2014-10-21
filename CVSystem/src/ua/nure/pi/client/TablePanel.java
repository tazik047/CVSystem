package ua.nure.pi.client;

import com.google.gwt.user.client.ui.SimplePanel;
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
import ua.nure.pi.entity.Student;

public class TablePanel extends SimplePanel{
	private Collection<Student> students;
	public TablePanel(Collection<Student> students)
	{
		this.students = students;
	}
	
	
	 public void onModuleLoad() {  
		  	
	        final ListGrid CVGrid = new ListGrid(){
	        	protected Canvas getRollOverCanvas(final ListGridRecord record, Integer colNum) {  
	        		String fieldName = this.getFieldName(colNum);
	        		if (fieldName.equals("show")) {  
	                    IButton button = new IButton();  
	                    button.setHeight(18);  
	                    button.setWidth(65);                      
	                    button.setTitle("Info");  
	                    button.addClickHandler(new ClickHandler() {  
	                        public void onClick(ClickEvent event) {  
	                            SC.say(record.getAttribute("idField"));  
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
	        CVGrid.setCanRemoveRecords(true);  
	  
	        CVGrid.setWidth(550);  
	        CVGrid.setHeight(224);  
	        CVGrid.setShowAllRecords(true);  
	        ListGridField idField = new ListGridField("idField", "ID");  
	        ListGridField SurnameField = new ListGridField("surname", "ФИО");  
	        ListGridField show = new ListGridField("show", "Просмотреть");  
	        show.setAlign(Alignment.CENTER);  
	        CVGrid.setFields(idField, SurnameField, show);  
	        CVGrid.setCanResizeFields(true);  
	        for(Student i : students){
		    ListGridRecord rec = new ListGridRecord(); 
	        rec.setAttribute("idField", i.getStudentsId());
	        rec.setAttribute("surname", i.getSurname()+i.getFirstname()+i.getPatronymic());
	        rec.setAttribute("show", "Просмотреть");
	        }
	        CVGrid.draw(); 
	    
	       
	        
	 }
	 
}
	