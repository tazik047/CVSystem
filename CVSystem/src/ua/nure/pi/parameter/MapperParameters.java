package ua.nure.pi.parameter;

public interface MapperParameters {

	// Faculties
	String FACULTY__ID = "FacultiesId";
	String FACULTY__TITLE = "Title";

	// Groups
	String GROUP_ID = "GroupsId";
	String GROUP_TITLE = "Title";
	String GROUP_FACULTIES_ID = "FacultiesId";

	// AnyTag
	String ANY_TAG_TITLE = "Title";
	String ANY_TAG_CVsId = "CVsId";

	// WorkExp
	String WORKEXP_ID = "WorkExpsId";
	String WORKEXP_START = "StartDate";
	String WORKEXP_DURATION = "Duration";
	String WORKEXP_TYPEDURATION = "TypeDuration";
	String WORKEXP_NAMEOFINSTITUTION = "NameOfInstitution";
	String WORKEXP_ROLE = "Role";
	String WORKEXP_CVsId = "CVsId";
	String WORKEXP_IS_NOW = "IsNow";

	// Students
	String STUDENT_ID = "StudentsId";
	String STUDENT_SURNAME = "Surname";
	String STUDENT_FIRSTNAME = "Firstname";
	String STUDENT_PATRONYMIC = "Patronymic";
	String STUDENT_GROUPSID = "GroupsId";
	String STUDENT_CVSID = "CVsId";
	String STUDENT_ADDRESS = "Address";
	String STUDENT_SKYPE = "Skype";
	
	//Language
	String LANGUAGE__ID = "LanguagesId";
	String LANGUAGE__LEVEL = "Level";
	
	//Program Language
	String PROGRAM_LANGUAGE__ID = "ProgramLanguagesId";
	String PROGRAM_LANGUAGE__LEVEL = "Level";
	
	//Education
	String EDUCATION__ID = "EducationsId";
	String EDUCATION__CVsId = "CVsId";
	String EDUCATION__SPECIALTY = "Specialty";
	String EDUCATION__NAMEOFINSTITUTION = "NameOfInstitution";
	String EDUCATION__END = "EndYear";
	String EDUCATION__START = "StartYear";
	
	//Sertificate
	String SERTIFICATE__ID = "SertificatsId";
	String SERTIFICATE__NAME = "Name";
	String SERTIFICATE__YEAR = "Year";
	String SERTIFICATE__CVsID = "CVsId";
	
	//Purpose
	String PURPOSE__ID = "PurposesId";
	
	//CV
	String CV__PURPOSES_ID = "PurposesId";
	String CV__QUALITIES = "Qualities";
	String CV__OTHERS = "Others";
	String CV__DATE_STAMP = "DateStamp";
	String CV__ID = "CVsId";
	
	//Students
	String STUDENT_EMAIL = "Email";
	String STUDENT_BIRTHDAY = "Birthday";
	String STUDENT_PHONE = "Phone";
	
	//User
	String USER__ID = "UsersId";
	String USER__LOGIN = "login";
	String USER__PASSWORD = "password";
	String USER__ROLES = "Role";
	String EDUCATION__FACULTY = "Faculty";
	
	//Company
	String COMPANY__EMAIL = "Email";
	String COMPANY__ID = "CompaniesId";
	String COMPANY__NAME = "Title";
	String COMPANY__NAME_RELIABLE = "FIORespPerson";
	String COMPANY__PHONE = "Phone";
	String COMPANY__PHONE_RELIABLE = "PhoneRespPerson";
	String COMPANY__SKYPE = "Skype";
	String COMPANY__ACTIVE = "Active";
	}
