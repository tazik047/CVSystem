package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import ua.nure.pi.dao.jdbc.JDBCEducationDAO;

public class MYSQLEducationDAO extends JDBCEducationDAO {
	
	private static volatile MYSQLEducationDAO instance;
	
	private MYSQLEducationDAO() {
	}
	
	public static MYSQLEducationDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLEducationDAO.class){
				if(instance == null)
					instance = new MYSQLEducationDAO();
			}
		return instance;
	}
	/*
	private static final String SQL__SELECT_EDUCATION = "SELECT * FROM Educations WHERE CVsId = ?";
	private static final String SQL__INSERT_EDUCATION = "INSERT INTO Educations(StartYear, EndYear, "
			+ "NameOfInstitution, Specialty, CVsId, Faculty) VALUES(?,?,?,?,?,?)";*/

	@Override
	protected Connection getConnection() throws SQLException {
		return MYSQLDAOFactory.getConnection();
	}
}
