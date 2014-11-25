package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCSertificatsDAO;

public class MYSQLSertificatsDAO extends JDBCSertificatsDAO {
	
	private static volatile MYSQLSertificatsDAO instance;
	
	private MYSQLSertificatsDAO() {
	}
	
	public static MYSQLSertificatsDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLSertificatsDAO.class){
				if(instance == null)
					instance = new MYSQLSertificatsDAO();
			}
		return instance;
	}
	/*
	private static final String SQL__SELECT_SERTIFICATE = "SELECT * FROM Sertificats WHERE CVsId = ?";
	private static final String SQL__INSERT_SERTIFICATE = "INSERT INTO Sertificats(Name, Year, "
			+ "CVsId) VALUES(?,?,?)";*/


	@Override
	protected Connection getConnection() throws SQLException {
		return MYSQLDAOFactory.getConnection();
	}
}
