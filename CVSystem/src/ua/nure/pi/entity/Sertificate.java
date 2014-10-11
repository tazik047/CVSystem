package ua.nure.pi.entity;

public class Sertificate {
	
	private long sertificateId;
	private String sertificateName;
	private int sertificateYear;
	private long CVsId;
	
	public long getSertificateId() {
		return sertificateId;
	}
	public void setSertificateId(long sertificateId) {
		this.sertificateId = sertificateId;
	}
	public String getSertificateName() {
		return sertificateName;
	}
	public void setSertificateName(String sertificateName) {
		this.sertificateName = sertificateName;
	}
	public int getSertificateYear() {
		return sertificateYear;
	}
	public void setSertificateYear(int sertificateYear) {
		this.sertificateYear = sertificateYear;
	}
	public long getCVsId() {
		return CVsId;
	}
	public void setCVsId(long cVsId) {
		CVsId = cVsId;
	}
	
}
