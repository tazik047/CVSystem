package ua.nure.pi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

import ua.nure.pi.entity.CV;
import ua.nure.pi.entity.Language;
import ua.nure.pi.entity.ProgramLanguage;
import ua.nure.pi.entity.Purpose;

public interface CVDAO {
		
	boolean insertCV(CV cv);
				
	CV getCv(long CVsId);
	
	Collection<CV> getCvs(Collection<Long> cvsids);

	boolean insertCV(CV cv, Connection con) throws SQLException;

	CV getCv(long studentsId, Connection con) throws SQLException;
			
	Collection<CV> searchCV(Collection<Language> languages,
			Collection<ProgramLanguage> planguages,
			Collection<Purpose> purposes, int start, int end );

	HashMap<String, Integer> getPurposeStat();

	HashMap<String, Integer> getProgLangStat();
}
