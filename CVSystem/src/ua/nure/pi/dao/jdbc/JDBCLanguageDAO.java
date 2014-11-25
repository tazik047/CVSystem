package ua.nure.pi.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.LanguageDAO;
import ua.nure.pi.entity.Language;
import ua.nure.pi.parameter.MapperParameters;

public abstract class JDBCLanguageDAO implements LanguageDAO {
	
	protected String SQL__SELECT_LANGUAGE = "SELECT * FROM Languages";
	protected String SQL__INSERT_LANGUAGE = "INSERT INTO Languages(Title) VALUES(?)";
	protected String SQL__UPDATE_LANGUAGE = "UPDATE Languages SET Title = ? WHERE LanguagesId = ?";
	protected String SQL__DELETE_LANGUAGE;// = "DELETE Languages WHERE LanguagesId = ?";
	
	protected String SQL__SELECT_STUDENT_LANGUAGE = "select lc.LanguagesId, lc.LanguagesCVsId, l.Title, lc.Level from LanguagesCVs lc"
			+" join Languages l on lc.LanguagesId = l.LanguagesId where lc.CVsId=?";
	protected String SQL__INSERT_STUENT_LANGUAGE = "INSERT INTO LanguagesCVs(CVsId, LanguagesId, Level) VALUES(?,?,?)";

	@Override
	public Collection<Language> getLanguages() {
		Collection<Language> result = null;
		Connection con = null;
		try {
			con = getConnection();
			result = getLanguages(con);
		} catch (SQLException e) {
			System.err.println("Can not get languages. " + e.getMessage());
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

	private Collection<Language> getLanguages(Connection con) throws SQLException {
		Collection<Language> result = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL__SELECT_LANGUAGE);
			result = new ArrayList<Language>();
			while(rs.next()){
				Language fc = unMapLanguage(rs);
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
	public Boolean insertLanguage(Collection<Language> languages) {
		Boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = insertLanguage(languages, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert languages. " + e.getMessage());
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

	private Boolean insertLanguage(Collection<Language> languages, Connection con) 
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__INSERT_LANGUAGE);
			for (Language at : languages) {
				
				mapLanguageForInsert(at, pstmt);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == languages.size();
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
	public Boolean deleteLanguage(Collection<Language> languages) {
		Boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = deleteLanguage(languages, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not delete languages. " + e.getMessage());
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

	private Boolean deleteLanguage(Collection<Language> languages, Connection con) 
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__DELETE_LANGUAGE);
			for (Language at : languages) {
				mapLanguageForDelete(at, pstmt);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == languages.size();
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
	public Boolean updateLanguages(Collection<Language> languages) {
		Boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = updateLanguage(languages, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not update languages. " + e.getMessage());
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

	private Boolean updateLanguage(Collection<Language> languages, Connection con)
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__UPDATE_LANGUAGE);
			for (Language at : languages) {
				mapLanguageForUpdate(at, pstmt);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == languages.size();
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
	public Boolean addLanguages(long id, Collection<Language> languages) {
		boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = addLanguage(id, languages, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not add languages. " + e.getMessage());
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
	public boolean addLanguage(long id, Collection<Language> languages, Connection con) throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__INSERT_STUENT_LANGUAGE);
			for (Language at : languages) {
				mapLanguageForStudent(at, id, pstmt);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == languages.size();
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
	public Collection<Language> getStudentsLanguages(long CVsId) {
		Collection<Language> result = null;
		Connection con = null;
		try {
			con = getConnection();
			result = getStudentsLanguages(CVsId, con);
		} catch (SQLException e) {
			System.err.println(String.format("Can not get from languages. " + e.getMessage()));
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
	public Collection<Language> getStudentsLanguages(long CVsId, Connection con) 
			throws SQLException {
		Collection<Language> result = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_STUDENT_LANGUAGE);
			pstmt.setLong(1, CVsId);
			ResultSet rs = pstmt.executeQuery();
			result = new ArrayList<Language>();
			while(rs.next()){
				Language fc = unMapLanguageForStudent(rs);
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

	private Language unMapLanguageForStudent(ResultSet rs) throws SQLException{
		Language at = unMapLanguage(rs);
		at.setLevel(rs.getInt(MapperParameters.LANGUAGE__LEVEL));
		return at;
	}
	
	private Language unMapLanguage(ResultSet rs) throws SQLException {
		Language at = new Language();
		at.setId(rs.getLong(MapperParameters.LANGUAGE__ID));
		at.setTitle(rs.getString(MapperParameters.ANY_TAG_TITLE));
		return at;
	}

	private void mapLanguageForInsert(Language at, PreparedStatement pstmt) 
			throws SQLException{
		pstmt.setString(1, at.getTitle());
	}
	
	private void mapLanguageForStudent(Language at, long id, PreparedStatement pstmt) throws SQLException{
		pstmt.setLong(1, id);
		pstmt.setLong(2, at.getId());
		pstmt.setInt(3, at.getLevel());
	}
	
	private void mapLanguageForUpdate(Language at, PreparedStatement pstmt) throws SQLException{
		mapLanguageForInsert(at, pstmt);
		pstmt.setLong(2,at.getId());
	}
	
	private void mapLanguageForDelete(Language at, PreparedStatement pstmt) throws SQLException {
		pstmt.setLong(1, at.getId());
	}
	
	protected abstract Connection getConnection() throws SQLException;
}
