package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jfork.nproperty.Cfg;
import jfork.nproperty.ConfigParser;
import ua.nure.pi.dao.*;

@Cfg(prefix = "db.")
public class MSSqlDAOFactory extends DAOFactory {

	private static volatile MSSqlDAOFactory instance;
	
	@Cfg(parametrize = true)
	private static String sqlLink;
	
	
	private MSSqlDAOFactory() {
		try {
			ClassLoader loader = this.getClass().getClassLoader();
			ConfigParser.parse(MSSqlDAOFactory.class, 
					loader.getResource("properties.ini").getPath().replaceAll("%20", " "));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static MSSqlDAOFactory getInstancce(){
		if(instance == null)
			synchronized (MSSqlDAOFactory.class){
				if(instance == null)
					instance = new MSSqlDAOFactory();
			}
		return instance;
	}
	
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	/**
	 * method to create MSSQL connections
	 * 
	 * @return
	 * @throws SQLException
	 */
	protected static synchronized Connection getConnection()
			throws SQLException {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(sqlLink);
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		} catch (SQLException e) {
			System.err.println("Can not get connection. # " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Can not load driver. # " + e.getMessage());
		}
		return con;
	}

	@Override
	public UserDAO getUserDAO() {
		return MSSqlUserDAO.getInstancce();
	}

	@Override
	public FacultyGroupDAO getFacultyGroupDAO() {
		return MSSqlFacultyGroupDAO.getInstancce();
	}

	@Override
	public EducationDAO getEducationDAO() {
		return MSSqlEducationDAO.getInstancce();
	}

	@Override
	public LanguageDAO getLanguageDAO() {
		return MSSqlLanguageDAO.getInstancce();
	}

	@Override
	public ProgramLanguageDAO getProgramLanguageDAO() {
		return MSSqlProgramLanguageDAO.getInstancce();
	}

	@Override
	public SertificatsDAO getSertificatsDAO() {
		return MSSqlSertificatsDAO.getInstancce();
	}

	@Override
	public StudentDAO getStudentDAO() {
		return MSSqlStudentDAO.getInstancce();
	}

	@Override
	public WorkExpDAO getWorkExpDAO() {
		return MSSqlWorkExpDAO.getInstancce();
	}

	@Override
	public PurposeDAO getPurposeDAO() {
		return MSSqlPurposeDAO.getInstancce();
	}

	@Override
	public CVDAO getCVDAO() {
		return MSSqlCVDAO.getInstancce();
	}

	@Override
	public PassDAO getPassDAO() {
		return MSSqlPassDAO.getInstancce();
	}
	


}
