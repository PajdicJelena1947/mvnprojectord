/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *Klasa terapija predstavlja entitet tabele terapija
 * @author jelena.pajdic
 */
@Entity
@Table(name = "terapija")
@XmlRootElement
public class Terapija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ime_pacijenta")
    private String imePacijenta;
    @Column(name = "prezime_pacijenta")
    private String prezimePacijenta;
    @Column(name = "jmbg_pacijeta")
    private String jmbgPacijeta;
    @Column(name = "minutaza")
    private Integer minutaza;
    @Column(name = "broj_terapija")
    private Integer brojTerapija;
    @Column(name = "pocetak")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pocetak;
    @Column(name = "kraj")
    @Temporal(TemporalType.TIMESTAMP)
    private Date kraj;
    @JoinColumn(name = "pregled_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pregled pregledId;
    @JoinColumn(name = "tip_id", referencedColumnName = "sifra")
    @ManyToOne
    private Tip tipId;
    @JoinColumn(name = "fizioterapeut_id", referencedColumnName = "broj_licence")
    @ManyToOne
    private Fizioterapeut fizioterapeutId;

    public Terapija() {
    }

    public Terapija(Integer id) {
        this.id = id;
    }

    public Terapija(Integer id, String imePacijenta, String prezimePacijenta, String jmbgPacijeta) {
        this.id = id;
        this.imePacijenta = imePacijenta;
        this.prezimePacijenta = prezimePacijenta;
        this.jmbgPacijeta = jmbgPacijeta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImePacijenta() {
        return imePacijenta;
    }

    public void setImePacijenta(String imePacijenta) {
        this.imePacijenta = imePacijenta;
    }

    public String getPrezimePacijenta() {
        return prezimePacijenta;
    }

    public void setPrezimePacijenta(String prezimePacijenta) {
        this.prezimePacijenta = prezimePacijenta;
    }

    public String getJmbgPacijeta() {
        return jmbgPacijeta;
    }

    public void setJmbgPacijeta(String jmbgPacijeta) {
        this.jmbgPacijeta = jmbgPacijeta;
    }

    public Integer getMinutaza() {
        return minutaza;
    }

    public void setMinutaza(Integer minutaza) {
        this.minutaza = minutaza;
    }

    public Integer getBrojTerapija() {
        return brojTerapija;
    }

    public void setBrojTerapija(Integer brojTerapija) {
        this.brojTerapija = brojTerapija;
    }

    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

    public Date getKraj() {
        return kraj;
    }

    public void setKraj(Date kraj) {
        this.kraj = kraj;
    }

    public Pregled getPregledId() {
        return pregledId;
    }

    public void setPregledId(Pregled pregledId) {
        this.pregledId = pregledId;
    }

    public Tip getTipId() {
        return tipId;
    }

    public void setTipId(Tip tipId) {
        this.tipId = tipId;
    }

    public Fizioterapeut getFizioterapeutId() {
        return fizioterapeutId;
    }

    public void setFizioterapeutId(Fizioterapeut fizioterapeutId) {
        this.fizioterapeutId = fizioterapeutId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Terapija)) {
            return false;
        }
        Terapija other = (Terapija) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Terapija[ id=" + id + " ]";
    }
    
}
