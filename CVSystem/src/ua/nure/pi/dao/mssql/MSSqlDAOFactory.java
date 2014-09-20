package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ua.nure.pi.dao.*;

public class MSSqlDAOFactory extends DAOFactory {

	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DB_URL = "jdbc:sqlserver://localhost; instanceName=TEMPUSMSSQL; database=FitnessUA; user=sa; password=master;";

	public MSSqlDAOFactory() {
	}

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
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			con.setAutoCommit(false);
		} catch (SQLException e) {
			System.err.println("Can not get connection. # " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Can not load driver. # " + e.getMessage());
		}
		return con;
	}

	@Override
	public UserDAO getUserDAO() {
		return new MSSqlUserDAO();
	}

	@Override
	public FacultyGroupDAO getFacultyGroupDAO() {
		return new MSSqlFacultyGroupDAO();
	}


}
