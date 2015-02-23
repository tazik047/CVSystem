package ua.nure.pi.dao.jdbc.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.jdbc.JDBCCVDAO;
import ua.nure.pi.entity.CV;
import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Purpose;
import ua.nure.pi.parameter.MapperParameters;

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
	protected String createInterval(int start, int end, StringBuilder where){
		String query="select distinct top %1$d cv.CVsId from CVs cv,LanguagesCVs lcv, ProgramLanguagesCVs pcv, Purposes p"
			+ " WHERE cv.CVsId=lcv.CVsId and pcv.CVsId=cv.CVsId and p.PurposesId=cv.PurposesId "
			+ "and cv.CVsId not in (select distinct top %2$d cv.CVsId from CVs cv,LanguagesCVs lcv, ProgramLanguagesCVs pcv, Purposes p"
			+ " WHERE cv.CVsId=lcv.CVsId and pcv.CVsId=cv.CVsId and p.PurposesId=cv.PurposesId %3$s) %3$s";
		return String.format(query, end-start, start, where.toString());
	}
	
	@Override
	protected Connection getConnection() throws SQLException {
		return MSSQLDAOFactory.getConnection();
	}
}
