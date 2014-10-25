package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.mssql.MSSqlDAOFactory;
import ua.nure.pi.dao.FacultyGroupDAO;
import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Group;
import ua.nure.pi.parameter.MapperParameters;


public class MSSqlFacultyGroupDAO implements FacultyGroupDAO {
	
	private static volatile MSSqlFacultyGroupDAO instance;
	
	private MSSqlFacultyGroupDAO() {
	}
	
	public static MSSqlFacultyGroupDAO getInstancce(){
		if(instance == null)
			synchronized (MSSqlFacultyGroupDAO.class){
				if(instance == null)
					instance = new MSSqlFacultyGroupDAO();
			}
		return instance;
	}
	
	private static final String SQL__SELECT_FACULTIES = "SELECT * FROM Faculties";
	private static final String SQL__INSERT_FACULTIES = "INSERT INTO Faculties(Title) VALUES(?)";
	private static final String SQL__UPDATE_FACULTIES = "UPDATE Faculties SET Title=? WHERE FacultiesId=?";
	private static final String SQL__DELETE_FACULTIES = "DELETE FROM Faculties WHERE FacultiesId=?";
	private static final String SQL__FACULTIES_IS_EMPTY = "SELECT count(*) FROM Groups WHERE FacultiesId=?";
	
	private static final String SQL__SELECT_GROUPS = "SELECT * FROM Groups WHERE FacultiesId=?";
	private static final String SQL__INSERT_GROUPS = "INSERT INTO Groups(Title, FacultiesId) VALUES(?, ?)";
	private static final String SQL__UPDATE_GROUPS = "UPDATE Groups SET Title=?, FacultiesId=? WHERE GroupsId=?";
	private static final String SQL__DELETE_GROUPS = "DELETE FROM Groups WHERE GroupsId = ?";
	private static final String SQL__GROUPS_IS_EMPTY = "SELECT count(*) FROM Students where GroupsId=?";
	private static final String SQL__SELECT_GROUP = "SELECT * FROM Groups WHERE GroupsId = ?";

