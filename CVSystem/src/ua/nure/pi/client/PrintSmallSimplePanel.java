package ua.nure.pi.client;
import java.util.Collection;

import ua.nure.pi.client.PrintSimplePanel.BlueBox;
import ua.nure.pi.entity.CV;
import ua.nure.pi.entity.Education;
import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Sertificate;
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

public class PrintSmallSimplePanel extends SimplePanel{
	private CV cv;
	
	private Collection<Language> languages;
	
	private Collection<ProgramLanguage> programLanguage;
	
	private String purpose;
	public PrintSmallSimplePanel(CV cv)
	{
		this.cv = cv;
		languages = cv.getLanguages();
		programLanguage = cv.getProgramLanguages();
		purpose = cv.getPurpose().getTitle();
		onModuleLoad();
	}
public void onModuleLoad() {  
  
    	
    	
        HLayout layout = new HLayout();
        layout.setWidth(150);  
       // layout.setHeight("*");  
        VLayout vLayout = new VLayout(); 
        vLayout.addMember(new BlueBox((String) null, "30", "Резюме № ", true));
        HLayout hLayout16 = new HLayout();  
        vLayout.addMember(hLayout16);  
        hLayout16.setHeight100();
        hLayout16.addMember(new BlueBox("40%", null, ""));  
        hLayout16.addMember(new BlueBox("10px", null, "")); 
        hLayout16.addMember(new BlueBox("*", null, String.valueOf(cv.getCvsId())));  
        vLayout.addMember(new BlueBox((String) null, "30", "Желаемая должность", true));
        HLayout hLayout4 = new HLayout();  
        vLayout.addMember(hLayout4);  
        hLayout4.setHeight100();
        hLayout4.addMember(new BlueBox("40%", null, ""));  
        hLayout4.addMember(new BlueBox("10px", null, ""));
        hLayout4.addMember(new BlueBox("*", null, purpose)); 
        layout.addMember(vLayout);
        setWidget(layout);
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

