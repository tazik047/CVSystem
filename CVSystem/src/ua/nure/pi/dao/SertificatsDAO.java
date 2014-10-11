package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.Sertificate;

public interface SertificatsDAO {
		
	Boolean insertSertificats(long CVsId, Collection<Sertificate> eds);
				
	Collection<Sertificate> getSertificats(long CVsId);
}
