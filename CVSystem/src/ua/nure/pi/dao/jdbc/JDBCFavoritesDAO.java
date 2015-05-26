package ua.nure.pi.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.DAOFactory;
import ua.nure.pi.dao.FavoritesDAO;
import ua.nure.pi.entity.Favorite;
import ua.nure.pi.parameter.MapperParameters;

public abstract class JDBCFavoritesDAO implements FavoritesDAO {

	protected String SQL__SELECT_FAVORITES = "SELECT * FROM Favorites WHERE CompaniesId = ?";
	protected String SQL__CHECK_FAVORITES = "SELECT FavoritesId FROM Favorites WHERE StudentsId= ? "
			+ "AND CompaniesId = ?";
	protected String SQL__INSERT_FAVORITES;

	protected DAOFactory jdbcDAOFactory;

	@Override
	public Boolean insertFavorites(long companiesId, long studentId) {
		Boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = insertFavorites(companiesId, studentId, con);
			if (result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert educations. " + e.getMessage());
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

	private boolean insertFavorites(long companiesId, long studentId,
			Connection con) throws SQLException {
		boolean result = true;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			pstmt2 = con.prepareStatement(SQL__CHECK_FAVORITES);
			mapEducationForInsert(companiesId, studentId, pstmt2);
			ResultSet rs = pstmt2.executeQuery();
			if(rs.next())
				return false;
			pstmt = con.prepareStatement(SQL__INSERT_FAVORITES);
			mapEducationForInsert(companiesId, studentId, pstmt);
			if (pstmt.executeUpdate() != 1)
				return false;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt2 != null) {
				try {
					pstmt2.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement. "
							+ e.getMessage());
				}
			}
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
	public Collection<Favorite> getFavorites(long companiesId) {
		Collection<Favorite> result = null;
		Connection con = null;
		try {
			con = getConnection();
			result = getFavorites(companiesId, con);
		} catch (SQLException e) {
			System.err.println("Can not get favorites." + e.getMessage());
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

	private Collection<Favorite> getFavorites(long companiesId, Connection con)
			throws SQLException {
		Collection<Favorite> result = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_FAVORITES);
			pstmt.setLong(1, companiesId);
			ResultSet rs = pstmt.executeQuery();
			result = new ArrayList<Favorite>();
			while (rs.next()) {
				Favorite ed = unMapFavorites(rs);
				result.add(ed);
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

	private Favorite unMapFavorites(ResultSet rs) throws SQLException {
		Favorite f = new Favorite();
		f.setStudent(jdbcDAOFactory.getStudentDAO().getStudent(
				rs.getLong(MapperParameters.STUDENT_ID)));
		f.setDate(rs.getDate(MapperParameters.CV__DATE_STAMP));
		return f;
	}

	private void mapEducationForInsert(long companiesId, long studentId,
			PreparedStatement pstmt) throws SQLException {
		pstmt.setLong(1, studentId);
		pstmt.setLong(2, companiesId);
	}

	protected abstract Connection getConnection() throws SQLException;
}
