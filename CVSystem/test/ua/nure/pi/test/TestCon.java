package ua.nure.pi.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class TestCon {

private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	/**
	 * method to create MSSQL connections
	 * 
	 * @return
	 * @throws SQLException
	 *//*
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
	*/
	public static void main(String[] args) throws Exception {
		Connection con = null;
			Class.forName(DRIVER);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cvsystem","root","");
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			PreparedStatement pstmt = con.prepareStatement("select * from Languages");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("Title"));
			}
	}

}
