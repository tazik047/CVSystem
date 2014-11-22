package ua.nure.pi.dao;

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
}
