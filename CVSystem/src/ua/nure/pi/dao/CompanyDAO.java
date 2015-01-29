package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.Company;

public interface CompanyDAO {
	
	Collection<Company> getCompanies();
	
	Collection<Company> getNotActiveCompanies();
	
	Collection<Company> getActiveCompanies();
	
	Company getCompany(long companiesId);
		
	Boolean insertCompany(Company company);
	
	Boolean deleteCompany(Company company);
	
	Boolean activateCompany(Company company);

	
}
