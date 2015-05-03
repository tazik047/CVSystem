package ua.nure.pi.client;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.form.DynamicForm;

public class StatisticSimplePanel extends LoadingSimplePanel {

	MainServiceAsync mainService;
	
	DynamicForm mainForm;
	
	

	public StatisticSimplePanel(MainServiceAsync main) {
		mainService = main;
		isReady = true;
		onModuleLoad();
	}

	private void onModuleLoad() {
	    VerticalPanel rootPanel = new VerticalPanel();
	    HorizontalPanel picturePanel1 = new HorizontalPanel();
	    picturePanel1.addStyleName("pic1");
	    
	    
	    HorizontalPanel picturePanel2 = new HorizontalPanel();
	    picturePanel2.addStyleName("pic2");

	    rootPanel.add(picturePanel1);
	    rootPanel.add(picturePanel2);

        setWidget(rootPanel);

	}
}
