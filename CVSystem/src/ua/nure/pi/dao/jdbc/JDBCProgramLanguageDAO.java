package ua.nure.pi.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import ua.nure.pi.dao.ProgramLanguageDAO;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.parameter.MapperParameters;

public abstract class JDBCProgramLanguageDAO implements ProgramLanguageDAO {

	protected String SQL_SELECT_STUDENTS_WICH_HAVE_PROGRAM_LANGUAGE_FOR_DEVIDE = "select p1.*, p2.ProgramLanguagesId from ProgramLanguagesCVs p1, ProgramLanguagesCVs p2 where p1.CVsId=p2.CVsId and p1.ProgramLanguagesId=? and "
			+ "p2.ProgramLanguagesId<>p1.ProgramLanguagesId and p2.Level>p1.Level and (%s) "
			+ "union "
			+ "select distinct p1.*, -1 from ProgramLanguagesCVs p1, ProgramLanguagesCVs p2 where p1.CVsId=p2.CVsId and p1.ProgramLanguagesId=? and "
			+ "p2.ProgramLanguagesId<>p1.ProgramLanguagesId and not (p2.Level>p1.Level and (%s))";
	protected String SQL_DELETE_UNNEEDED_PROGRAM_LANGUAGES = "delete ProgramLanguagesCVs where Level<? and (ProgramLanguagesId = 12143 or ProgramLanguagesId = 121763) and (CVsId = 1 or CVsId = 2)";	
	protected String SQL_SELECT_STUDENTS_WICH_HAVE_ONE_OF_PROGRAM_LANGUAGE = "select CVsId, Level from ProgramLanguagesCVs where ";
	protected String SQL__SELECT_PROGRAM_LANGUAGE = "SELECT * FROM ProgramLanguages order by Title";
	protected String SQL__INSERT_PROGRAM_LANGUAGE = "INSERT INTO ProgramLanguages(Title) VALUES(?)";
	protected String SQL__UPDATE_PROGRAM_LANGUAGE = "UPDATE ProgramLanguages SET Title = ? WHERE ProgramLanguagesId = ?";
	protected String SQL__DELETE_PROGRAM_LANGUAGE;// =
													// "DELETE ProgramLanguages WHERE ProgramLanguagesId = ?";

	protected String SQL__SELECT_STUDENT_PROGRAM_LANGUAGE = "SELECT p.Title, pc.ProgramLanguagesId, pc.Level"
			+ " FROM ProgramLanguages p, ProgramLanguagesCVs pc WHERE p.ProgramLanguagesId=pc.ProgramLanguagesId and pc.CVsId =?";
	protected String SQL__INSERT_STUENT_PROGRAM_LANGUAGE = "INSERT INTO ProgramLanguagesCVs(CVsId, ProgramLanguagesId, Level) VALUES(?,?,?)";
	protected String SQL__FIND_BY_TITLE = "SELECT ProgramLanguagesId FROM ProgramLanguages where Title = ?";

