package ua.nure.pi.client;

public class ActivateCompanyPanel extends LoadingSimplePanel {
	
	MainServiceAsync mainServ;
	
	public ActivateCompanyPanel(MainServiceAsync mainService) {
		mainServ = mainService;
		isReady = false;
		
	}
}
