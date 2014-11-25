package ua.nure.pi.dao.jdbc.mssql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCPassDAO;

public class MSSQLPassDAO extends JDBCPassDAO {
	
	private static volatile MSSQLPassDAO instance;
	
	private MSSQLPassDAO() {
	}
	
	public static MSSQLPassDAO getInstancce(){
		if(instance == null)
			synchronized (MSSQLPassDAO.class){
				if(instance == null)
					instance = new MSSQLPassDAO();
			}
		return instance;
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return MSSQLDAOFactory.getConnection();
	}
	
	/*private static final String SQL__SELECT_PASS = "SELECT count(*) FROM Pass where AccessPass = ?";*/
}
