package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.Purpose;

public interface PurposeDAO {
	
	Collection<Purpose> getPurposes();
	
	Purpose findPurposes(long PurposesId);
	
	Boolean insertPurposes(Collection<Purpose> purposes);
	
	Boolean deletePurposes(Collection<Purpose> purposes);
	
	Boolean updatePurposes(Collection<Purpose> purposes);
}
