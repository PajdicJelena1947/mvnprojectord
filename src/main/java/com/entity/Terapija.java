
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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @Column(name = "jmbg_pacijeta")
    private String jmbgPacijenta;
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
    private Pregled pregled;
    @JoinColumn(name = "tip_id", referencedColumnName = "sifra")
    @ManyToOne
    private Tip tip;
    @Column(name = "fizioterapeut_id")
    private String fizioterapeutId;

    public Terapija() {
    }

    public Terapija(Integer id) {
        this.id = id;
    }

    public Terapija(Integer id, String jmbgPacijeta) {
        this.id = id;
        this.jmbgPacijenta = jmbgPacijeta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   

    public String getJmbgPacijenta() {
        return jmbgPacijenta;
    }

    public void setJmbgPacijenta(String jmbgPacijeta) {
        this.jmbgPacijenta = jmbgPacijeta;
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
    
    @JsonIgnore
    public Pregled getPregled() {
        return pregled;
    }

    public void setPregledId(Pregled pregled) {
        this.pregled = pregled;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public String getFizioterapeutId() {
        return fizioterapeutId;
    }

    public void setFizioterapeutId(String fizioterapeutId) {
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
