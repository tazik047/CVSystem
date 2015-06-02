package ua.nure.pi.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class CompanyStaticPanel extends LoadingSimplePanel{

	public CompanyStaticPanel(MainServiceAsync main) {
		isReady = false;
		final LoadingSimplePanel p = this;
		main.getPage(PathClient.STATIC_PAGE_COMPANY,new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				setText(result);
				fireLoadEvent(p);
				isReady=true;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				setText(caught.getMessage());
				fireLoadEvent(p);
				isReady=true;
			}
		});
	}
}
