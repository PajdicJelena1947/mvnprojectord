/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import static javax.persistence.InheritanceType.SINGLE_TABLE;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Klasa AbstractEntity predstavlja abstract klasu entiteta Lekar i Fizioterapeut
 * @author jelena.pajdic
 */
@Entity
@Table(name = "lekar")
@XmlRootElement
@Inheritance(strategy=SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType=DiscriminatorType.STRING) 

public abstract class AbstractEntity implements Serializable {

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EnumRola type;
    
private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "broj_licence")
    private String brojLicence;
    @Column(name = "sifra")
    private String sifra;
    @Column(name = "ime")
    private String ime;
    @Column(name = "prezime")
    private String prezime;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "fizioterapeutId")
    private Collection<Terapija> terapijaCollection;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "lekarId")
    private Collection<Pregled> pregledCollection;

    public AbstractEntity() {
    }

    public AbstractEntity(String brojLicence) {
        this.brojLicence = brojLicence;
    }

    public AbstractEntity(String brojLicence, String sifra, String ime, String prezime, String email) {
        this.brojLicence = brojLicence;
        this.sifra = sifra;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        
    }

    public String getBrojLicence() {
        return brojLicence;
    }

    public void setBrojLicence(String brojLicence) {
        this.brojLicence = brojLicence;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  

    @XmlTransient
    public Collection<Terapija> getTerapijaCollection() {
        return terapijaCollection;
    }

    public void setTerapijaCollection(Collection<Terapija> terapijaCollection) {
        this.terapijaCollection = terapijaCollection;
    }

    @XmlTransient
    public Collection<Pregled> getPregledCollection() {
        return pregledCollection;
    }

    public void setPregledCollection(Collection<Pregled> pregledCollection) {
        this.pregledCollection = pregledCollection;
    }
    public EnumRola getType() {
		return type;
	}

	public void setType(EnumRola type) {
		this.type = type;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brojLicence != null ? brojLicence.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AbstractEntity)) {
            return false;
        }
        AbstractEntity other = (AbstractEntity) object;
        if ((this.brojLicence == null && other.brojLicence != null) || (this.brojLicence != null && !this.brojLicence.equals(other.brojLicence))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Lekar[ brojLicence=" + brojLicence + " ]";
    }

    

    
}
