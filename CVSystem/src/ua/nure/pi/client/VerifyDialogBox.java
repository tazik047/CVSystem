package ua.nure.pi.client;

import java.util.Collection;

import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Student;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VerifyDialogBox extends DialogBox{
	private Student student;
	MainServiceAsync main;
	Boolean newPurp;
	Button accept;
	Collection<ProgramLanguage> newPL;
	
	public VerifyDialogBox(Student st, MainServiceAsync registrationService, Boolean newPurp, Collection<ProgramLanguage> newPL) {
		try{
			student = st;
			main = registrationService;
			this.newPL=newPL;
			this.newPurp=newPurp;
			clear();
			setAnimationEnabled(true);
			VerticalPanel root = new VerticalPanel();
			HorizontalPanel bts = new HorizontalPanel();
			bts.setWidth("100%");
			root.add(new PrintSimplePanel(st, newPL));
			root.add(bts);
			Button close = new Button("Вернуться назад");
			setText("Предпросмотр составленного резюме");
			addStyleName("preViewDialogBox");
			setWidth("600px");
			accept = new Button("Подтвердить");
			bts.add(accept);
			bts.add(close);
			bts.setCellHorizontalAlignment(accept, HasHorizontalAlignment.ALIGN_LEFT);
			bts.setCellHorizontalAlignment(close, HasHorizontalAlignment.ALIGN_RIGHT);
			final DialogBox parent = this;
			accept.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					MyDialogBox db = new MyDialogBox(parent);
					db.center();
					
				}
			});
			
			close.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					hide();
				}
			});
			add(root);
    		center();
    	}
    	catch(Exception e){
    		Window.alert(e.getMessage());
    	}
	}
	
	@Override
    protected void onPreviewNativeEvent(NativePreviewEvent event) {
        super.onPreviewNativeEvent(event);
        switch (event.getTypeInt()) {
            case Event.ONKEYDOWN:
                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
                    hide();
                }
                else if(event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER 
                		&& accept != null){
                	accept.click();
                }
                break;
        }
    }
	
	class MyDialogBox extends DialogBox{
		Button ok;
		
		public MyDialogBox(final DialogBox parent) {
			addStyleName("newPreViewDialogBox");
			setAnimationEnabled(true);
			setText("Подтверждение сохранения сформированного резюме");
			VerticalPanel root = new VerticalPanel();
			root.add(new HTML("После сохранения резюме будет невозможно его дальнейшее редактирование<br/>"
					+ "Внимательно проверте все заполненные поля!<br/>"
					+ "Для продолжения позовите преподавателя, чтобы он ввел ключ подтверждения:"));
			final PasswordTextBox ptb = new PasswordTextBox();
			root.add(ptb);
			final Label err = new Label();
			root.add(err);
			err.addStyleName("error");
			HorizontalPanel hp = new HorizontalPanel();
			hp.setWidth("100%");
			root.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			root.add(hp);
			ok = new Button("Отправить");
			Button cancel = new Button("Отмена");			
			cancel.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					hide();
				}
			});
			
			ok.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					if(ptb.getValue()!=null && ptb.getValue()!=""){
						ok.setText("Отправка...");
						ok.setEnabled(false);
						main.checkPass(ptb.getValue(), new AsyncCallback<Boolean>() {
							
							@Override
							public void onSuccess(Boolean result) {
								if(result){
									main.sendStudent(student, newPurp, newPL, new AsyncCallback<Void>() {
										
										@Override
										public void onSuccess(Void result) {
											Window.alert("Резюме сохранено");
											ok.setText("Отправить");
											ok.setEnabled(true);
											ptb.setText("");
											hide();
											parent.hide();
											
										}
										
										@Override
										public void onFailure(Throwable caught) {
											Window.alert(caught.getLocalizedMessage());
											ok.setText("Отправить");
											ok.setEnabled(true);
										}
									});
								}
								else{
									err.setText("Неправильный ключ подтверждения");
								}
							}
							
							@Override
							public void onFailure(Throwable caught) {
								Window.alert(caught.getLocalizedMessage());
								
							}
						});

					}
				}
			});
			hp.add(ok);
			hp.add(cancel);
			hp.setCellHorizontalAlignment(cancel, HasHorizontalAlignment.ALIGN_RIGHT);
			hp.setCellHorizontalAlignment(ok, HasHorizontalAlignment.ALIGN_LEFT);
			add(root);
		}
		
		@Override
	    protected void onPreviewNativeEvent(NativePreviewEvent event) {
	        super.onPreviewNativeEvent(event);
	        switch (event.getTypeInt()) {
	            case Event.ONKEYDOWN:
	                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
	                    hide();
	                }
	                else if(event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER 
	                		&& ok != null){
	                	ok.click();
	                }
	                break;
	        }
	    }
	}
}
