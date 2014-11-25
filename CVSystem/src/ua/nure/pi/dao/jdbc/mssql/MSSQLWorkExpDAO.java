package ua.nure.pi.dao.jdbc.mssql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCWorkExpDAO;

public class MSSQLWorkExpDAO extends JDBCWorkExpDAO {
	
	private static volatile MSSQLWorkExpDAO instance;
	
	private MSSQLWorkExpDAO() {
	}
	
	public static MSSQLWorkExpDAO getInstancce(){
		if(instance == null)
			synchronized (MSSQLWorkExpDAO.class){
				if(instance == null)
					instance = new MSSQLWorkExpDAO();
			}
		return instance;
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return MSSQLDAOFactory.getConnection();
	}
	
	/*
	private static final String SQL__SELECT_WORKEXP = "SELECT * FROM WorkExps WHERE CVsId = ?";
	private static final String SQL__INSERT_WORKEXP = "INSERT INTO WorkExps(StartDate, "+
	"Duration, TypeDuration, NameOfInstitution,	Role, CVsId, isNow) VALUES(?,?,?,?,?,?,?)";*/

}
