package ua.nure.pi.entity;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

public class Company implements Serializable, IsSerializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private String email;
	private String phone;
	private String skype;
	private String phoneOfReliable;
	private String nameOfReliable;
	private User user;
	private boolean isActivate;
	
	public Company() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	public String getPhoneOfReliable() {
		return phoneOfReliable;
	}
	public void setPhoneOfReliable(String phoneOfReliable) {
		this.phoneOfReliable = phoneOfReliable;
	}
	public String getNameOfReliable() {
		return nameOfReliable;
	}
	public void setNameOfReliable(String nameOfReliable) {
		this.nameOfReliable = nameOfReliable;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isActivate() {
		return isActivate;
	}

	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}
}
