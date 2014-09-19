package ua.nure.pi.entity;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Group implements Serializable, IsSerializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private long groupId;
	private long facultiesId;

	public Group(){
		
	}
	
	public Group(String title, long facultiesId){
		setTitle(title);
		setFacultiesId(facultiesId);
	}
	
	public Group(long id, String title, long facultiesId){
		this(title, facultiesId);
		setGroupId(id);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getFacultiesId() {
		return facultiesId;
	}

	public void setFacultiesId(long facultiesId) {
		this.facultiesId = facultiesId;
	}
}
