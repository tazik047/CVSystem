package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Group;

public interface FacultyGroupDAO {
	
	Collection<Faculty> getFaculties();
	
	Boolean insertFaculties(Collection<Faculty> faculties);
	
	Boolean updateFaculties(Collection<Faculty> faculties);
	
	Boolean deleteFaculties(Collection<Faculty> faculties);
	
	Collection<Group> getGroups();
	
	Boolean insertGroups(Collection<Group> groups);
	
	Boolean updateGroups(Collection<Group> groups);
	
	Boolean deleteGroups(Collection<Group> groups);
}
