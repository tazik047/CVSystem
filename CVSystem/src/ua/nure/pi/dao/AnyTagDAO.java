package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.AnyTag;

public interface AnyTagDAO {
	
	Collection<AnyTag> getAnyTags(String tableName);
	
	Collection<AnyTag> getStudentsAnyTags(String tableName, long CVsId);
	
	Boolean insertAnyTag(Collection<AnyTag> anyTags);
	
	Boolean deleteAnyTag(Collection<AnyTag> anyTags);
	
	Boolean updateAnyTags(Collection<AnyTag> anyTags);
	
	Boolean addAnyTags(long CVsId, String tableName, Collection<AnyTag> anyTags);
}
