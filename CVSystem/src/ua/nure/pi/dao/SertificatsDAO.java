package ua.nure.pi.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import ua.nure.pi.entity.Sertificate;

public interface SertificatsDAO {
		
	Boolean insertSertificats(long CVsId, Collection<Sertificate> eds);
				
	Collection<Sertificate> getSertificats(long CVsId);

	boolean insertSertificats(long cvsId, Collection<Sertificate> sertificates,
			Connection con) throws SQLException;

	Collection<Sertificate> getSertificats(long cVsId, Connection con) throws SQLException;
}
