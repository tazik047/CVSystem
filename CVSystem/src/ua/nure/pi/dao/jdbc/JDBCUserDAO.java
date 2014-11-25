package ua.nure.pi.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.UserDAO;
import ua.nure.pi.entity.User;
import ua.nure.pi.parameter.MapperParameters;
import ua.nure.pi.security.Hashing;

public abstract class JDBCUserDAO implements UserDAO {

	protected String SQL__CONTAINS_USER_WITH_LOGIN = "SELECT * FROM Users WHERE Login=?";
	protected String SQL__READ_USER_BY_ID = "SELECT * FROM Users WHERE UsersId=?";
	protected String SQL__GET_ALL_USERS = "SELECT * FROM Users";
	protected String SQL__INSERT_USER = "INSERT INTO Users (Password, Login, Role) VALUES (?, ?, ?)";
	protected String SQL__DELETE_USER;// = "DELETE FROM Users WHERE UsersId = ?";
	protected String SQL__UPDATE_USER = "UPDATE Users SET Password = ? WHERE UsersId = ?";

	
	@Override
	public boolean containsUser(String login) {
		Connection con = null;
		try {
			con = getConnection();
			return containsUser(con, login);
		} catch (SQLException e) {
			System.err.println("Can not check user containing." + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection." + e.getMessage());
			}
		}
		return false;
	}

	private boolean containsUser(Connection con, String login)
			throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__CONTAINS_USER_WITH_LOGIN);
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			return rs.isBeforeFirst();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close connection." + e.getMessage());
				}
			}
		}
	}

	@Override
	public User getUser(String login) {
		Connection con = null;
		User user = null;
		try {
			con = getConnection();
			user = getUser(con, login);
		} catch (SQLException e) {
			System.err.println("Can not get user." + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection." + e.getMessage());
			}
		}
		return user;
	}

	private User getUser(Connection con, String login) throws SQLException {
		PreparedStatement pstmt = null;
		User user = null;
		try {
			pstmt = con.prepareStatement(SQL__CONTAINS_USER_WITH_LOGIN);
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = unMapUser(rs);
			}
			return user;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement" + e.getMessage());
				}
			}
		}
	}

	@Override
	public User getUser(long userId) {
		Connection con = null;
		User user = null;
		try {
			con = getConnection();
			user = getUser(userId, con);
		} catch (SQLException e) {
			System.err.println("Can not get user." + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection" + e.getMessage());
			}
		}
		return user;
	}

	@Override
	public User getUser(long userId, Connection con) throws SQLException {
		PreparedStatement pstmt = null;
		User user = null;
		try {
			pstmt = con.prepareStatement(SQL__READ_USER_BY_ID);
			pstmt.setLong(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = unMapUser(rs);
			}
			return user;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement" + e.getMessage());
				}
			}
		}
	}
	
	@Override
	public Collection<User> getAllUsers() {
		Connection con = null;
		try {
			con = getConnection();
			return getAllUsers(con);
		} catch (SQLException e) {
			System.err.println("Can not get all users." + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection" + e.getMessage());
			}
		}
		return null;
	}

	private Collection<User> getAllUsers(Connection con)
			throws SQLException {
		Collection<User> users = new ArrayList<User>();
		Statement stmt = null;
		User user = new User();
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL__GET_ALL_USERS);
			while (rs.next()) {
				user = unMapUser(rs);
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement" + e.getMessage());
				}
			}
		}
	}	
	
	private User unMapUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserId(rs.getLong(MapperParameters.USER__ID));
		user.setLogin(rs.getString(MapperParameters.USER__LOGIN));
		user.setPassword(rs.getString(MapperParameters.USER__PASSWORD));
		user.setAdmin(rs.getBoolean(MapperParameters.USER__ROLES));
		return user;
	}
	
	private void mapUser(User user, PreparedStatement pstmt)
			throws SQLException {
		pstmt.setString(1, Hashing.salt(user.getPassword(), user.getLogin()));
		pstmt.setString(2, user.getLogin());
		pstmt.setBoolean(3, user.isAdmin());

	}


	@Override
	public boolean insertUser(User user) {
		boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = insertUser(user, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert user." + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection" + e.getMessage());
			}
		}
		return result;
	}

	@Override
	public boolean insertUser(User user, Connection con)
			throws SQLException {
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			pstmt = con.prepareStatement(SQL__INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			mapUser(user, pstmt);
			result = pstmt.executeUpdate() == 1;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
				user.setUserId(rs.getLong(1));
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
	public boolean updateUser(User user) {
		Connection con = null;
		boolean updateResult = false;
		try {
			con = getConnection();
			updateResult = updateUser(con, user);
			if(updateResult)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not update pass." + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection" + e.getMessage());
			}
		}
		return updateResult;
	}

	private boolean updateUser(Connection con, User user)
			throws SQLException {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__UPDATE_USER);
			mapUserForUpdate(user, pstmt);
			int updatedRows = pstmt.executeUpdate();
			result = updatedRows == 1;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement" + e.getMessage());
				}
			}
		}
		return result;
	}
	
	private void mapUserForUpdate(User user, PreparedStatement pstmt)
			throws SQLException{
		pstmt.setString(1, Hashing.salt(user.getPassword(), user.getLogin()));
		pstmt.setLong(2, user.getUserId());
	}

	@Override
	public boolean deleteUser(User user) {
		Connection con = null;
		boolean result = false;
		try {
			con = getConnection();
			result = deleteUser(con, user);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not update pass." + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection" + e.getMessage());
			}
		}
		return result;
	}

	private boolean deleteUser(Connection con, User user)
			throws SQLException {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__DELETE_USER);
			pstmt.setLong(1, user.getUserId());
			result = pstmt.executeUpdate() == 1;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement" + e.getMessage());
				}
			}
		}
		return result;
	}
	
	protected abstract Connection getConnection() throws SQLException;
}
