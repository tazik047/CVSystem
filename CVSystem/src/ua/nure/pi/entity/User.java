package ua.nure.pi.entity;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements Serializable, IsSerializable {
	private static final long serialVersionUID = 1L;

	private long userId;
	private long employeeId;
	private boolean isAdmin;
	private String login;
	private String password;

	public User() {
	}

	public User(long userId, boolean isAdmin, String login,
			String password) {
		setUserId(userId);
		setAdmin(isAdmin);
		setLogin(login);
		setPassword(password);
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	@Override
	public int hashCode() {
		return new Long(userId).hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User [userId=");
		sb.append(userId);
		sb.append(", employeeId=");
		sb.append(employeeId);
		sb.append(", login=");
		sb.append(login);
		sb.append(", isAdmin=");
		sb.append(isAdmin);
		return sb.toString();
	}
}
