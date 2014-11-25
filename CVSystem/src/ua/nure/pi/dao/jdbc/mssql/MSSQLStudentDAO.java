package ua.nure.pi.dao.jdbc.mssql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCStudentDAO;

public class MSSQLStudentDAO extends JDBCStudentDAO {
	
	private static volatile MSSQLStudentDAO instance;
	
	private MSSQLStudentDAO() {
		SQL__DELETE_ANY_TAG = "DELETE Students WHERE StudentsId = ?";
		
		jdbcDAOFactory = MSSQLDAOFactory.getInstancce();
	}
	
	public static MSSQLStudentDAO getInstancce(){
		if(instance == null)
			synchronized (MSSQLStudentDAO.class){
				if(instance == null)
					instance = new MSSQLStudentDAO();
			}
		return instance;
	}
	/*
	private static final String SQL__SELECT_STUDENT = "SELECT * FROM Students WHERE StudentsId = ?";
	private static final String SQL__SELECT_ALL_STUDENT = "SELECT * FROM Students";
	private static final String SQL__INSERT_STUDENT = "INSERT INTO Students(Surname, Firstname, Patronymic, "
			+ "GroupsId, Address, Skype, Email, Phone, Birthday) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SQL__DELETE_ANY_TAG = "DELETE Students WHERE StudentsId = ?";*/

	@Override
	protected Connection getConnection() throws SQLException {
		return MSSQLDAOFactory.getConnection();
	}
	
}
