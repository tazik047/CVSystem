package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.AnyTagDAO;
import ua.nure.pi.dao.StudentDAO;
import ua.nure.pi.entity.AnyTag;
import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Student;
import ua.nure.pi.entity.WorkExp;
import ua.nure.pi.parameter.MapperParameters;

public class MSSqlStudentDAO implements StudentDAO {
	
	private static final String SQL__SELECT_STUDENT = "SELECT * FROM Students WHERE StudentsId = ?";
	private static final String SQL__INSERT_ANY_TAG = "INSERT INTO Students VALUES(?, ?, ?, ?, ?, ?)";
	private static final String SQL__DELETE_ANY_TAG = "DELETE %1$s WHERE %2$sId = ?";
	
	public Student getStudent(long studentId) {
		Student result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getStudent(studentId, con);
		} catch (SQLException e) {
			System.err.println("Can not get student." + e.getMessage());
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
	
	private Student getStudent(long studentId, Connection con) throws SQLException {
		Student result = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_STUDENT);
			pstmt.setLong(1, studentId);
			ResultSet rs = pstmt.executeQuery();
			result = unMapStudent(rs);
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

	public Boolean insertStudent(Student student) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = insertStudent(student, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert student. " + e.getMessage());
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

	private Boolean insertStudent(Student student, Connection con) 
			throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__INSERT_ANY_TAG);
			mapStudentForInsert(student, pstmt);
			if(pstmt.executeUpdate()!=1)
				return false;
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

	public Boolean deleteStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Student unMapStudent(ResultSet rs) throws SQLException{
		Student st = new Student();
		st.setStudentsId(rs.getLong(MapperParameters.STUDENT_ID));
		st.setFirstname(rs.getString(MapperParameters.STUDENT_FIRSTNAME));
		st.setSurname(rs.getString(MapperParameters.STUDENT_SURNAME));
		st.setCVsId(rs.getLong(MapperParameters.STUDENT_CVSID));
		st.setGroupsId(rs.getLong(MapperParameters.STUDENT_GROUPSID));
		st.setPatronymic(rs.getString(MapperParameters.STUDENT_PATRONYMIC));
		return st;
	}
	
	private void mapStudentForInsert(Student st, PreparedStatement pstmt) 
			throws SQLException{
		pstmt.setLong(1, st.getStudentsId());
		pstmt.setString(2, st.getSurname());
		pstmt.setString(3, st.getFirstname());
		pstmt.setString(4, st.getPatronymic());
		pstmt.setLong(5, st.getGroupsId());
		pstmt.setLong(6, st.getCVsId());
	}

	
}