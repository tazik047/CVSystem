package ua.nure.pi.client;

public class StatisticSimplePanel extends LoadingSimplePanel {

	MainServiceAsync mainService;

	public StatisticSimplePanel(MainServiceAsync main) {
		mainService = main;
		fireLoadEvent(this);
		isReady = true;
		onModuleLoad();
	}

	private void onModuleLoad() {
		// TODO Auto-generated method stub

	}
}
