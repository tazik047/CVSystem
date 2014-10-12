package ua.nure.pi.dao;

import ua.nure.pi.entity.CV;

public interface CVDAO {
		
	boolean insertCV(CV cv);
				
	CV getCv(long CVsId);
			

}
