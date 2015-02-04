package ua.nure.pi.client;
import java.util.Collection;

import ua.nure.pi.client.PrintSimplePanel.BlueBox;
import ua.nure.pi.entity.Education;
import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Sertificate;
import ua.nure.pi.entity.Student;
import ua.nure.pi.entity.WorkExp;

import com.smartgwt.client.types.Alignment;  
import com.smartgwt.client.widgets.Canvas;  
import com.smartgwt.client.widgets.Label;  
import com.smartgwt.client.widgets.layout.HLayout;  
import com.smartgwt.client.widgets.layout.VLayout;  
  
import com.google.gwt.core.client.EntryPoint;  
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimplePanel;

public class PrintSmallSimplePanel {
	private Student student;
	
	private Collection<Language> languages;
	
	private Collection<ProgramLanguage> programLanguage;
	
	private String purpose;
	public PrintSmallSimplePanel(Student student)
	{
		this.student = student;
		languages = student.getCv().getLanguages();
		programLanguage = student.getCv().getProgramLanguages();
		purpose = student.getCv().getPurpose().getTitle();
		onModuleLoad();
	}
public void onModuleLoad() {  
  
    	
    	
        HLayout layout = new HLayout();
        layout.setWidth(600);  
       // layout.setHeight("*");  
        VLayout vLayout = new VLayout(); 
        vLayout.addMember(new BlueBox((String) null, "30", "Желаемая должность", true));
        HLayout hLayout16 = new HLayout();  
        vLayout.addMember(hLayout16);  
        hLayout16.setHeight100();
        hLayout16.addMember(new BlueBox("40%", null, ""));  
        hLayout16.addMember(new BlueBox("10px", null, "")); 
        hLayout16.addMember(new BlueBox("*", null, purpose));  
        if ((programLanguage!=null && programLanguage.size()!=0))
        {
        vLayout.addMember(new BlueBox((String) null, "30", "Профессиональные навыки", true));
        HLayout hLayout4 = new HLayout();  
        vLayout.addMember(hLayout4);  
        hLayout4.setHeight100();
        hLayout4.addMember(new BlueBox("20%", null, ""));  
        hLayout4.addMember(new BlueBox("20%", null, "Технологии"));
        hLayout4.addMember(new BlueBox("10px", null, "")); 
        String lan = "";
        String level1 = "Владение технологиями на продвинутом уровне<br>";
        String level2 = "Владение технологиями на среднем уровне<br>";
        String level3 = "Владение технологиями на уровне начинающего<br>";
        
        if(programLanguage!=null)
	        for(ProgramLanguage I : programLanguage)
	        	{
	        		if (I.getLevel()==2)
	        			level1+=I.getTitle()+',';
	        		if (I.getLevel()==1)
	        			level2+=I.getTitle()+',';
	        		if (I.getLevel()==0)
	        			level3+=I.getTitle()+',';
	        	}
        
       
        if(languages!=null && languages.size()!=0){
        vLayout.addMember(new BlueBox((String) null, "30", "Знания языков", true));
        
        	String level = "";
        for(Language I : languages){
        	
        	switch(I.getLevel())
	    	{
	        	case 0: level = "начальный";
	        		break;
	        	case 1: level = "средний";
        			break;
	        	case 2: level = "продвинутый";
        			break;
	    	}
        HLayout hLayout11 = new HLayout();  
        vLayout.addMember(hLayout11);  
        hLayout11.setHeight100();
        hLayout11.addMember(new BlueBox("40%", null, ""));  
        hLayout11.addMember(new BlueBox("10px", null, "")); 
        
        hLayout11.addMember(new BlueBox("*", null, I.getTitle() + " - " + level));
        
        }
        }
        
    }  
}
    class BlueBox extends Label {  
  
        public BlueBox(String contents) {  
            setAlign(Alignment.LEFT);

            setStyleName("textbox");
            setContents(contents);  
        }  
  

        public BlueBox(String contents, boolean bool) {  
            setAlign(Alignment.LEFT);

            setStyleName("leftbox");
            setContents(contents);
            
        }  
        
        public BlueBox(Integer width, Integer height, String contents) {  
            this(contents);  
            if (width != null) setWidth(width);
            if (height != null) setHeight(height);  
        }   
  
        public BlueBox(Integer width, String height, String contents) {  
            this(contents);  
            if (width != null) setWidth(width);  
            if (height != null) setHeight(height);  
        }  
  
        public BlueBox(String width, String height, String contents, boolean line) {  
            this(contents, true);
            if (width != null) setWidth(width);  
            if (height != null) setHeight(height); 
        }

        public BlueBox(String width, String height, String contents) {  
            this(contents);
            if (width != null) setWidth(width);  
            if (height != null) setHeight(height); 
        } 
    }
 }

