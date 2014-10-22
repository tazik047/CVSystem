package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.EducationDAO;
import ua.nure.pi.entity.Education;
import ua.nure.pi.parameter.MapperParameters;

public class MSSqlEducationDAO implements EducationDAO {
	
	private static volatile MSSqlEducationDAO instance;
	
	private MSSqlEducationDAO() {
	}
	
	public static MSSqlEducationDAO getInstancce(){
		if(instance == null)
			synchronized (MSSqlEducationDAO.class){
				if(instance == null)
					instance = new MSSqlEducationDAO();
			}
		return instance;
	}
	
	private static final String SQL__SELECT_EDUCATION = "SELECT * FROM Educations WHERE CVsId = ?";
	private static final String SQL__INSERT_EDUCATION = "INSERT INTO Educations(StartYear, EndYear, "
			+ "NameOfInstitution, Specialty, CVsId) VALUES(?,?,?,?,?)";


	@Override
	public Boolean insertEducations(long CVsId, Collection<Education> eds) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = insertEducations(CVsId, eds, con);
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

	public Boolean insertEducations(long cVsId, Collection<Education> eds, Connection con) 
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			for (Education ed : eds) {
				pstmt = con.prepareStatement(SQL__INSERT_EDUCATION);
				mapEducationForInsert(cVsId, ed, pstmt);
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
	public Collection<Education> getEducations(long CVsId) {
		Collection<Education> result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getEducations(CVsId, con);
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
	
	public Collection<Education> getEducations(long CVsId, Connection con) throws SQLException {
		Collection<Education> result = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_EDUCATION);
			pstmt.setLong(1, CVsId);
			ResultSet rs = pstmt.executeQuery();
			result = new ArrayList<Education>();
			while(rs.next()){
				Education ed = unMapEducation(rs);
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

	private Education unMapEducation(ResultSet rs) throws SQLException {
		Education ed = new Education();
		ed.setEducationId(rs.getLong(MapperParameters.EDUCATION__ID));
		ed.setStartYear(rs.getInt(MapperParameters.EDUCATION__START));
		ed.setEndYear(rs.getInt(MapperParameters.EDUCATION__END));
		ed.setNameOfInstitution(rs.getString(MapperParameters.EDUCATION__NAMEOFINSTITUTION));
		ed.setSpecialty(rs.getString(MapperParameters.EDUCATION__SPECIALTY));
		ed.setCVsId(rs.getLong(MapperParameters.EDUCATION__CVsId));
		return ed;
	}
	
	private void mapEducationForInsert(long cVsId, Education ed, PreparedStatement pstmt) 
			throws SQLException{
		pstmt.setInt(1, ed.getStartYear());
		pstmt.setInt(2, ed.getEndYear());
		pstmt.setString(3, ed.getNameOfInstitution());
		pstmt.setString(4, ed.getSpecialty());
		pstmt.setLong(5, cVsId);
		
	}
}
