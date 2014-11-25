package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import ua.nure.pi.dao.jdbc.JDBCFacultyGroupDAO;


public class MYSQLFacultyGroupDAO extends JDBCFacultyGroupDAO {
	
	private static volatile MYSQLFacultyGroupDAO instance;
	
	private MYSQLFacultyGroupDAO() {
		SQL__DELETE_FACULTIES = "DELETE FROM Faculties WHERE FacultiesId=?";
		
		SQL__DELETE_GROUPS = "DELETE FROM Groups WHERE GroupsId = ?";
	}
	
	public static MYSQLFacultyGroupDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLFacultyGroupDAO.class){
				if(instance == null)
					instance = new MYSQLFacultyGroupDAO();
			}
		return instance;
	}
	/*
	private static final String SQL__SELECT_FACULTIES = "SELECT * FROM Faculties order by Title";
	private static final String SQL__INSERT_FACULTIES = "INSERT INTO Faculties(Title) VALUES(?)";
	private static final String SQL__UPDATE_FACULTIES = "UPDATE Faculties SET Title=? WHERE FacultiesId=?";
	private static final String SQL__DELETE_FACULTIES = "DELETE FROM Faculties WHERE FacultiesId=?";
	private static final String SQL__FACULTIES_IS_EMPTY = "SELECT count(*) FROM Groups WHERE FacultiesId=?";
	
	private static final String SQL__SELECT_GROUPS = "SELECT * FROM Groups WHERE FacultiesId=? order by Title";
	private static final String SQL__INSERT_GROUPS = "INSERT INTO Groups(Title, FacultiesId) VALUES(?, ?)";
	private static final String SQL__UPDATE_GROUPS = "UPDATE Groups SET Title=?, FacultiesId=? WHERE GroupsId=?";
	private static final String SQL__DELETE_GROUPS = "DELETE FROM Groups WHERE GroupsId = ?";
	private static final String SQL__GROUPS_IS_EMPTY = "SELECT count(*) FROM Students where GroupsId=?";
	private static final String SQL__SELECT_GROUP = "SELECT * FROM Groups WHERE GroupsId = ?";
*/
	@Override
	protected Connection getConnection() throws SQLException {
		return MYSQLDAOFactory.getConnection();
	}

}
