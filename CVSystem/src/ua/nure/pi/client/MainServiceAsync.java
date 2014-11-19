package ua.nure.pi.client;

import java.util.Collection;

import ua.nure.pi.entity.Company;
import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Purpose;
import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Student;
import ua.nure.pi.entity.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface MainServiceAsync {
	void getFaculties(AsyncCallback<Collection<Faculty>> callback);
	
	void sendStudent(Student st, Boolean newPurp, Collection<ProgramLanguage> newPL, AsyncCallback<Void> asyncCallback) throws IllegalArgumentException;

	void getProgramLanguages(
			AsyncCallback<Collection<ProgramLanguage>> asyncCallback);

	void getLanguages(AsyncCallback<Collection<Language>> asyncCallback);

	void getPurposes(AsyncCallback<Collection<Purpose>> asyncCallback);
	
	void getPage(String url, AsyncCallback<String> asyncCallback); 
	
	void checkPass(String pass, AsyncCallback<Boolean> asyncCallback) throws IllegalArgumentException;
	
	void login(String login, String password, AsyncCallback<Boolean> asyncCallback) throws IllegalArgumentException;
	
	void checkLogined(AsyncCallback<User> asyncCallback) throws IllegalArgumentException;
	
	void logout(AsyncCallback<Void> asyncCallback) throws IllegalArgumentException;
	
	void getStudents(AsyncCallback<Collection<Student>> callback) throws IllegalArgumentException;

	void insertCompany(Company comp, AsyncCallback<Void> asyncCallback);

}
