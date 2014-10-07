package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.entity.Sertificate;
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

public class SertificateSimplePanel extends SimplePanel{
	
	private ArrayList<SertificateElementSimplePanel> sertificates;
	private DynamicForm form;
	private int countColor = -1;
	private VerticalPanel root;
	public SertificateSimplePanel() {
		
		root = new VerticalPanel();
		root.setWidth("100%");
		final AbsolutePanel absP = new AbsolutePanel();
		
		sertificates = new ArrayList<SertificateElementSimplePanel>();
		form = new DynamicForm();  
        form.setIsGroup(true);  
        form.setGroupTitle("Сертификаты");
        form.setNumCols(4);
        final VerticalPanel vp = new VerticalPanel();
        form.addChild(vp);
        final Button btAdd = new Button("Добавить сертификат");
        btAdd.addStyleName("panel-startAddButton");
        btAdd.setTitle("Добавить сертификат");
        root.add(absP);
        root.add(btAdd);
        root.setCellHorizontalAlignment(btAdd, HasHorizontalAlignment.ALIGN_CENTER);
        btAdd.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				addExp(vp, absP, btAdd);
				form.markForRedraw();
			}
		});
        setWidget(root);
        form.markForRedraw();
        
	}
	
	private void addExp(final VerticalPanel vp, final AbsolutePanel absP, final Button btAdd){
		/*if(works.size()!=0)
			works.get(works.size()-1).addSeparator();
		else*/
		if(sertificates.size() == 0){
			absP.add(form);
			root.remove(btAdd);
			absP.add(btAdd);
			btAdd.removeStyleName("panel-startAddButton");
			btAdd.addStyleName("panel-newAddButton");
						
		}
		final SertificateElementSimplePanel exp = new SertificateElementSimplePanel();
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
				sertificates.remove(exp);
				if(sertificates.size()==0){
					form.removeFromParent();
					absP.remove(btAdd);
					root.add(btAdd);
					btAdd.removeStyleName("panel-newAddButton");
					btAdd.addStyleName("panel-startAddButton");
				}
				form.markForRedraw();
			}
		});
		/*hp.add(exp);
		hp.add(imgDel);
		hp.setCellVerticalAlignment(imgDel, HasVerticalAlignment.ALIGN_MIDDLE);*/
		vp.add(exp);
		sertificates.add(exp);
	}

	public Collection<Sertificate> getExp() throws IllegalArgumentException{
		Collection<Sertificate> studentSerts = new ArrayList<Sertificate>();
		for(SertificateElementSimplePanel we : sertificates)
			studentSerts.add(we.getSertificate());
		return studentSerts;
	}
}
