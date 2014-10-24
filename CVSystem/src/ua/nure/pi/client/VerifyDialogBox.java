package ua.nure.pi.client;

import java.util.Collection;

import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Student;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
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
			root.add(new PrintSimplePanel(st));
			root.add(bts);
			Button close = new Button("Вернуться назад");
			setText("Предпросмотр составленного резюме");
			addStyleName("preViewDialogBox");
			setWidth("600px");
			Button accept = new Button("Подтвердить");
			bts.add(accept);
			bts.add(close);
			bts.setCellHorizontalAlignment(accept, HasHorizontalAlignment.ALIGN_LEFT);
			bts.setCellHorizontalAlignment(close, HasHorizontalAlignment.ALIGN_RIGHT);
			
			accept.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					MyDialogBox db = new MyDialogBox();
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
	
	class MyDialogBox extends DialogBox{
		public MyDialogBox() {
			addStyleName("newPreViewDialogBox");
			setAnimationEnabled(true);
			setText("Подтеврждение сохранения сформированного резюме");
			VerticalPanel root = new VerticalPanel();
			root.add(new HTML("После сохранения резюме будет невозможно его дальнейшее редактирование<br/>"
					+ "Внимательно проверте все заполненые поля!<br/>"
					+ "Для продолжения позовить преподавтеля, чтобы он ввел ключ подтверждения:"));
			final PasswordTextBox ptb = new PasswordTextBox();
			root.add(ptb);
			final Label err = new Label();
			root.add(err);
			HorizontalPanel hp = new HorizontalPanel();
			root.setCellHorizontalAlignment(ptb, HasHorizontalAlignment.ALIGN_CENTER);
			root.add(hp);
			Button ok = new Button("Отправить");
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
						main.checkPass(ptb.getValue(), new AsyncCallback<Boolean>() {
							
							@Override
							public void onSuccess(Boolean result) {
								if(result){
									main.sendStudent(student, newPurp, newPL, new AsyncCallback<Void>() {
										
										@Override
										public void onSuccess(Void result) {
											Window.alert("Резюме сохранено");
											
										}
										
										@Override
										public void onFailure(Throwable caught) {
											Window.alert(caught.getLocalizedMessage());
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
			add(root);
		}
	}
}
