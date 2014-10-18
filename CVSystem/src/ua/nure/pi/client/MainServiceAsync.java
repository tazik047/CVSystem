package ua.nure.pi.client;

import java.util.Collection;

import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Student;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface MainServiceAsync {
	void getFaculties(AsyncCallback<Collection<Faculty>> callback);
	
	void sendStudent(Student st, AsyncCallback asyncCallback);

}
