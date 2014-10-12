package ua.nure.pi.entity;

import java.io.Serializable;
import java.util.Collection;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CV implements Serializable, IsSerializable {
	private static final long serialVersionUID = 1L;

	private long cvsId;
	
	private Collection<Education> educations;
	
	private Collection<Language> languages;
	
	private Collection<ProgramLanguage> programLanguages;
	
	private Collection<Sertificate> sertificates;
	
	private Collection<WorkExp> workExps;
	
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
}
