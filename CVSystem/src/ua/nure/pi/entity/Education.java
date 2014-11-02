package ua.nure.pi.entity;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Education implements Serializable, IsSerializable {
	private static final long serialVersionUID = 1L;

	private int startYear;
	
	private int endYear;
	
	private String nameOfInstitution;
	
	private String specialty;
	
	private long educationId;
	
	private long CVsId;
	
	private String faculty;
	
	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public String getNameOfInstitution() {
		return nameOfInstitution;
	}

	public void setNameOfInstitution(String nameOfInstitution) {
		this.nameOfInstitution = nameOfInstitution;
	}
	

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public Education(int start, int end, String name, String spec) {
		setStartYear(start);
		setEndYear(end);
		setNameOfInstitution(name);
		setSpecialty(spec);
	}

	public Education() {
	}

	public long getEducationId() {
		return educationId;
	}

	public void setEducationId(long educationId) {
		this.educationId = educationId;
	}

	public long getCVsId() {
		return CVsId;
	}

	public void setCVsId(long cVsId) {
		CVsId = cVsId;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

}
