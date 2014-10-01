package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.Education;
import ua.nure.pi.entity.WorkExp;

public interface EducationDAO {
		
	Boolean insertEducations(Collection<Education> eds);
				
	Collection<Education> getEducations(long studentId);
			

}
