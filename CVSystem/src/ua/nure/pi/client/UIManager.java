package ua.nure.pi.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class UIManager {
	SimplePanel current;
	RootPanel rootPanel;
	Image loadImg;
	LoadListener event;
	
	public UIManager(RootPanel root){
		rootPanel = root;
		rootPanel.setWidth("70%");
		loadImg = new Image("img/load.gif");
		event = new LoadListener() {
			
			@Override
			public void Load(LoadEvent e) {
				afterLoad(e.getPanel());
			}
		};
	}
	
	protected void afterLoad(LoadingSimplePanel panel) {
		loadImg.removeFromParent();
		rootPanel.removeStyleName("loading");
		rootPanel.add(panel);
		current=panel;
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
		else if(isSetted(simp)){
			return false;
		}
		else{
			removePanel();
			current = simp;
			rootPanel.add(current);
			return true;
		}
	}
	
	public boolean setPanel(LoadingSimplePanel simp){
		
		if(current==null){
			simp.setLoading(event);
			rootPanel.clear(true);
			rootPanel.add(loadImg);
			rootPanel.addStyleName("loading");
			if(simp.isReady())
				afterLoad(simp);
			//waitLoading(simp);
			return true;
		}
		else if(isSetted(simp)){
			return false;
		}
		else{
			simp.setLoading(event);
			removePanel();
			rootPanel.add(loadImg);
			rootPanel.addStyleName("loading");
			if(simp.isReady())
				afterLoad(simp);
			return true;
		}
	}

	public RootPanel getPanel() {
		return rootPanel;
	}
	
	public void waitLoading(LoadingSimplePanel l){
		
		while(!l.isReady());
		
	}
	
	public boolean isSetted(Object panel){
		return isSetted(panel.getClass().getName());
	}
	
	public boolean isSetted(String panel){
		if(current!=null){
			return current.getClass().getName().equals(panel);
		}
		return false;
	}
}
