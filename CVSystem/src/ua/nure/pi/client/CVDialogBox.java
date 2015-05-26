package ua.nure.pi.client;

import java.util.Collection;

import ua.nure.pi.entity.CV;
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

public class CVDialogBox extends DialogBox {
	private CV cv;
	MainServiceAsync main;
	Button accept;

	public CVDialogBox(CV cv, MainServiceAsync registrationService) {
		try {
			this.cv = cv;
			main = registrationService;
			clear();
			setAnimationEnabled(true);
			VerticalPanel root = new VerticalPanel();
			HorizontalPanel bts = new HorizontalPanel();
			bts.setWidth("100%");
			root.add(new PrintSimplePanel(cv));
			root.add(bts);
			final Button close = new Button("Вернуться назад");
			setText("Резюме");
			addStyleName("preViewDialogBox");
			setWidth("600px");
			accept = new Button("Добавить в закладки");
			bts.add(accept);
			bts.add(close);
			bts.setCellHorizontalAlignment(accept,
					HasHorizontalAlignment.ALIGN_LEFT);
			bts.setCellHorizontalAlignment(close,
					HasHorizontalAlignment.ALIGN_RIGHT);
			final DialogBox parent = this;
			accept.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					MyDialogBox db = new MyDialogBox(parent, close);
					// db.center();

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
		} catch (Exception e) {
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
			} else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER
					&& accept != null) {
				accept.click();
			}
			break;
		}
	}

	class MyDialogBox extends DialogBox {
		Button ok;

		public MyDialogBox(final DialogBox parent, final Button btnNewButton) {
			addStyleName("newPreViewDialogBox");
			setAnimationEnabled(true);
			setText("Подтверждение добавления");
			VerticalPanel root = new VerticalPanel();
			root.add(new HTML(
					"После добавления резюме в закладки станет доступна контактная информаци студента."));
			final Label err = new Label();
			root.add(err);
			err.addStyleName("error");
			HorizontalPanel hp = new HorizontalPanel();
			hp.setWidth("100%");
			root.add(hp);

			ok = new Button("Добавить");
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
					ok.setText("Отправка...");
					ok.setEnabled(false);
					main.addFavorite(cv.getCvsId(), new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							Window.alert("Закладка добавлена");
							ok.setText("Добавить");
							ok.setEnabled(true);
							hide();
							parent.hide();
							// btnNewButton.click();
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getLocalizedMessage());
							ok.setText("Добавить");
							ok.setEnabled(true);
						}
					});
				}
			});
			hp.add(ok);
			hp.add(cancel);
			hp.setCellHorizontalAlignment(cancel,
					HasHorizontalAlignment.ALIGN_RIGHT);
			hp.setCellHorizontalAlignment(ok, HasHorizontalAlignment.ALIGN_LEFT);
			add(root);
			center();
		}

		@Override
		protected void onPreviewNativeEvent(NativePreviewEvent event) {
			super.onPreviewNativeEvent(event);
			switch (event.getTypeInt()) {
			case Event.ONKEYDOWN:
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
					hide();
				} else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER
						&& ok != null) {
					ok.click();
				}
				break;
			}
		}
	}
}
