package ua.nure.pi.entity;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class WorkExp  implements Serializable, IsSerializable {
	private static final long serialVersionUID = 1L;
	
	private int startYear;
	
	private int duration;
	
	private typeOfDuration typeOfDuration;
	
	private String nameOfInstitution;
	
	private String role;
	
	private long CVsId;
	
	private long WorkExpsId;

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public String getNameOfInstitution() {
		return nameOfInstitution;
	}

	public void setNameOfInstitution(String nameOfInstitution) {
		this.nameOfInstitution = nameOfInstitution;
	}
	
	public WorkExp(){
		
	}
	
	public WorkExp(int start, int dur, typeOfDuration type, String name, String role) {
		setStartYear(start);
		setDuration(dur);
		setNameOfInstitution(name);
		setTypeOfDuration(type);
		setRole(role);
		}

	public typeOfDuration getTypeOfDuration() {
		return typeOfDuration;
	}

	public void setTypeOfDuration(typeOfDuration typeOfDuration) {
		this.typeOfDuration = typeOfDuration;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getCVsId() {
		return CVsId;
	}

	public void setCVsId(long cVsId) {
		CVsId = cVsId;
	}

	public long getWorkExpsId() {
		return WorkExpsId;
	}

	public void setWorkExpsId(long workExpsId) {
		WorkExpsId = workExpsId;
	}

}
