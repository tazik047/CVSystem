package ua.nure.pi.client;

public class SearchSimplePanel extends LoadingSimplePanel {

	MainServiceAsync mainService;
	
	public SearchSimplePanel(MainServiceAsync mainService) {
		this.mainService = mainService;
		isReady = true;
		//TODO: создать поиск
	}
	
}
