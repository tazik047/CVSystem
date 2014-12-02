package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;







import java.util.List;

import ua.nure.pi.dao.jdbc.JDBCFacultyGroupDAO;
import ua.nure.pi.dao.jdbc.JDBCProgramLanguageDAO;
import ua.nure.pi.entity.CV;
import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Group;
import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Student;
import ua.nure.pi.server.GreetingServiceImpl;
import ua.nure.pi.server.RegistrationServiceImpl;
import ua.nure.pi.entity.Purpose;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.layout.client.Layout.Alignment;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.smartgwt.client.widgets.DateChooser;
import com.smartgwt.client.widgets.Label;  
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.types.MultiComboBoxLayoutStyle;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.TreeModelType;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.widgets.TransferImgButton;
import com.smartgwt.client.widgets.events.DragStartEvent;
import com.smartgwt.client.widgets.events.DragStartHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IPickTreeItem;
import com.smartgwt.client.widgets.form.fields.MultiComboBoxItem;
import com.smartgwt.client.widgets.form.fields.PickTreeItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.EditorEnterEvent;
import com.smartgwt.client.widgets.form.fields.events.EditorEnterHandler;
import com.smartgwt.client.widgets.form.fields.events.EditorExitEvent;
import com.smartgwt.client.widgets.form.fields.events.EditorExitHandler;
import com.smartgwt.client.widgets.form.fields.events.FocusEvent;
import com.smartgwt.client.widgets.form.fields.events.FocusHandler;
import com.smartgwt.client.widgets.form.fields.events.ShowValueEvent;
import com.smartgwt.client.widgets.form.fields.events.ShowValueHandler;
import com.smartgwt.client.widgets.form.fields.events.ValueHoverEvent;
import com.smartgwt.client.widgets.form.fields.events.ValueHoverHandler;
import com.smartgwt.client.widgets.form.validator.CustomValidator;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RegistrationSimplePanel extends LoadingSimplePanel {
	
	int loadingElement;
	
	private MainServiceAsync registrationService;

    ArrayList<Faculty> faculties;
    
    ArrayList<ProgramLanguage> programLanguages;
    
    ArrayList<Language> langs;
    
    ArrayList<Purpose> purposes;

    public ComboBoxItem goalComboBox;
    
    public Boolean newPurp;
    
    public TextItem NametextBox;
        
    public TextItem SurnametextBox;
    
    public DateItem dateItem;
    
    public TextItem Emailtextbox;

    public TextItem PhonetextBox;
    
    public TextItem AddresstextBox;
    

    public Collection<ProgramLanguage> newPL;
    
    public ArrayList<ProgramLanguage> resPL;
    
    public TextAreaItem others;
    
    public TextAreaItem qualities;
    
    public CV cv;
    
    // public SelectItem goalComboBox;
    
    public DynamicForm form;
    
    public DynamicForm mainForm;
    
    public DynamicForm otherForm;
    
    public TreeNode[] children;
    
    public PickTreeItem pickDepartment;
    
    public TextItem Skypetextbox;
    
    public CheckBox cb;
    
    WorkExperienceSimplePanel expPanel; 
    EducationSimplePanel eduPanel;            
    LanguageSimplePanel lanPanel;
    SertificateSimplePanel ssp;
    
    MultiComboBox lowLevelTech;
    
    MultiComboBox midLevelTech;
    
    MultiComboBox highLevelTech;
    
    Student st;
    
    StaticTextItem nameHint;
    StaticTextItem surnameHint;

    StaticTextItem dateHint;
    StaticTextItem addressHint;
    StaticTextItem emailHint;
    StaticTextItem phoneHint;
    StaticTextItem skypeHint;
    StaticTextItem goalHint;
    
    Label label;
    
    public int generalWidth = 300;
    
    public RegistrationSimplePanel(MainServiceAsync reg, final Button btnNewButton){
    	registrationService = reg;
    	onModuleLoad(btnNewButton);
    }
    
	public void onModuleLoad(final Button btnNewButton) {

		isReady = false;
		final LoadingSimplePanel thisPanel = this;
		loadingElement = 0;
	    VerticalPanel rootPanel = new VerticalPanel();
	    mainForm = new DynamicForm();
	    mainForm.setNumCols(1);
	    mainForm.setTitleSuffix("");
	    mainForm.setRequiredTitleSuffix("");
	    mainForm.setRequiredRightTitleSuffix("");
	    mainForm.setZIndex(10);
	    rootPanel.setWidth("100%");
	    final SimplePanel facultiesPanel = new SimplePanel();
	    facultiesPanel.addStyleName("mainForm");
	    
        label = new Label();  
        label.setHeight(60);  
        //label.setPadding(10);  
        //label.setAlign(Alignment.LEFT);  
        label.setValign(VerticalAlignment.CENTER);  
        label.setWrap(false);  
        label.setContents("Добавление резюме студента");  
        label.setStyleName("title");
        label.draw();  
        
        
	    
	    rootPanel.add(label);	

	    rootPanel.add(facultiesPanel);

	    
	    rootPanel.add(mainForm);
	    	    
	    mainForm.setStyleName("mainForm");


	    // Факультеты и группы
	    
	    registrationService.getFaculties(new AsyncCallback<Collection<Faculty>>() {
            public void onFailure(Throwable caught) {
            	loadingElement++;
            	if(loadingElement==4){
            		isReady=true;
            		fireLoadEvent(thisPanel);
            		mainForm.markForRedraw();
            	}
            	Window.alert("Не удалось получить список факультетов");
            }

			@Override
			public void onSuccess(Collection<Faculty> result) {
				faculties = new ArrayList<Faculty>(result);		
				FillTree(faculties, facultiesPanel);
				loadingElement++;
				if(loadingElement==4){
            		isReady=true;
            		fireLoadEvent(thisPanel);
            		mainForm.markForRedraw();
            	}
			}
          });
        
        // Персональная информация
	    
	    
                
        SurnametextBox = new TextItem("surname", "Фамилия");  
        SurnametextBox.setWidth(generalWidth);  
        SurnametextBox.setHint("Введите фамилию");  
        SurnametextBox.setShowHintInField(true);
        //SurnametextBox.setMask(">C<CCCCCCCCCCCCCCCCCCCC");
	    surnameHint = new StaticTextItem();
	    surnameHint.setShowTitle(false);
	    surnameHint.setValue("Например, Иванов");
	    SurnametextBox.setRequired(true);

               
        NametextBox = new TextItem("name", "Имя");  
        NametextBox.setWidth(generalWidth);  
        NametextBox.setHint("Введите имя");  
        NametextBox.setShowHintInField(true);
       // NametextBox.setMask(">C<CCCCCCCCCCCCCCCCCCCC");
	    nameHint = new StaticTextItem();
	    nameHint.setShowTitle(false);
	    nameHint.setValue("Например, Иван");
	    NametextBox.setRequired(true);

	    dateHint = new StaticTextItem();
	    dateHint.setShowTitle(false);
	    dateHint.setValue("Например, 01.01.1970");
	    
	    dateItem = new DateItem("dob", "Дата рождения");
	    dateItem.setWrapTitle(false);
	    dateItem.setRequired(true);
	    Date today = new java.util.Date();
	    dateItem.setEndDate(today);
	    dateItem.setStartDate(new Date(0));

        
        AddresstextBox = new TextItem("address", "Домашний адрес");  
        AddresstextBox.setWidth(generalWidth);  
        AddresstextBox.setHint("Введите домашний адрес");  
        AddresstextBox.setShowHintInField(true);
        AddresstextBox.setMask("");
        addressHint = new StaticTextItem();
	    addressHint.setShowTitle(false);
	    addressHint.setValue("Например, г. Харьков, пр.Ленина, 9, кв.3");

        RegExpValidator emailValidator = new RegExpValidator();  
        emailValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");  
        
        Emailtextbox = new TextItem("email", "Email");  
        Emailtextbox.setWidth(generalWidth);  
        Emailtextbox.setHint("Введите email"); 
        Emailtextbox.setShowHintInField(true);
        Emailtextbox.setValidators(emailValidator);
	    emailHint = new StaticTextItem();
	    emailHint.setShowTitle(false);
	    emailHint.setValue("Например, example@gmail.com");
	    Emailtextbox.setRequired(true);

        
        PhonetextBox = new TextItem("phone", "Контакнтый телефон");  
        PhonetextBox.setWidth(generalWidth);  
        PhonetextBox.setShowHintInField(true);
        PhonetextBox.setHint("Введите контактный телефон"); 
        PhonetextBox.setMask("+38(###) ###-####");
        PhonetextBox.setRequired(true);
	    phoneHint = new StaticTextItem();
	    phoneHint.setShowTitle(false);
	    phoneHint.setValue("Например, +38(050)145-8872");

                
        Skypetextbox = new TextItem("skype", "Skype");  
        Skypetextbox.setWidth(generalWidth);  
        Skypetextbox.setHint("Введите skype"); 
        Skypetextbox.setShowHintInField(true);
        Skypetextbox.setRequired(false);
	    skypeHint = new StaticTextItem();
	    skypeHint.setShowTitle(false);
	    skypeHint.setValue("Например, skypelogin");

	    goalHint = new StaticTextItem();
	    goalHint.setShowTitle(false);
	    goalHint.setValue("Например, Junior Java developer");

	    goalComboBox = new ComboBoxItem("goal", "Желаемая должность");
        goalComboBox.setHint("-Должность-");
	    goalComboBox.setWidth(generalWidth);
	    goalComboBox.setRequired(true);
	    goalComboBox.setShowHintInField(true);
	    
	    expPanel = new WorkExperienceSimplePanel();
        
        eduPanel = new EducationSimplePanel();
        
        langs = new ArrayList<Language>();
        
        lanPanel = new LanguageSimplePanel();

        
	    


        ssp = new SertificateSimplePanel();
        
        
        registrationService.getLanguages(new AsyncCallback<Collection<Language>>() {
            public void onFailure(Throwable caught) {
              Window.alert("Не удалось получить языки");
              loadingElement++;
              if(loadingElement==4){
          		isReady=true;
          		fireLoadEvent(thisPanel);
          		mainForm.markForRedraw();
          	}
            }

			@Override
			public void onSuccess(Collection<Language> result) {
				try{
					langs = new ArrayList<Language>(result);
					lanPanel.FillLangs(langs);
					mainForm.markForRedraw();
					loadingElement++;
					if(loadingElement==4){
	            		isReady=true;
	            		fireLoadEvent(thisPanel);
	            		mainForm.markForRedraw();
	            	}
				}
				catch(Exception e){
					Window.alert(e.getMessage());
				}
			}
          });
        
	    registrationService.getPurposes(new AsyncCallback<Collection<Purpose>>() {
            public void onFailure(Throwable caught) {
            	loadingElement++;
            	if(loadingElement==4){
            		isReady=true;
            		fireLoadEvent(thisPanel);
            		mainForm.markForRedraw();
            	}
            	Window.alert("Не удалось получить должности");
            }

			@Override
			public void onSuccess(Collection<Purpose> result) {
				purposes = new ArrayList<Purpose>(result);
				String[] strings = new String[purposes.size()];
				int i=0;
				for (Purpose prp : purposes) {
		        	//phm.put(prp.getId(), prp.getTitle());
					strings[i++]=prp.getTitle();
		        }
				goalComboBox.setValueMap(strings);
				//goalComboBox.setValueMap(phm);
				loadingElement++;
				if(loadingElement==4){
            		isReady=true;
            		fireLoadEvent(thisPanel);
            		mainForm.markForRedraw();
            	}
			}
          });
        // Заполнение возможных целей из базы
                
        // Опыт работы и образование
        
        
        // Знание языков программирования и технологий
                
        lowLevelTech = new MultiComboBox("Владение технологиями на уровне начинающего");
        midLevelTech = new MultiComboBox("Владение технологиями на среднем уровне");
        highLevelTech = new MultiComboBox("Владение технологиями на продвинутом уровне");

        rootPanel.add(lowLevelTech);
        rootPanel.add(midLevelTech);
        rootPanel.add(highLevelTech);
        lowLevelTech.addStyleName("mainForm");
        midLevelTech.addStyleName("mainForm");
        highLevelTech.addStyleName("mainForm");

        

        final Collection<String> lhm = new ArrayList<String>();
        
	    registrationService.getProgramLanguages(new AsyncCallback<Collection<ProgramLanguage>>() {
            public void onFailure(Throwable caught) {
            	Window.alert("Не удалось получить языки программирования");
              	loadingElement++;
              	if(loadingElement==4){
            		isReady=true;
            		fireLoadEvent(thisPanel);
            		mainForm.markForRedraw();
            	}
            }

			@Override
			public void onSuccess(Collection<ProgramLanguage> result) {
				programLanguages = new ArrayList<ProgramLanguage>(result);
				for (ProgramLanguage prl : programLanguages) {
		        	lhm.add(prl.getTitle());
		        }
		        lowLevelTech.addValues(lhm);
		        midLevelTech.addValues(lhm);
		        highLevelTech.addValues(lhm);

		        loadingElement++;
		        if(loadingElement==4){
            		isReady=true;
            		fireLoadEvent(thisPanel);
            		mainForm.markForRedraw();
            	}
			}
          });

        
        //languages.setWidth(290);
        //languages.setColSpan(20);
        
	        
	        Button commit = new Button("Отправить анкету");
	        commit.addClickHandler(new ClickHandler() {
	        	
	            public void onClick(ClickEvent event) {
	            	Boolean b = mainForm.validate();
	            	//b = ValidateLanPanel(lanPanel) && b;
	            	b = form.validate() && b;
	            	b = ValidateSerPanel(ssp) && b;
	            		
	            	b = ValidateWorkPanel(expPanel) && b;
	            	b = ValidateEduPanel(eduPanel) && b;
	            	
	            	if(b && !ValidateProgLanguages()){
	            		Window.alert("Нельзя выбирать одинаковые технологии с разными уровнями знаний");
	            		b=false;
	            	}
	            	
	            	if (!cb.getValue())
	            	{
	            		Window.alert("Подтвердите согласие на обработку данных");
	            		b = false;
	            	}
        			if(b){
	            	
	            	st = getStudent();
	            	new VerifyDialogBox(st,registrationService, newPurp, newPL, btnNewButton);
	            	}
            	}

	        });
	        
                
      		
		otherForm = new DynamicForm();
		otherForm.setCellPadding(15);

		otherForm.setNumCols(1);
		otherForm.setTitleSuffix("");
	    qualities = new TextAreaItem("Qualities");
	    qualities.setCellStyle("fixTextArea");
	    qualities.setTitle("Личные качества");

	    others = new TextAreaItem("Others");
	    others.setCellStyle("fixTextArea");
	    others.setTitle("Прочее");
	    
	    
	    cb = new CheckBox();
	    cb.setText("Я даю согласие отделу практики \"Центр-Карьера\" ХНУРЭ на электронную обработку моих персональных данных");
	    cb.setSize("300px", "44px");
	    
        otherForm.addStyleName("mainForm");
        cb.addStyleName("mainForm");
	    
	    
	    surnameHint.setCellStyle("hint");
	    nameHint.setCellStyle("hint");
	    dateHint.setCellStyle("hint");
	    addressHint.setCellStyle("hint");
	    emailHint.setCellStyle("hint");
	    skypeHint.setCellStyle("hint");
	    goalHint.setCellStyle("hint");
	    phoneHint.setCellStyle("hint");

	    
	    
	    mainForm.setCellPadding(5);
	    
        mainForm.setTitleOrientation(TitleOrientation.TOP);

        
	    
	    mainForm.markForRedraw();
	    
	    
        lanPanel.addStyleName("mainForm");
        eduPanel.addStyleName("mainForm");
        expPanel.addStyleName("mainForm");
        ssp.addStyleName("mainForm");
	    commit.setStyleName("commit");

	    
	    rootPanel.add(lanPanel);
	    rootPanel.add(eduPanel);
	    rootPanel.add(expPanel);
	    rootPanel.add(ssp);
	    
	    rootPanel.add(otherForm);
	    rootPanel.setSpacing(15);
	    
	    otherForm.setTitleOrientation(TitleOrientation.TOP);
	    otherForm.markForRedraw();
	    rootPanel.add(cb);
	    
	    rootPanel.setCellHorizontalAlignment(commit, HasHorizontalAlignment.ALIGN_RIGHT);
	    rootPanel.add(commit);
	    
	    rootPanel.setCellHorizontalAlignment(eduPanel, HasHorizontalAlignment.ALIGN_LEFT);
	    rootPanel.setCellHorizontalAlignment(expPanel, HasHorizontalAlignment.ALIGN_LEFT);
	    rootPanel.setCellHorizontalAlignment(lanPanel, HasHorizontalAlignment.ALIGN_LEFT);
	    
	    rootPanel.setCellHorizontalAlignment(cb, HasHorizontalAlignment.ALIGN_LEFT);
        setWidget(rootPanel);
        

        
	}  
	
	
	
	public void FillTree(ArrayList<Faculty> faculties, SimplePanel facultiesPanel)
	{
		children = new TreeNode[faculties.size()];
	    
		for (int i = 0; i < faculties.size(); i++) {
		    
		    Faculty current = faculties.get(i);
		    children[i] = new TreeNode(current.getTitle());
		    TreeNode[] tn = new TreeNode[current.getGroups().size()];
		    int j = 0;
		    for (Group g : current.getGroups()) {
		    	try {
		    	tn[j] = new TreeNode(g.getTitle());
		    	tn[j].setAttribute("GroupId", Long.toString(g.getGroupId()));
		    	}
		    	catch (Exception e){
		    		Window.alert(e.getLocalizedMessage());
		    	}
		    	j++;
		    }
		    children[i].setChildren(tn);
		    
	    }
	    
	    TreeNode rootNode = new TreeNode();
	    rootNode.setName("root");
	    rootNode.setChildren(children);
	    Tree tree = new Tree();
	    tree.setModelType(TreeModelType.CHILDREN);
	    tree.setRoot(rootNode);
	    
	    form = new DynamicForm();
	    form.setRequiredTitleSuffix("");
	    pickDepartment = new PickTreeItem();
	    pickDepartment.setTitle("Группа");
	    pickDepartment.setName("department");
	    pickDepartment.setValueField("name");
	    pickDepartment.setValueTree(tree);
	    pickDepartment.setRequired(true);
	    form.setItems(pickDepartment);
	    
	    facultiesPanel.add(form);
	    
	    form.markForRedraw();

	}
	
	public void CollectLanguages(MultiComboBox m) {
		int level = -1;
		if (m.getTitle().contains("начинающего"))
			level = 0;
		if (m.getTitle().contains("среднем"))
			level = 1;
		if (m.getTitle().contains("продвинутом"))
			level = 2;
		for(String pl : m.getValues()){
			boolean n = true;
			for(ProgramLanguage p : programLanguages){
				if(p.getTitle().equals(pl)){
					resPL.add(new ProgramLanguage(pl, p.getId(),level));
					n=false;
					break;
				}
			}
			if(n) {
				ProgramLanguage pnew = new ProgramLanguage();
				pnew.setTitle(pl);
				pnew.setLevel(level);
				newPL.add(pnew);
			}
		}		
	}
	
	public Boolean ValidateLanPanel(LanguageSimplePanel lsp){
		return lsp.ValidateForm();
	}
	
	public Boolean ValidateEduPanel(EducationSimplePanel esp){
		return esp.ValidateForm();
	}
	
	public Boolean ValidateSerPanel(SertificateSimplePanel ssp){
		return ssp.ValidateForm();
	}
	
	public Boolean ValidateWorkPanel(WorkExperienceSimplePanel wsp){
		return wsp.ValidateForm();
	}
	
	public Boolean ValidateProgLanguages(){
		Collection<String> col = lowLevelTech.getValues();
		col.addAll(midLevelTech.getValues()); 
		col.addAll(highLevelTech.getValues()); 
		
		HashSet<String> set = new HashSet<String>();
		set.addAll(lowLevelTech.getValues()); 
		set.addAll(midLevelTech.getValues());
		set.addAll(highLevelTech.getValues());
		
		return col.size() == set.size();
	}
	
	
	public Student getStudent(){
		// 

		st = new Student();
		st.setFirstname(NametextBox.getValueAsString());
		st.setSurname(SurnametextBox.getValueAsString());

		st.setDateOfBirth((Date)dateItem.getValue());
		st.setEmail(Emailtextbox.getValueAsString());
		st.setPhone(PhonetextBox.getValueAsString());
		st.setAddress(AddresstextBox.getValueAsString());
		for (Faculty f : faculties)
			for (Group g : f.getGroups())
				if (pickDepartment.getValue().toString().equals(g.getTitle()))
				{
					st.setGroup(g);
					break;
				}
		st.setSkype(Skypetextbox.getValueAsString());
		cv = new CV();
		newPurp=false;
		for(Purpose i : purposes){
			if(i.getTitle().equals(goalComboBox.getDisplayValue())){
				cv.setPurpose(i);
					}
		}
		if (cv.getPurpose() == null) {
			Purpose p = new Purpose();
			p.setTitle(goalComboBox.getValueAsString());
			newPurp = true;
			cv.setPurpose(p);
		}
		String oth = others.getDisplayValue();
		oth = oth.replaceAll("\n", "<br/>");
		String qua = qualities.getDisplayValue();
		qua = qua.replaceAll("\n", "<br/>");
		cv.setOthers(oth);
		cv.setQualities(qua);
		cv.setEducations(eduPanel.getEducation());
		cv.setLanguages(lanPanel.getLanguages());
		cv.setSertificates(ssp.getSerts());
		cv.setWorkExps(expPanel.getExp());
		newPL = new ArrayList<ProgramLanguage>();

		resPL = new ArrayList<ProgramLanguage>();
		
		CollectLanguages(lowLevelTech);
		CollectLanguages(midLevelTech);
		CollectLanguages(highLevelTech);
		Collections.reverse(resPL);
		cv.setProgramLanguages(resPL);
		st.setCv(cv);
		return st;
	}
	
	@Override
	public void refresh(){
		mainForm.setFields(SurnametextBox,surnameHint, NametextBox, nameHint, dateItem, dateHint, 
	    		AddresstextBox, addressHint, Emailtextbox, emailHint, PhonetextBox, phoneHint, Skypetextbox, skypeHint,
	    		goalComboBox,goalHint);
		otherForm.setFields(qualities,others);
		otherForm.markForRedraw();
		lowLevelTech.draw();
		midLevelTech.draw();
		highLevelTech.draw();
		mainForm.markForRedraw();
		SurnametextBox.setTabIndex(1);
        NametextBox.setTabIndex(2);

        dateItem.setTabIndex(4);
        AddresstextBox.setTabIndex(5);
        Emailtextbox.setTabIndex(6);
        PhonetextBox.setTabIndex(7);
        Skypetextbox.setTabIndex(8);
        goalComboBox.setTabIndex(9);
	}
	
	public static native int getScrollPos() /*-{
		var scrollTop = $wnd.window.pageYOffset ? $wnd.window.pageYOffset : ($wnd.document.documentElement.scrollTop ? $wnd.document.documentElement.scrollTop : $wnd.document.body.scrollTop);
		return scrollTop;
	}-*/;
}