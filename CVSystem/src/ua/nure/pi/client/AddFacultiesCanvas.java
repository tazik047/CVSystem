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
import com.smartgwt.client.util.BooleanCallback;
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
import com.smartgwt.client.widgets.layout.HLayout;
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
		adminPanelService = admservice;
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(5);
		final TabSet tabSet = new TabSet();  
        tabSet.setTabBarPosition(Side.TOP);  
        tabSet.setTabBarAlign(Side.LEFT); 
        tabSet.setWidth("60%");
        tabSet.setHeight("345px");
        tabSet.setCanEditTabTitles(true);  
        tabSet.setTitleEditEvent(TabTitleEditEvent.DOUBLECLICK);  
        tabSet.setTitleEditorTopOffset(2);
        final LoadingSimplePanel thisPanel = this;
        admservice.getFaculties(new AsyncCallback<Collection<Faculty>>() {
			
			@Override
			public void onSuccess(Collection<Faculty> result) {
				faculties = result;
				for(Faculty f : result)
				{
					addNewTab(tabSet, f);
				}
				tabSet.markForRedraw();
				isReady = true;
				fireLoadEvent(thisPanel);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				clear();
				add(new Label(caught.getLocalizedMessage()));
				isReady = true;
				fireLoadEvent(thisPanel);
			}
		});
        
  
        tabSet.addTabTitleChangedHandler(new TabTitleChangedHandler() {  
            @Override  
            public void onTabTitleChanged(final TabTitleChangedEvent event) {  
                final Tab tab = event.getTab();
                final Faculty f = new Faculty();
                f.setTitle(event.getNewTitle());
                f.setFacultiesId(tab.getAttributeAsLong("id"));
                adminPanelService.updateFaculty(f, new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						SC.say("Название факультета изменено");
						for(Faculty faculty : faculties){
							if(f.getFacultiesId()==faculty.getFacultiesId()){
								faculty.setTitle(f.getTitle());
								break;
							}
						}
					}
					
					@Override
					public void onFailure(Throwable caught) {
						SC.say("Ошибка!", caught.getLocalizedMessage());
						tab.setTitle(event.getOldTitle());
					}
				});
            }  
        });  
        clear();
        vp.add(tabSet);
        Button add = new Button("Добавить факультет");
        add.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {
			
			@Override
			public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
				SC.askforValue("Новый факультет", "Введите название нового факультета", new ValueCallback() {
					
					@Override
					public void execute(String value) {
						if(value==null) return;
						final Faculty f = new Faculty();
						f.setTitle(value);
						adminPanelService.addFaculty(f, new AsyncCallback<Long>() {
							
							@Override
							public void onSuccess(Long result) {
								f.setFacultiesId(result);
								f.setGroups(new ArrayList<Group>());
								addNewTab(tabSet, f);
							}
							
							@Override
							public void onFailure(Throwable caught) {
								SC.say("Ошибка",caught.getLocalizedMessage());
							}
						});
					}
				});
			}
		});
        vp.add(add);
        add(vp);
	}
	
	private void addNewTab(final TabSet tabSet,final Faculty f) {
		final Tab t = new Tab(f.getTitle());
		t.setAttribute("id", f.getFacultiesId());
		VLayout vp = new VLayout();
		final ListGrid grid= createGrid(f);
		vp.addMember(grid);
		HLayout hp = new HLayout();
		
		hp.addMember(new IButton("Добавить группу", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			
				SC.askforValue("Новая группа", "Введите название новой группы", new ValueCallback() {
				
				@Override
				public void execute(String value) {
					if(value==null) return;
					final Group g = new Group();
					g.setTitle(value);
					g.setFacultiesId(f.getFacultiesId());
					adminPanelService.addGroup(g, new AsyncCallback<Long>() {
						
						@Override
						public void onSuccess(Long result) {
							ListGridRecord rec = new ListGridRecord(); 
					        rec.setAttribute("idField", 0);
					        rec.setAttribute("title", "dfsdf");
					        rec.setAttribute("edit", "");
					        rec.setAttribute("del", "");
							grid.addData(rec);
							/*
							f.setFacultiesId(result);
							f.setGroups(new ArrayList<Group>());
							addNewTab(tabSet, f);*/
						}
						
						@Override
						public void onFailure(Throwable caught) {
							SC.say("Ошибка",caught.getLocalizedMessage());
						}
					});
				}
				});
				
				
			}
		}));
		
		IButton delBt = new IButton("Удалить факультет", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				SC.ask("Вы уверены?", "Вы точно хотите удалить факультет " + t.getTitle() + "?", 
						new BooleanCallback() {
					
					@Override
					public void execute(Boolean value) {
						if(value){							
							adminPanelService.deleteFaculty(f, new AsyncCallback<Void>() {
								
								@Override
								public void onSuccess(Void result) {
									SC.say("Факультет " + f.getTitle() + "был удален");
									tabSet.removeTab(t);
								}
								
								@Override
								public void onFailure(Throwable caught) {
									SC.say(caught.getLocalizedMessage());
								}
							});
						}
					}
				});
			}
		});
		delBt.setSize("150px", delBt.getHeightAsString());
		
		hp.addMember(delBt);
		
		vp.addMember(hp);
		vp.setMembersMargin(5);
		hp.setMembersMargin(10);
		t.setPane(vp);
		tabSet.addTab(t);
		grid.markForRedraw();
	}
	
	private ListGrid createGrid(final Faculty f)
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
                            int idF  = Integer.parseInt(record.getAttribute("idFaculty"));
                            final Group g = new Group();
                            g.setFacultiesId(idF);
                            g.setGroupId(id);
                            g.setTitle(record.getAttribute("title"));
                            Dialog d = new Dialog();
                            d.setWidth(300);
                            SC.askforValue("Редактирование", "Введите новое название группы", g.getTitle(), 
                            		new ValueCallback() {
								
								@Override
								public void execute(String value) {
									if(value == null) return;
									g.setTitle(value);
									adminPanelService.updateGroup(g, new AsyncCallback<Void>() {
										
										@Override
										public void onSuccess(Void result) {
											record.setAttribute("idField", g.getTitle());
										}
										
										@Override
										public void onFailure(Throwable caught) {
											SC.say("Ошибка", caught.getLocalizedMessage());
										}
									});
								}
							}, d);
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
        CVGrid.setHeight(275);  
        CVGrid.setShowAllRecords(true);    
        ListGridField idField = new ListGridField("idField", "ID");  
        ListGridField SurnameField = new ListGridField("title", "Группа");  
        ListGridField edit = new ListGridField("edit", "Редактировать");
        ListGridField del = new ListGridField("del", "Удалить");
        ListGridField idFaculty = new ListGridField("idFaculty", "idFaculty");
        edit.setAlign(Alignment.CENTER);  
        CVGrid.setFields(idField, SurnameField, edit, del,idFaculty); 
        CVGrid.hideField("idFaculty");
        CVGrid.setCanResizeFields(true);
        ListGridRecord[] recs = new ListGridRecord[f.getGroups().size()];
        int ind = 0;
        for(Group i : f.getGroups()){
	    ListGridRecord rec = new ListGridRecord(); 
        rec.setAttribute("idField", i.getGroupId());
        rec.setAttribute("title", i.getTitle());
        rec.setAttribute("edit", "");
        rec.setAttribute("del", "");
        rec.setAttribute("idFaculty", i.getFacultiesId());
        recs[ind++]=rec;
        }
        CVGrid.setRecords(recs);
        CVGrid.markForRedraw(); 
        return CVGrid;
	}
}
