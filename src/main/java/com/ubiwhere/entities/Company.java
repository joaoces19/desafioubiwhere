/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ubiwhere.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author joaoces
 */
@Entity
@Table(name = "Company")
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findByIdCompany", query = "SELECT c FROM Company c WHERE c.idCompany = :idCompany"),
    @NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name"),
    @NamedQuery(name = "Company.findByAddress", query = "SELECT c FROM Company c WHERE c.address = :address"),
    @NamedQuery(name = "Company.findByNumber", query = "SELECT c FROM Company c WHERE c.number = :number")})
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCompany")
    @Expose(serialize = true, deserialize = false)
    private Integer idCompany;
    @Size(max = 45)
    @Column(name = "name")
    @Expose
    private String name;
    @Size(max = 45)
    @Column(name = "address")
    @Expose
    private String address;
    @Size(max = 45)
    @Column(name = "number")
    @Expose
    private String number;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdCompany")
    @Expose(serialize = true, deserialize = false)
    private Collection<Person> personCollection;

    public Company() {
    }

    public Company(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Collection<Person> getPersonCollection() {
        return personCollection;
    }

    public void setPersonCollection(Collection<Person> personCollection) {
        this.personCollection = personCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompany != null ? idCompany.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.idCompany == null && other.idCompany != null) || (this.idCompany != null && !this.idCompany.equals(other.idCompany))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ubiwhere.entities.Company[ idCompany=" + idCompany + " ]";
    }
    
}
