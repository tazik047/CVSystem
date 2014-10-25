package ua.nure.pi.client;


import java.util.ArrayList;
import java.util.Collection;

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
  
public class PrintSimplePanel extends SimplePanel {  
  
	private Student student;
	public PrintSimplePanel(Student student, Collection<ProgramLanguage> newLanguage2)
	{
		this.student = student;
		educations = student.getCv().getEducations();
		languages = student.getCv().getLanguages();
		programLanguage = student.getCv().getProgramLanguages();
		newLanguage = newLanguage2;
		sertificates = student.getCv().getSertificates();
		workExps = student.getCv().getWorkExps();
		qualities = student.getCv().getQualities();
		others = student.getCv().getOthers();
		purpose = student.getCv().getPurpose().getTitle();
		onModuleLoad();
	}
	
	public PrintSimplePanel(Student student){
		this(student, new ArrayList<ProgramLanguage>());
	}
	private Collection<Education> educations;
	
	private Collection<Language> languages;
	
	private Collection<ProgramLanguage> programLanguage;
	
	private Collection<ProgramLanguage> newLanguage;
	
	private Collection<Sertificate> sertificates;
	
	private Collection<WorkExp> workExps;
	
	private String qualities;
	
	private String others;
	
	private String purpose;
	
	
    
	
	
	
    public void onModuleLoad() {  
  
    	
    	
        HLayout layout = new HLayout();
        layout.setWidth(600);  
        layout.setHeight(800);  
        VLayout vLayout2 = new VLayout();  
        vLayout2.setWidth("35%");  
        vLayout2.setHeight("80");  
        
        
        VLayout vLayout = new VLayout();  
        vLayout.setWidth("100%");
        vLayout.setHeight("90");
        //vLayout2.addMember(new BlueBox((Integer) null, "50%", "")); по идее так будет работать 
        vLayout2.addMember(new BlueBox((Integer) null, "25%", student.getAddress()));
        vLayout2.addMember(new BlueBox((Integer) null, "25%", student.getSkype()==null?"":student.getSkype()));  
        vLayout2.addMember(new BlueBox((Integer) null, "25%", student.getPhone()));  
        vLayout2.addMember(new BlueBox((Integer) null, "25%", student.getEmail()));
        
        HLayout hLayout = new HLayout();  
        vLayout.addMember(hLayout);  
        hLayout.setHeight(80);
        String patr= student.getPatronymic()==null?"":student.getPatronymic();
        BlueBox FIO = new BlueBox("30%", "90", "<h3 align=\"center\">" +  student.getSurname() +" " + student.getFirstname() +" "+ patr + "</h3><br>"+ student.getGroup().getTitle());
        FIO.setStyleName("FIO");
        hLayout.addMember(FIO);  
        hLayout.addMember(new BlueBox("40%", null, ""));  
        hLayout.addMember(vLayout2);
        vLayout.addMember(new BlueBox((String) null, "30", "Желаемая должность", true));
        HLayout hLayout16 = new HLayout();  
        vLayout.addMember(hLayout16);  
        hLayout16.setHeight100();
        hLayout16.addMember(new BlueBox("40%", null, ""));  
        hLayout16.addMember(new BlueBox("10px", null, "")); 
        hLayout16.addMember(new BlueBox("*", null, purpose));  
        if(workExps != null || workExps.size()!=0){
        vLayout.addMember(new BlueBox(null, "30", "Опыт работы", true));
        
	        for(WorkExp I : workExps){
	        HLayout hLayout2 = new HLayout();  
	        vLayout.addMember(hLayout2);  
	        hLayout2.setHeight100();
	        hLayout2.addMember(new BlueBox("20%", null, "")); 
	        String duration = "";
	        switch(I.getTypeOfDuration().toString())
	    	{
	        	case "WEEK": duration = "недель";
	        		break;
	        	case "MONTH": duration = "месяцев";
        		break;
	        	case "YEAR": duration = "лет";
        		break;
	    	}
	        if (I.getDuration()%10==1&&I.getDuration()!=11){
	        	switch(duration)
		    	{
		        	case "недель": duration = "недели";
		        		break;
		        	case "месяцев": duration = "месяца";
	        			break;
		        	case "лет": duration = "года";
	        			break;
		    	}
	        }
	        hLayout2.addMember(new BlueBox("20%", null, "С " + I.getStartYear() + "<br>На протяжении " + I.getDuration() + " "+ duration));
	        hLayout2.addMember(new BlueBox("10px", null, "")); 
	        hLayout2.addMember(new BlueBox("*", null, I.getRole() + ":" + I.getNameOfInstitution()));
	        }
        }
        if(educations!=null || educations.size()!=0){
        vLayout.addMember(new BlueBox((String) null, "30", "Образование", true));  
        
	        for(Education I : educations){
		        HLayout hLayout3 = new HLayout();  
		       vLayout.addMember(hLayout3);  
		        hLayout3.setHeight100();
		        hLayout3.addMember(new BlueBox("20%", null, ""));  
		        hLayout3.addMember(new BlueBox("20%", null, I.getStartYear() + " - " + I.getEndYear()));
		        hLayout3.addMember(new BlueBox("10px", null, "")); 
		        hLayout3.addMember(new BlueBox("*", null, I.getSpecialty() + ": " + I.getNameOfInstitution()));
		        }
        }
        vLayout.addMember(new BlueBox((String) null, "30", "Профессиональные навыки", true));
        HLayout hLayout4 = new HLayout();  
        vLayout.addMember(hLayout4);  
        hLayout4.setHeight100();
        hLayout4.addMember(new BlueBox("20%", null, ""));  
        hLayout4.addMember(new BlueBox("20%", null, "Технологии"));
        hLayout4.addMember(new BlueBox("10px", null, "")); 
        String lan = "";
        if(programLanguage!=null)
	        for(ProgramLanguage I : programLanguage)
	        	lan = lan + " " + I.getTitle();
        	if (newLanguage.size()!=0){
        		lan += "/nТехнологии, отсутсвующие в БД";
        		for(ProgramLanguage I : newLanguage)
        			lan = lan + " " + I.getTitle();
        	}
        hLayout4.addMember(new BlueBox("*", null, lan));
        /*
        HLayout hLayout5 = new HLayout();  
        vLayout.addMember(hLayout5);  
        hLayout5.setHeight100();
        hLayout5.addMember(new BlueBox("20%", null, ""));  
        hLayout5.addMember(new BlueBox("20%", null, "Технологии"));
        hLayout5.addMember(new BlueBox("*", null, "GWT, Win32 API, WinForms"));
        HLayout hLayout6 = new HLayout();  
        vLayout.addMember(hLayout6);  
        hLayout6.setHeight100();
        hLayout6.addMember(new BlueBox("20%", null, ""));  
        hLayout6.addMember(new BlueBox("20%", null, "Web"));
        hLayout6.addMember(new BlueBox("*", null, "HTML, CSS, Javascript"));
        HLayout hLayout7 = new HLayout();  
        vLayout.addMember(hLayout7);  
        hLayout7.setHeight100();
        hLayout7.addMember(new BlueBox("20%", null, ""));  
        hLayout7.addMember(new BlueBox("20%", null, "Базы данных"));
        hLayout7.addMember(new BlueBox("*", null, "SQL Server, MySQL, TSQL"));
        HLayout hLayout8 = new HLayout();  
        vLayout.addMember(hLayout8);  
        hLayout8.setHeight100();
        hLayout8.addMember(new BlueBox("20%", null, ""));  
        hLayout8.addMember(new BlueBox("20%", null, "Офисные программы"));
        hLayout8.addMember(new BlueBox("*", null, "MS Word, MS Power Point, MS Acces, Open Office"));
        HLayout hLayout9 = new HLayout(); 
         
        vLayout.addMember(hLayout9);  
        hLayout9.setHeight100();
        hLayout9.addMember(new BlueBox("20%", null, ""));  
        hLayout9.addMember(new BlueBox("20%", null, "Графические программы"));
        hLayout9.addMember(new BlueBox("*", null, "Photoshop"));
        */
        if(sertificates!=null || sertificates.size()!=0){
        vLayout.addMember(new BlueBox((String) null, "30", "Сертификаты", true));
        
	        for(Sertificate I : sertificates){
	        HLayout hLayout10 = new HLayout();  
	        vLayout.addMember(hLayout10);  
	        hLayout10.setHeight100();
	        
	        hLayout10.addMember(new BlueBox("20%", null, ""));
	        hLayout10.addMember(new BlueBox("20%", null, ""+I.getSertificateYear()));
	        hLayout10.addMember(new BlueBox("10px", null, "")); 
	        
	        hLayout10.addMember(new BlueBox("*", null, I.getSertificateName()));
	        }
        }
        vLayout.addMember(new BlueBox((String) null, "30", "Знания языков", true));
        
        if(languages!=null){
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
        vLayout.addMember(new BlueBox((String) null, "30", "Личные качества", true));
        HLayout hLayout14 = new HLayout();  
        vLayout.addMember(hLayout14);  
        hLayout14.setHeight100();
        hLayout14.addMember(new BlueBox("40%", null, "")); 
        hLayout14.addMember(new BlueBox("10px", null, "")); 
        
        hLayout14.addMember(new BlueBox("*", null,qualities));
        vLayout.addMember(new BlueBox((String) null, "30", "Прочее", true));
        HLayout hLayout15 = new HLayout();  
        vLayout.addMember(hLayout15);  
        hLayout15.setHeight100();
        hLayout15.addMember(new BlueBox("40%", null, ""));
        hLayout15.addMember(new BlueBox("10px", null, "")); 
        
        DateTimeFormat format = DateTimeFormat.getFormat("dd.MM.yyyy");
        hLayout15.addMember(new BlueBox("*", null, "Дата рождения " + format.format(student.getDateOfBirth())+"<br>"+ others));
        
 
        
        
              layout.addMember(vLayout);  
        layout.markForRedraw();
        add(layout);  
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