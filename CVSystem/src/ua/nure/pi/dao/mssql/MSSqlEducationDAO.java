package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.EducationDAO;
import ua.nure.pi.dao.WorkExpDAO;
import ua.nure.pi.entity.AnyTag;
import ua.nure.pi.entity.Education;
import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Group;
import ua.nure.pi.entity.WorkExp;
import ua.nure.pi.entity.typeOfDuration;
import ua.nure.pi.parameter.MapperParameters;

public class MSSqlEducationDAO implements EducationDAO {
	
	private static final String SQL__SELECT_EDUCATION = "SELECT * FROM Educations WHERE StudentId = ?";
	private static final String SQL__INSERT_EDUCATION = "INSERT INTO Educations VALUES(?,?,?,?,?,?,?)";


	@Override
	public Boolean insertEducations(Collection<Education> eds) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = insertEducations(eds, con);
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

	private Boolean insertEducations(Collection<Education> eds, Connection con) 
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			for (Education ed : eds) {
				pstmt = con.prepareStatement(SQL__INSERT_EDUCATION);
				mapEducationForInsert(ed, pstmt);
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
	public Collection<Education> getEducations(long studentId) {
		Collection<Education> result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getEducations(studentId, con);
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
	
	private Collection<Education> getEducations(long studentId, Connection con) throws SQLException {
		Collection<Education> result = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_EDUCATION);
			pstmt.setLong(1, studentId);
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
		/*
		WorkExp we = new WorkExp();
		we.setWorkExpsId(rs.getLong(MapperParameters.WORKEXP_ID));
		we.setStartYear(rs.getInt(MapperParameters.WORKEXP_START));
		we.setDuration(rs.getInt(MapperParameters.WORKEXP_DURATION));
		int type = rs.getInt(MapperParameters.WORKEXP_TYPEDURATION);
		switch (type) {
		case  0 : 
			we.setTypeOfDuration(typeOfDuration.WEEK);
			break;
		case 1 :
			we.setTypeOfDuration(typeOfDuration.MONTH);
			break;
		case 2 :
			we.setTypeOfDuration(typeOfDuration.YEAR);
			break;
		}	
		we.setNameOfInstitution(rs.getString(MapperParameters.WORKEXP_NAMEOFINSTITUTION));
		we.setRole(rs.getString(MapperParameters.WORKEXP_ROLE));
		we.setCVsId(rs.getLong(MapperParameters.WORKEXP_CVsId));
		return we;
		*/
		return ed;
	}
	
	private void mapEducationForInsert(Education ed, PreparedStatement pstmt) 
			throws SQLException{
		/*
		pstmt.setLong(1, we.getWorkExpsId());
		pstmt.setInt(2, we.getStartYear());
		pstmt.setInt(3, we.getStartYear());
		switch (we.getTypeOfDuration()) {
		case WEEK : 
			pstmt.setInt(4, 0);
			break;
		case MONTH :
			pstmt.setInt(4, 1);
			break;
		case YEAR :
			pstmt.setInt(4, 2);
			break;
		}			
		pstmt.setString(5, we.getNameOfInstitution());
		pstmt.setString(6, we.getRole());
		pstmt.setLong(7, we.getCVsId());
		*/
	}
}
