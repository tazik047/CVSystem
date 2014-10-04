package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.AnyTagDAO;
import ua.nure.pi.entity.AnyTag;
import ua.nure.pi.parameter.MapperParameters;

public class MSSqlAnyTagDAO implements AnyTagDAO {
	
	private static final String SQL__SELECT_ANY_TAG = "SELECT * FROM %s";
	private static final String SQL__INSERT_ANY_TAG = "INSERT INTO %s(Title) VALUES(?)";
	private static final String SQL__UPDATE_ANY_TAG = "UPDATE %1$s SET Title = ? WHERE %2$sId = ?";
	private static final String SQL__DELETE_ANY_TAG = "DELETE %1$s WHERE %2$sId = ?";
	
	private static final String SQL__SELECT_STUDENT_ANY_TAG = "SELECT * FROM %1$s WHERE %1$sId =?";
	private static final String SQL__INSERT_STUENT_ANY_TAG = "INSERT INTO %1$sCVs(CVsId, %2$SId) VALUES(?,?)";

	@Override
	public Collection<AnyTag> getAnyTags(String tableName) {
		Collection<AnyTag> result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getAnyTags(tableName, con);
		} catch (SQLException e) {
			System.err.println(String.format("Can not get %1$s. %2$s",tableName ,e.getMessage()));
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

	private Collection<AnyTag> getAnyTags(String tableName, Connection con) throws SQLException {
		Collection<AnyTag> result = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format(SQL__SELECT_ANY_TAG, tableName));
			result = new ArrayList<AnyTag>();
			while(rs.next()){
				AnyTag fc = unMapAnyTag(tableName, rs);
				result.add(fc);
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

	@Override
	public Boolean insertAnyTag(Collection<AnyTag> anyTags) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = insertAnyTag(anyTags, con);
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

	private Boolean insertAnyTag(Collection<AnyTag> anyTags, Connection con) 
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			for (AnyTag at : anyTags) {
				pstmt = con.prepareStatement(String.format(SQL__INSERT_ANY_TAG, at.getTableName()));
				mapAnyTagForInsert(at, pstmt);
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
	public Boolean deleteAnyTag(Collection<AnyTag> anyTags) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = deleteAnyTag(anyTags, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not delete anyTags. " + e.getMessage());
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

	private Boolean deleteAnyTag(Collection<AnyTag> anyTags, Connection con) 
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			
			for (AnyTag at : anyTags) {
				pstmt = con.prepareStatement(String.format(SQL__DELETE_ANY_TAG, at.getTableName(),
						at.getTableName()));
				mapAnyTagForDelete(at, pstmt);
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
	public Boolean updateAnyTags(Collection<AnyTag> anyTags) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = updateAnyTag(anyTags, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not update anyTags. " + e.getMessage());
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

	private Boolean updateAnyTag(Collection<AnyTag> anyTags, Connection con)
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			
			for (AnyTag at : anyTags) {
				pstmt = con.prepareStatement(String.format(SQL__UPDATE_ANY_TAG, at.getTableName(),
						at.getTableName()));
				mapAnyTagForUpdate(at, pstmt);
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
	public Boolean addAnyTags(long id, String tableName, Collection<AnyTag> anyTags) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = addAnyTag(id, tableName, anyTags, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not add anyTags. " + e.getMessage());
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

	private Boolean addAnyTag(long id, String tableName,
			Collection<AnyTag> anyTags, Connection con) throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(String.format(SQL__INSERT_STUENT_ANY_TAG, tableName,
					tableName));
			for (AnyTag at : anyTags) {
				mapAnyTagForStudent(at, id, pstmt);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == anyTags.size();
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
	public Collection<AnyTag> getStudentsAnyTags(String tableName, long CVsId) {
		Collection<AnyTag> result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getStudentsAnyTags(tableName, CVsId, con);
		} catch (SQLException e) {
			System.err.println(String.format("Can not get from %1$s. %2$s",tableName ,e.getMessage()));
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

	private Collection<AnyTag> getStudentsAnyTags(String tableName,
			long CVsId, Connection con) throws SQLException {
		Collection<AnyTag> result = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_STUDENT_ANY_TAG);
			pstmt.setLong(1, CVsId);
			ResultSet rs = pstmt.executeQuery();
			result = new ArrayList<AnyTag>();
			while(rs.next()){
				AnyTag fc = unMapAnyTag(tableName, rs);
				result.add(fc);
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

	private AnyTag unMapAnyTag(String tableName, ResultSet rs) throws SQLException{
		AnyTag at = new AnyTag(tableName);
		at.setId(rs.getLong(tableName + "Id"));
		at.setTitle(rs.getString(MapperParameters.ANY_TAG_TITLE));
		return at;
	}
	
	private void mapAnyTagForInsert(AnyTag at, PreparedStatement pstmt) 
			throws SQLException{
		pstmt.setString(1, at.getTitle());
	}
	
	private void mapAnyTagForStudent(AnyTag at, long id, PreparedStatement pstmt) throws SQLException{
		pstmt.setLong(1, id);
		pstmt.setLong(2, at.getId());
	}
	
	private void mapAnyTagForUpdate(AnyTag at, PreparedStatement pstmt) throws SQLException{
		mapAnyTagForInsert(at, pstmt);
		pstmt.setLong(2,at.getId());
	}
	
	private void mapAnyTagForDelete(AnyTag at, PreparedStatement pstmt) throws SQLException {
		pstmt.setLong(1, at.getId());
	}
}
