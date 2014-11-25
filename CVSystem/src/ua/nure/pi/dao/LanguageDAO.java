package ua.nure.pi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import ua.nure.pi.entity.Language;

public interface LanguageDAO {
	
	Collection<Language> getLanguages();
	
	Collection<Language> getStudentsLanguages(long CVsId);
	
	Boolean insertLanguage(Collection<Language> languages);
	
	Boolean deleteLanguage(Collection<Language> languages);
	
	Boolean updateLanguages(Collection<Language> languages);
	
	Boolean addLanguages(long CVsId, Collection<Language> languages);

	boolean addLanguage(long cvsId, Collection<Language> languages,
			Connection con) throws SQLException;

	Collection<Language> getStudentsLanguages(long cVsId, Connection con) throws SQLException;
}