	@Override
	public Collection<ProgramLanguage> getProgramLanguages() {
		Collection<ProgramLanguage> result = null;
		Connection con = null;
		try {
			con = getConnection();
			result = getProgramLanguages(con);
		} catch (SQLException e) {
			System.err.println(String.format("Can not get ProgramLanguages. "
					+ e.getMessage()));
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection. "
						+ e.getMessage());
			}
		}
		return result;
	}

	private Collection<ProgramLanguage> getProgramLanguages(Connection con)
			throws SQLException {
		Collection<ProgramLanguage> result = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL__SELECT_PROGRAM_LANGUAGE);
			result = new ArrayList<ProgramLanguage>();
			while (rs.next()) {
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
					System.err.println("Can not close statement. "
							+ e.getMessage());
				}
			}
		}
		return result;
	}

	@Override
	public Boolean insertProgramLanguage(
			Collection<ProgramLanguage> programLanguages) {
		Boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = insertProgramLanguage(programLanguages, con);
			if (result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert programLanguages. "
					+ e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection. "
						+ e.getMessage());
			}
		}
		return result;
	}

	private Boolean insertProgramLanguage(
			Collection<ProgramLanguage> programLanguages, Connection con)
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
					System.err.println("Can not close statement. "
							+ e.getMessage());
				}
			}
		}
		return result;
	}

	@Override
	public Boolean deleteProgramLanguage(
			Collection<ProgramLanguage> programLanguages) {
		Boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = deleteProgramLanguage(programLanguages, con);
			if (result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not delete programLanguages. "
					+ e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection. "
						+ e.getMessage());
			}
		}
		return result;
	}

	private Boolean deleteProgramLanguage(
			Collection<ProgramLanguage> programLanguages, Connection con)
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
					System.err.println("Can not close statement. "
							+ e.getMessage());
				}
			}
		}
		return result;
	}

	@Override
	public Boolean updateProgramLanguages(
			Collection<ProgramLanguage> programLanguages) {
		Boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = updateProgramLanguage(programLanguages, con);
			if (result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not update programLanguages. "
					+ e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection. "
						+ e.getMessage());
			}
		}
		return result;
	}

	private Boolean updateProgramLanguage(
			Collection<ProgramLanguage> programLanguages, Connection con)
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
					System.err.println("Can not close statement. "
							+ e.getMessage());
				}
			}
		}
		return result;
	}

	@Override
	public Boolean addProgramLanguages(long id,
			Collection<ProgramLanguage> programLanguages) {
		boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = addProgramLanguage(id, programLanguages, con);
			if (result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not add programLanguages. "
					+ e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection. "
						+ e.getMessage());
			}
		}
		return result;
	}

	@Override
	public boolean addProgramLanguage(long id,
			Collection<ProgramLanguage> programLanguages, Connection con)
			throws SQLException {
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
					System.err.println("Can not close statement. "
							+ e.getMessage());
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
			con = getConnection();
			result = getStudentsProgramLanguages(CVsId, con);
		} catch (SQLException e) {
			System.err.println(String
					.format("Can not get from ProgramLanguagesCVs. "
							+ e.getMessage()));
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection. "
						+ e.getMessage());
			}
		}
		return result;
	}

	public Collection<ProgramLanguage> getStudentsProgramLanguages(long CVsId,
			Connection con) throws SQLException {
		Collection<ProgramLanguage> result = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_STUDENT_PROGRAM_LANGUAGE);
			pstmt.setLong(1, CVsId);
			ResultSet rs = pstmt.executeQuery();
			result = new ArrayList<ProgramLanguage>();
			while (rs.next()) {
				ProgramLanguage fc = unMapProgramLanguageWithLevel(rs);
				result.add(fc);
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement. "
							+ e.getMessage());
				}
			}
		}
		return result;
	}

	private ProgramLanguage unMapProgramLanguage(ResultSet rs)
			throws SQLException {
		ProgramLanguage at = new ProgramLanguage();
		at.setId(rs.getLong(MapperParameters.PROGRAM_LANGUAGE__ID));
		at.setTitle(rs.getString(MapperParameters.ANY_TAG_TITLE));
		return at;
	}

	private ProgramLanguage unMapProgramLanguageWithLevel(ResultSet rs)
			throws SQLException {
		ProgramLanguage at = unMapProgramLanguage(rs);
		at.setLevel(rs.getInt(MapperParameters.PROGRAM_LANGUAGE__LEVEL));
		return at;
	}

	private void mapProgramLanguageForInsert(ProgramLanguage at,
			PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, at.getTitle());
	}

	private void mapProgramLanguageForStudent(ProgramLanguage at, long id,
			PreparedStatement pstmt) throws SQLException {
		pstmt.setLong(1, id);
		pstmt.setLong(2, at.getId());
		pstmt.setInt(3, at.getLevel());
	}

	private void mapProgramLanguageForUpdate(ProgramLanguage at,
			PreparedStatement pstmt) throws SQLException {
		mapProgramLanguageForInsert(at, pstmt);
		pstmt.setLong(2, at.getId());
	}

	private void mapProgramLanguageForDelete(ProgramLanguage at,
			PreparedStatement pstmt) throws SQLException {
		pstmt.setLong(1, at.getId());
	}

	@Override
	public Boolean insertProgramLanguageAndGenerateKey(
			Collection<ProgramLanguage> programLanguage) {
		Boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = insertProgramLanguageAndGenerateKey(programLanguage, con);
			if (result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert programLanguages. "
					+ e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection. "
						+ e.getMessage());
			}
		}
		return result;
	}

	private Boolean insertProgramLanguageAndGenerateKey(
			Collection<ProgramLanguage> programLanguage, Connection con)
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			pstmt2 = con.prepareStatement(SQL__FIND_BY_TITLE);
			pstmt = con.prepareStatement(SQL__INSERT_PROGRAM_LANGUAGE,
					Statement.RETURN_GENERATED_KEYS);
			for (ProgramLanguage i : programLanguage) {
				mapProgramLanguageForInsert(i, pstmt2);
				ResultSet merged = pstmt2.executeQuery();
				if (merged.next()) {
					i.setId(merged.getLong(1));
					continue;
				}
				mapProgramLanguageForInsert(i, pstmt);
				result = pstmt.executeUpdate() == 1;
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
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
					System.err.println("Can not close statement. "
							+ e.getMessage());
				}
			}
			if (pstmt2 != null) {
				try {
					pstmt2.close();
				} catch (SQLException e) {
					System.err.println("Can not close searched statement. "
							+ e.getMessage());
				}
			}
		}
		return result;
	}

	@Override
	public boolean divideProgramLanguages(ProgramLanguage old,
			Collection<ProgramLanguage> newProgramLanguages) {
		boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = divideProgramLanguages(old, newProgramLanguages, con);
			if (result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not divide programLanguages. "
					+ e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection. "
						+ e.getMessage());
			}
		}
		return result;
	}

	private boolean divideProgramLanguages(ProgramLanguage old,
			Collection<ProgramLanguage> newProgramLanguages, Connection con)
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {

			result = insertProgramLanguageAndGenerateKey(newProgramLanguages,
					con);
			if (!result)
				return false;
			StringBuilder newPL = new StringBuilder();
			for (ProgramLanguage p : newProgramLanguages) {
				newPL.append("p2.ProgramLanguagesId = " + p.getId() + " OR ");
			}
			newPL.replace(newPL.length() - 4, newPL.length() - 1, "");
			String newQuery = String.format(
					SQL_SELECT_STUDENTS_WICH_HAVE_PROGRAM_LANGUAGE_FOR_DEVIDE,
					newPL, newPL);
			pstmt1 = con.prepareStatement(newQuery);
			pstmt1.setLong(1, old.getId());
			pstmt1.setLong(2, old.getId());
			ResultSet rs = pstmt1.executeQuery();
			HashMap<Long, Integer> cvs = new HashMap<Long, Integer>();
			HashMap<Long, ArrayList<Long>> cvsNotInsert = new HashMap<Long, ArrayList<Long>>();
			long id = -1;
			int level;
			long tempId; 
			ArrayList<Long> ids;
			
			while (rs.next()) {
				if (id != -1)
					rs.previous();
				id = rs.getLong(1);
				level = rs.getInt(2);
				ids = new ArrayList<Long>();
				tempId = rs.getLong(3);
				if(tempId!=-1){
					ids.add(tempId);
					while(rs.next()){
						if(rs.getLong(1)!= id)
							break;
						tempId = rs.getLong(3);
						ids.add(tempId);
					}
					cvsNotInsert.put(id, ids);
				}
				else{
					id = -1;
				}
				cvs.put(id, level);
			}

			Collection<ProgramLanguage> pls = new ArrayList<ProgramLanguage>(1);
			pls.add(old);
			result = deleteProgramLanguage(pls, con);
			if (!result)
				return false;

			StringBuilder cvQuery = new StringBuilder();
			StringBuilder plQuery = new StringBuilder();
			ids = new ArrayList<Long>();
			
			for (Long i : cvs.keySet()) {
				ArrayList<ProgramLanguage> toAddPL = new ArrayList<ProgramLanguage>(); 
				for (ProgramLanguage pl : newProgramLanguages){
					if(cvsNotInsert.containsKey(i) && cvsNotInsert.get(i).contains(pl.getId())){
						if(!ids.contains(pl.getId())){
							ids.add(pl.getId());
							plQuery.append("ProgramLanguagesId = " + pl.getId() + " OR");
						}
						continue;
					}
					pl.setLevel(cvs.get(i));
					toAddPL.add(pl);
				}
				cvQuery.append("(CVsId = " + i + "AND Level<" + cvs.get(i) + ") OR ");
				result = addProgramLanguage(i, toAddPL, con);
				if (!result)
					return false;
			}
			
			cvQuery.replace(cvQuery.length() - 4, cvQuery.length() - 1, "");
			plQuery.replace(plQuery.length() - 4, plQuery.length() - 1, "");
			newQuery = String.format(SQL_DELETE_UNNEEDED_PROGRAM_LANGUAGES, cvQuery, plQuery);
			
			pstmt2 = con.prepareStatement(newQuery);
			result = pstmt2.execute();

		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt1 != null) {
				try {
					pstmt1.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement. "
							+ e.getMessage());
				}
			}
			if (pstmt2 != null) {
				try {
					pstmt2.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement. "
							+ e.getMessage());
				}
			}
		}
		return result;
	}

	public boolean mergeProgramLanguages(
			Collection<ProgramLanguage> oldProgramLanguages,
			ProgramLanguage newProgramLanguage) {
		boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = mergeProgramLanguages(oldProgramLanguages,
					newProgramLanguage, con);
			if (result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not merge programLanguages. "
					+ e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection. "
						+ e.getMessage());
			}
		}
		return result;
	}

	private boolean mergeProgramLanguages(
			Collection<ProgramLanguage> oldProgramLanguages,
			ProgramLanguage newProgramLanguage, Connection con)
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			StringBuilder query = new StringBuilder(
					SQL_SELECT_STUDENTS_WICH_HAVE_ONE_OF_PROGRAM_LANGUAGE);
			for (ProgramLanguage i : oldProgramLanguages) {
				query.append(String.format("ProgramLanguagesId = %d or ",
						i.getId()));
			}
			query.replace(query.length() - 4, query.length() - 1, "");
			System.out.println("Query for merge: " + query.toString());
			pstmt = con.prepareStatement(query.toString());
			ResultSet rs = pstmt.executeQuery();
			HashMap<Long, Integer> cvs = new HashMap<Long, Integer>();
			long id;
			int level;
			while (rs.next()) {
				id = rs.getLong(1);
				level = rs.getInt(2);
				if (!cvs.containsKey(id))
					cvs.put(id, level);
				else if (level > cvs.get(id)) {
					cvs.remove(id);
					cvs.put(id, level);
				}
			}

			result = deleteProgramLanguage(oldProgramLanguages, con);
			if (!result)
				return false;

			Collection<ProgramLanguage> pls = new ArrayList<ProgramLanguage>(1);
			pls.add(newProgramLanguage);
			result = insertProgramLanguageAndGenerateKey(pls, con);
			if (!result)
				return false;

			for (long i : cvs.keySet()) {
				pls = new ArrayList<ProgramLanguage>(1);
				newProgramLanguage.setLevel(cvs.get(i));
				pls.add(newProgramLanguage);
				result = addProgramLanguage(i, pls, con);
				if (!result)
					return false;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement. "
							+ e.getMessage());
				}
			}
		}
		return result;
	}

	protected abstract Connection getConnection() throws SQLException;
}
