package ua.nure.pi.client;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Student;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>Registration</code>.
 */
public interface RegistrationServiceAsync {

	void getFaculties(AsyncCallback<Collection<Faculty>> callback);

}
