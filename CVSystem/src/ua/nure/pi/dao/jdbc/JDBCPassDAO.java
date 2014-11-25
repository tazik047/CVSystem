package ua.nure.pi.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.nure.pi.dao.PassDAO;

public abstract class JDBCPassDAO implements PassDAO {
	
	protected String SQL__SELECT_PASS = "SELECT count(*) FROM Pass where AccessPass = ?";

	@Override
	public Boolean checkPass(String pass) {
		Boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
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
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_PASS);
			pstmt.setString(1, pass);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				int c = rs.getInt(1);
				if(c==1)
					result = true;
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement. " + e.getMessage());
				}
			}
		}
		return result;
	}
	
	protected abstract Connection getConnection() throws SQLException;
}
