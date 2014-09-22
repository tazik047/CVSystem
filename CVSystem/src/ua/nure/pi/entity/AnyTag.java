package ua.nure.pi.entity;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AnyTag implements Serializable, IsSerializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String title;
	private String tableName;
	
	public AnyTag(){
		
	}
	
	public AnyTag(String tableName){
		setTableName(tableName);
	}
	
	public AnyTag(String tableName, String title){
		this(tableName);
		setTitle(title);
	}
	
	public AnyTag(String tableName, String title, long id){
		this(tableName, title);
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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
