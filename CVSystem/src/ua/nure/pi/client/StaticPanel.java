package ua.nure.pi.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;

public class StaticPanel extends SimplePanel {
	
	protected HTML html;
	
	private ua.nure.pi.client.LoadListener loading;
	
	public StaticPanel() {
		html = new HTML();
		add(html);
	}
	
	protected void setText(String htm){
		html.setHTML(htm);
	}
/*
	public ua.nure.pi.client.LoadListener getLoading() {
		return loading;
	}*/

	public void setLoading(ua.nure.pi.client.LoadListener loading) {
		this.loading = loading;
	}
	
	 protected void fireLoadEvent(LoadingSimplePanel p){
          LoadEvent ev = new LoadEvent(p);
          loading.Load(ev);
     }
}
