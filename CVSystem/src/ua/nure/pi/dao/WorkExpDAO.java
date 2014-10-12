package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.WorkExp;

public interface WorkExpDAO {
		
	Boolean insertWorkExps(long id, Collection<WorkExp> wes);
				
	Collection<WorkExp> getWorkExps(long CVsId);
			

}
