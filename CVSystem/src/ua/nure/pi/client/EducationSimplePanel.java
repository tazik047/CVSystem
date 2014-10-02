package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.entity.Education;
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

public class EducationSimplePanel extends SimplePanel{
	
	private ArrayList<EducationElementSimplePanel> educations;
	private DynamicForm form;
	private int countColor = -1;
	public EducationSimplePanel() {
		VerticalPanel hp = new VerticalPanel();
		hp.setWidth("100%");
		final AbsolutePanel absP = new AbsolutePanel();
		
		educations = new ArrayList<EducationElementSimplePanel>();
		form = new DynamicForm();  
        form.setIsGroup(true);  
        form.setGroupTitle("Образование");
        form.setNumCols(4);
        final VerticalPanel vp = new VerticalPanel();
        form.addChild(vp);
        Button btAdd = new Button("Добавить образование");
        hp.add(absP);
        hp.add(btAdd);
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
		/*if(educations.size()!=0)
			educations.get(educations.size()-1).addSeparator();
		else
			absP.add(form);
		final EducationElementSimplePanel exp = new EducationElementSimplePanel();
		final HorizontalPanel hp = new HorizontalPanel();
		Image imgDel = new Image("img/close.png", 0, 0, 16, 16);
		imgDel.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				vp.remove(hp);
				educations.remove(exp);
				if(educations.size()==0)
					form.removeFromParent();
				form.markForRedraw();
			}
		});
		hp.add(exp);
		hp.add(imgDel);
		hp.setCellVerticalAlignment(imgDel, HasVerticalAlignment.ALIGN_MIDDLE);
		vp.add(hp);
		educations.add(exp);
		
		*/
		
		if(educations.size() == 0)
			absP.add(form);
		final EducationElementSimplePanel exp = new EducationElementSimplePanel();
		countColor  = (++countColor)%2;
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
				educations.remove(exp);
				if(educations.size()==0)
					form.removeFromParent();
				form.markForRedraw();
			}
		});
		/*hp.add(exp);
		hp.add(imgDel);
		hp.setCellVerticalAlignment(imgDel, HasVerticalAlignment.ALIGN_MIDDLE);*/
		vp.add(exp);
		educations.add(exp);
	}

	public Collection<Education> getEducation() throws IllegalArgumentException{
		Collection<Education> studentEducations = new ArrayList<Education>();
		for(EducationElementSimplePanel we : educations)
			studentEducations.add(we.getEducation());
		return studentEducations;
	}
}
