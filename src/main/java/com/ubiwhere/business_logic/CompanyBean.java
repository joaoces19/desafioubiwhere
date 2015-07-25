/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ubiwhere.business_logic;

import com.ubiwhere.business_logic.responses.CompanyResponse;
import com.ubiwhere.business_logic.responses.PersonResponse;
import com.ubiwhere.entities.Company;
import com.ubiwhere.entities.Person;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author joaoces
 */
@Stateless(mappedName="CompanyBean")
public class CompanyBean {
    
    @PersistenceContext(name="com.mycompany_DesafioUbiwhere_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    public CompanyResponse addCompany(Company company){
        TypedQuery<Company> query
                = entityManager.createNamedQuery("Company.findByName", Company.class);
        
        Company c;
        
        try{
            c = query.setParameter("name", company.getName()).getSingleResult();
        } catch(NoResultException nre){
            c = null;
        }
        
        //Se a empresa com aquele nome existir
        if(c!=null){
            return CompanyResponse.COMPANY_NAME_ALREADY_EXISTS;
        }
        
        entityManager.persist(company);   
        
        return CompanyResponse.OK;
    }
    
    public CompanyResponse editCompany(Company company){
        Company c = entityManager.find(Company.class, company.getIdCompany());
        
        //Se a empresa com aquele nome existir
        if(c==null){
            return CompanyResponse.INVALID_COMPANY_ID;
        }
        
        TypedQuery<Company> query
                = entityManager.createNamedQuery("Person.findByName", Company.class);
        
        try{
            c = query.setParameter("name", company.getName()).getSingleResult();
        } catch(NoResultException nre){
            c = null;
        }
        
        //Se já existir uma pessoa com aquele nome
        if(c!=null){
            return CompanyResponse.COMPANY_NAME_ALREADY_EXISTS;
        }
        
        entityManager.merge(company);
        
        return CompanyResponse.OK;
    }
    
    public CompanyResponse removeCompany(int idCompany){
        Company c = entityManager.find(Company.class, idCompany);
        
        //Se a empresa com aquele nome existir
        if(c==null){
            return CompanyResponse.INVALID_COMPANY_ID;
        }
        
        for(Person p : c.getPersonCollection()){
            p.setFkIdCompany(null);
        }
                
        entityManager.remove(c);        
        
        return CompanyResponse.OK;
    }
    
    public Company companyDetails(int idCompany){  
        return entityManager.find(Company.class, idCompany);
    }
    
    public Collection<Company> companiesDetails(){      
        return entityManager.createNamedQuery("Company.findAll", Company.class)
                .getResultList();
    }
}
