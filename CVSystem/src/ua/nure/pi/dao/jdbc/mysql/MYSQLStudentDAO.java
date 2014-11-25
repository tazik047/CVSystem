package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCStudentDAO;

public class MYSQLStudentDAO extends JDBCStudentDAO {
	
	private static volatile MYSQLStudentDAO instance;
	
	private MYSQLStudentDAO() {
		SQL__DELETE_ANY_TAG = "DELETE Students WHERE StudentsId = ?";
		
		jdbcDAOFactory = MYSQLDAOFactory.getInstancce();
	}
	
	public static MYSQLStudentDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLStudentDAO.class){
				if(instance == null)
					instance = new MYSQLStudentDAO();
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
		return MYSQLDAOFactory.getConnection();
	}
	
}
