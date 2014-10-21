package ua.nure.pi.client;

import ua.nure.pi.entity.Student;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VerifyDialogBox extends DialogBox{
	public VerifyDialogBox(Student st) {
		try{
			clear();
			setAnimationEnabled(true);
			VerticalPanel root = new VerticalPanel();
			root.add(new PrintSimplePanel(st));
			//final DialogBox db =new DialogBox();
			Window.alert("end create cv");
			Button close = new Button("Вернуться назад");
			setText("Предпросмотр составленного резюме");
			addStyleName("preViewDialogBox");
			setWidth("600px");
			root.add(close);
			close.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					hide();
				}
			});
			add(root);
    		center();
    		Window.alert("the end");
    	}
    	catch(Exception e){
    		Window.alert(e.getMessage());
    	}
	}
}
