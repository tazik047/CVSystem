package ua.nure.pi.client;

import com.google.gwt.user.client.ui.Button;

public class RegistrationCompanySimplePanel extends LoadingSimplePanel {

	MainServiceAsync mainService;
	Button toMainPage;
	
	public RegistrationCompanySimplePanel(MainServiceAsync main, Button main2) {
		isReady = true;
		mainService = main;
		toMainPage = main2;
	}

}
