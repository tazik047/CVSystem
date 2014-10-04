package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.ProgramLanguage;

public interface ProgramLanguageDAO {
	
	Collection<ProgramLanguage> getProgramLanguages();
	
	Collection<ProgramLanguage> getStudentsProgramLanguages(long CVsId);
	
	Boolean insertProgramLanguage(Collection<ProgramLanguage> programLanguages);
	
	Boolean deleteProgramLanguage(Collection<ProgramLanguage> programLanguages);
	
	Boolean updateProgramLanguages(Collection<ProgramLanguage> programLanguages);
	
	Boolean addProgramLanguages(long CVsId, Collection<ProgramLanguage> programLanguages);
}
