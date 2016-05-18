/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jelena.pajdic
 */
@Entity
@Table(name = "tip")
@XmlRootElement
public class Tip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "sifra")
    private Integer sifra;
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(mappedBy = "tipId")
    private Collection<Terapija> terapijaCollection;

    public Tip() {
    }

    public Tip(Integer sifra) {
        this.sifra = sifra;
    }

    public Tip(Integer sifra, String naziv) {
        this.sifra = sifra;
        this.naziv = naziv;
    }

    public Integer getSifra() {
        return sifra;
    }

    public void setSifra(Integer sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public Collection<Terapija> getTerapijaCollection() {
        return terapijaCollection;
    }

    public void setTerapijaCollection(Collection<Terapija> terapijaCollection) {
        this.terapijaCollection = terapijaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifra != null ? sifra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tip)) {
            return false;
        }
        Tip other = (Tip) object;
        if ((this.sifra == null && other.sifra != null) || (this.sifra != null && !this.sifra.equals(other.sifra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Tip[ sifra=" + sifra + " ]";
    }
    
}
