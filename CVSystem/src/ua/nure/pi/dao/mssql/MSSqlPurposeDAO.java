package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.PurposeDAO;
import ua.nure.pi.entity.Purpose;
import ua.nure.pi.parameter.MapperParameters;

public class MSSqlPurposeDAO implements PurposeDAO {
	
	private static volatile MSSqlPurposeDAO instance;
	
	private MSSqlPurposeDAO() {
	}
	
	public static MSSqlPurposeDAO getInstancce(){
		if(instance == null)
			synchronized (MSSqlPurposeDAO.class){
				if(instance == null)
					instance = new MSSqlPurposeDAO();
			}
		return instance;
	}
	
	private static final String SQL__SELECT_PURPOSE = "SELECT * FROM Purposes order by Title";
	private static final String SQL__INSERT_PURPOSE= "INSERT INTO Purposes(Title) VALUES(?)";
	private static final String SQL__UPDATE_PURPOSE = "UPDATE Purposes SET Title = ? WHERE PurposesId = ?";
	private static final String SQL__DELETE_PURPOSE = "DELETE Purposes WHERE PurposesId = ?";	
	private static final String SQL__FIND_PURPOSE = "SELECT * FROM Purposes where PurposesId = ?";
	private static final String SQL__FIND_BY_TITLE = "SELECT PurposesId FROM Purposes where Title = ?";

	@Override
	public Collection<Purpose> getPurposes() {
		Collection<Purpose> result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getPurposes(con);
		} catch (SQLException e) {
			System.err.println("Can not get purposes. " + e.getMessage());
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

	private Collection<Purpose> getPurposes(Connection con) throws SQLException {
		Collection<Purpose> result = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL__SELECT_PURPOSE);
			result = new ArrayList<Purpose>();
			while(rs.next()){
				Purpose fc = unMapPurpose(rs);
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
	public Purpose findPurposes(long PurposesId) {
		Purpose result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = findPurposes(PurposesId, con);
		} catch (SQLException e) {
			System.err.println(String.format("Can not get from purpose. " + e.getMessage()));
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

	public Purpose findPurposes(long purposesId, Connection con) throws SQLException {
		Purpose result = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__FIND_PURPOSE);
			pstmt.setLong(1, purposesId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				result = unMapPurpose(rs);
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
	public Boolean insertPurposes(Collection<Purpose> purposes) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = insertPurposes(purposes, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert purposes. " + e.getMessage());
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

	private Boolean insertPurposes(Collection<Purpose> purposes, Connection con) throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__INSERT_PURPOSE);
			for (Purpose at : purposes) {
				
				mapPurposeForInsert(at, pstmt);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == purposes.size();
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
	public Boolean deletePurposes(Collection<Purpose> purposes) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = deletePurposes(purposes, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not delete purposes. " + e.getMessage());
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

	private Boolean deletePurposes(Collection<Purpose> purposes, Connection con) throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__DELETE_PURPOSE);
			for (Purpose at : purposes) {
				mapPurposeForDelete(at, pstmt);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == purposes.size();
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
	public Boolean updatePurposes(Collection<Purpose> purposes) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = updatePurposes(purposes, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not update purposes. " + e.getMessage());
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

	private Boolean updatePurposes(Collection<Purpose> purposes, Connection con) throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__UPDATE_PURPOSE);
			for (Purpose at : purposes) {
				mapPurposeForUpdate(at, pstmt);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == purposes.size();
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
	
	private Purpose unMapPurpose(ResultSet rs) throws SQLException{
		Purpose at = new Purpose();
		at.setId(rs.getLong(MapperParameters.PURPOSE__ID));
		at.setTitle(rs.getString(MapperParameters.ANY_TAG_TITLE));
		return at;
	}
	
	private void mapPurposeForInsert(Purpose at, PreparedStatement pstmt) 
			throws SQLException{
		pstmt.setString(1, at.getTitle());
	}
	
	private void mapPurposeForUpdate(Purpose at, PreparedStatement pstmt) throws SQLException{
		mapPurposeForInsert(at, pstmt);
		pstmt.setLong(2,at.getId());
	}
	
	private void mapPurposeForDelete(Purpose at, PreparedStatement pstmt) throws SQLException {
		pstmt.setLong(1, at.getId());
	}

	@Override
	public Boolean insertPurposeAndGenerateKey(Purpose purpose) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = insertPurposeAndGenerateKey(purpose, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert purposes. " + e.getMessage());
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

	private Boolean insertPurposeAndGenerateKey(Purpose purpose, Connection con) 
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			pstmt2 = con.prepareStatement(SQL__FIND_BY_TITLE);
			mapPurposeForInsert(purpose, pstmt2);
			ResultSet merged = pstmt2.executeQuery();
			if(merged.next()){
				purpose.setId(merged.getLong(1));
				return true;
			}
			pstmt = con.prepareStatement(SQL__INSERT_PURPOSE, Statement.RETURN_GENERATED_KEYS);
			mapPurposeForInsert(purpose, pstmt);
			result = 1==pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				purpose.setId(rs.getLong(1));
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
			if (pstmt2 != null) {
				try {
					pstmt2.close();
				} catch (SQLException e) {
					System.err.println("Can not close searched statement. " + e.getMessage());
				}
			}
		}
		return result;
	}
}
