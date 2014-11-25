package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCPurposeDAO;

public class MYSQLPurposeDAO extends JDBCPurposeDAO {
	
	private static volatile MYSQLPurposeDAO instance;
	
	private MYSQLPurposeDAO() {
		SQL__DELETE_PURPOSE = "DELETE Purposes WHERE PurposesId = ?";
	}
	
	public static MYSQLPurposeDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLPurposeDAO.class){
				if(instance == null)
					instance = new MYSQLPurposeDAO();
			}
		return instance;
	}
	/*
	private static final String SQL__SELECT_PURPOSE = "SELECT * FROM Purposes order by Title";
	private static final String SQL__INSERT_PURPOSE= "INSERT INTO Purposes(Title) VALUES(?)";
	private static final String SQL__UPDATE_PURPOSE = "UPDATE Purposes SET Title = ? WHERE PurposesId = ?";
	private static final String SQL__DELETE_PURPOSE = "DELETE Purposes WHERE PurposesId = ?";	
	private static final String SQL__FIND_PURPOSE = "SELECT * FROM Purposes where PurposesId = ?";
	private static final String SQL__FIND_BY_TITLE = "SELECT PurposesId FROM Purposes where Title = ?";*/

	@Override
	protected Connection getConnection() throws SQLException {
		return MYSQLDAOFactory.getConnection();
	}

}
