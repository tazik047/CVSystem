package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ua.nure.pi.dao.*;

public class MSSqlDAOFactory extends DAOFactory {

	private static volatile MSSqlDAOFactory instance;
	
	private MSSqlDAOFactory() {
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
	private static final String DB_URL = "jdbc:sqlserver://localhost:1433; instanceName=SQLSERVER; database=CVSystem; user=sa; password=master;";

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
			con = DriverManager.getConnection(DB_URL);
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
	


}
