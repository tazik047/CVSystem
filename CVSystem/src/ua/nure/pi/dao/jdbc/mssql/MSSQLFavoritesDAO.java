package ua.nure.pi.dao.jdbc.mssql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCFavoritesDAO;

public class MSSQLFavoritesDAO extends JDBCFavoritesDAO {
	
	private static volatile MSSQLFavoritesDAO instance;
	
	private MSSQLFavoritesDAO() {
		SQL__INSERT_FAVORITES = "INSERT INTO Favorites(StudentsId, "
				+ "CompaniesId, DateStamp) VALUES(?, ?, getdate())";
		jdbcDAOFactory = MSSQLDAOFactory.getInstancce();
	}
	
	public static MSSQLFavoritesDAO getInstancce(){
		if(instance == null)
			synchronized (MSSQLFavoritesDAO.class){
				if(instance == null)
					instance = new MSSQLFavoritesDAO();
			}
		return instance;
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return MSSQLDAOFactory.getConnection();
	}
}
