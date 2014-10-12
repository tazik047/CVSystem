package ua.nure.pi.dao.mssql;

import java.sql.SQLException;
import java.sql.Connection;

import ua.nure.pi.dao.UserDAO;

public class MSSqlUserDAO implements UserDAO {

	private static volatile MSSqlUserDAO instance;
	
	private MSSqlUserDAO() {
	}
	
	public static MSSqlUserDAO getInstancce(){
		if(instance == null)
			synchronized (MSSqlUserDAO.class){
				if(instance == null)
					instance = new MSSqlUserDAO();
			}
		return instance;
	}
	
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
