package ua.nure.pi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import ua.nure.pi.entity.Education;

public interface EducationDAO {
		
	Boolean insertEducations(long CVsId, Collection<Education> eds);
				
	Collection<Education> getEducations(long CVsId);

	boolean insertEducations(long cvsId, Collection<Education> educations,
			Connection con) throws SQLException;

	Collection<Education> getEducations(long cVsId, Connection con) throws SQLException;
			

}
