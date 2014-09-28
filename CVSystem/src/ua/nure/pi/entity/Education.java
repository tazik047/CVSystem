package ua.nure.pi.entity;

public class Education {

	private int startYear;
	
	private int endYear;
	
	private String nameOfInstitution;
	
	private String specialty;
	
	private long educationId;
	
	private long CVsId;
	
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

}
