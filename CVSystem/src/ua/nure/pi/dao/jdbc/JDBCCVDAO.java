package ua.nure.pi.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import ua.nure.pi.dao.CVDAO;
import ua.nure.pi.dao.DAOFactory;
import ua.nure.pi.entity.CV;
import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Purpose;
import ua.nure.pi.parameter.MapperParameters;

public abstract class JDBCCVDAO implements CVDAO {
	
	protected String SQL__INSERT_CV; //= "INSERT INTO CVs(PurposesId, Qualities, Others, DateStamp, CVsId) VALUES(?, ?, ?, getdate(), ?)";
	protected String SQL__SELECT_CV = "SELECT * FROM CVs WHERE CVsId=?";
	protected String SQL__SEARCH_CV = "select distinct cv.CVsId from CVs cv,LanguagesCVs lcv, ProgramLanguagesCVs pcv, Purposes p"
			+ " WHERE cv.CVsId=lcv.CVsId and pcv.CVsId=cv.CVsId and p.PurposesId=cv.PurposesId";

	protected String SQL__COUNT_STUDENTS_BY_PURPOSE = "SELECT Title, COUNT(CVsId) FROM CVs, Purposes "
			+ "WHERE Purposes.PurposesId = CVs.PurposesId GROUP BY Title ";

	protected DAOFactory jdbcDAOFactory;
	
