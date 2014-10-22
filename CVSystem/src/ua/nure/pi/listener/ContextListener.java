package ua.nure.pi.listener;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//import org.apache.log4j.Logger;







import ua.nure.pi.dao.DAOFactory;
import ua.nure.pi.dao.FacultyGroupDAO;
import ua.nure.pi.dao.LanguageDAO;
import ua.nure.pi.dao.PassDAO;
import ua.nure.pi.dao.ProgramLanguageDAO;
import ua.nure.pi.dao.PurposeDAO;
import ua.nure.pi.dao.StudentDAO;
import ua.nure.pi.dao.UserDAO;
import ua.nure.pi.parameter.AppConstants;


/**
 * Context listener.
 * 
 * @author Volodymyr_Semerkov
 * 
 */
public class ContextListener implements ServletContextListener {
	//private static final Logger log = Logger.getLogger(ContextListener.class);

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
	}

	private void setPassAttribute(ServletContext servletContext) {
		PassDAO passDAO = DAOFactory.getDAOFactory(DAOFactory.MSSQL).getPassDAO();
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
		UserDAO userDAO = DAOFactory.getDAOFactory(DAOFactory.MSSQL)
				.getUserDAO();
		servletContext.setAttribute(AppConstants.USER_DAO, userDAO);
		//log.debug("UserDAO was created");
	}
	
	private void setFacultyGroupAttribute(ServletContext servletContext) {
		FacultyGroupDAO facultyGroupDAO = DAOFactory.getDAOFactory(DAOFactory.MSSQL)
				.getFacultyGroupDAO();
		servletContext.setAttribute(AppConstants.FACULTYGROUP_DAO, facultyGroupDAO);
	}
	
	private void setLanguageAttribute(ServletContext servletContext) {
		LanguageDAO languageDAO = DAOFactory.getDAOFactory(DAOFactory.MSSQL)
				.getLanguageDAO();
		servletContext.setAttribute(AppConstants.LANGUAGE_DAO, languageDAO);
	}
	
	private void setProgramLanguageAttribute(ServletContext servletContext) {
		ProgramLanguageDAO programLanguagesDAO = DAOFactory.getDAOFactory(DAOFactory.MSSQL)
				.getProgramLanguageDAO();
		servletContext.setAttribute(AppConstants.PROGRAM_LANGUAGE_DAO, programLanguagesDAO);
	}
	
	private void setStudentAttribute(ServletContext servletContext) {
		StudentDAO studentDAO = DAOFactory.getDAOFactory(DAOFactory.MSSQL)
				.getStudentDAO();
		servletContext.setAttribute(AppConstants.STUDENT_DAO, studentDAO);
	}
	
	private void setPurposeAttribute(ServletContext servletContext) {
		PurposeDAO purposeDAO = DAOFactory.getDAOFactory(DAOFactory.MSSQL)
				.getPurposeDAO();
		servletContext.setAttribute(AppConstants.PURPOSE_DAO, purposeDAO);
	}
}
