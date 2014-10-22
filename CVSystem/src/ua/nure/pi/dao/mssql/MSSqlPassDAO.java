package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.AnyTagDAO;
import ua.nure.pi.dao.PassDAO;
import ua.nure.pi.entity.AnyTag;
import ua.nure.pi.parameter.MapperParameters;

public class MSSqlPassDAO implements PassDAO {
	
	private static volatile MSSqlPassDAO instance;
	
	private MSSqlPassDAO() {
	}
	
	public static MSSqlPassDAO getInstancce(){
		if(instance == null)
			synchronized (MSSqlPassDAO.class){
				if(instance == null)
					instance = new MSSqlPassDAO();
			}
		return instance;
	}
	
	private static final String SQL__SELECT_PASS = "SELECT count(*) FROM Pass where AcceptPass = ?";

	@Override
	public Boolean checkPass(String pass) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = checkPass(pass, con);
		} catch (SQLException e) {
			System.err.println("Can not get pass" + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection. " + e.getMessage());
			}
		}
		return result;
	}

	private Boolean checkPass(String pass, Connection con) throws SQLException {
		Boolean result = false;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL__SELECT_PASS);
			if(rs.next()){
				int c = rs.getInt(1);
				if(c==1)
					result = true;
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement. " + e.getMessage());
				}
			}
		}
		return result;
	}
}
