package ua.nure.pi.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ua.nure.pi.dao.CVDAO;
import ua.nure.pi.dao.CompanyDAO;
import ua.nure.pi.dao.DAOFactory;
import ua.nure.pi.dao.FacultyGroupDAO;
import ua.nure.pi.dao.LanguageDAO;
import ua.nure.pi.dao.PassDAO;
import ua.nure.pi.dao.ProgramLanguageDAO;
import ua.nure.pi.dao.PurposeDAO;
import ua.nure.pi.dao.StudentDAO;
import ua.nure.pi.dao.UserDAO;
import ua.nure.pi.parameter.AppConstants;


public class ContextListener implements ServletContextListener {
	//private static final Logger log = Logger.getLogger(ContextListener.class);
	
	private static final int DBType = DAOFactory.MSSQL;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		/*if (log.isDebugEnabled()) {
			log.debug("Servlet context initialization starts");
		}*/
		ServletContext servletContext = event.getServletContext();
		setUserDAOAttribute(servletContext);
		setFacultyGroupAttribute(servletContext);
		setLanguageAttribute(servletContext);
		setProgramLanguageAttribute(servletContext);
		setPurposeAttribute(servletContext);
		setStudentAttribute(servletContext);
		setPassAttribute(servletContext);
		setCompanyAttribute(servletContext);
		setCVAttribute(servletContext);
	}

	private void setPassAttribute(ServletContext servletContext) {
		PassDAO passDAO = DAOFactory.getDAOFactory(DBType).getPassDAO();
		servletContext.setAttribute(AppConstants.PASS_DAO, passDAO);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		/*if (log.isDebugEnabled()) {
			log.debug("Servlet context destruction starts");
			log.debug("Servlet context destruction finished");
		}*/
	}

	private void setUserDAOAttribute(ServletContext servletContext) {
		UserDAO userDAO = DAOFactory.getDAOFactory(DBType)
				.getUserDAO();
		servletContext.setAttribute(AppConstants.USER_DAO, userDAO);
		//log.debug("UserDAO was created");
	}
	
	private void setFacultyGroupAttribute(ServletContext servletContext) {
		FacultyGroupDAO facultyGroupDAO = DAOFactory.getDAOFactory(DBType)
				.getFacultyGroupDAO();
		servletContext.setAttribute(AppConstants.FACULTYGROUP_DAO, facultyGroupDAO);
	}
	
	private void setLanguageAttribute(ServletContext servletContext) {
		LanguageDAO languageDAO = DAOFactory.getDAOFactory(DBType)
				.getLanguageDAO();
		servletContext.setAttribute(AppConstants.LANGUAGE_DAO, languageDAO);
	}
	
	private void setProgramLanguageAttribute(ServletContext servletContext) {
		ProgramLanguageDAO programLanguagesDAO = DAOFactory.getDAOFactory(DBType)
				.getProgramLanguageDAO();
		servletContext.setAttribute(AppConstants.PROGRAM_LANGUAGE_DAO, programLanguagesDAO);
	}
	
	private void setStudentAttribute(ServletContext servletContext) {
		StudentDAO studentDAO = DAOFactory.getDAOFactory(DBType)
				.getStudentDAO();
		servletContext.setAttribute(AppConstants.STUDENT_DAO, studentDAO);
	}
	
	private void setPurposeAttribute(ServletContext servletContext) {
		PurposeDAO purposeDAO = DAOFactory.getDAOFactory(DBType)
				.getPurposeDAO();
		servletContext.setAttribute(AppConstants.PURPOSE_DAO, purposeDAO);
	}
	
	private void setCompanyAttribute(ServletContext servletContext) {
		CompanyDAO companyDAO = DAOFactory.getDAOFactory(DBType)
				.getCompanyDAO();
		servletContext.setAttribute(AppConstants.COMPANY_DAO, companyDAO);
	}
	
	private void setCVAttribute(ServletContext servletContext) {
		CVDAO cvDAO = DAOFactory.getDAOFactory(DBType)
				.getCVDAO();
		servletContext.setAttribute(AppConstants.CV_DAO, cvDAO);
	}
}