	@Override
	public HashMap<String, Integer> getPurposeStat() {
		HashMap<String, Integer> result = null;
		Connection con = null;
		try {
			con = getConnection();
			result = getPurposeStat(con);
		} catch (SQLException e) {
			System.err.println("Can not get stat." + e.getMessage());
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

	public HashMap<String, Integer> getPurposeStat(Connection con) throws SQLException {
		ResultSet rs = null;
		Statement st = null;
		 HashMap<String, Integer> result = new  HashMap<String, Integer> ();
		try {
			st = con.createStatement();
			rs = st.executeQuery(SQL__COUNT_STUDENTS_BY_PURPOSE);
			while(rs.next())
				result.put(rs.getString(1), rs.getInt(2));
		} catch (SQLException e) {
			throw e;
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement. " + e.getMessage());
				}
			}
		}
		return result;
	}

	
	@Override
	public boolean insertCV(CV cv) {
		boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
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

	@Override
	public boolean insertCV(CV cv, Connection con) throws SQLException {
		boolean result = false;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__INSERT_CV);
			mapCV(cv, pstmt);
			if(pstmt.executeUpdate()!=1)
				return false;
			result = jdbcDAOFactory.getEducationDAO()
						.insertEducations(cv.getCvsId(), cv.getEducations(), con) &&
					jdbcDAOFactory.getLanguageDAO()
						.addLanguage(cv.getCvsId(), cv.getLanguages(), con) &&
					jdbcDAOFactory.getProgramLanguageDAO()
						.addProgramLanguage(cv.getCvsId(), cv.getProgramLanguages(), con) &&
					jdbcDAOFactory.getSertificatsDAO()
						.insertSertificats(cv.getCvsId(), cv.getSertificates(), con) &&
					jdbcDAOFactory.getWorkExpDAO()
						.insertWorkExps(cv.getCvsId(), cv.getWorkExps(), con);
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

	private void mapCV(CV cv, PreparedStatement pstmt) throws SQLException {
		pstmt.setLong(1, cv.getPurpose().getId());
		pstmt.setString(2, cv.getQualities());
		pstmt.setString(3, cv.getOthers());
		pstmt.setLong(4, cv.getCvsId());
	}

	@Override
	public CV getCv(long CVsId) {
		CV result = null;
		Connection con = null;
		try {
			con = getConnection();
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

	@Override
	public CV getCv(long cVsId, Connection con) throws SQLException {
		CV result = null;
		PreparedStatement pstmt = null;
		try {
			result = new CV();
			result.setCvsId(cVsId);
			result.setEducations(jdbcDAOFactory.getEducationDAO().getEducations(cVsId, con));
			result.setLanguages(jdbcDAOFactory.getLanguageDAO().getStudentsLanguages(cVsId, con));
			result.setProgramLanguages(jdbcDAOFactory.getProgramLanguageDAO().getStudentsProgramLanguages(cVsId, con));
			result.setSertificates(jdbcDAOFactory.getSertificatsDAO().getSertificats(cVsId, con));
			result.setWorkExps(jdbcDAOFactory.getWorkExpDAO().getWorkExps(cVsId, con));
			
			pstmt = con.prepareStatement(SQL__SELECT_CV);
			pstmt.setLong(1, cVsId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				result.setPurpose(jdbcDAOFactory.getPurposeDAO()
						.findPurposes(rs.getLong(MapperParameters.CV__PURPOSES_ID), con));
				result.setQualities(rs.getString(MapperParameters.CV__QUALITIES));
				result.setOthers(rs.getString(MapperParameters.CV__OTHERS));
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
	public Collection<CV> getCvs(Collection<Long> cvsids) {
		Collection<CV> result = null;
		Connection con = null;
		try {
			con = getConnection();
			result = getCvs(cvsids, con);
		} catch (SQLException e) {
			System.err.println("Can not get cvs." + e.getMessage());
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
	
	private Collection<CV> getCvs(Collection<Long> cvsids, Connection con) throws SQLException {
		Collection<CV> res = new ArrayList<CV>();
		for(long i : cvsids)
			res.add(getCv(i, con));
		return res;
	}
	
	@Override
	public Collection<CV> searchCV(Collection<Language> languages,
			Collection<ProgramLanguage> planguages,
			Collection<Purpose> purposes, int start, int end ) {
		Collection<CV> result = null;
		Connection con = null;
		try {
			con = getConnection();
			result = searchCV(languages, planguages, purposes, start, end, con);
		} catch (SQLException e) {
			System.err.println("Can not find cvs." + e.getMessage());
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
	
	protected void createQuery(StringBuilder query, String title, Collection<Long> objs){
		query.append(" AND (");
		int ind = 0;
		for(long i : objs){
			if(ind+1==objs.size())
				query.append(String.format("%s = %d", title, i));
			else
				query.append(String.format("%s = %d OR ", title, i));
			ind++;
		}
		query.append(')');
	}
	
	
	protected Collection<CV> searchCV(Collection<Language> languages,
			Collection<ProgramLanguage> planguages,
			Collection<Purpose> purposes, int start, int end, Connection con) throws SQLException{
		Collection<CV> result = null;
		String q = "";
		PreparedStatement pstmt = null;
		try {
			StringBuilder query = new StringBuilder();
			
			if(languages!= null && languages.size()!=0){
				Collection<Long> langIds = new ArrayList<Long>();
				for(Language l : languages)
					langIds.add(l.getId());
				createQuery(query, "lcv." + MapperParameters.LANGUAGE__ID, langIds);
			}
			
			if(planguages!= null && planguages.size()!=0){
				Collection<Long> plangIds = new ArrayList<Long>();
				for(ProgramLanguage l : planguages)
					plangIds.add(l.getId());
				createQuery(query, "pcv." + MapperParameters.PROGRAM_LANGUAGE__ID, plangIds);
			}
			
			if(purposes!= null && purposes.size()!=0){
				Collection<Long> purpIds = new ArrayList<Long>();
				for(Purpose l : purposes)
					purpIds.add(l.getId());
				createQuery(query, "p." + MapperParameters.PURPOSE__ID, purpIds);
			}			
			query.append(" ORDER BY cv."+MapperParameters.CV__ID);
			q = createInterval(start, end, query);
			System.out.println(q);
			pstmt = con.prepareStatement(q);
			ResultSet rs = pstmt.executeQuery();
			result = new ArrayList<CV>();
			while(rs.next()){
				result.add(getCv(rs.getLong(MapperParameters.CV__ID)));
			}
			
		} catch (SQLException e) {
			throw new SQLException("Query: "+q+"\n"+e.getMessage());
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
	
	protected abstract String createInterval(int start, int end, StringBuilder query);

	protected abstract Connection getConnection() throws SQLException;
}
