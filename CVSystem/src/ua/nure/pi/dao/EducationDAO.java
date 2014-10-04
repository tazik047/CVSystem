package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.Education;

public interface EducationDAO {
		
	Boolean insertEducations(long CVsId, Collection<Education> eds);
				
	Collection<Education> getEducations(long CVsId);
			

}
