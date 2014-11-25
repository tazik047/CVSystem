package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCProgramLanguageDAO;

public class MYSQLProgramLanguageDAO extends JDBCProgramLanguageDAO {
	
	private static volatile MYSQLProgramLanguageDAO instance;
	
	private MYSQLProgramLanguageDAO() {
		SQL__DELETE_PROGRAM_LANGUAGE = "DELETE ProgramLanguages WHERE ProgramLanguagesId = ?";
	}
	
	public static MYSQLProgramLanguageDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLProgramLanguageDAO.class){
				if(instance == null)
					instance = new MYSQLProgramLanguageDAO();
			}
		return instance;
	}
	
	/*private static final String SQL__SELECT_PROGRAM_LANGUAGE = "SELECT * FROM ProgramLanguages order by Title";
	private static final String SQL__INSERT_PROGRAM_LANGUAGE = "INSERT INTO ProgramLanguages(Title) VALUES(?)";
	private static final String SQL__UPDATE_PROGRAM_LANGUAGE = "UPDATE ProgramLanguages SET Title = ? WHERE ProgramLanguagesId = ?";
	private static final String SQL__DELETE_PROGRAM_LANGUAGE = "DELETE ProgramLanguages WHERE ProgramLanguagesId = ?";
	
	private static final String SQL__SELECT_STUDENT_PROGRAM_LANGUAGE = "SELECT p.Title, pc.ProgramLanguagesId, pc.Level"
			+ " FROM ProgramLanguages p, ProgramLanguagesCVs pc WHERE p.ProgramLanguagesId=pc.ProgramLanguagesId and pc.CVsId =?";
	private static final String SQL__INSERT_STUENT_PROGRAM_LANGUAGE = "INSERT INTO ProgramLanguagesCVs(CVsId, ProgramLanguagesId, Level) VALUES(?,?,?)";
	private static final String SQL__FIND_BY_TITLE = "SELECT ProgramLanguagesId FROM ProgramLanguages where Title = ?";*/

	@Override
	protected Connection getConnection() throws SQLException {
		return MYSQLDAOFactory.getConnection();
	}
}
