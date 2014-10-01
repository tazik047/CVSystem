package ua.nure.pi.entity;

public class Student {

	private long studentsId;
	
	private String surname;
	
	private String firstname;
	
	private String patronymic;
	
	private long groupsId;
	
	private long CVsId;

	public long getStudentsId() {
		return studentsId;
	}

	public void setStudentsId(long studentsId) {
		this.studentsId = studentsId;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public long getGroupsId() {
		return groupsId;
	}

	public void setGroupsId(long groupsId) {
		this.groupsId = groupsId;
	}

	public long getCVsId() {
		return CVsId;
	}

	public void setCVsId(long cVsId) {
		CVsId = cVsId;
	}
	
	public Student() {
		
	}
	
}
