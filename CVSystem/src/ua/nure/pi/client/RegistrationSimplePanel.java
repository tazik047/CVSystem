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
import com.smartgwt.client.widgets.form.fields.IPickTreeItem;
import com.smartgwt.client.widgets.form.fields.MultiComboBoxItem;
import com.smartgwt.client.widgets.form.fields.PickTreeItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.EditorExitEvent;
import com.smartgwt.client.widgets.form.fields.events.EditorExitHandler;
import com.smartgwt.client.widgets.form.fields.events.ValueHoverEvent;
import com.smartgwt.client.widgets.form.fields.events.ValueHoverHandler;
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
	    rootPanel.setWidth("100%");
	    HorizontalPanel upPanel = new HorizontalPanel();
	    rootPanel.add(upPanel);
	    
	    VerticalPanel leftUpPanel = new VerticalPanel();
	    VerticalPanel rightUpPanel = new VerticalPanel();
	    VerticalPanel mainPanel = new VerticalPanel();
	    final SimplePanel facultiesPanel = new SimplePanel();
	    upPanel.add(leftUpPanel);
	    upPanel.add(rightUpPanel);
	    rootPanel.add(mainPanel);
	    leftUpPanel.add(facultiesPanel);
	    
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
        leftUpPanel.add(labelSurname);
        labelSurname.setSize("180px", "18px");
        
        final TextBox SurnametextBox = new TextBox();
        leftUpPanel.add(SurnametextBox);
        SurnametextBox.setSize("170px", "22px");
        
        Label labelName = new Label("Имя");
        labelName.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        leftUpPanel.add(labelName);
        labelName.setSize("180px", "18px");
        
        final TextBox NametextBox = new TextBox();
        leftUpPanel.add(NametextBox);
        NametextBox.setSize("170px", "22px");
        
        Label labelBirthday = new Label("Дата рождения");
        labelBirthday.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        leftUpPanel.add(labelBirthday);
        labelBirthday.setSize("180px", "18px");
        
        DateBox dateBox = new DateBox();
		dateBox.setFormat(new DateBox.DefaultFormat(DateTimeFormat
				.getFormat("dd.MM.yyyy")));
        leftUpPanel.add(dateBox);
        
        Label labelAddress = new Label("Домашний адрес");
        labelAddress.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rightUpPanel.add(labelAddress);
        labelAddress.setSize("186px", "18px");
        
        final TextBox AddresstextBox = new TextBox();
        rightUpPanel.add(AddresstextBox);
        AddresstextBox.setSize("170px", "22px");
        
        final Label Phonelabel = new Label("Мобильный телефон");
        Phonelabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rightUpPanel.add(Phonelabel);
        Phonelabel.setSize("186px", "18px");
        
        final TextBox PhonetextBox = new TextBox();
        rightUpPanel.add(PhonetextBox);
        PhonetextBox.setSize("170px", "22px");
        
        Label labelEmail = new Label("Email");
        labelEmail.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rightUpPanel.add(labelEmail);
        labelEmail.setSize("186px", "18px");
        
        TextBox EmailtextBox = new TextBox();
        rightUpPanel.add(EmailtextBox);
        EmailtextBox.setSize("170px", "22px");
        
        Label labelSkype = new Label("Skype");
        rightUpPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rightUpPanel.add(labelSkype);
        labelSkype.setSize("186px", "18px");
        
        TextBox SkypetextBox = new TextBox();
        rightUpPanel.add(SkypetextBox);
        SkypetextBox.setSize("170px", "22px");
        
        Button validation = new Button("Проверить");
        validation.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {
        	public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        		if (NametextBox.getText().length()>50 || NametextBox.getText().length() == 0)
        			NametextBox.setStyleName("invalid");
        		else 
        			NametextBox.setStyleName("valid");
        		if (SurnametextBox.getText().length()>50 || SurnametextBox.getText().length() == 0)
        			SurnametextBox.setStyleName("invalid");
        		else 
        			SurnametextBox.setStyleName("valid");
        		if (AddresstextBox.getText().length()>100 || AddresstextBox.getText().length() == 0)
        			AddresstextBox.setStyleName("invalid");
        		else 
        			AddresstextBox.setStyleName("valid");
        		if (PhonetextBox.getText().length()>13 || PhonetextBox.getText().length()==0 
        				|| PhonetextBox.getText().toCharArray()[0]!='+')
        			PhonetextBox.setStyleName("invalid");
        		else 
        			PhonetextBox.setStyleName("valid");
        	}
        });
        rightUpPanel.add(validation);
        
        Label goalLabel = new Label("Цель");
        goalLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        mainPanel.add(goalLabel);
        goalLabel.setSize("186px", "18px");
        
        ListBox goalComboBox = new ListBox();
        mainPanel.add(goalComboBox);
        goalComboBox.setSize("343px", "22px");
        
        // Заполнение возможных целей из базы
        
        goalComboBox.insertItem("Java Junior", 0);
        goalComboBox.insertItem("PHP Senior", 1);
        
        // Опыт работы и образование
        
        WorkExperienceSimplePanel expPanel = new WorkExperienceSimplePanel();
        mainPanel.add(expPanel);
        
        EducationSimplePanel eduPanel = new EducationSimplePanel();
        mainPanel.add(eduPanel);
        
        LanguageSimplePanel lanPanel = new LanguageSimplePanel();
        mainPanel.add(lanPanel);
        
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
        mainPanel.add(suppliesForm);
        
	    TextArea first = new TextArea();
	    first.setWidth("343px");
	    first.addStyleName("fixTextArea");
	    TextArea second = new TextArea();
	    second.addStyleName("fixTextArea");
	    second.setWidth("343px");
        HorizontalPanel hpFirst = new HorizontalPanel();
	    HorizontalPanel hpSecond = new HorizontalPanel();
	    Label lbHobby = new Label("Личные качества");
	    Label lbOther = new Label("Прочее");
	    mainPanel.add(lbHobby);
	    mainPanel.add(first);
	    mainPanel.add(lbOther);
	    mainPanel.add(second);
        
	    CheckBox cb = new CheckBox();
	    cb.setText("Даю право на обработку и хранение личной информации администрации ресурса");
	    cb.setWidth("343px");
	    mainPanel.add(cb);
	    
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