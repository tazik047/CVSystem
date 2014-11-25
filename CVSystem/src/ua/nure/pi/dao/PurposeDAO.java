package ua.nure.pi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import ua.nure.pi.entity.Purpose;

public interface PurposeDAO {
	
	Collection<Purpose> getPurposes();
	
	Purpose findPurposes(long PurposesId);
	
	Boolean insertPurposes(Collection<Purpose> purposes);
	
	Boolean deletePurposes(Collection<Purpose> purposes);
	
	Boolean updatePurposes(Collection<Purpose> purposes);
	
	Boolean insertPurposeAndGenerateKey(Purpose purpose);

	Purpose findPurposes(long long1, Connection con) throws SQLException;
}
