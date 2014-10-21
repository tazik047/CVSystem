package ua.nure.pi.client;

import ua.nure.pi.entity.Student;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VerifyDialogBox extends DialogBox {
	public VerifyDialogBox(Student st) {
		try{
			VerticalPanel root = new VerticalPanel();
			root.add(new PrintSimplePanel(st));
			Button close = new Button("Вернуться назад");
			root.add(close);
			//эту супер костыль, подскажите как поправить(
			final VerifyDialogBox i = this;
			close.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					i.hide();
				}
			});
    		DialogBox show = new DialogBox();
    		show.add(root);
    		show.setSize("600px", "700px");
    		show.center();
    	}
    	catch(Exception e){
    		Window.alert(e.getMessage());
    	}
	}
}
