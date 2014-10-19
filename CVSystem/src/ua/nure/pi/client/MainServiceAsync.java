package ua.nure.pi.client;

import java.util.Collection;

import javax.xml.crypto.KeySelector.Purpose;

import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Student;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface MainServiceAsync {
	void getFaculties(AsyncCallback<Collection<Faculty>> callback);
	
	void sendStudent(Student st, AsyncCallback asyncCallback);

	void getProgramLanguages(
			AsyncCallback<Collection<ProgramLanguage>> asyncCallback);

	void getLanguages(AsyncCallback<Collection<Language>> asyncCallback);

	void getPurposes(AsyncCallback<Collection<Purpose>> asyncCallback);

}
