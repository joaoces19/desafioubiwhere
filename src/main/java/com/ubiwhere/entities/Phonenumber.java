/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ubiwhere.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joaoces
 */
@Entity
@Table(name = "Phonenumber")
@NamedQueries({
    @NamedQuery(name = "Phonenumber.findAll", query = "SELECT p FROM Phonenumber p"),
    @NamedQuery(name = "Phonenumber.findByIdPhonenumber", query = "SELECT p FROM Phonenumber p WHERE p.idPhonenumber = :idPhonenumber"),
    @NamedQuery(name = "Phonenumber.findByNumber", query = "SELECT p FROM Phonenumber p WHERE p.number = :number")})
public class Phonenumber implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPhonenumber")
    @Expose(serialize = true, deserialize = false)
    private Integer idPhonenumber;
    @Size(max = 45)
    @Column(name = "number")
    @Expose
    private String number;
    @JoinColumn(name = "fkIdPerson", referencedColumnName = "idPerson")
    @ManyToOne(optional = true)
    private Person fkIdPerson;

    public Phonenumber() {
    }

    public Phonenumber(Integer idPhonenumber) {
        this.idPhonenumber = idPhonenumber;
    }

    public Integer getIdPhonenumber() {
        return idPhonenumber;
    }

    public void setIdPhonenumber(Integer idPhonenumber) {
        this.idPhonenumber = idPhonenumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getFkIdPerson() {
        return fkIdPerson;
    }

    public void setFkIdPerson(Person fkIdPerson) {
        this.fkIdPerson = fkIdPerson;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPhonenumber != null ? idPhonenumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Phonenumber)) {
            return false;
        }
        Phonenumber other = (Phonenumber) object;
        if ((this.idPhonenumber == null && other.idPhonenumber != null) || (this.idPhonenumber != null && !this.idPhonenumber.equals(other.idPhonenumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ubiwhere.entities.Phonenumber[ idPhonenumber=" + idPhonenumber + " ]";
    }
    
}
