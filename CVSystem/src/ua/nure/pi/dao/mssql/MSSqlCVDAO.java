package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.nure.pi.dao.CVDAO;
import ua.nure.pi.entity.CV;
import ua.nure.pi.parameter.MapperParameters;

public class MSSqlCVDAO implements CVDAO {
	
	private static volatile MSSqlCVDAO instance;
	
	private MSSqlCVDAO() {
	}
	
	public static MSSqlCVDAO getInstancce(){
		if(instance == null)
			synchronized (MSSqlCVDAO.class){
				if(instance == null)
					instance = new MSSqlCVDAO();
			}
		return instance;
	}
	
	private static final String SQL__INSERT_CV = "INSERT INTO CVs(PurposesId, Qualities, Others, DateStamp, CVsId) VALUES(?, ?, ?, getdate(), ?)";
	private static final String SQL__SELECT_CV = "SELECT * FROM CVs WHERE CVsId=?";

	@Override
	public boolean insertCV(CV cv) {
		boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = insertCV(cv, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert CV. " + e.getMessage());
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

	public boolean insertCV(CV cv, Connection con) throws SQLException {
		boolean result = false;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__INSERT_CV);
			mapCV(cv, pstmt);
			if(pstmt.executeUpdate()!=1)
				return false;
			result = MSSqlEducationDAO.getInstancce()
						.insertEducations(cv.getCvsId(), cv.getEducations(), con) &&
					MSSqlLanguageDAO.getInstancce()
						.addLanguage(cv.getCvsId(), cv.getLanguages(), con) &&
					MSSqlProgramLanguageDAO.getInstancce()
						.addProgramLanguage(cv.getCvsId(), cv.getProgramLanguages(), con) &&
					MSSqlSertificatsDAO.getInstancce()
						.insertSertificats(cv.getCvsId(), cv.getSertificates(), con) &&
					MSSqlWorkExpDAO.getInstancce()
						.insertWorkExps(cv.getCvsId(), cv.getWorkExps(), con);
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

	private void mapCV(CV cv, PreparedStatement pstmt) throws SQLException {
		pstmt.setLong(1, cv.getPurpose().getId());
		pstmt.setString(2, cv.getQualities());
		pstmt.setString(3, cv.getOthers());
		pstmt.setLong(4, cv.getCvsId());
	}

	@Override
	public CV getCv(long CVsId) {
		CV result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getCv(CVsId, con);
		} catch (SQLException e) {
			System.err.println("Can not get cv." + e.getMessage());
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

	public CV getCv(long cVsId, Connection con) throws SQLException {
		CV result = null;
		PreparedStatement pstmt = null;
		try {
			result = new CV();
			result.setCvsId(cVsId);
			result.setEducations(MSSqlEducationDAO.getInstancce().getEducations(cVsId, con));
			result.setLanguages(MSSqlLanguageDAO.getInstancce().getStudentsLanguages(cVsId, con));
			result.setProgramLanguages(MSSqlProgramLanguageDAO.getInstancce().getStudentsProgramLanguages(cVsId, con));
			result.setSertificates(MSSqlSertificatsDAO.getInstancce().getSertificats(cVsId, con));
			result.setWorkExps(MSSqlWorkExpDAO.getInstancce().getWorkExps(cVsId, con));
			
			pstmt = con.prepareStatement(SQL__SELECT_CV);
			pstmt.setLong(1, cVsId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				result.setPurpose(MSSqlPurposeDAO.getInstancce()
						.findPurposes(rs.getLong(MapperParameters.CV__PURPOSES_ID), con));
				result.setQualities(rs.getString(MapperParameters.CV__QUALITIES));
				result.setOthers(rs.getString(MapperParameters.CV__OTHERS));
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
}
