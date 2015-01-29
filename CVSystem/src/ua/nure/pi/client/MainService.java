package ua.nure.pi.client;

import java.util.Collection;

import ua.nure.pi.entity.CV;
import ua.nure.pi.entity.Company;
import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Group;
import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Purpose;
import ua.nure.pi.entity.Student;
import ua.nure.pi.entity.User;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("")
public interface MainService extends RemoteService {
	Collection<Faculty> getFaculties() throws IllegalArgumentException;
	
	Collection<ProgramLanguage> getProgramLanguages();
	
	void sendStudent(Student st, Boolean newPurp, Collection<ProgramLanguage> newPL) throws IllegalArgumentException;
	
	Collection<Language> getLanguages();

	Collection<Purpose> getPurposes();
	
	String getPage(String url);
	
	Boolean checkPass(String pass) throws IllegalArgumentException;

	User login(String login, String password) throws IllegalArgumentException;
	
	User checkLogined() throws IllegalArgumentException;
	
	void logout() throws IllegalArgumentException;
	
	Collection<Student> getStudents() throws IllegalArgumentException;

	void insertCompany(Company c) throws IllegalArgumentException;
	
	void updateFaculty(Faculty f) throws IllegalArgumentException;

	long addFaculty(Faculty f) throws IllegalArgumentException;
	
	void deleteFaculty(Faculty f) throws IllegalArgumentException;
	
	long addGroup(Group g) throws IllegalArgumentException;
	
	void updateGroup(Group f) throws IllegalArgumentException;
	
	void deleteGroup(Group f) throws IllegalArgumentException;
	
	Collection<Company> getNotActivedCompany() throws IllegalArgumentException;
	
	Collection<CV> searchCV(Collection<Language> languages,
			Collection<ProgramLanguage> planguages,
			Collection<Purpose> purposes, int startIndex, int endIndex) throws IllegalArgumentException;
	
	void activateCompany(Company c) throws IllegalArgumentException;
}
