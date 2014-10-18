package ua.nure.pi.server;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.pi.Path;
import ua.nure.pi.client.GreetingService;
import ua.nure.pi.client.MainService;
import ua.nure.pi.client.RegistrationService;
import ua.nure.pi.dao.FacultyGroupDAO;
import ua.nure.pi.dao.UserDAO;
import ua.nure.pi.dao.mssql.MSSqlDAOFactory;
import ua.nure.pi.dao.mssql.MSSqlStudentDAO;
import ua.nure.pi.dao.mssql.MSSqlUserDAO;
import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Student;
import ua.nure.pi.parameter.AppConstants;
import ua.nure.pi.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MainServiceImpl extends RemoteServiceServlet implements
	MainService {
	
	private FacultyGroupDAO facultyGroupDAO;
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*if (log.isDebugEnabled()) {
			log.debug("GET method starts");
		}*/
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(Path.PAGE__MAIN);
		dispatcher.forward(request, response);
		/*if (log.isDebugEnabled()) {
			log.debug("Response was sent");
		}*/
	}
	
	@Override
	public void init() {
		ServletContext servletContext = getServletContext();
		facultyGroupDAO = (FacultyGroupDAO) servletContext.getAttribute(AppConstants.FACULTYGROUP_DAO);
		
		if (facultyGroupDAO == null) {
			//log.error("UserDAO attribute is not exists.");
			throw new IllegalStateException("FacultyGroupDAO attribute is not exists.");
		}
	}
	
	  public Collection<Faculty> getFaculties() throws IllegalArgumentException {
		    return facultyGroupDAO.getFaculties();
		  }
	  
	  public void sendStudent(Student st) {
		  MSSqlStudentDAO.getInstancce().insertStudent(st);
	  }
}
