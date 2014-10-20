package ua.nure.pi.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import ua.nure.pi.dao.LanguageDAO;
import ua.nure.pi.dao.ProgramLanguageDAO;
import ua.nure.pi.dao.PurposeDAO;
import ua.nure.pi.dao.StudentDAO;
import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Purpose;
import ua.nure.pi.entity.Student;
import ua.nure.pi.parameter.AppConstants;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MainServiceImpl extends RemoteServiceServlet implements
	MainService {
	
	private FacultyGroupDAO facultyGroupDAO;
	
	private StudentDAO studentDAO;
	
	private ProgramLanguageDAO programLanguageDAO;
	
	private LanguageDAO languageDAO;
	
	private PurposeDAO purposeDAO;

	
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
		studentDAO = (StudentDAO) servletContext.getAttribute(AppConstants.STUDENT_DAO);
		programLanguageDAO = (ProgramLanguageDAO) servletContext.getAttribute(AppConstants.PROGRAM_LANGUAGE_DAO);
		languageDAO = (LanguageDAO) servletContext.getAttribute(AppConstants.LANGUAGE_DAO);
		purposeDAO = (PurposeDAO) servletContext.getAttribute(AppConstants.PURPOSE_DAO);
		
		if (facultyGroupDAO == null) {
			throw new IllegalStateException("FacultyGroupDAO attribute is not exists.");
		}
		
		if (studentDAO == null) {
			throw new IllegalStateException("FacultyGroupDAO attribute is not exists.");
		}
		if (programLanguageDAO == null) {
			throw new IllegalStateException("FacultyGroupDAO attribute is not exists.");
		}
		
		if (languageDAO == null) {
			throw new IllegalStateException("FacultyGroupDAO attribute is not exists.");
		}
		
		if (purposeDAO == null) {
			throw new IllegalStateException("FacultyGroupDAO attribute is not exists.");
		}
	}
	
	  public Collection<Faculty> getFaculties() throws IllegalArgumentException {
		    return facultyGroupDAO.getFaculties();
		  }
	  
	  public void sendStudent(Student st) {
		  studentDAO.insertStudent(st);
	  }
	  
	  public Collection<ProgramLanguage> getProgramLanguages() throws IllegalArgumentException {
		    return programLanguageDAO.getProgramLanguages();  
		  }
	  
	  public Collection<Language> getLanguages() throws IllegalArgumentException {
		    return languageDAO.getLanguages();
		  }
	  
	  public Collection<Purpose> getPurposes() throws IllegalArgumentException {
		    return purposeDAO.getPurposes();
		   }

	@Override
	public String getPage(String url) {
		url = getServletContext().getRealPath("WEB-INF") + url;
		String res = "";
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File(url)));
	        String str = null;
	        
	        while ((str = br.readLine()) != null) {
	            res+=str;
	        }
		}
		catch(Exception e){
			throw new IllegalArgumentException(e.getLocalizedMessage());
		}
		return res;
	}

}
