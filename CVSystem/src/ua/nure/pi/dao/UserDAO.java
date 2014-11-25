package ua.nure.pi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import ua.nure.pi.entity.User;

public interface UserDAO {

	public boolean containsUser(String login);

	public User getUser(String login);

	public User getUser(long userId);
	
	public Collection<User> getAllUsers();
	
	public boolean insertUser(User user);
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(User user);

	public boolean insertUser(User user, Connection con) throws SQLException;

	public User getUser(long id, Connection con) throws SQLException;
}
