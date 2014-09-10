package ua.nure.pi.dao.mssql;

import java.sql.SQLException;

import java.sql.Connection;

import ua.nure.pi.dao.UserDAO;

public class MSSqlUserDAO implements UserDAO {

	@Override
	public String test() {
		try{
			Connection con = MSSqlDAOFactory.getConnection();
			return con.toString();
		}
		catch(SQLException e){
			System.err.println("can't get connection + # "+e.getMessage());
		}
		return "";
	}

}
