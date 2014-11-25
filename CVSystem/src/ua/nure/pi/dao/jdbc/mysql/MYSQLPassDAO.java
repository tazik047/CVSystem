package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCPassDAO;

public class MYSQLPassDAO extends JDBCPassDAO {
	
	private static volatile MYSQLPassDAO instance;
	
	private MYSQLPassDAO() {
	}
	
	public static MYSQLPassDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLPassDAO.class){
				if(instance == null)
					instance = new MYSQLPassDAO();
			}
		return instance;
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return MYSQLDAOFactory.getConnection();
	}
	
	/*private static final String SQL__SELECT_PASS = "SELECT count(*) FROM Pass where AccessPass = ?";*/
}
