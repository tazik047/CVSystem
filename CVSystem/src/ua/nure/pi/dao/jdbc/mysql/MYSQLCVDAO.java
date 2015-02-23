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

	@Override
	protected String createInterval(int start, int end, StringBuilder query) {
		return String.format("select distinct cv.CVsId from CVs cv,LanguagesCVs lcv, ProgramLanguagesCVs pcv, Purposes p"
				+ " WHERE cv.CVsId=lcv.CVsId and pcv.CVsId=cv.CVsId and p.PurposesId=cv.PurposesId " + query.toString()
				+ " LIMIT %1$d, %2$d", start, end-start);
	}
}
