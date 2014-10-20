package ua.nure.pi.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;

public class StaticPanel extends SimplePanel {
	
	protected HTML html;
	
	public StaticPanel() {
		html = new HTML();
		add(html);
	}
	
	protected void setText(String htm){
		html.setHTML(htm);
	}
}
