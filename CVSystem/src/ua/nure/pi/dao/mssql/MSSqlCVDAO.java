package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.nure.pi.dao.CVDAO;
import ua.nure.pi.entity.CV;

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
	
	private static final String SQL__INSERT_CV = "INSERT INTO CVs DEFAULT VALUES";

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
			pstmt = con.prepareStatement(SQL__INSERT_CV, Statement.RETURN_GENERATED_KEYS);
			if(pstmt.executeUpdate()!=1)
				return false;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				long id = rs.getLong(1);
				cv.setCvsId(id);
				result = MSSqlEducationDAO.getInstancce()
							.insertEducations(id, cv.getEducations(), con) &&
						MSSqlLanguageDAO.getInstancce()
							.addLanguage(id, cv.getLanguages(), con) &&
						MSSqlProgramLanguageDAO.getInstancce()
							.addProgramLanguage(id, cv.getProgramLanguages(), con) &&
						MSSqlSertificatsDAO.getInstancce()
							.insertSertificats(id, cv.getSertificates(), con) &&
						MSSqlWorkExpDAO.getInstancce()
							.insertWorkExps(id, cv.getWorkExps(), con);
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

	private CV getCv(long cVsId, Connection con) throws SQLException {
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
