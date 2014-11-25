package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCAnyTagDAO;

public class MYSQLAnyTagDAO extends JDBCAnyTagDAO {
	
	private static volatile MYSQLAnyTagDAO instance;
	
	private MYSQLAnyTagDAO() {
		SQL__INSERT_ANY_TAG = "INSERT INTO %s(Title) VALUES(?)";
		SQL__UPDATE_ANY_TAG = "UPDATE %1$s SET Title = ? WHERE %2$sId = ?";
		SQL__DELETE_ANY_TAG = "DELETE %1$s WHERE %2$sId = ?";
		
		SQL__INSERT_STUENT_ANY_TAG = "INSERT INTO %1$sCVs(CVsId, %2$SId) VALUES(?,?)";
	}
	
	public static MYSQLAnyTagDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLAnyTagDAO.class){
				if(instance == null)
					instance = new MYSQLAnyTagDAO();
			}
		return instance;
	}
	/*
	private static final String SQL__SELECT_ANY_TAG = "SELECT * FROM %s";
	private static final String SQL__INSERT_ANY_TAG = "INSERT INTO %s(Title) VALUES(?)";
	private static final String SQL__UPDATE_ANY_TAG = "UPDATE %1$s SET Title = ? WHERE %2$sId = ?";
	private static final String SQL__DELETE_ANY_TAG = "DELETE %1$s WHERE %2$sId = ?";
	
	private static final String SQL__SELECT_STUDENT_ANY_TAG = "SELECT * FROM %1$s WHERE %1$sId =?";
	private static final String SQL__INSERT_STUENT_ANY_TAG = "INSERT INTO %1$sCVs(CVsId, %2$SId) VALUES(?,?)";*/

	@Override
	protected Connection getConnection() throws SQLException {
		return MYSQLDAOFactory.getConnection();
	}
}
