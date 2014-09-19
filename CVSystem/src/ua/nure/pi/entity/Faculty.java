package ua.nure.pi.entity;

import java.io.Serializable;
import java.util.Collection;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Faculty implements Serializable, IsSerializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private Collection<Group> groups;
	private long facultiesId;
	
	public Faculty(){	
	}
	
	public Faculty(long id, String title, Collection<Group> groups){
		setFacultiesId(id);
		setGroups(groups);
		setTitle(title);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Collection<Group> getGroups() {
		return groups;
	}
	public void setGroups(Collection<Group> groups) {
		this.groups = groups;
	}
	public long getFacultiesId() {
		return facultiesId;
	}
	public void setFacultiesId(long facultiesId) {
		this.facultiesId = facultiesId;
	}
	
}
