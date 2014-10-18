package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;

import ua.nure.pi.dao.mssql.MSSqlFacultyGroupDAO;
import ua.nure.pi.entity.CV;
import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Group;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Student;
import ua.nure.pi.server.GreetingServiceImpl;
import ua.nure.pi.server.RegistrationServiceImpl;

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
    
    public TextItem NametextBox;
    
    public TextItem SurnametextBox;
    
    public DateItem dateItem;
    
    public TextItem Emailtextbox;

    public TextItem PhonetextBox;
    
    public TextItem AddresstextBox;
    
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
	    DynamicForm mainForm = new DynamicForm();
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

               
        NametextBox = new TextItem("name", "Имя");  
        NametextBox.setWidth(300);  
        NametextBox.setHint("Введите имя");  
        NametextBox.setShowHintInField(true);
        NametextBox.setMask(">C<CCCCCCCCCCCCCCCCCCCC");
	    StaticTextItem nameHint = new StaticTextItem();
	    nameHint.setShowTitle(false);
	    nameHint.setValue("Например, Иван");

        
        dateItem = new DateItem();
		dateItem.setTitle("Дата рождения");
		dateItem.setUseTextField(true);
		dateItem.setWidth(300);
		dateItem.setHint("Введите дату рождения или выберите справа");
		dateItem.setShowHintInField(true);
		dateItem.setEndDate(new Date());
		/*DateChooser dc = dateItem.getPickerProperties();
		dc.setPageLeft(dc.getPageLeft() + dc.getWidth()/2 + 5);*/
	    StaticTextItem dateHint = new StaticTextItem();
	    dateHint.setShowTitle(false);
	    dateHint.setValue("Например, 21.09");

        
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
        Emailtextbox.setHint("Введите email. "); 
        Emailtextbox.setShowHintInField(true);
        Emailtextbox.setValidators(emailValidator);
	    StaticTextItem emailHint = new StaticTextItem();
	    emailHint.setShowTitle(false);
	    emailHint.setValue("Например, example@gmail.com");

        
        PhonetextBox = new TextItem("phone", "Контакнтый телефон");  
        PhonetextBox.setWidth(300);  
        PhonetextBox.setHint(""); 
        PhonetextBox.setMask("+38(###) ###-####");  
                
        TextItem Skypetextbox = new TextItem("email", "Skype");  
        Skypetextbox.setWidth(300);  
        Skypetextbox.setHint(""); 
        Skypetextbox.setShowHintInField(true);

        
      
        SelectItem goalComboBox = new SelectItem("Желаемая должность");
        goalComboBox.setHint("-Должности-");
        goalComboBox.setShowHintInField(true);
        goalComboBox.setWidth(300);
        goalComboBox.setHeight(22);
        // Заполнение возможных целей из базы
                
        // Опыт работы и образование
        
        expPanel = new WorkExperienceSimplePanel();
                
        eduPanel = new EducationSimplePanel();
                
        lanPanel = new LanguageSimplePanel();
        
        ssp = new SertificateSimplePanel();
        
        // Знание языков программирования и технологий
                
        final MultiComboBoxLayoutStyle initialLayoutStyle = MultiComboBoxLayoutStyle.HORIZONTAL;  
        languages = new MultiComboBoxItem("languages", "Языки программирования");
        
        final LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("Java", "Java");
        lhm.put("Haskell", "Haskell");
        lhm.put("Python", "Python");
        lhm.put("Java1", "Jav1a");
        lhm.put("Haske1ll", "Ha1skell");
        lhm.put("Pyth1on", "Py1thon");
        lhm.put("Jav1a", "Ja2va");
        lhm.put("Hask2ell", "Has2kell");
        lhm.put("Pyth2on", "Py2hon");
        languages.setValueMap(lhm);
        languages.setLayoutStyle(initialLayoutStyle);

        languages.setAddUnknownValues(false);
        languages.setColSpan(20);
	        
	        Button commit = new Button("Отправить анкету");
	        commit.addClickHandler(new ClickHandler() {
	        	
	            public void onClick(ClickEvent event) {
	            	st = getStudent();
	            	registrationService.sendStudent(st, new AsyncCallback() {
	                    public void onFailure(Throwable caught) {
	                      Window.alert(caught.getLocalizedMessage());
	                    }

						@Override
						public void onSuccess(Object result) {
		                      Window.alert("Анкета сохранена");
							
						}
	                  });	              }

	        });
	        
        
		languages.addChangedHandler(new ChangedHandler() {
					
					@Override
					public void onChanged(ChangedEvent event) {
						if(!lhm.containsKey(languages.getValues()[0])){
							Collection<String> col = new ArrayList<String>();
							for(int i = 1; i < languages.getValues().length; i++)
								col.add(languages.getValues()[i]);
							languages.clearValue();
							languages.setValues(col.toArray());
						}
					}
				});
                
	
		
		DynamicForm form = new DynamicForm();
		form.setCellPadding(15);
		
		//form.setStyleName("fixTextArea");
		form.setNumCols(1);
		
	    TextAreaItem first = new TextAreaItem("Личные качества");
	    first.setCellStyle("fixTextArea");

	    TextAreaItem second = new TextAreaItem("Прочее");
	    second.setCellStyle("fixTextArea");
	    
	    CheckBox cb = new CheckBox();
	    cb.setText("Даю право на обработку и хранение личной информации администрации ресурса");
	    cb.setSize("343px", "44px");
	    
	    surnameHint.setCellStyle("hint");
	    nameHint.setCellStyle("hint");
	    dateHint.setCellStyle("hint");
	    addressHint.setCellStyle("hint");
	    emailHint.setCellStyle("hint");
	    Skypetextbox.setCellStyle("hint");
	    PhonetextBox.setCellStyle("hint");
	    goalComboBox.setCellStyle("hint");
	    languages.setCellStyle("hint");
	    
	    
	    mainForm.setCellPadding(5);
	    mainForm.setFields(SurnametextBox,surnameHint, NametextBox, nameHint, dateItem, dateHint, 
	    		AddresstextBox, addressHint, Emailtextbox, emailHint, PhonetextBox, Skypetextbox,
	    		goalComboBox, languages);
        mainForm.setTitleOrientation(TitleOrientation.TOP);

	    
	    mainForm.markForRedraw();
	    
	    rootPanel.add(lanPanel);
	    rootPanel.add(eduPanel);
	    rootPanel.add(expPanel);
	    rootPanel.add(ssp);
	    
	    rootPanel.add(form);
	    rootPanel.setSpacing(15);
	    
	    form.setFields(first,second);
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
		TreeNode[] children = new TreeNode[faculties.size()];
	    
		for (int i = 0; i < faculties.size(); i++) {
		    
		    Faculty current = faculties.get(i);
		    children[i] = new TreeNode(current.getTitle());
		    TreeNode[] tn = new TreeNode[current.getGroups().size()];
		    int j = 0;
		    for (Group g : current.getGroups()) {
		    	try {
		    	tn[j] = new TreeNode(g.getTitle());
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
	    
	    final DynamicForm form = new DynamicForm();
	    PickTreeItem pickDepartment = new PickTreeItem();
	    pickDepartment.setTitle("Группа");
	    pickDepartment.setName("department");
	    pickDepartment.setValueField("name");
	    pickDepartment.setValueTree(tree);
	    form.setItems(pickDepartment);
	    form.draw(); 
	    
	    facultiesPanel.add(form);
	    
	    form.markForRedraw();

	}
	
	public Student getStudent(){
		st = new Student();
		st.setFirstname(NametextBox.getValueAsString());
		st.setSurname(SurnametextBox.getValueAsString());
		st.setDateOfBirth((Date)dateItem.getValue());
		st.setEmail(Emailtextbox.getValueAsString());
		st.setPhone(PhonetextBox.getValueAsString());
		st.setAddress(AddresstextBox.getValueAsString());
		CV cv = new CV();
		cv.setEducations(eduPanel.getEducation());
		cv.setLanguages(lanPanel.getLanguages());
		cv.setSertificates(ssp.getSerts());
		cv.setWorkExps(expPanel.getExp());
		cv.setProgramLanguages((Collection<ProgramLanguage>) languages.getValueAsRecordList());
		st.setCv(cv);
		return st;
	}
}