package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Group;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SimplePanel;
import com.smartgwt.client.util.SC;  
import com.smartgwt.client.util.ValueCallback;
import com.smartgwt.client.widgets.Canvas;  
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.grid.ListGridField;  
import com.smartgwt.client.widgets.layout.HStack;  
import com.smartgwt.client.widgets.menu.Menu;  
import com.smartgwt.client.widgets.menu.MenuButton;  
import com.smartgwt.client.widgets.menu.MenuItem;  
import com.smartgwt.client.widgets.menu.events.ItemClickEvent;  
import com.smartgwt.client.widgets.menu.events.ItemClickHandler;  

public class AddFacultiesCanvas extends LoadingSimplePanel {

	MainServiceAsync adminPanelService;
	Collection<Faculty> faculties;
	Collection<MenuItem> items;
	
	public AddFacultiesCanvas(MainServiceAsync admservice) {
		isReady = true;
		items = new ArrayList<MenuItem>();
		this.adminPanelService = admservice;
        final Menu menu = new Menu();
        menu.setShowShadow(true);
        menu.setShadowDepth(10);

        final ListGridField closeField = new ListGridField();
        closeField.setName("canDismiss");
        closeField.setShowValueIconOnly(true);
Map<String, String> valueIcons = new HashMap<String, String>();
valueIcons.put("true", "/img/close.png");
        closeField.setValueIcons(valueIcons);
        closeField.setValueIconSize(16);
        closeField.setWidth(16);

        menu.setFields(Menu.TITLE_FIELD, closeField);
        
        getFacultiesInMenu(menu, closeField);

        menu.addItemClickHandler(new ItemClickHandler() {
                public void onItemClick(ItemClickEvent event) {
                        MenuItem clicked = event.getItem();
                        if ((event.getColNum() == 1) && clicked.getAttributeAsBoolean("canDismiss")) {
            menu.removeItem(clicked);
                        } else if(clicked.getAttributeAsBoolean("addButton")){
                        	final Dialog dialogProperties = new Dialog();  
                            dialogProperties.setWidth(300);  
                          
                            SC.askforValue("Добавление факультета", "Введите название факультета", "", new ValueCallback() {  
                                @Override  
                                public void execute(String value) {  
                                    if (value != null && !value.isEmpty()) {  
                                        Faculty faculty = new Faculty();  
                                        faculty.setTitle(value);
                                        /*adminPanelService.setFaculties(faculty, new AsyncCallback<Void>() {
											
											@Override
											public void onSuccess(Void result) {
												SC.say("Факультет успешно добавлен");
												getFacultiesInMenu(menu, closeField);
											}
											
											@Override
											public void onFailure(Throwable caught) {
												SC.say(caught.getMessage());
												
											}
										});*/
                                    } 
                                }  
                            }, dialogProperties);  
                        }
                        else {
                                SC.say("You Selected '" + clicked.getTitle() + "'.");
                        }
                }
        });

        MenuButton menuButton = new MenuButton("Show Menu", menu);

        HStack layout = new HStack();
        layout.setWidth100();
        layout.setMembers(menuButton);
        this.clear();
        this.add(layout);
	}

	private void getFacultiesInMenu(final Menu menu,
			final ListGridField closeField) {
		adminPanelService.getFaculties(new AsyncCallback<Collection<Faculty>>() {
			
			@Override
			public void onSuccess(Collection<Faculty> result) {
				faculties = new ArrayList<Faculty>(result);
				cleanMenu(menu);
				for(Faculty f: result){
					MenuItem item = new MenuItem(f.getTitle());
					Menu subMenu = new Menu();
					MenuItem addNew = new MenuItem("Добавить группу");
					addNew.setAttribute("addButton", true);
					addNew.setAttribute("selectedObject", new Group());
					if(f.getGroups().size()!=0){
						for(Group g : f.getGroups()){
							MenuItem subItem = new MenuItem(g.getTitle());
							subItem.setAttribute("canDismass", true);
							subItem.setAttribute("selectedObject", g);
							subMenu.addItem(subItem);
						}
					}
					else{
						item.setAttribute("canDismiss", true);
					}
					subMenu.addItem(addNew);
					subMenu.addItemClickHandler(new ItemClickHandler() {
		                public void onItemClick(ItemClickEvent event) {
		                        MenuItem clicked = event.getItem();
		                        if ((event.getColNum() == 1) && clicked.getAttributeAsBoolean("canDismiss")) {
		                        	menu.removeItem(clicked);
		                        } else if(clicked.getAttributeAsBoolean("addButton")){
		                        	final Dialog dialogProperties = new Dialog();  
		                            dialogProperties.setWidth(300);  
		                          
		                            SC.askforValue("Добавление группы", "Введите название группы", "", new ValueCallback() {  
		                                @Override  
		                                public void execute(String value) {  
		                                    if (value != null && !value.isEmpty()) {  
		                                        Group group = new Group();  
		                                        group.setTitle(value);
		                                        /*adminPanelService.setGroup(group, new AsyncCallback<Void>() {
													
													@Override
													public void onSuccess(Void result) {
														SC.say("Группа успешно добавлен");
														getFacultiesInMenu(menu, closeField);
													}
													
													@Override
													public void onFailure(Throwable caught) {
														SC.say(caught.getMessage());
														
													}
												});*/
		                                    } 
		                                }  
		                            }, dialogProperties);  
		                        }
		                        else {
		                                SC.say("You Selected '" + clicked.getTitle() + "'.");
		                        }
		                }
					});
					item.setAttribute("selectedObject",f);
					item.setSubmenu(subMenu);
					menu.addItem(item);
				}
				MenuItem addNew = new MenuItem("Добавить факультет");
				addNew.setAttribute("addButton", true);
				addNew.setAttribute("selectedObject", new Faculty());
				menu.addItem(addNew);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				SC.say("Ошибка при загрузки факультетов.");
			}
		});
	}
	
	private void cleanMenu(Menu menu){
		for(MenuItem item: items)
			menu.removeItem(item);
	}
}
