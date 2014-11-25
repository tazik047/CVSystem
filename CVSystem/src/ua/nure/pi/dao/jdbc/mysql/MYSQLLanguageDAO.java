package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCLanguageDAO;

public class MYSQLLanguageDAO extends JDBCLanguageDAO {
	
	private static volatile MYSQLLanguageDAO instance;
	
	private MYSQLLanguageDAO() {
		SQL__DELETE_LANGUAGE = "DELETE Languages WHERE LanguagesId = ?";
	}
	
	public static MYSQLLanguageDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLLanguageDAO.class){
				if(instance == null)
					instance = new MYSQLLanguageDAO();
			}
		return instance;
	}
	/*
	private static final String SQL__SELECT_LANGUAGE = "SELECT * FROM Languages";
	private static final String SQL__INSERT_LANGUAGE = "INSERT INTO Languages(Title) VALUES(?)";
	private static final String SQL__UPDATE_LANGUAGE = "UPDATE Languages SET Title = ? WHERE LanguagesId = ?";
	private static final String SQL__DELETE_LANGUAGE = "DELETE Languages WHERE LanguagesId = ?";
	
	private static final String SQL__SELECT_STUDENT_LANGUAGE = "select lc.LanguagesId, lc.LanguagesCVsId, l.Title, lc.Level from LanguagesCVs lc"
			+" join Languages l on lc.LanguagesId = l.LanguagesId where lc.CVsId=?";
	private static final String SQL__INSERT_STUENT_LANGUAGE = "INSERT INTO LanguagesCVs(CVsId, LanguagesId, Level) VALUES(?,?,?)";*/

	@Override
	protected Connection getConnection() throws SQLException {
		return MYSQLDAOFactory.getConnection();
	}

}
