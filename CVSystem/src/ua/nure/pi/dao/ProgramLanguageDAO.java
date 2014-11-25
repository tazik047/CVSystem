package ua.nure.pi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import ua.nure.pi.entity.ProgramLanguage;

public interface ProgramLanguageDAO {
	
	Collection<ProgramLanguage> getProgramLanguages();
	
	Collection<ProgramLanguage> getStudentsProgramLanguages(long CVsId);
	
	Boolean insertProgramLanguage(Collection<ProgramLanguage> programLanguages);
	
	Boolean insertProgramLanguageAndGenerateKey(Collection<ProgramLanguage> programLanguage);
	
	Boolean deleteProgramLanguage(Collection<ProgramLanguage> programLanguages);
	
	Boolean updateProgramLanguages(Collection<ProgramLanguage> programLanguages);
	
	Boolean addProgramLanguages(long CVsId, Collection<ProgramLanguage> programLanguages);

	boolean addProgramLanguage(long cvsId,
			Collection<ProgramLanguage> programLanguages, Connection con) throws SQLException;

	Collection<ProgramLanguage> getStudentsProgramLanguages(long cVsId,
			Connection con) throws SQLException;
}
