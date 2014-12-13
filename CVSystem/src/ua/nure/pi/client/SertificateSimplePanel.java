package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.entity.Sertificate;

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

public class SertificateSimplePanel extends SimplePanel{
	
	private final int BLOCK_HEIGHT = 80;
	
	public ArrayList<SertificateElementSimplePanel> sertificates;
	private SectionStack sectionStack;
	private int countColor = -1;
	private int pixelCount = 26;
	private VerticalPanel root;
	private boolean expend = true;
	private Button btAdd;
	
	public SertificateSimplePanel() {
		sectionStack = new SectionStack();  
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
        sectionStack.setWidth("355px");
        sectionStack.setHeight(String.valueOf(pixelCount)+"px");
        SectionStackSection section1 = new SectionStackSection("Сертификаты");
        sectionStack.addSection(section1);
        
		root = new VerticalPanel();
		root.setWidth("100%");
		final AbsolutePanel absP = new AbsolutePanel();
		
		sertificates = new ArrayList<SertificateElementSimplePanel>();
		DynamicForm form = new DynamicForm();
		form.setStyleName("fixFormFox");
		section1.addItem(form);
        form.setNumCols(4);
        final VerticalPanel vp = new VerticalPanel();
        form.addChild(vp);
        btAdd = new Button("Добавить сертификат");
        btAdd.addStyleName("panel-startAddButton");
        btAdd.setTitle("Добавить сертификат");
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
		if(sertificates.size() == 0){
			absP.add(sectionStack);
			root.remove(btAdd);
			absP.add(btAdd);
			btAdd.removeStyleName("panel-startAddButton");
			btAdd.addStyleName("panel-newAddButton");
						
		}
		final SertificateElementSimplePanel exp = new SertificateElementSimplePanel();
		pixelCount += BLOCK_HEIGHT;
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
				sertificates.remove(exp);
				pixelCount -= BLOCK_HEIGHT;
				sectionStack.setHeight(String.valueOf(pixelCount)+"px");
				if(sertificates.size()==0){
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
		sertificates.add(exp);
	}
	public Boolean ValidateForm() {
		Boolean b = true;
		for (SertificateElementSimplePanel sesp : sertificates) {
			b = sesp.controls.validate() && b;
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

	public Collection<Sertificate> getSerts() throws IllegalArgumentException{
		Collection<Sertificate> studentSerts = new ArrayList<Sertificate>();
		for(SertificateElementSimplePanel se : sertificates)
			studentSerts.add(se.getSertificate());
		return studentSerts;
	}
}
