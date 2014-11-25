package ua.nure.pi.dao.jdbc.mssql;

import java.sql.Connection;
import java.sql.SQLException;
import ua.nure.pi.dao.jdbc.JDBCEducationDAO;

public class MSSQLEducationDAO extends JDBCEducationDAO {
	
	private static volatile MSSQLEducationDAO instance;
	
	private MSSQLEducationDAO() {
	}
	
	public static MSSQLEducationDAO getInstancce(){
		if(instance == null)
			synchronized (MSSQLEducationDAO.class){
				if(instance == null)
					instance = new MSSQLEducationDAO();
			}
		return instance;
	}
	/*
	private static final String SQL__SELECT_EDUCATION = "SELECT * FROM Educations WHERE CVsId = ?";
	private static final String SQL__INSERT_EDUCATION = "INSERT INTO Educations(StartYear, EndYear, "
			+ "NameOfInstitution, Specialty, CVsId, Faculty) VALUES(?,?,?,?,?,?)";*/

	@Override
	protected Connection getConnection() throws SQLException {
		return MSSQLDAOFactory.getConnection();
	}
}
