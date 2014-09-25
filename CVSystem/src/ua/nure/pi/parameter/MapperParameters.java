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
	
}
