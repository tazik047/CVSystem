package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.entity.WorkExp;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.form.DynamicForm;

public class WorkExperienceSimplePanel extends SimplePanel{
	
	private ArrayList<WorkExperinceElementSimplePanel> works;
	private DynamicForm form;
	private int countColor = -1;
	
	public WorkExperienceSimplePanel() {
		VerticalPanel hp = new VerticalPanel();
		hp.setWidth("100%");
		final AbsolutePanel absP = new AbsolutePanel();
		
		works = new ArrayList<WorkExperinceElementSimplePanel>();
		form = new DynamicForm();  
        form.setIsGroup(true);  
        form.setGroupTitle("Опыт работы");
        form.setNumCols(4);
        final VerticalPanel vp = new VerticalPanel();
        form.addChild(vp);
        Button btAdd = new Button("Добавить опыт работы");
        hp.add(absP);
        hp.add(btAdd);
        hp.setCellHorizontalAlignment(btAdd, HasHorizontalAlignment.ALIGN_CENTER);
        btAdd.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				addExp(vp, absP);
				form.markForRedraw();
			}
		});
        setWidget(hp);
        form.markForRedraw();
        
	}
	
	private void addExp(final VerticalPanel vp, AbsolutePanel absP){
		/*if(works.size()!=0)
			works.get(works.size()-1).addSeparator();
		else*/
		if(works.size() == 0)
			absP.add(form);
		final WorkExperinceElementSimplePanel exp = new WorkExperinceElementSimplePanel();
		countColor = (++countColor)%2;
		if(countColor == 0){
			exp.setStyleName("stylePanel1");
		}
		else
			exp.setStyleName("stylePanel2");
		/*final HorizontalPanel hp = new HorizontalPanel();
		Image imgDel = new Image("img/close.png", 0, 0, 16, 16);*/
		exp.imgDel.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				vp.remove(exp);
				works.remove(exp);
				if(works.size()==0)
					form.removeFromParent();
				form.markForRedraw();
			}
		});
		/*hp.add(exp);
		hp.add(imgDel);
		hp.setCellVerticalAlignment(imgDel, HasVerticalAlignment.ALIGN_MIDDLE);*/
		vp.add(exp);
		works.add(exp);
	}

	public Collection<WorkExp> getExp() throws IllegalArgumentException{
		Collection<WorkExp> studentWorks = new ArrayList<WorkExp>();
		for(WorkExperinceElementSimplePanel we : works)
			studentWorks.add(we.getWorkExp());
		return studentWorks;
	}
}
