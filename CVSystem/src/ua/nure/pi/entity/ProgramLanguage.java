package ua.nure.pi.entity;

public class ProgramLanguage {
	private long id;
	private String title;
	
	public ProgramLanguage(){
		
	}
	
	public ProgramLanguage(String title){
		setTitle(title);
	}
	
	public ProgramLanguage(long id, String title){
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
