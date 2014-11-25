package ua.nure.pi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import ua.nure.pi.entity.WorkExp;

public interface WorkExpDAO {
		
	Boolean insertWorkExps(long id, Collection<WorkExp> wes);
				
	Collection<WorkExp> getWorkExps(long CVsId);

	boolean insertWorkExps(long cvsId, Collection<WorkExp> workExps,
			Connection con) throws SQLException;

	Collection<WorkExp> getWorkExps(long cVsId, Connection con) throws SQLException;
			

}
