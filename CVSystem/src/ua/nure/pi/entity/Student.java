package ua.nure.pi.entity;

import java.util.Date;

public class Student {

	private long studentsId;
	
	private String surname;
	
	private String firstname;
	
	private String patronymic;
	
	private long groupsId;
	
	private long CVsId;
	
	private Date dateOfBirth;
	
	private String phone;
	
	private String email;
	
	private String skype;
	
	

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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
