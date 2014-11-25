package ua.nure.pi.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.WorkExpDAO;
import ua.nure.pi.entity.WorkExp;
import ua.nure.pi.entity.typeOfDuration;
import ua.nure.pi.parameter.MapperParameters;

public abstract class JDBCWorkExpDAO implements WorkExpDAO {	
	
	protected String SQL__SELECT_WORKEXP = "SELECT * FROM WorkExps WHERE CVsId = ?";
	protected String SQL__INSERT_WORKEXP = "INSERT INTO WorkExps(StartDate, "+
	"Duration, TypeDuration, NameOfInstitution,	Role, CVsId, isNow) VALUES(?,?,?,?,?,?,?)";


	
	@Override
	public Boolean insertWorkExps(long id, Collection<WorkExp> wes) {
		Boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = insertWorkExps(id, wes, con);
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

	@Override
	public boolean insertWorkExps(long id, Collection<WorkExp> wes, Connection con) 
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			for (WorkExp we : wes) {
				pstmt = con.prepareStatement(SQL__INSERT_WORKEXP);
				mapWorkExpForInsert(id, we, pstmt);
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
	public Collection<WorkExp> getWorkExps(long CVsId) {
		Collection<WorkExp> result = null;
		Connection con = null;
		try {
			con = getConnection();
			result = getWorkExps(CVsId, con);
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
	
	public Collection<WorkExp> getWorkExps(long CVsId, Connection con) throws SQLException {
		Collection<WorkExp> result = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_WORKEXP);
			pstmt.setLong(1, CVsId);
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
		we.setIsNow(rs.getBoolean(MapperParameters.WORKEXP_IS_NOW));
		return we;
	}
	
	private void mapWorkExpForInsert(long id, WorkExp we, PreparedStatement pstmt) 
			throws SQLException{
		pstmt.setInt(1, we.getStartYear());
		pstmt.setInt(2, we.getDuration());
		if(we.getTypeOfDuration()==null){
			pstmt.setInt(3, 0);
		}
		else{
			switch (we.getTypeOfDuration()) {
			case WEEK : 
				pstmt.setInt(3, 0);
				break;
			case MONTH :
				pstmt.setInt(3, 1);
				break;
			case YEAR :
				pstmt.setInt(3, 2);
				break;
			}	
		}
		pstmt.setString(4, we.getNameOfInstitution());
		pstmt.setString(5, we.getRole());
		pstmt.setLong(6, id);
		pstmt.setBoolean(7, we.getIsNow());
	}

	protected abstract Connection getConnection() throws SQLException;
}
