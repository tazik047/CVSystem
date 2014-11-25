package ua.nure.pi.dao.jdbc.mssql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCCVDAO;

public class MSSQLCVDAO extends JDBCCVDAO {
	
	private static volatile MSSQLCVDAO instance;
	
	private MSSQLCVDAO() {
		SQL__INSERT_CV = "INSERT INTO CVs(PurposesId, Qualities, Others, DateStamp, CVsId) VALUES(?, ?, ?, getdate(), ?)";
		jdbcDAOFactory = MSSQLDAOFactory.getInstancce();
	}
	
	public static MSSQLCVDAO getInstancce(){
		if(instance == null)
			synchronized (MSSQLCVDAO.class){
				if(instance == null)
					instance = new MSSQLCVDAO();
			}
		return instance;
	}
	/*
	private static final String SQL__INSERT_CV = "INSERT INTO CVs(PurposesId, Qualities, Others, DateStamp, CVsId) VALUES(?, ?, ?, getdate(), ?)";
	private static final String SQL__SELECT_CV = "SELECT * FROM CVs WHERE CVsId=?";*/

	@Override
	protected Connection getConnection() throws SQLException {
		return MSSQLDAOFactory.getConnection();
	}
}
