package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;






import ua.nure.pi.dao.mssql.MSSqlFacultyGroupDAO;
import ua.nure.pi.dao.mssql.MSSqlProgramLanguageDAO;
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
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.EditorExitEvent;
import com.smartgwt.client.widgets.form.fields.events.EditorExitHandler;
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
public class RegistrationSimplePanel extends SimplePanel {
	
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
    
    public TextItem PatronymictextBox;
    
    public Collection<ProgramLanguage> newPL;
    
    
    public TextAreaItem others;
    
    public TextAreaItem qualities;
    
    public CV cv;
    
    // public SelectItem goalComboBox;
    
    public DynamicForm form;
    
    public TreeNode[] children;
    
    public PickTreeItem pickDepartment;
    
    public TextItem Skypetextbox;
    
    public CheckBox cb;
    
    WorkExperienceSimplePanel expPanel; 
    EducationSimplePanel eduPanel;            
    LanguageSimplePanel lanPanel;
    SertificateSimplePanel ssp;
    
    MultiComboBoxItem languages;
    
    Student st;

    public RegistrationSimplePanel(MainServiceAsync reg){
    	registrationService = reg;
    	onModuleLoad();
    }
    
	public void onModuleLoad() {

	    VerticalPanel rootPanel = new VerticalPanel();
	    final DynamicForm mainForm = new DynamicForm();
	    mainForm.setNumCols(1);
	    rootPanel.setWidth("100%");
	    final SimplePanel facultiesPanel = new SimplePanel();
	    rootPanel.add(facultiesPanel);
	    rootPanel.add(mainForm);

	    // Факультеты и группы
	    
	    registrationService.getFaculties(new AsyncCallback<Collection<Faculty>>() {
            public void onFailure(Throwable caught) {
              Window.alert("Не удалось получить список факультетов");
            }

			@Override
			public void onSuccess(Collection<Faculty> result) {
				faculties = new ArrayList<Faculty>(result);		
				FillTree(faculties, facultiesPanel);
			}
          });
        
        // Персональная информация
	    
	    
                
        SurnametextBox = new TextItem("surname", "Фамилия");  
        SurnametextBox.setWidth(300);  
        SurnametextBox.setHint("Введите фамилию");  
        SurnametextBox.setShowHintInField(true);
        SurnametextBox.setMask(">C<CCCCCCCCCCCCCCCCCCCC");
	    StaticTextItem surnameHint = new StaticTextItem();
	    surnameHint.setShowTitle(false);
	    surnameHint.setValue("Например, Иванов");
	    SurnametextBox.setRequired(true);

               
        NametextBox = new TextItem("name", "Имя");  
        NametextBox.setWidth(300);  
        NametextBox.setHint("Введите имя");  
        NametextBox.setShowHintInField(true);
        NametextBox.setMask(">C<CCCCCCCCCCCCCCCCCCCC");
	    StaticTextItem nameHint = new StaticTextItem();
	    nameHint.setShowTitle(false);
	    nameHint.setValue("Например, Иван");
	    NametextBox.setRequired(true);
	    
        PatronymictextBox = new TextItem("patronymic", "Отчество");  
        PatronymictextBox.setWidth(300);  
        PatronymictextBox.setHint("Введите отчество");  
        PatronymictextBox.setShowHintInField(true);
        PatronymictextBox.setMask(">C<CCCCCCCCCCCCCCCCCCCC");
	    StaticTextItem patronHint = new StaticTextItem();
	    patronHint.setShowTitle(false);
	    patronHint.setValue("Например, Иванович");
	    PatronymictextBox.setRequired(false);

        /*
        dateItem = new DateItem();
		dateItem.setTitle("Дата рождения");
		dateItem.setUseTextField(true);
		dateItem.setWidth(300);
		dateItem.setHint("Введите дату рождения или выберите справа");
		dateItem.setShowHintInField(true);
		dateItem.setEndDate(new Date());
	    dateItem.setRequired(true);
		*/
	    StaticTextItem dateHint = new StaticTextItem();
	    dateHint.setShowTitle(false);
	    dateHint.setValue("Например, 01.01.1970");
	    
	    dateItem = new DateItem("dob", "Дата рождения");
	    dateItem.setWrapTitle(false);
	    dateItem.setRequired(true);
	    Date today = new java.util.Date();
	    dateItem.setEndDate(today);
	    dateItem.setStartDate(new Date(0));

        
        AddresstextBox = new TextItem("address", "Домашний адрес");  
        AddresstextBox.setWidth(300);  
        AddresstextBox.setHint("Введите домашний адрес");  
        AddresstextBox.setShowHintInField(true);
        AddresstextBox.setMask("");
	    StaticTextItem addressHint = new StaticTextItem();
	    addressHint.setShowTitle(false);
	    addressHint.setValue("Например, г. Харьков, пр.Ленина, 9, кв.3");

        RegExpValidator emailValidator = new RegExpValidator();  
        emailValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");  
        
        Emailtextbox = new TextItem("email", "Email");  
        Emailtextbox.setWidth(300);  
        Emailtextbox.setHint("Введите email"); 
        Emailtextbox.setShowHintInField(true);
        Emailtextbox.setValidators(emailValidator);
	    StaticTextItem emailHint = new StaticTextItem();
	    emailHint.setShowTitle(false);
	    emailHint.setValue("Например, example@gmail.com");
	    Emailtextbox.setRequired(true);

        
        PhonetextBox = new TextItem("phone", "Контакнтый телефон");  
        PhonetextBox.setWidth(300);  
        PhonetextBox.setShowHintInField(true);
        PhonetextBox.setHint("Введите контактный телефон"); 
        PhonetextBox.setMask("+38(###) ###-####");
        PhonetextBox.setRequired(true);
	    StaticTextItem phoneHint = new StaticTextItem();
	    phoneHint.setShowTitle(false);
	    phoneHint.setValue("Например, +38(050)145-8872");

                
        Skypetextbox = new TextItem("skype", "Skype");  
        Skypetextbox.setWidth(300);  
        Skypetextbox.setHint("Введите skype"); 
        Skypetextbox.setShowHintInField(true);
        Skypetextbox.setRequired(false);
	    StaticTextItem skypeHint = new StaticTextItem();
	    skypeHint.setShowTitle(false);
	    skypeHint.setValue("Например, skypelogin");

        /*
        goalComboBox = new SelectItem("Goal");
        goalComboBox.setTitle("Желаемая должность");
        goalComboBox.setHint("-Должности-");
        goalComboBox.setShowHintInField(true);
        goalComboBox.setWidth(300);
        goalComboBox.setHeight(22);
        goalComboBox.setRequired(true);
		*/
	    
	    StaticTextItem goalHint = new StaticTextItem();
	    goalHint.setShowTitle(false);
	    goalHint.setValue("Например, Junior Java developer");

	    goalComboBox = new ComboBoxItem("goal", "Желаемая должность");
        goalComboBox.setHint("-Должность-");
	    goalComboBox.setWidth(300);
	    goalComboBox.setRequired(true);
	    goalComboBox.setShowHintInField(true);

        
        final LinkedHashMap<Long, String> phm = new LinkedHashMap<>();

        
	    registrationService.getPurposes(new AsyncCallback<Collection<Purpose>>() {
            public void onFailure(Throwable caught) {
              Window.alert("Не удалось получить должности");
            }

			@Override
			public void onSuccess(Collection<Purpose> result) {
				purposes = new ArrayList<Purpose>(result);
				for (Purpose prp : purposes) {
		        	phm.put(prp.getId(), prp.getTitle());
		        }
				goalComboBox.setValueMap(phm);
			}
          });
        // Заполнение возможных целей из базы
                
        // Опыт работы и образование
        
        expPanel = new WorkExperienceSimplePanel();
                
        eduPanel = new EducationSimplePanel();
        
        langs = new ArrayList<Language>();
        
        lanPanel = new LanguageSimplePanel();

        
	    registrationService.getLanguages(new AsyncCallback<Collection<Language>>() {
            public void onFailure(Throwable caught) {
              Window.alert("Не удалось получить языки программирования");
            }

			@Override
			public void onSuccess(Collection<Language> result) {
				langs = new ArrayList<Language>(result);
				lanPanel.FillLangs(langs);
			}
          });


        ssp = new SertificateSimplePanel();
        
        // Знание языков программирования и технологий
                
        final MultiComboBoxLayoutStyle initialLayoutStyle = MultiComboBoxLayoutStyle.FLOW;  
        languages = new MultiComboBoxItem("skills", "Профессиональные навыки");
        ComboBoxItem child = new ComboBoxItem();
        child.setHint("-Технологии-");
        child.setWidth(290);
        languages.setComboBoxProperties(child);
        
        final LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        
	    registrationService.getProgramLanguages(new AsyncCallback<Collection<ProgramLanguage>>() {
            public void onFailure(Throwable caught) {
              Window.alert("Не удалось получить языки программирования");
            }

			@Override
			public void onSuccess(Collection<ProgramLanguage> result) {
				programLanguages = new ArrayList<ProgramLanguage>(result);
				for (ProgramLanguage prl : programLanguages) {
		        	lhm.put(prl.getTitle(), prl.getTitle());
		        }
		        languages.setValueMap(lhm);
			}
          });

        languages.setLayoutStyle(initialLayoutStyle);
        //languages.setWidth(290);
        languages.setAddUnknownValues(false);
        languages.setColSpan(20);
        
	        
	        Button commit = new Button("Отправить анкету");
	        commit.addClickHandler(new ClickHandler() {
	        	
	            public void onClick(ClickEvent event) {
	            	Boolean b = mainForm.validate();
	            	//b = ValidateLanPanel(lanPanel) && b;
	            	b = form.validate() && b;
	            	b = ValidateSerPanel(ssp) && b;
	            		
	            	b = ValidateWorkPanel(expPanel) && b;
	            	b = ValidateEduPanel(eduPanel) && b;
	            	
	            	if (!cb.getValue())
	            	{
	            		Window.alert("Подтвердите согласие на обработку данных");
	            		b = false;
	            	}
        			if(b){
	            	
	            	st = getStudent();
	            	new VerifyDialogBox(st,registrationService, newPurp, newPL);
	            	/*registrationService.sendStudent(st, newPurp, newPL, new AsyncCallback<Void>() {
	                    public void onFailure(Throwable caught) {
	                      Window.alert(caught.getLocalizedMessage());
	                    }

						@Override
						public void onSuccess(Void result) {
		                      Window.alert("Анкета сохранена");
							
						}
	                  });	*/              
	            	}
            	}

	        });
	        
                
      		
		DynamicForm form = new DynamicForm();
		form.setCellPadding(15);

		form.setNumCols(1);
		
	    qualities = new TextAreaItem("Qualities");
	    qualities.setCellStyle("fixTextArea");
	    qualities.setTitle("Личные качества");

	    others = new TextAreaItem("Others");
	    others.setCellStyle("fixTextArea");
	    others.setTitle("Прочее");
	    
	    cb = new CheckBox();
	    cb.setText("Я даю согласие отделу практики \"Центр-Карьера\" ХНУРЭ на электронную обработку моих персональных данных");
	    cb.setSize("343px", "44px");
	    
	    
	    
	    surnameHint.setCellStyle("hint");
	    nameHint.setCellStyle("hint");
	    dateHint.setCellStyle("hint");
	    addressHint.setCellStyle("hint");
	    emailHint.setCellStyle("hint");
	    skypeHint.setCellStyle("hint");
	    goalHint.setCellStyle("hint");
	    languages.setCellStyle("hint");
	    phoneHint.setCellStyle("hint");
	    patronHint.setCellStyle("hint");

	    
	    
	    mainForm.setCellPadding(5);
	    mainForm.setFields(SurnametextBox,surnameHint, NametextBox, nameHint,PatronymictextBox, patronHint, dateItem, dateHint, 
	    		AddresstextBox, addressHint, Emailtextbox, emailHint, PhonetextBox, phoneHint, Skypetextbox, skypeHint,
	    		goalComboBox,goalHint, languages);
        mainForm.setTitleOrientation(TitleOrientation.TOP);

        SurnametextBox.setTabIndex(1);
        NametextBox.setTabIndex(2);
        PatronymictextBox.setTabIndex(3);
        dateItem.setTabIndex(4);
        AddresstextBox.setTabIndex(5);
        Emailtextbox.setTabIndex(6);
        PhonetextBox.setTabIndex(7);
        Skypetextbox.setTabIndex(8);
        goalComboBox.setTabIndex(9);
        languages.setTabIndex(10);
	    
	    mainForm.markForRedraw();
	    
	    rootPanel.add(lanPanel);
	    rootPanel.add(eduPanel);
	    rootPanel.add(expPanel);
	    rootPanel.add(ssp);
	    
	    rootPanel.add(form);
	    rootPanel.setSpacing(15);
	    
	    form.setFields(qualities,others);
	    form.setTitleOrientation(TitleOrientation.TOP);
	    form.markForRedraw();
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
	    pickDepartment = new PickTreeItem();
	    pickDepartment.setTitle("Группа");
	    pickDepartment.setName("department");
	    pickDepartment.setValueField("name");
	    pickDepartment.setValueTree(tree);
	    pickDepartment.setRequired(true);
	    form.setItems(pickDepartment);
	    form.draw(); 
	    
	    facultiesPanel.add(form);
	    
	    form.markForRedraw();

	}
	