	@Override
	public Collection<Faculty> getFaculties() {
		Collection<Faculty> result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getFaculties(con);
		} catch (SQLException e) {
			System.err.println("Can not get faculties." + e.getMessage());
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
	
	private Collection<Faculty> getFaculties(Connection con)
			throws SQLException {
		Collection<Faculty> result = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL__SELECT_FACULTIES);
			result = new ArrayList<Faculty>();
			while(rs.next()){
				Faculty fc = unMapFaculty(rs);
				fc.setGroups(getGroups(fc.getFacultiesId(), con));
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
	public Boolean insertFaculties(Collection<Faculty> faculties) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = insertFaculties(faculties, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert faculties. " + e.getMessage());
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
	
	private boolean insertFaculties(Collection<Faculty> faculties, Connection con)
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__INSERT_FACULTIES, Statement.RETURN_GENERATED_KEYS);
			for (Faculty f : faculties) {
				mapFacultyForInsert(f, pstmt);
				if(pstmt.executeUpdate()!=1)
					return false;
				ResultSet rs = pstmt.getGeneratedKeys();
				if(f.getGroups()==null)
					continue;
				if(rs.next()){
					if(!insertGroups(rs.getLong(1), f.getGroups(), con))
						return false;
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

	@Override
	public Boolean updateFaculties(Collection<Faculty> faculties) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = updateFaculties(faculties, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not update faculties. " + e.getMessage());
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
	
	private Boolean updateFaculties(Collection<Faculty> faculties, Connection con) throws SQLException{
		boolean result;
		PreparedStatement pstmt = null;
		try {
			
			pstmt = con.prepareStatement(SQL__UPDATE_FACULTIES);
			for (Faculty f : faculties) {
				mapFacultyForUpdate(f, pstmt);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == faculties.size();
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
	public Boolean deleteFaculties(Collection<Faculty> faculties) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = deleteFaculties(faculties, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not delete faculties. " + e.getMessage());
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
	
	private Boolean deleteFaculties(Collection<Faculty> faculties, Connection con) throws SQLException{
		boolean result;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			pstmt2 = con.prepareStatement(SQL__FACULTIES_IS_EMPTY);
			pstmt = con.prepareStatement(SQL__DELETE_FACULTIES);
			for (Faculty f : faculties) {
				pstmt2.setLong(1, f.getFacultiesId());
				ResultSet rs = pstmt2.executeQuery();
				if(rs.next())
					if(rs.getInt(1) != 0)
						return false;
				pstmt.setLong(1, f.getFacultiesId());
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == faculties.size();
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
					System.err.println("Can not close statement. " + e.getMessage());
				}
			}
		}
		return result;
	}

	@Override
	public Collection<Group> getGroups(long facultyId) {
		Collection<Group> result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getGroups(facultyId, con);
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

	@Override
	public Collection<Group> getGroups(Faculty faculty) {
		return getGroups(faculty.getFacultiesId());
	}
	
	private Collection<Group> getGroups(long id, Connection con) 
			throws SQLException {
		Collection<Group> result = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_GROUPS);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			result = new ArrayList<Group>();
			while(rs.next()){
				Group gr = unMapGroup(rs);
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

	@Override
	public Boolean insertGroups(long facultiesId, Collection<Group> groups) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = insertGroups(facultiesId, groups, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert groups. " + e.getMessage());
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
	
	private Boolean insertGroups(long id, Collection<Group> groups, Connection con) throws SQLException {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__INSERT_GROUPS);
			for (Group g : groups) {
				mapGroupForInsert(g, id, pstmt);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == groups.size();
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
	public Boolean updateGroups(Collection<Group> groups) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = updateGroups(groups, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not update groups. " + e.getMessage());
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
	
	private Boolean updateGroups(Collection<Group> groups, Connection con) throws SQLException {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			
			pstmt = con.prepareStatement(SQL__UPDATE_GROUPS);
			for (Group g : groups) {
				mapGroupForUpdate(g, pstmt);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == groups.size();
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
	public Boolean deleteGroups(Collection<Group> groups) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = deleteGroups(groups, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not delete groups. " + e.getMessage());
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
	
	private Boolean deleteGroups(Collection<Group> groups, Connection con) throws SQLException {
		boolean result;
		PreparedStatement pstmt = null;
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(SQL__GROUPS_IS_EMPTY);
			pstmt = con.prepareStatement(SQL__DELETE_GROUPS);
			for (Group g : groups) {
				st.setLong(1, g.getGroupId());
				ResultSet rs = st.executeQuery();
				if(rs.next())
					if(rs.getInt(1) != 0)
						return false;
				pstmt.setLong(1, g.getGroupId());
				pstmt.addBatch();
			}
			result = pstmt.executeBatch().length == groups.size();
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
	

	private Faculty unMapFaculty(ResultSet rs) throws SQLException {
		Faculty fc = new Faculty();
		fc.setFacultiesId(rs.getLong(MapperParameters.FACULTY__ID));
		fc.setTitle(rs.getString(MapperParameters.FACULTY__TITLE));
		fc.setGroups(new ArrayList<Group>());
		return fc;
	}
	
	private Group unMapGroup(ResultSet rs) throws SQLException{
		Group gr = new Group();
		gr.setGroupId(rs.getLong(MapperParameters.GROUP_ID));
		gr.setTitle(rs.getString(MapperParameters.GROUP_TITLE));
		gr.setFacultiesId(rs.getLong(MapperParameters.GROUP_FACULTIES_ID));
		return gr;
	}
	
	private void mapFacultyForInsert(Faculty f, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, f.getTitle());
	}
	
	private void mapFacultyForUpdate(Faculty f, PreparedStatement pstmt) throws SQLException {
		mapFacultyForInsert(f, pstmt);
		pstmt.setLong(2, f.getFacultiesId());
	}
	
	private void mapGroupForInsert(Group g, PreparedStatement pstmt)  throws SQLException{
		pstmt.setString(1,g.getTitle());
		pstmt.setLong(2, g.getFacultiesId());
	}
	
	private void mapGroupForInsert(Group g, long facultiesId, PreparedStatement pstmt) throws SQLException {
		mapGroupForInsert(g, pstmt);
		pstmt.setLong(2, facultiesId);
		
	}
	
	private void mapGroupForUpdate(Group g, PreparedStatement pstmt)  throws SQLException{
		mapGroupForInsert(g, pstmt);
		pstmt.setLong(3, g.getGroupId());
	}

	@Override
	public Boolean insertFaculty(Faculty faculty) {
		Collection<Faculty> faculties = new ArrayList<Faculty>();
		faculties.add(faculty);
		return insertFaculties(faculties);
	}

	@Override
	public Boolean updateFaculty(Faculty faculty) {
		Collection<Faculty> faculties = new ArrayList<Faculty>();
		faculties.add(faculty);
		return updateFaculties(faculties);
	}

	@Override
	public Boolean deleteFaculty(Faculty faculty) {
		Collection<Faculty> faculties = new ArrayList<Faculty>();
		faculties.add(faculty);
		return deleteFaculties(faculties);
	}

	@Override
	public Boolean insertGroup(Group group) {
		Collection<Group> groups = new ArrayList<Group>();
		groups.add(group);
		return insertGroups(group.getGroupId(), groups);
	}

	@Override
	public Boolean updateGroup(Group group) {
		Collection<Group> groups = new ArrayList<Group>();
		groups.add(group);
		return updateGroups(groups);
	}

	@Override
	public Boolean deleteGroup(Group group) {
		Collection<Group> groups = new ArrayList<Group>();
		groups.add(group);
		return deleteGroups(groups);
	}

	@Override
	public Group getGroup(long id) {
		Group result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getGroup(id, con);
		} catch (SQLException e) {
			System.err.println("Can not select groups. " + e.getMessage());
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
	
	private Group getGroup(long id, Connection con) throws SQLException {
		Group result = null;
		PreparedStatement pstmt = null;
		try {
			
			pstmt = con.prepareStatement(SQL__SELECT_GROUP);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				result = unMapGroupForStudent(rs);
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

	private Group unMapGroupForStudent(ResultSet rs) throws SQLException {
		Group gr = new Group();
		gr.setGroupId(rs.getLong(MapperParameters.GROUP_ID));
		gr.setTitle(rs.getString(MapperParameters.GROUP_TITLE));
		gr.setFacultiesId(rs.getLong(MapperParameters.GROUP_FACULTIES_ID));
		return gr;
	}
}
