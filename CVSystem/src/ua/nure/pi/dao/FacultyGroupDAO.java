package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.Faculty;
import ua.nure.pi.entity.Group;

public interface FacultyGroupDAO {
	
	Collection<Faculty> getFaculties();
	
	Boolean insertFaculty(Faculty faculty);
	
	Boolean insertFaculties(Collection<Faculty> faculties);
	
	Boolean updateFaculties(Collection<Faculty> faculties);
	
	Boolean updateFaculty(Faculty faculty);
	
	Boolean deleteFaculties(Collection<Faculty> faculties);
	
	Boolean deleteFaculty(Faculty faculty);
	
	Collection<Group> getGroups(long facultyId);
	
	Collection<Group> getGroups(Faculty faculty);
	
	Boolean insertGroups(long FacultiesId, Collection<Group> groups);
	
	Boolean insertGroup(Group group);
	
	Boolean updateGroups(Collection<Group> groups);
	
	Boolean updateGroup(Group group);
	
	Boolean deleteGroups(Collection<Group> groups);
	
	Boolean deleteGroup(Group group);

}
