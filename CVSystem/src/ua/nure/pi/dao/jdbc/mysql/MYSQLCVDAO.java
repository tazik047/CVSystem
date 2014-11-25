package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCCVDAO;

public class MYSQLCVDAO extends JDBCCVDAO {
	
	private static volatile MYSQLCVDAO instance;
	
	private MYSQLCVDAO() {
		SQL__INSERT_CV = "INSERT INTO CVs(PurposesId, Qualities, Others, DateStamp, CVsId) VALUES(?, ?, ?, now(), ?)";
		jdbcDAOFactory = MYSQLDAOFactory.getInstancce();
	}
	
	public static MYSQLCVDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLCVDAO.class){
				if(instance == null)
					instance = new MYSQLCVDAO();
			}
		return instance;
	}
	/*
	private static final String SQL__INSERT_CV = "INSERT INTO CVs(PurposesId, Qualities, Others, DateStamp, CVsId) VALUES(?, ?, ?, getdate(), ?)";
	private static final String SQL__SELECT_CV = "SELECT * FROM CVs WHERE CVsId=?";*/

	@Override
	protected Connection getConnection() throws SQLException {
		return MYSQLDAOFactory.getConnection();
	}
}
