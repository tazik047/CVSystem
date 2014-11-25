package ua.nure.pi.dao.jdbc.mssql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCSertificatsDAO;

public class MSSQLSertificatsDAO extends JDBCSertificatsDAO {
	
	private static volatile MSSQLSertificatsDAO instance;
	
	private MSSQLSertificatsDAO() {
	}
	
	public static MSSQLSertificatsDAO getInstancce(){
		if(instance == null)
			synchronized (MSSQLSertificatsDAO.class){
				if(instance == null)
					instance = new MSSQLSertificatsDAO();
			}
		return instance;
	}
	/*
	private static final String SQL__SELECT_SERTIFICATE = "SELECT * FROM Sertificats WHERE CVsId = ?";
	private static final String SQL__INSERT_SERTIFICATE = "INSERT INTO Sertificats(Name, Year, "
			+ "CVsId) VALUES(?,?,?)";*/


	@Override
	protected Connection getConnection() throws SQLException {
		return MSSQLDAOFactory.getConnection();
	}
}
