package ua.nure.pi.entity;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Favorite implements Serializable, IsSerializable {
	private static final long serialVersionUID = 1L;
	private Date date;
	private Student student;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
