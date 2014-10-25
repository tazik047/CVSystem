package ua.nure.pi.client;

import java.util.Collection;

import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Group;
import ua.nure.pi.entity.Student;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AdminPanelServiceAsync {
	
	public void getFaculties(AsyncCallback<Collection<Faculty>> callback) throws IllegalArgumentException;
	
	public void setFaculties(Faculty faculty, AsyncCallback<Void> callback) throws IllegalArgumentException;
	
	public void setGroup(Group group, AsyncCallback<Void> callback) throws IllegalArgumentException;
	
	public void getStudents(String pass, AsyncCallback<Collection<Student>> callback) throws IllegalArgumentException;

}
