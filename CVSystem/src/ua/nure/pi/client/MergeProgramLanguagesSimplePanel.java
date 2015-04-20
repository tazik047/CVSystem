package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import ua.nure.pi.entity.Company;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Purpose;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.SuggestOracle.Callback;
import com.google.gwt.user.client.ui.SuggestOracle.Request;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;

public class MergeProgramLanguagesSimplePanel extends LoadingSimplePanel {

	MainServiceAsync mainService;
	public TextItem nameOfCompany;
	public TextItem login;
	public PasswordItem password;
	public PasswordItem password2;
	public TextItem PhonetextBox;
	public TextItem Skypetextbox;
    public TextItem Emailtextbox;
    public TextItem nameOfReliable;
    public TextItem phoneOfReliable;
    
    private MultiSelect oldPLs;
    private SuggestBox plComboBox;   
    
    public Label label;

    VerticalPanel left;
	VerticalPanel right;

	//public DynamicForm mainForm;
	
	public int generalWidth = 300;
	
	Collection<ProgramLanguage> oldProgramLanguages;

	boolean first;
	
	public MergeProgramLanguagesSimplePanel(MainServiceAsync main) {
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
	    /*mainForm = new DynamicForm();
	    mainForm.setNumCols(1);
	    mainForm.setTitleSuffix("");
	    mainForm.setRequiredTitleSuffix("");
	    mainForm.setRequiredRightTitleSuffix("");
	    mainForm.setZIndex(10);*/
	    rootPanel.setWidth("100%");
	    
	    
        label = new Label();  
        label.setHeight(60);  
        label.setAlign(Alignment.LEFT);  
        label.setValign(VerticalAlignment.CENTER);  
        label.setWrap(false);  
        label.setContents("Объеденение технологий");  
        label.setStyleName("title");
        label.draw();  
        
	    //mainForm.setStyleName("mainForm");
	    
	    rootPanel.add(label);	
	    
	    rootPanel.add(main);
	    main.add(left);
	    main.add(right);
	    
	    left.add(new Label("Выберите технологии:"));
	    right.add(new Label("Объеденить в:"));

	    //rootPanel.add(mainForm);   	  
       
	    HorizontalPanel buttonPanel = new HorizontalPanel();
	    buttonPanel.setWidth("60%");
	    
	    rootPanel.add(buttonPanel);
	    
	    rootPanel.setCellHorizontalAlignment(buttonPanel, HasHorizontalAlignment.ALIGN_CENTER);
	    
	    Button commit = new Button("Сохранить");
        commit.addClickHandler(new ClickHandler() {
        	
        	@Override
            public void onClick(ClickEvent event) {
            	ProgramLanguage newPl = getNewPl();
            	Collection<ProgramLanguage> ps = getOldPls();
            	mainService.mergeProgramLanguages(ps, newPl, new AsyncCallback<Void>() {
					
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
	
	private Collection<ProgramLanguage> getOldPls() {
		ArrayList<ProgramLanguage> res = new ArrayList<ProgramLanguage>();
		HashMap<Integer, Collection<String>> temp = oldPLs.getValues();
		for (Entry<Integer, Collection<String>> entry : temp.entrySet()) {
            for (String s : entry.getValue()) {
            ProgramLanguage curr = new ProgramLanguage();
            curr.setId(Long.parseLong(s));
            res.add(curr);
            }
        }
		return res;
	}
	
	private ProgramLanguage getNewPl(){
		ProgramLanguage p = new ProgramLanguage(plComboBox.getValue());
		return p;
	}

	private void start() {
		final LoadingSimplePanel thisPanel = this;
		mainService.getProgramLanguages(new AsyncCallback<Collection<ProgramLanguage>>() {
			
			@Override
			public void onSuccess(Collection<ProgramLanguage> result) {
				oldProgramLanguages = result;
				HashMap<String, String> pls1 = new HashMap<String, String>();
				MultiWordSuggestOracle pls2 = new MultiWordSuggestOracle();			
				if(!first){
					left.remove(oldPLs);
					right.remove(plComboBox);
				}
				for(ProgramLanguage i : oldProgramLanguages){
					pls1.put(String.valueOf(i.getId()), i.getTitle());
					pls2.add(i.getTitle());
				}
				
				oldPLs = new MultiSelect(pls1);
				plComboBox = new SuggestBox(pls2);
				left.add(oldPLs);
				right.add(plComboBox);
				if(first){
					fireLoadEvent(thisPanel);
					isReady = true;
					first = false;
				}
				oldPLs.draw();
				oldPLs.setWidth(300);
				oldPLs.setHeight(500);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				setWidget(new Label(caught.getMessage()));
				
			}
		});
	}
}
