package ua.nure.pi.client;


import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.entity.Student;

import com.smartgwt.client.types.Alignment;  
import com.smartgwt.client.widgets.Canvas;  
import com.smartgwt.client.widgets.Label;  
import com.smartgwt.client.widgets.layout.HLayout;  
import com.smartgwt.client.widgets.layout.VLayout;  
  
import com.google.gwt.core.client.EntryPoint;  
import com.google.gwt.user.client.ui.SimplePanel;
  
public class PrintSimplePanel extends SimplePanel {  
  
	private Student student;
	public PrintSimplePanel(Student student)
	{
		this.student = student;
	}
    public void onModuleLoad() {  
  
        HLayout layout = new HLayout();
        layout.setWidth100();  
        layout.setHeight100();  
        VLayout vLayout2 = new VLayout();  
        vLayout2.setWidth("35%");  
        vLayout2.setHeight("80");  
        
        
        VLayout vLayout = new VLayout();  
        vLayout.setWidth("100%");
        vLayout.setHeight("90");
        vLayout2.addMember(new BlueBox((Integer) null, "50%", ""));  
        vLayout2.addMember(new BlueBox((Integer) null, "25%", student.getSkype()));  
        vLayout2.addMember(new BlueBox((Integer) null, "25%", student.getPhone()));  
        vLayout2.addMember(new BlueBox((Integer) null, "25%", student.getEmail()));
        
        HLayout hLayout = new HLayout();  
        vLayout.addMember(hLayout);  
        hLayout.setHeight(80);
        BlueBox FIO = new BlueBox("30%", "90", student.getFirstname()+student.getSurname());
        FIO.setStyleName("FIO");
        hLayout.addMember(FIO);  
        hLayout.addMember(new BlueBox("40%", null, ""));  
        hLayout.addMember(vLayout2);
        vLayout.addMember(new BlueBox(null, "30", "Опыт работы", true));
        HLayout hLayout2 = new HLayout();  
        vLayout.addMember(hLayout2);  
        hLayout2.setHeight100();
        hLayout2.addMember(new BlueBox("20%", null, ""));  
        hLayout2.addMember(new BlueBox("20%", null, "2010-2014"));
        hLayout2.addMember(new BlueBox("*", null, "nix solution 2 year jubior some another insormation and more another information"));
        
        vLayout.addMember(new BlueBox((String) null, "30", "Образование", true));  
        HLayout hLayout3 = new HLayout();  
        vLayout.addMember(hLayout3);  
        hLayout3.setHeight100();
        hLayout3.addMember(new BlueBox("20%", null, ""));  
        hLayout3.addMember(new BlueBox("20%", null, "2010-2014"));
        hLayout3.addMember(new BlueBox("*", null, "ХНУРЭ"));
        vLayout.addMember(new BlueBox((String) null, "30", "Профессиональные навыки", true));
        HLayout hLayout4 = new HLayout();  
        vLayout.addMember(hLayout4);  
        hLayout4.setHeight100();
        hLayout4.addMember(new BlueBox("20%", null, ""));  
        hLayout4.addMember(new BlueBox("20%", null, "Языки программирования"));
        hLayout4.addMember(new BlueBox("*", null, "Java, Haskell, C#"));
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
        vLayout.addMember(new BlueBox((String) null, "30", "Сертификаты", true));
        HLayout hLayout10 = new HLayout();  
        vLayout.addMember(hLayout10);  
        hLayout10.setHeight100();
        hLayout10.addMember(new BlueBox("40%", null, ""));  
        hLayout10.addMember(new BlueBox("*", null, "Где выдан, почему выдан, какой компанией, другие данные, нужно больше текста для тестирования, отфонарные слова, день радио, спааааааать, nix"));
        vLayout.addMember(new BlueBox((String) null, "30", "Знания языков", true));
        HLayout hLayout11 = new HLayout();  
        vLayout.addMember(hLayout11);  
        hLayout11.setHeight100();
        hLayout11.addMember(new BlueBox("40%", null, ""));  
        hLayout11.addMember(new BlueBox("*", null, "-русский - родной"));
        
        HLayout hLayout12 = new HLayout();  
        vLayout.addMember(hLayout12);  
        hLayout12.setHeight100();
        hLayout12.addMember(new BlueBox("40%", null, ""));  
        hLayout12.addMember(new BlueBox("*", null, "-украинский - высокий уровень"));
        
        HLayout hLayout13 = new HLayout();  
        vLayout.addMember(hLayout13);  
        hLayout13.setHeight100();
        hLayout13.addMember(new BlueBox("40%", null, ""));  
        hLayout13.addMember(new BlueBox("*", null, "-английский - средний уровень"));
        
        vLayout.addMember(new BlueBox((String) null, "30", "Личные качества", true));

        HLayout hLayout14 = new HLayout();  
        vLayout.addMember(hLayout14);  
        hLayout14.setHeight100();
        hLayout14.addMember(new BlueBox("40%", null, ""));  
        hLayout14.addMember(new BlueBox("*", null, "Аналитический, творческий склад ума, способность работе как в команде, так и одному, множество другой личной информации"));
        vLayout.addMember(new BlueBox((String) null, "30", "Прочее", true));
        HLayout hLayout15 = new HLayout();  
        vLayout.addMember(hLayout15);  
        hLayout15.setHeight100();
        hLayout15.addMember(new BlueBox("40%", null, ""));  
        hLayout15.addMember(new BlueBox("*", null, "год рождения-1994, не женать, не курю, хобби-баскетбол"));
        
        
              layout.addMember(vLayout);  
        
        layout.draw();  
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