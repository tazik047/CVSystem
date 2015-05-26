package ua.nure.pi.client;
import java.util.Collection;

import ua.nure.pi.entity.CV;
import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;

import com.smartgwt.client.types.Alignment;  
import com.smartgwt.client.widgets.Label;  
  
import com.google.gwt.user.client.ui.SimplePanel;

public class PrintSmallSimplePanel extends SimplePanel{
	private CV cv;
	
	public CV getCV(){
		return cv;
	}
	
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
	
		setStyleName("cv_block");
        getElement().setId("cv_block"+cv.getCvsId());
        getElement().setInnerHTML("<div class='image'></div><div class='id'>№&nbsp;"+cv.getCvsId()+"</div><div class='purpose' title='"+purpose+"'>" + purpose+"</div>");        
}

private native void insert(String i,  String p)/*-{
	$wnd.$('#cv_block'+i).html('<div class="id">'+i+'</div><div class="purpose">' + p+'</div>');
}-*/;

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

