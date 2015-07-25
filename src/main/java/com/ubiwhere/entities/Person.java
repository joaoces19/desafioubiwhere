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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Person")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByIdPerson", query = "SELECT p FROM Person p WHERE p.idPerson = :idPerson"),
    @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name"),
    @NamedQuery(name = "Person.findByAddress", query = "SELECT p FROM Person p WHERE p.address = :address")})
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPerson")
    @Expose(serialize = true, deserialize = false)
    private Integer idPerson;
    @Size(max = 45)
    @Column(name = "name")
    @Expose
    private String name;
    @Size(max = 45)
    @Column(name = "address")
    @Expose
    private String address;
    @JoinColumn(name = "fkIdCompany", referencedColumnName = "idCompany")
    @ManyToOne
    @Expose(serialize = true, deserialize = false)
    private Company fkIdCompany;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdPerson")
    @Expose(serialize = true, deserialize = false)
    private Collection<Phonenumber> phonenumberCollection;

    public Person() {
    }

    public Person(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
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

    public Company getFkIdCompany() {
        return fkIdCompany;
    }

    public void setFkIdCompany(Company fkIdCompany) {
        this.fkIdCompany = fkIdCompany;
    }

    public Collection<Phonenumber> getPhonenumberCollection() {
        return phonenumberCollection;
    }

    public void setPhonenumberCollection(Collection<Phonenumber> phonenumberCollection) {
        this.phonenumberCollection = phonenumberCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerson != null ? idPerson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.idPerson == null && other.idPerson != null) || (this.idPerson != null && !this.idPerson.equals(other.idPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ubiwhere.entities.Person[ idPerson=" + idPerson + " ]";
    }
    
}
