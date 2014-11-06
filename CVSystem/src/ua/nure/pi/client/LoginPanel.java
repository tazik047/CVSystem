package ua.nure.pi.client;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;

public class LoginPanel extends LoadingSimplePanel {
	public LoginPanel() {
		VerticalPanel root = new VerticalPanel();
		
		add(root);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		root.add(horizontalPanel);
		
		Label lblNewLabel = new Label("Логин");
		horizontalPanel.add(lblNewLabel);
		
		TextBox textBox = new TextBox();
		horizontalPanel.add(textBox);
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		root.add(horizontalPanel_1);
		
		Label lblNewLabel_1 = new Label("Пароль");
		horizontalPanel_1.add(lblNewLabel_1);
		
		TextBox textBox_1 = new TextBox();
		horizontalPanel_1.add(textBox_1);
		
		Button btnNewButton = new Button("Войти");
		root.add(btnNewButton);
	}
	
}
