package ua.nure.pi.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.pi.Path;
import ua.nure.pi.client.MainService;
import ua.nure.pi.dao.CVDAO;
import ua.nure.pi.dao.CompanyDAO;
import ua.nure.pi.dao.FacultyGroupDAO;
import ua.nure.pi.dao.LanguageDAO;
import ua.nure.pi.dao.PassDAO;
import ua.nure.pi.dao.ProgramLanguageDAO;
import ua.nure.pi.dao.PurposeDAO;
import ua.nure.pi.dao.StudentDAO;
import ua.nure.pi.dao.UserDAO;
import ua.nure.pi.entity.CV;
import ua.nure.pi.entity.Company;
import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Group;
import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Purpose;
import ua.nure.pi.entity.Student;
import ua.nure.pi.entity.User;
import ua.nure.pi.parameter.AppConstants;
import ua.nure.pi.security.Hashing;

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

	private PassDAO passDAO;
	
	private UserDAO userDAO;
	
	private CompanyDAO companyDAO;
	
	private CVDAO cvDAO; 
	
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
		passDAO = (PassDAO) servletContext.getAttribute(AppConstants.PASS_DAO);
		userDAO = (UserDAO) servletContext.getAttribute(AppConstants.USER_DAO);
		companyDAO = (CompanyDAO) servletContext.getAttribute(AppConstants.COMPANY_DAO);
		cvDAO = (CVDAO) servletContext.getAttribute(AppConstants.CV_DAO);
		
		if (facultyGroupDAO == null) {
			throw new IllegalStateException("FacultyGroupDAO attribute is not exists.");
		}
		
		if (studentDAO == null) {
			throw new IllegalStateException("StudentDAO attribute is not exists.");
		}
		if (programLanguageDAO == null) {
			throw new IllegalStateException("ProgramLanguageDAO attribute is not exists.");
		}
		
		if (languageDAO == null) {
			throw new IllegalStateException("LanguageDAO attribute is not exists.");
		}
		
		if (purposeDAO == null) {
			throw new IllegalStateException("PurposeDAO attribute is not exists.");
		}
		
		if (passDAO == null) {
			throw new IllegalStateException("PassDAO attribute is not exists.");
		}
		
		if (userDAO == null) {
			throw new IllegalStateException("UserDAO attribute is not exists.");
		}
		
		if (companyDAO == null) {
			throw new IllegalStateException("CompanyDAO attribute is not exists.");
		}
		
		if (cvDAO == null) {
			throw new IllegalStateException("CVDAO attribute is not exists.");
		}
	}
	
	  public Collection<Faculty> getFaculties() throws IllegalArgumentException {
		    return facultyGroupDAO.getFaculties();
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
		url = getServletContext().getRealPath("") + url;
		String res = "";
		try{
		    BufferedReader br = new BufferedReader(
		       new InputStreamReader(
		                  new FileInputStream(new File(url)), "UTF8"));
	        String str = null;
	        
	        while ((str = br.readLine()) != null) {
	            res+=str;
	        }
	        br.close();
		}
		catch(Exception e){
			throw new IllegalArgumentException(e.getLocalizedMessage());
		}
		return res;
	}

	@Override
	public Boolean checkPass(String pass) throws IllegalArgumentException {
		return passDAO.checkPass(pass);
	}

	@Override
	public void sendStudent(Student st, Boolean newPurp,
			Collection<ProgramLanguage> newPL) {
		if(newPurp){
			purposeDAO.insertPurposeAndGenerateKey(st.getCv().getPurpose());
		}
		if(newPL.size()!=0){
			programLanguageDAO.insertProgramLanguageAndGenerateKey(newPL);
			st.getCv().getProgramLanguages().addAll(newPL);
		}
		if(!studentDAO.insertStudent(st))
			throw new IllegalArgumentException("Произошла ошибка при сохранении резюме");
	}

	@Override
	public User login(String login, String password)
			throws IllegalArgumentException {
		if(checkLogined()!=null)
			throw new IllegalArgumentException("Вы уже вошли  в систему");
		User user = userDAO.getUser(login);
		if(user!=null &&
				Hashing.salt(password, login).equals(user.getPassword())){
			HttpServletRequest request = getThreadLocalRequest();
			HttpSession session = request.getSession();
			if(!user.isAdmin()){
				Company c = companyDAO.getCompany(user.getUserId());
				if(c==null)
					throw new IllegalArgumentException("Произошла ошибка при получении компании.");
				if(!c.isActivate())
					throw new IllegalArgumentException("Ваша учетная запись еще не активирована.");
				session.setAttribute(AppConstants.COMPANY, c);
			}
			session.setAttribute(AppConstants.USER, user);
			return user;
		}
		return null;
	}
	
	@Override
	public Collection<Student> getStudents()
			throws IllegalArgumentException {
		checkAdminRoleAndThrowEx();
		return studentDAO.getStudents();
	}
	
	/**
	 * 
	 * @return true if user admin, false if company or visitor
	 */
	private boolean checkAdminRole(){
		HttpServletRequest request = getThreadLocalRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(AppConstants.USER);
		return user==null?false:user.isAdmin();
	}
	
	private void checkAdminRoleAndThrowEx() throws IllegalArgumentException{
		if(!checkAdminRole())
			throw new IllegalArgumentException("У вас недостаточно прав для выполнения этого действия.");
	}

	/**
	 * 
	 * @return true if user logined
	 */
	@Override
	public User checkLogined(){
		HttpServletRequest request = getThreadLocalRequest();
		HttpSession session = request.getSession();
		return (User) session.getAttribute(AppConstants.USER);
	}

	@Override
	public void logout() throws IllegalArgumentException {
		if(checkLogined()!=null){
			HttpServletRequest request = getThreadLocalRequest();
			HttpSession session = request.getSession();
			session.removeAttribute(AppConstants.USER);
			return;
		}
		throw new IllegalArgumentException("Вы не авторизированы!");
	}
	
	public void insertCompany(Company c){
		if(!companyDAO.insertCompany(c))
			throw new IllegalArgumentException("Произошла ошибка при сохранении данных");
	}

	@Override
	public void updateFaculty(Faculty f) throws IllegalArgumentException {
		checkAdminRoleAndThrowEx();
		if(!facultyGroupDAO.updateFaculty(f))
			throw new IllegalArgumentException("Не удалось обновить факультет.");
	}

	@Override
	public long addFaculty(Faculty f) throws IllegalArgumentException {
		checkAdminRoleAndThrowEx();
		long id = facultyGroupDAO.insertFaculty(f);
		if(id==-1)
			throw new IllegalArgumentException("Не удалось вставить новый факультет");
		return id;
	}

	@Override
	public void deleteFaculty(Faculty f) throws IllegalArgumentException {
		checkAdminRoleAndThrowEx();
		if(!facultyGroupDAO.deleteFaculty(f))
			throw new IllegalArgumentException("Не удалось удалить факультет");
	}

	@Override
	public long addGroup(Group g) throws IllegalArgumentException {
		checkAdminRoleAndThrowEx();
		long id = facultyGroupDAO.insertGroup(g);
		if(id == -1)
			throw new IllegalArgumentException("Не удалось вставить новую группу");
		return id;
	}

	@Override
	public void updateGroup(Group g) throws IllegalArgumentException {
		checkAdminRoleAndThrowEx();
		if(!facultyGroupDAO.updateGroup(g))
			throw new IllegalArgumentException("Не удалось обновить группу.");
	}

	@Override
	public void deleteGroup(Group g) throws IllegalArgumentException {
		checkAdminRoleAndThrowEx();
		if(!facultyGroupDAO.deleteGroup(g))
			throw new IllegalArgumentException("Не удалось удалить группу");		
	}

	@Override
	public Collection<Company> getNotActivedCompany()
			throws IllegalArgumentException {
		checkAdminRoleAndThrowEx();
		Collection<Company> companies = companyDAO.getNotActiveCompanies();
		if(companies==null)
			throw new IllegalArgumentException("Не удалось получить список компаний");	
		return companies;
	}

	@Override
	public Collection<CV> searchCV(Collection<Language> languages,
			Collection<ProgramLanguage> planguages,
			Collection<Purpose> purposes, int startIndex, int endIndex)
			throws IllegalArgumentException {
		
		return cvDAO.searchCV(languages, planguages, purposes, startIndex, endIndex);
	}
	
	
}
