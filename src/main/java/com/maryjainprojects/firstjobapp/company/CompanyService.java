package com.maryjainprojects.firstjobapp.company;

import com.maryjainprojects.firstjobapp.job.Job;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies(); // to get all company
    boolean updateCompany(Company company,Long id); // update the company
    void createCompany(Company company); // create new company
    boolean deleteCompany(Long id);
    Company  getCompanyById(Long id);

}
