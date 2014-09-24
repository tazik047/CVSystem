package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.form.DynamicForm;

public class WorkExperienceSimplePanel extends SimplePanel{
	
	private Collection<WorkExperinceElementSimplePanel> works;
	
	public WorkExperienceSimplePanel() {
		HorizontalPanel hp = new HorizontalPanel();
		works = new ArrayList<WorkExperinceElementSimplePanel>();
		final DynamicForm form = new DynamicForm();  
        form.setIsGroup(true);  
        form.setGroupTitle("Опыт работы");
        form.setNumCols(4);
        final VerticalPanel vp = new VerticalPanel();
        form.addChild(vp);
        Button btAdd = new Button("Добавить опыт работы");
        hp.add(form);
        hp.add(btAdd);
        hp.setCellVerticalAlignment(btAdd, HasVerticalAlignment.ALIGN_BOTTOM);
        btAdd.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				addExp(vp);
				form.markForRedraw();
			}
		});
        
        setWidget(hp);
        form.markForRedraw();
        
	}
	
	private void addExp(VerticalPanel vp){
		WorkExperinceElementSimplePanel exp = new WorkExperinceElementSimplePanel();
		vp.add(exp);
		works.add(exp);
	}

}
