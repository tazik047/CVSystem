package ua.nure.pi.dao.jdbc.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.dao.jdbc.JDBCFavoritesDAO;
import ua.nure.pi.dao.jdbc.mssql.MSSQLDAOFactory;

public class MYSQLFavoritesDAO extends JDBCFavoritesDAO {
	
private static volatile MYSQLFavoritesDAO instance;
	
	private MYSQLFavoritesDAO() {
		SQL__INSERT_FAVORITES = "INSERT INTO Favorites(StudentsId, "
				+ "CompaniesId, DateStamp) VALUES(?, ?, now())";
		jdbcDAOFactory = MSSQLDAOFactory.getInstancce();
	}
	
	public static MYSQLFavoritesDAO getInstancce(){
		if(instance == null)
			synchronized (MYSQLFavoritesDAO.class){
				if(instance == null)
					instance = new MYSQLFavoritesDAO();
			}
		return instance;
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return MYSQLDAOFactory.getConnection();
	}
}
