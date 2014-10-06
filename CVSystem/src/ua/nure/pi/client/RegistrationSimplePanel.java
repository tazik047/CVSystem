package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import ua.nure.pi.dao.mssql.MSSqlFacultyGroupDAO;
import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Group;
import ua.nure.pi.server.GreetingServiceImpl;
import ua.nure.pi.server.RegistrationServiceImpl;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.layout.client.Layout.Alignment;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
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
import com.smartgwt.client.widgets.form.fields.IPickTreeItem;
import com.smartgwt.client.widgets.form.fields.MultiComboBoxItem;
import com.smartgwt.client.widgets.form.fields.PickTreeItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
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
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RegistrationSimplePanel extends SimplePanel {
	
	private MainServiceAsync registrationService;

    ArrayList<Faculty> faculties;

    public RegistrationSimplePanel(MainServiceAsync reg){
    	registrationService = reg;
    	onModuleLoad();
    }
    
	public void onModuleLoad() {

	    VerticalPanel rootPanel = new VerticalPanel();
	    DynamicForm mainForm = new DynamicForm();
	    rootPanel.setWidth("100%");
	    rootPanel.add(mainForm);
	    final SimplePanel facultiesPanel = new SimplePanel();
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
        
        Label labelSurname = new Label("Фамилия");
        labelSurname.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        labelSurname.setSize("344px", "18px");
        
        TextItem SurnametextBox = new TextItem("surname", "Фамилия");  
        SurnametextBox.setWidth(300);  
        SurnametextBox.setHint("Введите фамилию");  
        
        Label labelName = new Label("Имя");
        labelName.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        labelName.setSize("180px", "18px");
        
        TextItem NametextBox = new TextItem("name", "Имя");  
        NametextBox.setWidth(300);  
        NametextBox.setHint("Введите имя");  
        
        Label labelBirthday = new Label("Дата рождения");
        labelBirthday.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        labelBirthday.setSize("180px", "18px");
        
        DateItem dateItem = new DateItem();
		dateItem.setTitle("Введите дату рождения");
		dateItem.setUseTextField(true);
		dateItem.setWidth(300);
		dateItem.setHint("");
		//rootPanel.add(dateBox);
        
        Label labelAddress = new Label("Домашний адрес");
        labelAddress.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        labelAddress.setSize("186px", "18px");
         

        
        final Label Phonelabel = new Label("Мобильный телефон");
        Phonelabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        Phonelabel.setSize("186px", "18px");
        
        TextItem PhonetextBox = new TextItem("phone", "Введите телефон");  
        PhonetextBox.setWidth(300);  
        PhonetextBox.setHint(""); 
        PhonetextBox.setMask("(###) ###-####");  
        
        RegExpValidator emailValidator = new RegExpValidator();  
        emailValidator.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");  
        
        Label labelEmail = new Label("Email");
        labelEmail.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        labelEmail.setSize("186px", "18px");
        
        TextItem Emailtextbox = new TextItem("email", "Введите email");  
        Emailtextbox.setWidth(300);  
        Emailtextbox.setHint(""); 
        Emailtextbox.setValidators(emailValidator);
        
        Label labelSkype = new Label("Skype");
        rootPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        labelSkype.setSize("186px", "18px");
        
        TextItem Skypetextbox = new TextItem("email", "Введите skype");  
        Skypetextbox.setWidth(300);  
        Skypetextbox.setHint(""); 
        
        Label goalLabel = new Label("Цель");
        goalLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        goalLabel.setSize("186px", "10px");
        
        SelectItem goalComboBox = new SelectItem("Выберите цель");
        goalComboBox.setWidth(300);
        goalComboBox.setHeight(22);
        // Заполнение возможных целей из базы
        
        goalComboBox.setTitle("Выберите цель");
        
        // Опыт работы и образование
        
        WorkExperienceSimplePanel expPanel = new WorkExperienceSimplePanel();
                
        EducationSimplePanel eduPanel = new EducationSimplePanel();
                
        LanguageSimplePanel lanPanel = new LanguageSimplePanel();
               
        // Знание языков программирования и технологий
                
        final MultiComboBoxLayoutStyle initialLayoutStyle = MultiComboBoxLayoutStyle.FLOW;  
        final MultiComboBoxItem languages = new MultiComboBoxItem("languages", "Языки");
        
        final LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("Java", "Java");
        lhm.put("Haskell", "Haskell");
        lhm.put("Python", "Python");
        languages.setValueMap(lhm);
        languages.setLayoutStyle(initialLayoutStyle);

        
        languages.setAddUnknownValues(false);
        final DynamicForm suppliesForm = new DynamicForm();  
        suppliesForm.setWidth100();  
        suppliesForm.setNumCols(1);  
        suppliesForm.setTitleOrientation(TitleOrientation.TOP);  
        //suppliesForm.setItems(languages);  
        languages.setAddUnknownValues(false); 
        //languages.redraw();
                
	    TextArea first = new TextArea();
	    first.setWidth("343px");
	    first.addStyleName("fixTextArea");
	   
	    TextArea second = new TextArea();
	    second.addStyleName("fixTextArea");
	    second.setWidth("343px");
	    Label lbHobby = new Label("Личные качества");
	    Label lbOther = new Label("Прочее");
	    
	    CheckBox cb = new CheckBox();
	    cb.setText("Даю право на обработку и хранение личной информации администрации ресурса");
	    cb.setSize("343px", "44px");
	    	    
	    
	    mainForm.setFields(SurnametextBox, NametextBox, PhonetextBox, Emailtextbox, Skypetextbox, dateItem, goalComboBox, languages);
	    mainForm.markForRedraw();
	    rootPanel.add(lanPanel);
	    rootPanel.add(eduPanel);
	    rootPanel.add(expPanel);
	    
	    rootPanel.add(lbHobby);
	    rootPanel.add(first);
	    rootPanel.add(lbOther);
	    rootPanel.add(second);
	    
	    rootPanel.add(cb);
	    
	    
	    rootPanel.setCellHorizontalAlignment(eduPanel, HasHorizontalAlignment.ALIGN_CENTER);
	    rootPanel.setCellHorizontalAlignment(expPanel, HasHorizontalAlignment.ALIGN_CENTER);
	    rootPanel.setCellHorizontalAlignment(lanPanel, HasHorizontalAlignment.ALIGN_CENTER);
	    
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

        suppliesForm.setItems(languages);
        setWidget(rootPanel);
        languages.setAddUnknownValues(false);
        suppliesForm.markForRedraw();
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
}