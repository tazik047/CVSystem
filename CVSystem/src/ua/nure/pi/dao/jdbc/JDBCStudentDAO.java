package ua.nure.pi.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import ua.nure.pi.dao.DAOFactory;
import ua.nure.pi.dao.StudentDAO;
import ua.nure.pi.entity.Student;
import ua.nure.pi.parameter.MapperParameters;

public abstract class JDBCStudentDAO implements StudentDAO {

	protected String SQL__SELECT_STUDENT = "SELECT * FROM Students WHERE StudentsId = ?";
	protected String SQL__SELECT_ALL_STUDENT = "SELECT * FROM Students";
	protected String SQL__INSERT_STUDENT = "INSERT INTO Students(Surname, Firstname, Patronymic, "
			+ "GroupsId, Address, Skype, Email, Phone, Birthday) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

	protected String SQL__DELETE_ANY_TAG;// =
											// "DELETE Students WHERE StudentsId = ?";

	protected DAOFactory jdbcDAOFactory;

	@Override
	public Student getStudent(long studentId) {
		Student result = null;
		Connection con = null;
		try {
			con = getConnection();
			result = getStudent(studentId, con);
		} catch (SQLException e) {
			System.err.println("Can not get student." + e.getMessage());
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

	public Student getStudent(long studentId, Connection con)
			throws SQLException {
		Student result = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_STUDENT);
			pstmt.setLong(1, studentId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				result = unMapStudent(rs);
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
	public Boolean insertStudent(Student student) {
		Boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = insertStudent(student, con);
			if (result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert student. " + e.getMessage());
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

	private Boolean insertStudent(Student student, Connection con)
			throws SQLException {
		boolean result = false;
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(SQL__INSERT_STUDENT,
					Statement.RETURN_GENERATED_KEYS);
			mapStudentForInsert(student, pstmt);
			if (pstmt.executeUpdate() != 1)
				return false;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				student.getCv().setCvsId(rs.getLong(1));
				if (!jdbcDAOFactory.getCVDAO().insertCV(student.getCv(), con))
					return false;
				result = true;
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

	@Override
	public Boolean deleteStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Student> getStudents() {
		Collection<Student> result = null;
		Connection con = null;
		try {
			con = getConnection();
			result = getStudents(con);
		} catch (SQLException e) {
			System.err.println("Can not get student." + e.getMessage());
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

	private Collection<Student> getStudents(Connection con) throws SQLException {
		Collection<Student> result = new ArrayList<Student>();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_ALL_STUDENT);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Student st = unMapStudent(rs);
				st.setCv(jdbcDAOFactory.getCVDAO().getCv(st.getStudentsId(),
						con));
				st.setGroup(jdbcDAOFactory.getFacultyGroupDAO().getGroup(
						rs.getLong(MapperParameters.STUDENT_GROUPSID), con));
				result.add(st);
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

	private Student unMapStudent(ResultSet rs) throws SQLException {
		Student st = new Student();
		st.setStudentsId(rs.getLong(MapperParameters.STUDENT_ID));
		st.setFirstname(rs.getString(MapperParameters.STUDENT_FIRSTNAME));
		st.setSurname(rs.getString(MapperParameters.STUDENT_SURNAME));
		st.setPatronymic(rs.getString(MapperParameters.STUDENT_PATRONYMIC));
		st.setAddress(rs.getString(MapperParameters.STUDENT_ADDRESS));
		st.setSkype(rs.getString(MapperParameters.STUDENT_SKYPE));
		st.setEmail(rs.getString(MapperParameters.STUDENT_EMAIL));
		st.setDateOfBirth(rs.getDate(MapperParameters.STUDENT_BIRTHDAY));
		st.setPhone(rs.getString(MapperParameters.STUDENT_PHONE));
		return st;
	}

	private void mapStudentForInsert(Student st, PreparedStatement pstmt)
			throws SQLException {
		pstmt.setString(1, st.getSurname());
		pstmt.setString(2, st.getFirstname());
		pstmt.setString(3, st.getPatronymic());
		pstmt.setLong(4, st.getGroup().getGroupId());
		pstmt.setString(5, st.getAddress());
		pstmt.setString(6, st.getSkype());
		pstmt.setString(7, st.getEmail());
		pstmt.setString(8, st.getPhone());
		pstmt.setDate(9, new java.sql.Date(st.getDateOfBirth().getTime()));
	}

	protected abstract Connection getConnection() throws SQLException;

}
