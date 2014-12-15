package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Group;
import ua.nure.pi.entity.Student;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.TabTitleEditEvent;
import com.smartgwt.client.util.SC;  
import com.smartgwt.client.util.ValueCallback;
import com.smartgwt.client.widgets.Canvas;  
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;  
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HStack;  
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;  
import com.smartgwt.client.widgets.menu.MenuButton;  
import com.smartgwt.client.widgets.menu.MenuItem;  
import com.smartgwt.client.widgets.menu.events.ItemClickEvent;  
import com.smartgwt.client.widgets.menu.events.ItemClickHandler;  
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabTitleChangedEvent;
import com.smartgwt.client.widgets.tab.events.TabTitleChangedHandler;

public class AddFacultiesCanvas extends LoadingSimplePanel {

	MainServiceAsync adminPanelService;
	Collection<Faculty> faculties;
	Collection<MenuItem> items;
	
	public AddFacultiesCanvas(MainServiceAsync admservice) {
		isReady = true;
		adminPanelService = admservice;
		final TabSet tabSet = new TabSet();  
        tabSet.setTabBarPosition(Side.TOP);  
        tabSet.setTabBarAlign(Side.LEFT); 
        tabSet.setWidth("60%");
        tabSet.setHeight("60%");
        tabSet.setCanEditTabTitles(true);  
        tabSet.setTitleEditEvent(TabTitleEditEvent.DOUBLECLICK);  
        tabSet.setTitleEditorTopOffset(2);
        admservice.getFaculties(new AsyncCallback<Collection<Faculty>>() {
			
			@Override
			public void onSuccess(Collection<Faculty> result) {
				faculties = result;
				for(Faculty f : result)
				{
					Tab t = new Tab(f.getTitle());
					VLayout vp = new VLayout();
					final ListGrid grid= createGreate(f);
					vp.addMember(grid);
					vp.addMember(new IButton("Добавить группу", new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							ListGridRecord rec = new ListGridRecord(); 
					        rec.setAttribute("idField", 0);
					        rec.setAttribute("title", "dfsdf");
					        rec.setAttribute("edit", "");
					        rec.setAttribute("del", "");
							grid.addData(rec);
						}
					}));
					vp.setMembersMargin(5); 
					t.setPane(vp);
					tabSet.addTab(t);
					grid.markForRedraw();
				}
				tabSet.markForRedraw();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				clear();
				add(new Label(caught.getLocalizedMessage()));
			}
		});
        
  
        tabSet.addTabTitleChangedHandler(new TabTitleChangedHandler() {  
            @Override  
            public void onTabTitleChanged(TabTitleChangedEvent event) {  
                Tab tab = event.getTab();  
                Window.alert(tab.getTitle());
            }  
        });  
        clear();
        add(tabSet);
	}
	
	private ListGrid createGreate(final Faculty f)
	{
		final ListGrid CVGrid = new ListGrid(){
        	@Override  
            protected Canvas createRecordComponent(final ListGridRecord record, final Integer colNum) {  
        		String fieldName = this.getFieldName(colNum);
        		if (fieldName.equals("edit")) {  
                    IButton button = new IButton();    
                    button.setHeight(18);  
                    button.setWidth(65); 
                    button.setTitle("Редактировать");  
                    button.addClickHandler(new ClickHandler() {  
                        public void onClick(ClickEvent event) {  
                            int id  = Integer.parseInt(record.getAttribute("idField"));
                            for(Group g : f.getGroups())
                            {
                            	if(g.getGroupId()==id)
                            	Window.alert(g.getTitle());
                            }
                        }  
                    }); 
                    return button;  
                } 
        		else if (fieldName.equals("del")) {  
                    IButton button1 = new IButton();   
                    button1.setHeight(18);  
                    button1.setWidth(65); 
                    button1.setTitle("Удалить");  
                    button1.addClickHandler(new ClickHandler() {  
                        public void onClick(ClickEvent event) {  
                            int id  = Integer.parseInt(record.getAttribute("idField"));
                            for(Group g : f.getGroups())
                            {
                            	if(g.getGroupId()==id)
                            	Window.alert(g.getTitle());
                            }
                        }  
                    }); 
                    return button1;  
                }
                else {  
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
        ListGridField SurnameField = new ListGridField("title", "Группа");  
        ListGridField edit = new ListGridField("edit", "Редактировать");
        ListGridField del = new ListGridField("del", "Удалить");
        edit.setAlign(Alignment.CENTER);  
        CVGrid.setFields(idField, SurnameField, edit, del);  
        CVGrid.setCanResizeFields(true);
        ListGridRecord[] recs = new ListGridRecord[f.getGroups().size()];
        int ind = 0;
        for(Group i : f.getGroups()){
	    ListGridRecord rec = new ListGridRecord(); 
        rec.setAttribute("idField", i.getGroupId());
        rec.setAttribute("title", i.getTitle());
        rec.setAttribute("edit", "");
        rec.setAttribute("del", "");
        recs[ind++]=rec;
        }
        CVGrid.setRecords(recs);
        CVGrid.markForRedraw(); 
        return CVGrid;
	}
}
