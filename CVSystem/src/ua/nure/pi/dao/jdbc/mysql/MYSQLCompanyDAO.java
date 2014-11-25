package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCCompanyDAO;

public class MYSQLCompanyDAO extends JDBCCompanyDAO {
	
	private static volatile MYSQLCompanyDAO instance;
	
	private MYSQLCompanyDAO() {
		jdbcDAOFaactory = MYSQLDAOFactory.getInstancce();
	}
	
	public static MYSQLCompanyDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLCompanyDAO.class){
				if(instance == null)
					instance = new MYSQLCompanyDAO();
			}
		return instance;
	}
	/*
	private static final String SQL__SELECT_COMPANY = "SELECT * FROM Companies WHERE CompaniesId = ?";
	private static final String SQL__SELECT_ALL_COMPANY = "SELECT * FROM Companies";
	private static final String SQL__INSERT_COMPANY = "INSERT INTO Companies(Title, Phone, PhoneRespPerson, "
			+ "Email, FIORespPerson, Skype, Active, CompaniesId) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL__SELECT_NOT_ACTIVE_COMPANY = "SELECT * FROM Companies WHERE Atcive = 1";
	private static final String SQL__ACTIVATE_COMPANY = "UPDATE Companies SET Active=? WHERE CompaniesId=?";*/

	@Override
	protected Connection getConnection() throws SQLException {
		return MYSQLDAOFactory.getConnection();
	}
	
}
