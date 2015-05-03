package ua.nure.pi.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainEntryPoint implements EntryPoint {

	private final MainServiceAsync mainService = (MainServiceAsync) GWT
			.create(MainService.class);

	private UIManager uiManager;

	private static Button main;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("buttons");
		rootPanel.setWidth("650px");
		rootPanel.setStyleName("#menu-left");

		uiManager = new UIManager(RootPanel.get("content"));

		main = new Button("Главная");
		main.setStyleName("buttons");
		main.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				main.setFocus(false);
				if (!uiManager.isSetted(MainStaticPanel.class.getName()))
					uiManager.setPanel(new MainStaticPanel(mainService));
			}
		});

		rootPanel.add(main);

		final Button btnNewButton_4 = new Button("О нас");
		btnNewButton_4.setStyleName("buttons");
		rootPanel.add(btnNewButton_4);

		btnNewButton_4.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnNewButton_4.setFocus(false);
				if (!uiManager.isSetted(AboutUsStaticPanel.class.getName()))
					uiManager.setPanel(new AboutUsStaticPanel(mainService));
			}
		});

		final Button butAddCV = new Button("Добавить резюме");
		butAddCV.setStyleName("buttons");
		rootPanel.add(butAddCV);
		butAddCV.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				butAddCV.setFocus(false);
				if (!uiManager.isSetted(RegistrationSimplePanel.class.getName()))
					uiManager.setPanel(new RegistrationSimplePanel(mainService,
							main));

			}
		});

		Button btnNewButton_3 = new Button("Партнеры");
		btnNewButton_3.setStyleName("buttons");
		rootPanel.add(btnNewButton_3);

		final Button btnNewButton_1 = new Button("Списки резюме");
		btnNewButton_1.setStyleName("buttons");
		rootPanel.add(btnNewButton_1);
		btnNewButton_1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnNewButton_1.setFocus(false);
				if (!uiManager.isSetted(SearchSimplePanel.class.getName()))
					uiManager.setPanel(new SearchSimplePanel(mainService));

			}
		});

		Button btnNewButton_2 = new Button("Статистика");
		btnNewButton_2.setStyleName("buttons");
		rootPanel.add(btnNewButton_2);
		btnNewButton_2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnNewButton_1.setFocus(false);
				if (!uiManager.isSetted(StatisticSimplePanel.class.getName()))
					uiManager.setPanel(new StatisticSimplePanel(mainService));

			}
		});
		AdminManager.start(uiManager, mainService, main);

		main.click();
	}
}
