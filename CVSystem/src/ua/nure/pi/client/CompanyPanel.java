package ua.nure.pi.client;

import java.util.Collection;

import ua.nure.pi.entity.Company;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

public class CompanyPanel extends LoadingSimplePanel {
	
	MainServiceAsync mainServ;
	
	public CompanyPanel(final boolean activate, MainServiceAsync mainService) {
		mainServ = mainService;
        final LoadingSimplePanel lsp = this;
        mainServ.getCompany(activate, new AsyncCallback<Collection<Company>>() {
			
			@Override
			public void onSuccess(Collection<Company> result) {
				isReady = true;
				VLayout root = new VLayout();
				Label label = new Label();  
		        label.setHeight(60);  
		        label.setValign(VerticalAlignment.CENTER);  
		        label.setWrap(false);  
		        if(activate)
		        	label.setContents("Компании");
		        else
		        	label.setContents("Компании, требующие активации");  
		        label.setStyleName("title");
		        root.addMember(label);
		        root.addMember(new CompanyTable(result, mainServ,  !activate));
				setWidget(root);
		        root.setStyleName("mainForm", true);
		        root.markForRedraw();
		        root.setPadding(10);
		        root.setMargin(10);
		        isReady = true;
		        fireLoadEvent(lsp);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				setWidget(new Label(caught.getMessage()));
				isReady = true;
		        fireLoadEvent(lsp);
			}
		});
	}
}
