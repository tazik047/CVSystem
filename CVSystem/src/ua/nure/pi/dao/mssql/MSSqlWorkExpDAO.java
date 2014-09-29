package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.WorkExpDAO;
import ua.nure.pi.entity.AnyTag;
import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Group;
import ua.nure.pi.entity.WorkExp;
import ua.nure.pi.entity.typeOfDuration;
import ua.nure.pi.parameter.MapperParameters;

public class MSSqlWorkExpDAO implements WorkExpDAO {
	
	private static final String SQL__SELECT_WORKEXP = "SELECT * FROM WorkExp WHERE StudentId = ?";
	private static final String SQL__INSERT_WORKEXP = "INSERT INTO WorkExp VALUES(?,?,?,?,?,?,?)";


	@Override
	public Boolean insertWorkExps(Collection<WorkExp> wes) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = insertWorkExps(wes, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert anyTags. " + e.getMessage());
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

	private Boolean insertWorkExps(Collection<WorkExp> wes, Connection con) 
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			for (WorkExp we : wes) {
				pstmt = con.prepareStatement(SQL__INSERT_WORKEXP);
				mapWorkExpForInsert(we, pstmt);
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
	public Collection<WorkExp> getWorkExps(long studentId) {
		Collection<WorkExp> result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getWorkExps(studentId, con);
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
	
	private Collection<WorkExp> getWorkExps(long studentId, Connection con) throws SQLException {
		Collection<WorkExp> result = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_WORKEXP);
			pstmt.setLong(1, studentId);
			ResultSet rs = pstmt.executeQuery();
			result = new ArrayList<WorkExp>();
			while(rs.next()){
				WorkExp gr = unMapWorkExp(rs);
				result.add(gr);
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

	private WorkExp unMapWorkExp(ResultSet rs) throws SQLException {
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
	}
	
	private void mapWorkExpForInsert(WorkExp we, PreparedStatement pstmt) 
			throws SQLException{
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
	}

}