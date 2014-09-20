package ua.nure.pi.dao;

import ua.nure.pi.dao.mssql.MSSqlDAOFactory;


public abstract class DAOFactory {
	public static final int MSSQL = 1;
	
	public abstract UserDAO getUserDAO();
	
	public static DAOFactory getDAOFactory(int whichFactory) {

		switch (whichFactory) {
		case MSSQL:
			return new MSSqlDAOFactory();
		default:
			return null;
		}
	}

	public abstract FacultyGroupDAO getFacultyGroupDAO();
}
