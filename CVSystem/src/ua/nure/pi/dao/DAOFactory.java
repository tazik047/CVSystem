package ua.nure.pi.dao;

import ua.nure.pi.dao.jdbc.mssql.MSSQLDAOFactory;
import ua.nure.pi.dao.jdbc.mysql.MYSQLDAOFactory;


public abstract class DAOFactory {
	public static final int MSSQL = 1;
	public static final int MYSQL = 2;
	
	public static DAOFactory getDAOFactory(int whichFactory) {

		switch (whichFactory) {
		case MSSQL:
			return MSSQLDAOFactory.getInstancce();
		case MYSQL:
			return MYSQLDAOFactory.getInstancce();
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
	
	public abstract PurposeDAO getPurposeDAO();
	
	public abstract CVDAO getCVDAO();
	
	public abstract PassDAO getPassDAO();
	
	public abstract CompanyDAO getCompanyDAO();
}
