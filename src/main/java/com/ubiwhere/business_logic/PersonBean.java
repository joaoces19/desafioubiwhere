/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ubiwhere.business_logic;

import com.ubiwhere.business_logic.responses.PersonResponse;
import com.ubiwhere.entities.Company;
import com.ubiwhere.entities.Person;
import com.ubiwhere.entities.Phonenumber;
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
@Stateless(mappedName="PersonBean")
public class PersonBean {

    @PersistenceContext(name="com.mycompany_DesafioUbiwhere_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;

    public PersonResponse addPerson(Person person) {
        TypedQuery<Person> query
                = entityManager.createNamedQuery("Person.findByName", Person.class);
        
        Person p;
        
        try{
            p = query.setParameter("name", person.getName()).getSingleResult();
        } catch(NoResultException nre){
            p = null;
        }

        //Se a pessoa já existir
        if(p!=null){
            return PersonResponse.PERSON_NAME_ALREADY_EXISTS;
        }
        
        entityManager.persist(person);	
        
        return PersonResponse.OK;
    }

    public PersonResponse editPerson(Person person) {
        Person p = entityManager.find(Person.class, person.getIdPerson());
        
        //Se a pessoa não existir
        if(p==null){
            return PersonResponse.INVALID_PERSON_ID;
        }
        
        TypedQuery<Person> query
                = entityManager.createNamedQuery("Person.findByName", Person.class);
        
        try{
            p = query.setParameter("name", person.getName()).getSingleResult();
        } catch(NoResultException nre){
            p = null;
        }
        
        //Se já existir uma pessoa com aquele nome
        if(p!=null){
            return PersonResponse.PERSON_NAME_ALREADY_EXISTS;
        }
        
        entityManager.merge(person);
        
        return PersonResponse.OK;
    }

    public PersonResponse removePerson(int idPerson) {
        Person p = entityManager.find(Person.class, idPerson);
        
        //Se a pessoa não existir
        if(p==null){
            return PersonResponse.INVALID_PERSON_ID;
        }
        
        entityManager.remove(p);
        
        return PersonResponse.OK;
    }

    public PersonResponse numberAdd(int idPerson, Phonenumber number) {
        Person p = entityManager.find(Person.class, idPerson);
        
        //Se a pessoa não existir
        if(p==null){
            return PersonResponse.INVALID_PERSON_ID;
        }
        
        TypedQuery<Phonenumber> query
                = entityManager.createNamedQuery("Phonenumber.findByNumber", Phonenumber.class);
        
        Phonenumber n;
        
        try{
            n = query.setParameter("number", number.getNumber()).getSingleResult();
        } catch(NoResultException nre){
            n = null;
        }
        
        if(n!=null){
            return PersonResponse.PERSON_NUMBER_ALREADY_EXISTS;
        }
        
        number.setFkIdPerson(p);
        p.getPhonenumberCollection().add(number);
        
        entityManager.merge(p);
        
        return PersonResponse.OK;
    }

    public PersonResponse numberRemove(int idPerson, int idPhonenumber) {
        Person p = entityManager.find(Person.class, idPerson);
        
        //Se a pessoa não existir
        if(p==null){
            return PersonResponse.INVALID_PERSON_ID;
        }
        
        Phonenumber number = entityManager.find(Phonenumber.class, idPhonenumber);

        if(number==null){
            return PersonResponse.PERSON_NUMBER_DOESNT_EXIST;
        }
        
        p.getPhonenumberCollection().remove(number);
        
        entityManager.merge(p);
        
        return PersonResponse.OK;
    }

    public Person personDetails(int idPerson) {
        return entityManager.find(Person.class, idPerson);
    }

    public Collection<Person> personsDetails() {
        return entityManager.createNamedQuery("Person.findAll", Person.class)
                .getResultList();
    }
    
    public PersonResponse personLinkCompany(int idPerson, int idCompany){
        Person p = entityManager.find(Person.class, idPerson);
        Company c = entityManager.find(Company.class, idCompany);
        
        //Se a pessoa não existir
        if(p==null){
            return PersonResponse.INVALID_PERSON_ID;
        } else if(c==null){
            return PersonResponse.INVALID_COMPANY_ID;
        }
        
        c.getPersonCollection().add(p);
        
        entityManager.merge(c);
        
        return PersonResponse.OK;
    }
    
    public PersonResponse personUnlinkCompany(int idPerson, int idCompany){
        Person p = entityManager.find(Person.class, idPerson);
        Company c = entityManager.find(Company.class, idCompany);
        
        //Se a pessoa não existir
        if(p==null){
            return PersonResponse.INVALID_PERSON_ID;
        } else if(c==null){
            return PersonResponse.INVALID_COMPANY_ID;
        }
        
        c.getPersonCollection().remove(p);
        
        entityManager.merge(c);
        
        return PersonResponse.OK;
    }
}
