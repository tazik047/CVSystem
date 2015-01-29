package ua.nure.pi.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import ua.nure.pi.entity.Company;
import ua.nure.pi.entity.ProgramLanguage;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

public class ActivateCompanyPanel extends LoadingSimplePanel {
	
	MainServiceAsync mainServ;
	
	public ActivateCompanyPanel(MainServiceAsync mainService) {
		mainServ = mainService;
        final LoadingSimplePanel lsp = this;
        mainServ.getNotActivedCompany(new AsyncCallback<Collection<Company>>() {
			
			@Override
			public void onSuccess(Collection<Company> result) {
				isReady = true;
				VLayout root = new VLayout();
				Label label = new Label();  
		        label.setHeight(60);  
		        label.setValign(VerticalAlignment.CENTER);  
		        label.setWrap(false);  
		        label.setContents("Компании, требующие активации");  
		        label.setStyleName("title");
		        root.addMember(label);
		        root.addMember(new CompanyTable(result, mainServ,  true));
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
		/*isReady = true;
		mainService.getProgramLanguages(new AsyncCallback<Collection<ProgramLanguage>>() {
			
			@Override
			public void onSuccess(Collection<ProgramLanguage> result) {
				HashMap<String, String> map = new HashMap<String, String>();
				for(ProgramLanguage pl : result){
					map.put(String.valueOf(pl.getId()), pl.getTitle());
				}
				ArrayList<String> opt = new ArrayList<String>();
				opt.add("Плохо");
				opt.add("Хорошо");
				opt.add("Отлично");
				MultiSelect ms = new MultiSelect(map, opt);
				setWidget(ms);
				ms.draw();			
				ms.setWidth(200);
				ms.setHeight(300);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				setWidget(new Label(caught.getMessage()));
			}
		});
		*/
	}
}
