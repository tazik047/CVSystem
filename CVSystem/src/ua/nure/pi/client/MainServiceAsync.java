package ua.nure.pi.client;

import java.util.Collection;

import ua.nure.pi.entity.CV;
import ua.nure.pi.entity.Company;
import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Group;
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
	
	void login(String login, String password, AsyncCallback<User> asyncCallback) throws IllegalArgumentException;
	
	void checkLogined(AsyncCallback<User> asyncCallback) throws IllegalArgumentException;
	
	void logout(AsyncCallback<Void> asyncCallback) throws IllegalArgumentException;
	
	void getStudents(AsyncCallback<Collection<Student>> callback) throws IllegalArgumentException;

	void insertCompany(Company comp, AsyncCallback<Void> asyncCallback) throws IllegalArgumentException;

	void updateFaculty(Faculty faculty, AsyncCallback<Void> asyncCallback) throws IllegalArgumentException;
	
	void addFaculty(Faculty faculty, AsyncCallback<Long> asyncCallback) throws IllegalArgumentException;
	
	void deleteFaculty(Faculty faculty, AsyncCallback<Void> asyncCallback) throws IllegalArgumentException;
	
	void addGroup(Group group, AsyncCallback<Long> asyncCallback) throws IllegalArgumentException;
	
	void updateGroup(Group group, AsyncCallback<Void> asyncCallback) throws IllegalArgumentException;
	
	void deleteGroup(Group group, AsyncCallback<Void> asyncCallback) throws IllegalArgumentException;
	
	void getNotActivedCompany(AsyncCallback<Collection<Company>> asyncCallback) throws IllegalArgumentException;
	
	void searchCV(Collection<Language> languages,
			Collection<ProgramLanguage> planguages,
			Collection<Purpose> purposes, int startIndex, int endIndex,
			AsyncCallback<Collection<CV>> asyncCallback) throws IllegalArgumentException;
	
	void activateCompany(Company c, AsyncCallback<Void> asyncCallback) throws IllegalArgumentException;
}
