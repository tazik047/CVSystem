package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import ua.nure.pi.entity.ProgramLanguage;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class DivideProgramLanguageSimplePanel extends LoadingSimplePanel {
	
	MainServiceAsync mainService;
    
    private MultiComboBox newPLs;
    private ListBox plComboBox;   
    
    public Label label;

    VerticalPanel left;
	VerticalPanel right;
	
	public int generalWidth = 300;
	
	Collection<ProgramLanguage> oldProgramLanguages;

	boolean first;
	
	public DivideProgramLanguageSimplePanel(MainServiceAsync main) {
		mainService = main;
		first = true;
		clear();
		onModuleLoad();
	}
	
	public void onModuleLoad() {	    
		
		VerticalPanel rootPanel = new VerticalPanel();
		HorizontalPanel main = new HorizontalPanel();
		left = new VerticalPanel();
		right = new VerticalPanel();
	    rootPanel.setWidth("100%");
	    
	    
        label = new Label();  
        label.setHeight(60);  
        label.setAlign(Alignment.LEFT);  
        label.setValign(VerticalAlignment.CENTER);  
        label.setWrap(false);  
        label.setContents("Разбиение технологий");  
        label.setStyleName("title");
        label.draw();  
        
	    //mainForm.setStyleName("mainForm");
	    
	    rootPanel.add(label);	
	    
	    rootPanel.add(main);
	    main.add(left);
	    main.add(right);
	    
	    left.add(new Label("Выберите технологию:"));
	    right.add(new Label("Разбить на в:"));

	    //rootPanel.add(mainForm);   	  
       
	    HorizontalPanel buttonPanel = new HorizontalPanel();
	    buttonPanel.setWidth("60%");
	    
	    rootPanel.add(buttonPanel);
	    
	    rootPanel.setCellHorizontalAlignment(buttonPanel, HasHorizontalAlignment.ALIGN_CENTER);
	    
	    Button commit = new Button("Сохранить");
        commit.addClickHandler(new ClickHandler() {
        	
        	@Override
            public void onClick(ClickEvent event) {
            	ProgramLanguage oldPl = getOldPls();
            	Collection<ProgramLanguage> ps = getNewPl();
            	mainService.divideProgramLanguages(oldPl, ps, new AsyncCallback<Void>() {
					
            		@Override
					public void onSuccess(Void result) {
						start();
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}
				});
        	}

        });
	    buttonPanel.add(commit);
	    
	    Button reset = new Button("Обновить");
	    
	    reset.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				start();
			}
		});
	    
	    buttonPanel.add(reset);
	    
	    buttonPanel.setCellHorizontalAlignment(commit, HasHorizontalAlignment.ALIGN_LEFT);
	    buttonPanel.setCellHorizontalAlignment(reset, HasHorizontalAlignment.ALIGN_RIGHT);
	    
	    //rootPanel.setSpacing(15);

	    add(rootPanel);
	    
	    start();
	    
	}
	
	private ProgramLanguage getOldPls() {
		if(plComboBox.getSelectedIndex()!=-1){
			String pl = plComboBox.getItemText(plComboBox.getSelectedIndex());
			for(ProgramLanguage p : oldProgramLanguages){
				if(p.getTitle().equals(pl)){
					return p;
				}
			}
		}
		return null;
	}
	
	private Collection<ProgramLanguage> getNewPl(){
		Collection<ProgramLanguage> res = new ArrayList<ProgramLanguage>(); 
		for(String pl : newPLs.getValues()){
			boolean n = true;
			for(ProgramLanguage p : oldProgramLanguages){
				if(p.getTitle().equals(pl)){
					res.add(new ProgramLanguage(pl, p.getId()));
					n=false;
					break;
				}
			}
			if(n) {
				ProgramLanguage pnew = new ProgramLanguage();
				pnew.setTitle(pl);
				res.add(pnew);
			}					
		}
		return res;
	}

	private void start() {
		final LoadingSimplePanel thisPanel = this;
		mainService.getProgramLanguages(new AsyncCallback<Collection<ProgramLanguage>>() {
			
			@Override
			public void onSuccess(Collection<ProgramLanguage> result) {
				oldProgramLanguages = result;
				Collection<String> pls1 = new ArrayList<String>();
					
				if(!first){
					right.remove(newPLs);
					left.remove(plComboBox);
				}
				plComboBox = new ListBox();	
				for(ProgramLanguage i : oldProgramLanguages){
					pls1.add(i.getTitle());
					plComboBox.addItem(i.getTitle());
				}
				
				newPLs = new MultiComboBox(pls1, "");			
				plComboBox.setWidth("300px");
				right.add(newPLs);
				left.add(plComboBox);
				if(first){
					fireLoadEvent(thisPanel);
					isReady = true;
					first = false;
				}
				newPLs.draw();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				setWidget(new Label(caught.getMessage()));
				
			}
		});
	}
}
