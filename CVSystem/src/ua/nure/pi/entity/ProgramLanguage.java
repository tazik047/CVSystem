package ua.nure.pi.entity;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ProgramLanguage implements Serializable, IsSerializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String title;
	
	public ProgramLanguage(){
		
	}
	
	public ProgramLanguage(String title){
		setTitle(title);
	}
	
	public ProgramLanguage(String title, long id){
		this(title);
		setId(id);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
