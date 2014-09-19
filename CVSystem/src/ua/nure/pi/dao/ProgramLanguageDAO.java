package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.ProgramLanguage;

public interface ProgramLanguageDAO {
	
	Collection<ProgramLanguage> getProgramLanguage(long id);
	
	Boolean insertProgramLanguage(Collection<ProgramLanguage> programLanguage);
	
	Boolean deleteProgramLanguage(Collection<ProgramLanguage> programLanguage);
	
	Boolean updateProgramLanguage(Collection<ProgramLanguage> programLanguage);
	
	Boolean addProgramLanguage(long id, Collection<ProgramLanguage> programLanguage);
}
