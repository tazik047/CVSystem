package ua.nure.pi.dao.jdbc.mssql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCUserDAO;

public class MSSQLUserDAO extends JDBCUserDAO {
	
private static volatile MSSQLUserDAO instance;
	
	private MSSQLUserDAO() {
		SQL__DELETE_USER = "DELETE FROM Users WHERE UsersId = ?";
	}
	
	public static MSSQLUserDAO getInstancce(){
		if(instance == null)
			synchronized (MSSQLUserDAO.class){
				if(instance == null)
					instance = new MSSQLUserDAO();
			}
		return instance;
	}/*

	private static final String SQL__CONTAINS_USER_WITH_LOGIN = "SELECT * FROM Users WHERE Login=?";
	private static final String SQL__READ_USER_BY_ID = "SELECT * FROM Users WHERE UsersId=?";
	private static final String SQL__GET_ALL_USERS = "SELECT * FROM Users";
	private static final String SQL__INSERT_USER = "INSERT INTO Users (Password, Login, Role) VALUES (?, ?, ?)";
	private static final String SQL__DELETE_USER = "DELETE FROM Users WHERE UsersId = ?";
	private static final String SQL__UPDATE_USER = "UPDATE Users SET Password = ? WHERE UsersId = ?";*/

	@Override
	protected Connection getConnection() throws SQLException {
		return MSSQLDAOFactory.getConnection();
	}

}
