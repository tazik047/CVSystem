package ua.nure.pi.entity;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Language implements Serializable, IsSerializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String title;
	private int level;
	
	public Language(){
		
	}
	
	public Language(String title){
		setTitle(title);
	}
	
	public Language(String title, int level){
		this(title);
		setLevel(level);
	}
	
	public Language(String title, int level, long id){
		this(title,level);
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
