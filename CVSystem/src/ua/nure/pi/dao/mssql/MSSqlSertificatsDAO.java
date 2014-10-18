package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.SertificatsDAO;
import ua.nure.pi.entity.Sertificate;
import ua.nure.pi.parameter.MapperParameters;

public class MSSqlSertificatsDAO implements SertificatsDAO {
	
	private static volatile MSSqlSertificatsDAO instance;
	
	private MSSqlSertificatsDAO() {
	}
	
	public static MSSqlSertificatsDAO getInstancce(){
		if(instance == null)
			synchronized (MSSqlSertificatsDAO.class){
				if(instance == null)
					instance = new MSSqlSertificatsDAO();
			}
		return instance;
	}
	
	private static final String SQL__SELECT_SERTIFICATE = "SELECT * FROM Sertificate WHERE CVsId = ?";
	private static final String SQL__INSERT_SERTIFICATE = "INSERT INTO Sertificate(Name, Year, "
			+ "CVsId) VALUES(?,?,?)";


	@Override
	public Boolean insertSertificats(long CVsId, Collection<Sertificate> eds) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = insertSertificats(CVsId, eds, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert educations. " + e.getMessage());
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

	public Boolean insertSertificats(long cVsId, Collection<Sertificate> eds, Connection con) 
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			for (Sertificate ed : eds) {
				pstmt = con.prepareStatement(SQL__INSERT_SERTIFICATE);
				mapSertificateForInsert(ed, pstmt);
				if(pstmt.executeUpdate()!=1)
					return false;
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
	


	@Override
	public Collection<Sertificate> getSertificats(long CVsId) {
		Collection<Sertificate> result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getSertificats(CVsId, con);
		} catch (SQLException e) {
			System.err.println("Can not get groups." + e.getMessage());
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
	
	public Collection<Sertificate> getSertificats(long CVsId, Connection con) throws SQLException {
		Collection<Sertificate> result = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_SERTIFICATE);
			pstmt.setLong(1, CVsId);
			ResultSet rs = pstmt.executeQuery();
			result = new ArrayList<Sertificate>();
			while(rs.next()){
				Sertificate ed = unMapSertificate(rs);
				result.add(ed);
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

	private Sertificate unMapSertificate(ResultSet rs) throws SQLException {
		Sertificate ed = new Sertificate();
		ed.setSertificateId(rs.getLong(MapperParameters.SERTIFICATE__ID));
		ed.setSertificateName(rs.getString(MapperParameters.SERTIFICATE__NAME));
		ed.setSertificateYear(rs.getInt(MapperParameters.SERTIFICATE__YEAR));
		ed.setCVsId(rs.getLong(MapperParameters.SERTIFICATE__CVsID));
		return ed;
	}
	
	private void mapSertificateForInsert(Sertificate ed, PreparedStatement pstmt) 
			throws SQLException{
		pstmt.setString(1, ed.getSertificateName());
		pstmt.setInt(2, ed.getSertificateYear());
		pstmt.setLong(3, ed.getCVsId());
		
	}
}