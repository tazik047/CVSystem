package ua.nure.pi.client;

import java.util.EventObject;

public class LoadEvent extends EventObject {

	LoadingSimplePanel panel;
	public LoadEvent(LoadingSimplePanel arg0) {
		super(arg0);
		panel = arg0;
		// TODO Auto-generated constructor stub
	}
	
	public LoadingSimplePanel getPanel(){
		return panel;
	}

}
