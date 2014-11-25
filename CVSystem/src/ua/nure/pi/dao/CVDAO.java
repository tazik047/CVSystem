package ua.nure.pi.dao;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.pi.entity.CV;

public interface CVDAO {
		
	boolean insertCV(CV cv);
				
	CV getCv(long CVsId);

	boolean insertCV(CV cv, Connection con) throws SQLException;

	CV getCv(long studentsId, Connection con) throws SQLException;
			

}
