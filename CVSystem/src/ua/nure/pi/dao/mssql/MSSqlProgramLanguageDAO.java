package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.ProgramLanguageDAO;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.parameter.MapperParameters;

public class MSSqlProgramLanguageDAO implements ProgramLanguageDAO {
	
	private static volatile MSSqlProgramLanguageDAO instance;
	
	private MSSqlProgramLanguageDAO() {
	}
	
	public static MSSqlProgramLanguageDAO getInstancce(){
		if(instance == null)
			synchronized (MSSqlProgramLanguageDAO.class){
				if(instance == null)
					instance = new MSSqlProgramLanguageDAO();
			}
		return instance;
	}
	
	private static final String SQL__SELECT_PROGRAM_LANGUAGE = "SELECT * FROM ProgramLanguages";
	private static final String SQL__INSERT_PROGRAM_LANGUAGE = "INSERT INTO ProgramLanguages(Title) VALUES(?)";
	private static final String SQL__UPDATE_PROGRAM_LANGUAGE = "UPDATE ProgramLanguages SET Title = ? WHERE ProgramLanguagesId = ?";
	private static final String SQL__DELETE_PROGRAM_LANGUAGE = "DELETE ProgramLanguages WHERE ProgramLanguagesId = ?";
	
	private static final String SQL__SELECT_STUDENT_PROGRAM_LANGUAGE = "SELECT p.Title, pc.ProgramLanguagesId"
			+ " FROM ProgramLanguages p, ProgramLanguagesCVs pc WHERE p.ProgramLanguagesId=pc.ProgramLanguagesId and pc.CVsId =?";
	private static final String SQL__INSERT_STUENT_PROGRAM_LANGUAGE = "INSERT INTO ProgramLanguagesCVs(CVsId, ProgramLanguagesId) VALUES(?,?)";

	@Override
	public Collection<ProgramLanguage> getProgramLanguages() {
		Collection<ProgramLanguage> result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getProgramLanguages(con);
		} catch (SQLException e) {
			System.err.println(String.format("Can not get ProgramLanguages. " + e.getMessage()));
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

	private Collection<ProgramLanguage> getProgramLanguages(Connection con) throws SQLException {
		Collection<ProgramLanguage> result = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL__SELECT_PROGRAM_LANGUAGE);
			result = new ArrayList<ProgramLanguage>();
			while(rs.next()){
				ProgramLanguage fc = unMapProgramLanguage(rs);
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
	public Boolean insertProgramLanguage(Collection<ProgramLanguage> programLanguages) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = insertProgramLanguage(programLanguages, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert programLanguages. " + e.getMessage());
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

	private Boolean insertProgramLanguage(Collection<ProgramLanguage> programLanguages, Connection con) 
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__INSERT_PROGRAM_LANGUAGE);
			for (ProgramLanguage at : programLanguages) {
				mapProgramLanguageForInsert(at, pstmt);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == programLanguages.size();
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
	public Boolean deleteProgramLanguage(Collection<ProgramLanguage> programLanguages) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = deleteProgramLanguage(programLanguages, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not delete programLanguages. " + e.getMessage());
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

	private Boolean deleteProgramLanguage(Collection<ProgramLanguage> programLanguages, Connection con) 
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__DELETE_PROGRAM_LANGUAGE);
			for (ProgramLanguage at : programLanguages) {
				mapProgramLanguageForDelete(at, pstmt);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == programLanguages.size();
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
	public Boolean updateProgramLanguages(Collection<ProgramLanguage> programLanguages) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = updateProgramLanguage(programLanguages, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not update programLanguages. " + e.getMessage());
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

	private Boolean updateProgramLanguage(Collection<ProgramLanguage> programLanguages, Connection con)
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__UPDATE_PROGRAM_LANGUAGE);
			for (ProgramLanguage at : programLanguages) {
				mapProgramLanguageForUpdate(at, pstmt);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == programLanguages.size();
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
	public Boolean addProgramLanguages(long id, Collection<ProgramLanguage> programLanguages) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = addProgramLanguage(id, programLanguages, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not add programLanguages. " + e.getMessage());
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

	public Boolean addProgramLanguage(long id, Collection<ProgramLanguage> programLanguages, Connection con) throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__INSERT_STUENT_PROGRAM_LANGUAGE);
			for (ProgramLanguage at : programLanguages) {
				mapProgramLanguageForStudent(at, id, pstmt);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == programLanguages.size();
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
	public Collection<ProgramLanguage> getStudentsProgramLanguages(long CVsId) {
		Collection<ProgramLanguage> result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getStudentsProgramLanguages(CVsId, con);
		} catch (SQLException e) {
			System.err.println(String.format("Can not get from ProgramLanguagesCVs. " + e.getMessage()));
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

	public Collection<ProgramLanguage> getStudentsProgramLanguages(long CVsId, Connection con) throws SQLException {
		Collection<ProgramLanguage> result = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_STUDENT_PROGRAM_LANGUAGE);
			pstmt.setLong(1, CVsId);
			ResultSet rs = pstmt.executeQuery();
			result = new ArrayList<ProgramLanguage>();
			while(rs.next()){
				ProgramLanguage fc = unMapProgramLanguage(rs);
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

	private ProgramLanguage unMapProgramLanguage(ResultSet rs) throws SQLException{
		ProgramLanguage at = new ProgramLanguage();
		at.setId(rs.getLong(MapperParameters.PROGRAM_LANGUAGE__ID));
		at.setTitle(rs.getString(MapperParameters.ANY_TAG_TITLE));
		return at;
	}
	
	private void mapProgramLanguageForInsert(ProgramLanguage at, PreparedStatement pstmt) 
			throws SQLException{
		pstmt.setString(1, at.getTitle());
	}
	
	private void mapProgramLanguageForStudent(ProgramLanguage at, long id, PreparedStatement pstmt) throws SQLException{
		pstmt.setLong(1, id);
		pstmt.setLong(2, at.getId());
	}
	
	private void mapProgramLanguageForUpdate(ProgramLanguage at, PreparedStatement pstmt) throws SQLException{
		mapProgramLanguageForInsert(at, pstmt);
		pstmt.setLong(2,at.getId());
	}
	
	private void mapProgramLanguageForDelete(ProgramLanguage at, PreparedStatement pstmt) throws SQLException {
		pstmt.setLong(1, at.getId());
	}

	@Override
	public Boolean insertProgramLanguageAndGenerateKey(
			Collection<ProgramLanguage> programLanguage) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = insertProgramLanguageAndGenerateKey(programLanguage, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert programLanguages. " + e.getMessage());
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

	private Boolean insertProgramLanguageAndGenerateKey(
			Collection<ProgramLanguage> programLanguage, Connection con) throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__INSERT_PROGRAM_LANGUAGE,
					Statement.RETURN_GENERATED_KEYS);
			for(ProgramLanguage i : programLanguage){
				mapProgramLanguageForInsert(i, pstmt);
				result = pstmt.executeUpdate() == 1;
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()){
					i.setId(rs.getLong(1));
				}
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
