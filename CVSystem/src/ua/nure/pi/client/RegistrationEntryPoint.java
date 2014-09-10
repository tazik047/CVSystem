package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TabPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RegistrationEntryPoint implements EntryPoint {
	private final RegistrationServiceAsync RegistrationService = GWT
			.create(RegistrationService.class);


	public void onModuleLoad() {

	    // Add the nameField and sendButton to the RootPanel
	    // Use RootPanel.get() to get the entire body element
	    RootPanel rootPanel = RootPanel.get("nameFieldContainer");
	    
	    TabPanel tabPanel = new TabPanel();
	    rootPanel.add(tabPanel, 21, 10);
	    tabPanel.setSize("523px", "459px");
	    
	    AbsolutePanel absolutePanel = new AbsolutePanel();
	    tabPanel.add(absolutePanel, "Шаг 1", false);
	    absolutePanel.setSize("162px", "342px");
	    
	    AbsolutePanel absolutePanel_1 = new AbsolutePanel();
	    tabPanel.add(absolutePanel_1, "Шаг 2", false);
	    absolutePanel_1.setSize("5cm", "3cm");
	    
	    AbsolutePanel absolutePanel_2 = new AbsolutePanel();
	    tabPanel.add(absolutePanel_2, "Шаг 3", false);
	    absolutePanel_2.setSize("5cm", "338px");


	}
}
