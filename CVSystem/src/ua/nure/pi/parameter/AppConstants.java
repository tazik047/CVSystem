package ua.nure.pi.parameter;

import ua.nure.pi.dao.LanguageDAO;
import ua.nure.pi.dao.ProgramLanguageDAO;
import ua.nure.pi.dao.PurposeDAO;
import ua.nure.pi.dao.StudentDAO;

public interface AppConstants {
	public static final String USER = "user";
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	public static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	// DAO
	public static final String USER_DAO = "userDAO";
	public static final String FACULTYGROUP_DAO = "facultyGroupDAO";
	public static final String STUDENT_DAO = "studentDAO";
	public static final String PROGRAM_LANGUAGE_DAO = "programLanguageDAO";
	public static final String LANGUAGE_DAO = "languageDAO";
	public static final String PURPOSE_DAO = "purposeDAO";
	public static final String PASS_DAO = "passDAO";
	
	// TagName
	public static final String TABLE_PROGRAM_LANGUAGE = "ProgramLanguages";
	public static final String TABLE_LANGUAGE = "Languages";

	// security parameters
	public static final String SECURITY_XML = "securityXML";
	
}
