package ua.nure.pi.client;


/* 
 * Smart GWT (GWT for SmartClient) 
 * Copyright 2008 and beyond, Isomorphic Software, Inc. 
 * 
 * Smart GWT is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License version 3 
 * as published by the Free Software Foundation.  Smart GWT is also 
 * available under typical commercial license terms - see 
 * http://smartclient.com/license 
 * 
 * This software is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * Lesser General Public License for more details. 
 */  
  
import com.smartgwt.client.data.DataSource;  
import com.smartgwt.client.widgets.Canvas;  
import com.smartgwt.client.widgets.IButton;  
import com.smartgwt.client.widgets.Label;  
import com.smartgwt.client.widgets.events.ClickEvent;  
import com.smartgwt.client.widgets.events.ClickHandler;  
import com.smartgwt.client.widgets.form.DynamicForm;  
import com.smartgwt.client.widgets.grid.ListGrid;  
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;  
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;  
import com.smartgwt.client.widgets.layout.VLayout;  
import com.google.gwt.core.client.EntryPoint;  
import com.google.gwt.user.client.ui.SimplePanel;
  
public class ExprienceSimplePanel extends SimplePanel {  
  
    public void onModuleLoad() {  
  
        VLayout layout = new VLayout(15);  
  
        Label label = new Label();  
        label.setHeight(10);  
        label.setWidth100();  
        label.setContents("Showing items in Category 'Rollfix Glue");  
        layout.addMember(label);  
  
  
        final DynamicForm form = new DynamicForm();  
        form.setIsGroup(true);  
        form.setGroupTitle("Update");  
        form.setNumCols(4);  
  
  
        final ListGrid listGrid = new ListGrid();  
        listGrid.setWidth100();  
        listGrid.setHeight(200);  
        listGrid.setAutoFetchData(true);  
        listGrid.addRecordClickHandler(new RecordClickHandler() {  
            public void onRecordClick(RecordClickEvent event) {  
                form.reset();  
                form.editSelectedData(listGrid);  
            }  
        });  
  
        layout.addMember(listGrid);  
        layout.addMember(form);  
  
        IButton button = new IButton("Save");  
        button.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
                form.saveData();                  
            }  
        });  
        layout.addMember(button);  
  
        layout.draw();  
    }  
  
}  