package ua.nure.pi.entity;

public class Sertificate {
	
	private long SertificateId;
	private String SertificateName;
	private int SertificateYear;
	
	public long getSertificateId() {
		return SertificateId;
	}
	public void setSertificateId(long sertificateId) {
		SertificateId = sertificateId;
	}
	public String getSertificateName() {
		return SertificateName;
	}
	public void setSertificateName(String sertificateName) {
		SertificateName = sertificateName;
	}
	public int getSertificateYear() {
		return SertificateYear;
	}
	public void setSertificateYear(int sertificateYear) {
		SertificateYear = sertificateYear;
	}
	
}
