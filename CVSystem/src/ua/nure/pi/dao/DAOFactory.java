package ua.nure.pi.dao;

import ua.nure.pi.dao.mssql.MSSqlDAOFactory;


public abstract class DAOFactory {
	public static final int MSSQL = 1;
	
	public static DAOFactory getDAOFactory(int whichFactory) {

		switch (whichFactory) {
		case MSSQL:
			return MSSqlDAOFactory.getInstancce();
		default:
			return null;
		}
	}

	public abstract FacultyGroupDAO getFacultyGroupDAO();
	
	public abstract EducationDAO getEducationDAO();
	
	public abstract LanguageDAO getLanguageDAO();
	
	public abstract ProgramLanguageDAO getProgramLanguageDAO();
	
	public abstract SertificatsDAO getSertificatsDAO();
	
	public abstract StudentDAO getStudentDAO();
	
	public abstract UserDAO getUserDAO();
	
	public abstract WorkExpDAO getWorkExpDAO();
}
