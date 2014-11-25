package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCWorkExpDAO;

public class MYSQLWorkExpDAO extends JDBCWorkExpDAO {
	
	private static volatile MYSQLWorkExpDAO instance;
	
	private MYSQLWorkExpDAO() {
	}
	
	public static MYSQLWorkExpDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLWorkExpDAO.class){
				if(instance == null)
					instance = new MYSQLWorkExpDAO();
			}
		return instance;
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return MYSQLDAOFactory.getConnection();
	}
	
	/*
	private static final String SQL__SELECT_WORKEXP = "SELECT * FROM WorkExps WHERE CVsId = ?";
	private static final String SQL__INSERT_WORKEXP = "INSERT INTO WorkExps(StartDate, "+
	"Duration, TypeDuration, NameOfInstitution,	Role, CVsId, isNow) VALUES(?,?,?,?,?,?,?)";*/

}
