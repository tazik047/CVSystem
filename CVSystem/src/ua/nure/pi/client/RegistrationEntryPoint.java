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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
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
import com.smartgwt.client.widgets.form.fields.MultiComboBoxItem;
import com.smartgwt.client.widgets.form.fields.PickTreeItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tree.Tree;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RegistrationEntryPoint implements EntryPoint {
	private final RegistrationServiceAsync RegistrationService = GWT
			.create(RegistrationService.class);

    ArrayList<Faculty> faculties;

	public void onModuleLoad() {

	    final RootPanel rootPanel = RootPanel.get("content");
	    
	    // Факультеты и группы
	    /*
	    TreeNode children[] = new TreeNode[3];
	    children[0] = new TreeNode();
	    children[0].setTitle("КН");
	    children[0].setChildren(new TreeNode[] {
	    new TreeNode("ПИ-13-1"),
	    new TreeNode("ПИ-13-2")
	    });
	    children[1] = new TreeNode();
	    children[1].setTitle("ПММ");
	    children[1].setChildren(new TreeNode[] {
	    new TreeNode("ПМ-13-1"),
	    new TreeNode("СА-13-1")
	    });
	    children[2] = new TreeNode();
	    children[2].setTitle("КИУ");
	    children[2].setChildren(new TreeNode[] {
	    new TreeNode("КИ-13-1"),
	    new TreeNode("КИ-13-2"),
	    new TreeNode("КИ-13-3")
	    });
	    */
	    
	    RegistrationService.getFaculties(new AsyncCallback<Collection<Faculty>>() {
            public void onFailure(Throwable caught) {
              Window.alert("Не удалось получить список факультетов");
            }

			@Override
			public void onSuccess(Collection<Faculty> result) {
				faculties = new ArrayList<Faculty>(result);		
				FillTree(faculties, rootPanel);
			}
          });
        
        // Персональная информация
        
        Label labelSurname = new Label("Фамилия");
        labelSurname.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(labelSurname, 84, 93);
        labelSurname.setSize("180px", "18px");
        
        final TextBox SurnametextBox = new TextBox();
        rootPanel.add(SurnametextBox, 84, 117);
        SurnametextBox.setSize("170px", "22px");
        
        Label labelName = new Label("Имя");
        labelName.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(labelName, 84, 157);
        labelName.setSize("180px", "18px");
        
        final TextBox NametextBox = new TextBox();
        rootPanel.add(NametextBox, 84, 181);
        NametextBox.setSize("170px", "22px");
        
        Label labelAddress = new Label("Домашний адрес");
        labelAddress.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(labelAddress, 498, 93);
        labelAddress.setSize("186px", "18px");
        
        final TextBox AddresstextBox = new TextBox();
        rootPanel.add(AddresstextBox, 498, 130);
        AddresstextBox.setSize("170px", "22px");
        
        final Label Phonelabel = new Label("Мобильный телефон");
        Phonelabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(Phonelabel, 498, 188);
        Phonelabel.setSize("186px", "18px");
        
        final TextBox PhonetextBox = new TextBox();
        rootPanel.add(PhonetextBox, 498, 226);
        PhonetextBox.setSize("170px", "22px");
        
        Label labelEmail = new Label("Email");
        labelEmail.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(labelEmail, 367, 289);
        labelEmail.setSize("186px", "18px");
        
        TextBox EmailtextBox = new TextBox();
        rootPanel.add(EmailtextBox, 367, 326);
        EmailtextBox.setSize("170px", "22px");
        
        Label labelSkype = new Label("Skype");
        labelSkype.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(labelSkype, 576, 289);
        labelSkype.setSize("186px", "18px");
        
        TextBox SkypetextBox = new TextBox();
        rootPanel.add(SkypetextBox, 576, 326);
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
        		if (PhonetextBox.getText().length()>13 || PhonetextBox.getText().toCharArray()[0]!='+')
        			PhonetextBox.setStyleName("invalid");
        		else 
        			PhonetextBox.setStyleName("valid");
        	}
        });
        rootPanel.add(validation, 669, 423);
        
        Label goalLabel = new Label("Цель");
        goalLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(goalLabel, 84, 507);
        goalLabel.setSize("186px", "18px");
        
        ListBox goalComboBox = new ListBox();
        rootPanel.add(goalComboBox, 370, 507);
        goalComboBox.setSize("343px", "22px");
        
        // Заполнение возможных целей из базы
        
        goalComboBox.insertItem("Java Junior", 0);
        goalComboBox.insertItem("PHP Senior", 1);
        
        // Опыт работы и образование
        
        Label labelExperience = new Label("Опыт работы");
        labelExperience.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(labelExperience, 84, 565);
        labelExperience.setSize("186px", "18px");
        
        Label labelStart = new Label("Год");
        labelStart.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(labelStart, 367, 565);
        labelStart.setSize("31px", "18px");
        
        TextBox StarttextBox = new TextBox();
        rootPanel.add(StarttextBox, 409, 565);
        StarttextBox.setSize("62px", "6px");
        
        Label labelDuration = new Label("Длительность");
        labelDuration.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(labelDuration, 500, 565);
        labelDuration.setSize("80px", "18px");
        
        TextBox EndTextBox = new TextBox();
        rootPanel.add(EndTextBox, 612, 565);
        EndTextBox.setSize("62px", "6px");
        
        TextBox textBox = new TextBox();
        rootPanel.add(textBox, 367, 606);
        textBox.setSize("336px", "18px");
        
        Label labelBirthday = new Label("Дата рождения");
        labelBirthday.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(labelBirthday, 84, 234);
        labelBirthday.setSize("180px", "18px");
        
        DatePicker datePicker = new DatePicker();
        datePicker.setYearArrowsVisible(true);
        rootPanel.add(datePicker, 84, 262);
        
        TextBox textBox_3 = new TextBox();
        rootPanel.add(textBox_3, 367, 689);
        textBox_3.setSize("336px", "18px");
        
        Label label = new Label("Год");
        label.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(label, 367, 653);
        label.setSize("31px", "18px");
        
        TextBox textBox_1 = new TextBox();
        rootPanel.add(textBox_1, 409, 653);
        textBox_1.setSize("62px", "6px");
        
        Label label_1 = new Label("Длительность");
        label_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        rootPanel.add(label_1, 500, 653);
        label_1.setSize("80px", "18px");
        
        TextBox textBox_2 = new TextBox();
        rootPanel.add(textBox_2, 612, 653);
        textBox_2.setSize("62px", "6px");
        
        // Знание языков программирования и технологий
                
        final MultiComboBoxLayoutStyle initialLayoutStyle = MultiComboBoxLayoutStyle.FLOW;  
        final MultiComboBoxItem languages = new MultiComboBoxItem("languages", "Языки");
        
        LinkedHashMap<Long, String> lhm = new LinkedHashMap<>();
        lhm.put((long) 1, "Java");
        lhm.put((long) 2, "Haskell");
        lhm.put((long) 3, "Python");
        languages.setValueMap(lhm);
        languages.setDisplayField("itemName");  
        languages.setValueField("SKU");  
        languages.setAutoFetchData(true);  
        languages.setLayoutStyle(initialLayoutStyle);  
        languages.setAddUnknownValues(true);    
      
        final DynamicForm suppliesForm = new DynamicForm();  
        suppliesForm.setWidth100();  
        suppliesForm.setNumCols(1);  
        suppliesForm.setTitleOrientation(TitleOrientation.TOP);  
        suppliesForm.setItems(languages);  
  
        rootPanel.add(suppliesForm, 118, 773);
    }  
	
	public void FillTree(ArrayList<Faculty> faculties, RootPanel rootPanel)
	{
		TreeNode children[] = new TreeNode[faculties.size()];
	    for (int i = 0; i < faculties.size(); i++) {
		    children[i] = new TreeNode();
		    Faculty current = faculties.get(i);
		    children[i].setTitle(current.getTitle());
		    TreeNode[] tn = new TreeNode[current.getGroups().size()];
		    int j = 0;
		    for (Group g : current.getGroups()) {
		    	tn[j] = new TreeNode(g.getTitle());
		    	j++;
		    	Window.alert(g.getTitle());
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

	    DataSource ds = new DataSource();
	    PickTreeItem pickCategory = new PickTreeItem();
	    pickCategory.setTitle("Category");
	    pickCategory.setName("category");
	    pickCategory.setDataSource(ds); 
	    pickCategory.setEmptyMenuMessage("No Sub Categories");
	    pickCategory.setCanSelectParentItems(true); 

	    form.setFields(pickDepartment);
	    form.draw(); 
	    
        rootPanel.add(form, 84, 55);

	}
}
