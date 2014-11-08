package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.entity.WorkExp;

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

public class WorkExperienceSimplePanel extends SimplePanel{
	
	public ArrayList<WorkExperinceElementSimplePanel> works;
	private SectionStack sectionStack;
	private int countColor = -1;
	private int pixelCount = 26;
	private VerticalPanel root;
	private boolean expend = true;
	private Button btAdd;
	public WorkExperienceSimplePanel() {
		sectionStack = new SectionStack();  
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
  
        sectionStack.setWidth("355px");
        sectionStack.setHeight(String.valueOf(pixelCount)+"px");
        SectionStackSection section1 = new SectionStackSection("Опыт работы");
        sectionStack.addSection(section1);
                
		root = new VerticalPanel();
		root.setWidth("100%");
		final AbsolutePanel absP = new AbsolutePanel();
		
		works = new ArrayList<WorkExperinceElementSimplePanel>();
		DynamicForm form = new DynamicForm();  
		section1.addItem(form);
        form.setNumCols(4);
        final VerticalPanel vp = new VerticalPanel();
        form.addChild(vp);
        btAdd = new Button("Добавить опыт работы");
        btAdd.addStyleName("panel-startAddButton");
        btAdd.setTitle("Добавить опыт работы");
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
		if(works.size() == 0){
			absP.add(sectionStack);
			root.remove(btAdd);
			absP.add(btAdd);
			btAdd.removeStyleName("panel-startAddButton");
			btAdd.addStyleName("panel-newAddButton");
						
		}
		final WorkExperinceElementSimplePanel exp = new WorkExperinceElementSimplePanel();
		pixelCount+=163;
		sectionStack.setHeight(String.valueOf(pixelCount)+"px");
		countColor = (++countColor)%2;
		if(countColor == 0){
			exp.setStyleName("stylePanel1");
		}
		else
			exp.setStyleName("stylePanel2");
		exp.imgDel.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				vp.remove(exp);
				works.remove(exp);
				pixelCount-=163;
				sectionStack.setHeight(String.valueOf(pixelCount)+"px");
				if(works.size()==0){
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
		works.add(exp);
	}
	public Boolean ValidateForm() {
		Boolean b = true;
		for (WorkExperinceElementSimplePanel wesp : works) {
			b = wesp.controls.validate() && b;
		}
		if (!b && pixelCount != 26);
		{
            sectionStack.expandSection(0);
			sectionStack.setHeight(String.valueOf(pixelCount+"px"));
			
			expend = true;
			btAdd.setVisible(true);
			sectionStack.markForRedraw();
		}			
		return b;
	}

	
	public Collection<WorkExp> getExp() throws IllegalArgumentException{
		Collection<WorkExp> studentWorks = new ArrayList<WorkExp>();
		for(WorkExperinceElementSimplePanel we : works)
			studentWorks.add(we.getWorkExp());
		return studentWorks;
	}
}
