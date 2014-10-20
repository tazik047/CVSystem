package ua.nure.pi.entity;

import java.io.Serializable;
import java.util.Collection;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CV implements Serializable, IsSerializable {
	private static final long serialVersionUID = 1L;

	private long cvsId;
	
	private Purpose purpose;
	
	private Collection<Education> educations;
	
	private Collection<Language> languages;
	
	private Collection<ProgramLanguage> programLanguages;
	
	private Collection<Sertificate> sertificates;
	
	private Collection<WorkExp> workExps;
	
	private String qualities;
	
	private String others;
	
	public long getCvsId() {
		return cvsId;
	}
	
	public void setCvsId(long cvsId) {
		this.cvsId = cvsId;
	}
	
	public Collection<Education> getEducations() {
		return educations;
	}
	
	public void setEducations(Collection<Education> educations) {
		this.educations = educations;
	}
	
	public Collection<Language> getLanguages() {
		return languages;
	}
	
	public void setLanguages(Collection<Language> languages) {
		this.languages = languages;
	}
	
	public Collection<ProgramLanguage> getProgramLanguages() {
		return programLanguages;
	}
	
	public void setProgramLanguages(Collection<ProgramLanguage> programLanguages) {
		this.programLanguages = programLanguages;
	}
	
	public Collection<Sertificate> getSertificates() {
		return sertificates;
	}
	
	public void setSertificates(Collection<Sertificate> sertificates) {
		this.sertificates = sertificates;
	}
	
	public Collection<WorkExp> getWorkExps() {
		return workExps;
	}
	
	public void setWorkExps(Collection<WorkExp> workExps) {
		this.workExps = workExps;
	}

	public Purpose getPurpose() {
		return purpose;
	}

	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}

	public String getQualities() {
		return qualities;
	}

	public void setQualities(String qualities) {
		this.qualities = qualities;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}
}
