package ua.nure.pi.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class UIManager {
	SimplePanel current;
	RootPanel rootPanel;
	
	public UIManager(RootPanel root){
		rootPanel = root;
	}
	
	public UIManager(RootPanel root, SimplePanel simple){
		this(root);
		root.clear(true);
		current=simple;
	}
	
	public boolean removePanel(){
		if(current!=null && rootPanel.remove(current)) {
			current = null;
			rootPanel.clear(true);
			return true;
		}
		return false;
	}
	
	public boolean setPanel(SimplePanel simp){
		if(current==null){
			rootPanel.clear(true);
			rootPanel.add(simp);
			current=simp;
			return true;
		}
		else if(simp.getClass().toString().equals(current.getClass().toString()))
			return false;
		else{
			removePanel();
			current = simp;
			rootPanel.add(current);
			return true;
		}
	}

	public RootPanel getPanel() {
		return rootPanel;
	}
}