	public Boolean ValidateLanPanel(LanguageSimplePanel lsp){
		Boolean f = true;
		for (LanguageElementSimplePanel lesp : lsp.languages){
			f = lesp.controls.validate()&& f;
			lesp.controls.markForRedraw();
		}
		return f;
	}
	
	public Boolean ValidateEduPanel(EducationSimplePanel esp){
		Boolean f = true;
		for (EducationElementSimplePanel eesp : esp.educations){
			f = eesp.controls.validate() && f;
			eesp.controls.markForRedraw();
		}
		return f;
	}
	
	public Boolean ValidateSerPanel(SertificateSimplePanel ssp){
		Boolean f = true;
		for (SertificateElementSimplePanel sesp : ssp.sertificates){
			f = sesp.controls.validate()&& f;
			sesp.controls.markForRedraw();
		}
		return f;
	}
	
	public Boolean ValidateWorkPanel(WorkExperienceSimplePanel wsp){
		Boolean f = true;
		for (WorkExperinceElementSimplePanel wesp : wsp.works){
			f = wesp.controls.validate()&& f;
			wesp.controls.markForRedraw();
		}
		return f;
	}
	
	
	
	public Student getStudent(){
		// 

		st = new Student();
		st.setFirstname(NametextBox.getValueAsString());
		st.setSurname(SurnametextBox.getValueAsString());
		st.setPatronymic(PatronymictextBox.getValueAsString());
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
		Window.alert(goalComboBox.getValueAsString() + " " + goalComboBox.getDisplayValue());
		for(Purpose i : purposes){
			if(i.getTitle().equals(goalComboBox.getDisplayValue()) &&
					String.valueOf(i.getId()).equals(goalComboBox.getValueAsString())){
				cv.setPurpose(i);
				Window.alert(String.valueOf(i.getId()) + " " + i.getTitle());
					}
		}
		if (cv.getPurpose() == null) {
			Purpose p = new Purpose();
			p.setTitle(goalComboBox.getValueAsString());
			newPurp = true;
			cv.setPurpose(p);
		}
		cv.setOthers(others.getValueAsString());
		cv.setQualities(qualities.getValueAsString());
		cv.setEducations(eduPanel.getEducation());
		cv.setLanguages(lanPanel.getLanguages());
		cv.setSertificates(ssp.getSerts());
		cv.setWorkExps(expPanel.getExp());
		newPL = new ArrayList<ProgramLanguage>();

		Collection<ProgramLanguage> resPL = new ArrayList<ProgramLanguage>();
		for(String pl : languages.getValues()){
			boolean n = true;
			for(ProgramLanguage p : programLanguages){
				if(p.getTitle().equals(pl)){
					resPL.add(new ProgramLanguage(pl, p.getId()));
					n=false;
					break;
				}
			}
			if(n) {
				ProgramLanguage pnew = new ProgramLanguage();
				pnew.setTitle(pl);
				newPL.add(pnew);
			}
		}
		resPL.addAll(newPL);
		cv.setProgramLanguages(resPL);
		st.setCv(cv);
		return st;
	}
}