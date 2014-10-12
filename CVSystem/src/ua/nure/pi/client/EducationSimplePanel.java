package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.entity.Education;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickEvent;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickHandler;

public class EducationSimplePanel extends SimplePanel{
	
	private ArrayList<EducationElementSimplePanel> educations;
	private SectionStack sectionStack;
	private int countColor = -1;
	private int pixelCount = 26;
	private VerticalPanel root;
	private boolean expend = true;
	
	public EducationSimplePanel() {
		sectionStack = new SectionStack();  
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
  
        sectionStack.setWidth("355px");
        sectionStack.setHeight(String.valueOf(pixelCount)+"px");
        SectionStackSection section1 = new SectionStackSection("Образование");
        sectionStack.addSection(section1);
        
		root = new VerticalPanel();
		root.setWidth("100%");
		final AbsolutePanel absP = new AbsolutePanel();
		
		educations = new ArrayList<EducationElementSimplePanel>();
		DynamicForm form = new DynamicForm();
		section1.addItem(form);
        form.setNumCols(4);
        final VerticalPanel vp = new VerticalPanel();
        form.addChild(vp);
        final Button btAdd = new Button("Добавить образование");
        btAdd.addStyleName("panel-startAddButton");
        btAdd.setTitle("Добавить образование");
        root.add(absP);
        root.add(btAdd);
        root.setCellHorizontalAlignment(btAdd, HasHorizontalAlignment.ALIGN_LEFT);
        btAdd.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				addExp(vp, absP, btAdd);
				sectionStack.markForRedraw();
			}
		});
        
        sectionStack.addSectionHeaderClickHandler(new SectionHeaderClickHandler() {
			
			@Override
			public void onSectionHeaderClick(SectionHeaderClickEvent event) {
				if(expend)
					sectionStack.setHeight(String.valueOf("26px"));
				else
					sectionStack.setHeight(String.valueOf(pixelCount)+"px");
				btAdd.setVisible(!expend);
				sectionStack.markForRedraw();
				expend = !expend; 
			}
		});
        setWidget(root);
        form.markForRedraw();
        
	}
	
	private void addExp(final VerticalPanel vp, final AbsolutePanel absP, final Button btAdd){
		
		if(educations.size() == 0){
			absP.add(sectionStack);
			root.remove(btAdd);
			absP.add(btAdd);
			btAdd.removeStyleName("panel-startAddButton");
			btAdd.addStyleName("panel-newAddButton");
						
		}
		final EducationElementSimplePanel exp = new EducationElementSimplePanel();
		pixelCount+=137;
		sectionStack.setHeight(String.valueOf(pixelCount)+"px");
		countColor  = (++countColor)%2;
		if(countColor == 0){
			exp.setStyleName("stylePanel1");
		}
		else
			exp.setStyleName("stylePanel2");
		exp.imgDel.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				vp.remove(exp);
				educations.remove(exp);
				pixelCount-=137;
				sectionStack.setHeight(String.valueOf(pixelCount)+"px");
				if(educations.size()==0){
					sectionStack.removeFromParent();
					absP.remove(btAdd);
					root.add(btAdd);
					btAdd.removeStyleName("panel-newAddButton");
					btAdd.addStyleName("panel-startAddButton");
				}
				sectionStack.markForRedraw();
			}
		});
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
