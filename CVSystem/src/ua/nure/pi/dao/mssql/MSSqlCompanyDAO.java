package ua.nure.pi.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.CompanyDAO;
import ua.nure.pi.entity.Company;
import ua.nure.pi.parameter.MapperParameters;

public class MSSqlCompanyDAO implements CompanyDAO {
	
	private static volatile MSSqlCompanyDAO instance;
	
	private MSSqlCompanyDAO() {
	}
	
	public static MSSqlCompanyDAO getInstancce(){
		if(instance == null)
			synchronized (MSSqlCompanyDAO.class){
				if(instance == null)
					instance = new MSSqlCompanyDAO();
			}
		return instance;
	}
	
	private static final String SQL__SELECT_COMPANY = "SELECT * FROM Companies WHERE CompaniesId = ?";
	private static final String SQL__SELECT_ALL_COMPANY = "SELECT * FROM Companies";
	private static final String SQL__INSERT_COMPANY = "INSERT INTO Companies(Title, Phone, PhoneRespPerson, "
			+ "Email, FIORespPerson, Skype, Active, CompaniesId) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL__SELECT_NOT_ACTIVE_COMPANY = "SELECT * FROM Companies WHERE Atcive = 1";
	private static final String SQL__ACTIVATE_COMPANY = "UPDATE Companies SET Active=? WHERE CompaniesId=?";
	
	@Override
	public Company getCompany(long companyId) {
		Company result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getCompany(companyId, con);
		} catch (SQLException e) {
			System.err.println("Can not get Company." + e.getMessage());
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
	
	private Company getCompany(long companyId, Connection con) throws SQLException {
		Company result = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_COMPANY);
			pstmt.setLong(1, companyId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				result = unMapCompany(rs);
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
	public Boolean insertCompany(Company company) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = insertCompany(company, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert Company. " + e.getMessage());
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

	private Boolean insertCompany(Company company, Connection con) 
			throws SQLException {
		boolean result = false;
		PreparedStatement pstmt = null;
		try {
			if(!MSSqlUserDAO.getInstancce().containsUser(company.getUser().getLogin())){
				result = MSSqlUserDAO.getInstancce().insertUser(company.getUser(), con);
				if(!result)
					return false;
				pstmt = con.prepareStatement(SQL__INSERT_COMPANY);
				mapCompanyForInsert(company, pstmt);
				if(pstmt.executeUpdate()!=1)
					return false;
				result=true;
			}
			else{
				throw new IllegalArgumentException("Пользователь с таким логином уже существует");
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
	public Boolean deleteCompany(Company company) {
		Boolean result = false;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = MSSqlUserDAO.getInstancce().deleteUser(company.getUser());
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can delete Company. " + e.getMessage());
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
	public Collection<Company> getCompanies() {
		Collection<Company> result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getCompanies(con);
		} catch (SQLException e) {
			System.err.println("Can not get companies." + e.getMessage());
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
	
	private Collection<Company> getCompanies(Connection con) throws SQLException {
		Collection<Company> result = new ArrayList<Company>();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_ALL_COMPANY);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Company c = unMapCompany(rs);
				c.setUser(MSSqlUserDAO.getInstancce().getUser(c.getId()));
				result.add(c);
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
	public Collection<Company> getNotActiveCompanies() {
		Collection<Company> result = null;
		Connection con = null;
		try {
			con = MSSqlDAOFactory.getConnection();
			result = getNotActiveCompanies(con);
		} catch (SQLException e) {
			System.err.println("Can not get not active company." + e.getMessage());
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
	
	private Collection<Company> getNotActiveCompanies(Connection con) throws SQLException {
		Collection<Company> result = new ArrayList<Company>();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__SELECT_NOT_ACTIVE_COMPANY);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Company c = unMapCompany(rs);
				c.setUser(MSSqlUserDAO.getInstancce().getUser(c.getId()));
				result.add(c);
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
	public Boolean activateCompany(Company company) {
			Boolean result = false;
			Connection con = null;
			try {
				con = MSSqlDAOFactory.getConnection();
				result = activateCompany(company, con);
				if(result)
					con.commit();
			} catch (SQLException e) {
				System.err.println("Can not activate company. " + e.getMessage());
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
		
		private Boolean activateCompany(Company company, Connection con) throws SQLException{
			boolean result;
			PreparedStatement pstmt = null;
			try {
				
				pstmt = con.prepareStatement(SQL__ACTIVATE_COMPANY);
				mapCompanyForActivate(company, pstmt);
				result = pstmt.executeUpdate() == 1;
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

		private Company unMapCompany(ResultSet rs) throws SQLException{
			Company c = new Company();
			c.setEmail(rs.getString(MapperParameters.COMPANY__EMAIL));
			c.setId(rs.getLong(MapperParameters.COMPANY__ID));
			c.setName(rs.getString(MapperParameters.COMPANY__NAME));
			c.setNameOfReliable(rs.getString(MapperParameters.COMPANY__NAME_RELIABLE));
			c.setPhone(rs.getString(MapperParameters.COMPANY__PHONE));
			c.setPhoneOfReliable(rs.getString(MapperParameters.COMPANY__PHONE_RELIABLE));
			c.setSkype(rs.getString(MapperParameters.COMPANY__SKYPE));
			c.setActivate(rs.getBoolean(MapperParameters.COMPANY__ACTIVE));
			return c;
		}
		
		private void mapCompanyForInsert(Company c, PreparedStatement pstmt) 
				throws SQLException{
			pstmt.setString(1, c.getName());
			pstmt.setString(2, c.getPhone());
			pstmt.setString(3, c.getPhoneOfReliable());
			pstmt.setString(4, c.getEmail());
			pstmt.setString(5, c.getNameOfReliable());
			pstmt.setString(6, c.getSkype());
			pstmt.setBoolean(7, c.isActivate());
			pstmt.setLong(8, c.getUser().getUserId());
		}
		
		private void mapCompanyForActivate(Company company,
				PreparedStatement pstmt) throws SQLException {
			pstmt.setBoolean(1, true);
			pstmt.setLong(2, company.getId());
			company.setActivate(true);
		}
	
}
