package ua.nure.pi.client;

import java.util.Collection;

import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Purpose;
import ua.nure.pi.entity.Student;
import ua.nure.pi.entity.User;

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

	Boolean login(String login, String password) throws IllegalArgumentException;
	
	User checkLogined() throws IllegalArgumentException;
	
	void logout() throws IllegalArgumentException;
}
