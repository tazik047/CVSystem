package ua.nure.pi.dao.jdbc.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jfork.nproperty.Cfg;
import jfork.nproperty.ConfigParser;
import ua.nure.pi.dao.*;

@Cfg(prefix = "db.")
public class MSSQLDAOFactory extends DAOFactory {

	private static volatile MSSQLDAOFactory instance;
	
	protected static String sqlLink;
	
	protected static String user;
	
	protected static String password;
	
	protected static String host;
	
	protected static String port;
	
	protected static String database;
	
	protected static String DRIVER;
	
	private static boolean first = true;
	
	private MSSQLDAOFactory() {
		try {
			ClassLoader loader = this.getClass().getClassLoader();
			ConfigParser.parse(MSSQLDAOFactory.class, 
					loader.getResource("properties.ini").getPath().replaceAll("%20", " "));
			DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			sqlLink = "jdbc:sqlserver://"
					+host + ":"+ port + ";instanceName=SQLSERVER;database="
					+database;//user="+user+";password="+password;
			first = false;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	public static MSSQLDAOFactory getInstancce(){
		if(instance == null)
			synchronized (MSSQLDAOFactory.class){
				if(instance == null)
					instance = new MSSQLDAOFactory();
			}
		return instance;
	}
	
	//private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	/**
	 * method to create MSSQL connections
	 * 
	 * @return
	 * @throws SQLException
	 */
	protected static synchronized Connection getConnection()
			throws SQLException {
		Connection con = null;
		if(first){
			MSSQLDAOFactory.getInstancce();
			first = false;
		}
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(sqlLink, user, password);
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
		return MSSQLUserDAO.getInstancce();
	}

	@Override
	public FacultyGroupDAO getFacultyGroupDAO() {
		return MSSQLFacultyGroupDAO.getInstancce();
	}

	@Override
	public EducationDAO getEducationDAO() {
		return MSSQLEducationDAO.getInstancce();
	}

	@Override
	public LanguageDAO getLanguageDAO() {
		return MSSQLLanguageDAO.getInstancce();
	}

	@Override
	public ProgramLanguageDAO getProgramLanguageDAO() {
		return MSSQLProgramLanguageDAO.getInstancce();
	}

	@Override
	public SertificatsDAO getSertificatsDAO() {
		return MSSQLSertificatsDAO.getInstancce();
	}

	@Override
	public StudentDAO getStudentDAO() {
		return MSSQLStudentDAO.getInstancce();
	}

	@Override
	public WorkExpDAO getWorkExpDAO() {
		return MSSQLWorkExpDAO.getInstancce();
	}

	@Override
	public PurposeDAO getPurposeDAO() {
		return MSSQLPurposeDAO.getInstancce();
	}

	@Override
	public CVDAO getCVDAO() {
		return MSSQLCVDAO.getInstancce();
	}

	@Override
	public PassDAO getPassDAO() {
		return MSSQLPassDAO.getInstancce();
	}

	@Override
	public CompanyDAO getCompanyDAO() {
		return MSSQLCompanyDAO.getInstancce();
	}
	


}
