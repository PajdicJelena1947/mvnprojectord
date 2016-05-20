
package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Klasa pregled predstavlja entitet Pregled u tabeli pregled.
 * @author jelena.pajdic
 */
@Entity
@Table(name = "pregled")
@XmlRootElement
public class Pregled implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "vreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vreme;
    @Column(name = "bolest")
    private String bolest;
    @Column(name = "dijagnoza")
    private String dijagnoza;
    @Column(name = "lekovi")
    private String lekovi;
    @JoinColumn(name = "jmbg", referencedColumnName = "jmbg")
    @ManyToOne(optional = false)
    private Pacijent pacijent;
    @JoinColumn(name = "lekar_id", referencedColumnName = "broj_licence")
    @ManyToOne(optional = false)
    private Fizijatar fizijatar;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "pregled")
    private Collection<Terapija> terapijaCollection;
    
    public Pregled() {
    }

    public Pregled(Integer id) {
        this.id = id;
    }

    public Pregled(Integer id, Date vreme, String bolest) {
        this.id = id;
        this.vreme = vreme;
        this.bolest = bolest;
    }

      
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public String getBolest() {
        return bolest;
    }

    public void setBolest(String bolest) {
        this.bolest = bolest;
    }

    public String getDijagnoza() {
        return dijagnoza;
    }

    public void setDijagnoza(String dijagnoza) {
        this.dijagnoza = dijagnoza;
    }

    public String getLekovi() {
        return lekovi;
    }

    public void setLekovi(String lekovi) {
        this.lekovi = lekovi;
    }
 
   
    @JsonIgnore
    public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent karton) {
		this.pacijent = karton;
	}
	@JsonIgnore
	public Fizijatar getFizijatar() {
		return fizijatar;
	}

	public void setFizijatar(Fizijatar fizijatar) {
		this.fizijatar = fizijatar;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
       
        if (!(object instanceof Pregled)) {
            return false;
        }
        Pregled other = (Pregled) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Pregled[ id=" + id + " ]";
    }

  
    @JsonIgnore
    public Collection<Terapija> getTerapijaCollection() {
        return terapijaCollection;
    }


    public void setTerapijaCollection(Collection<Terapija> terapijaCollection) {
        this.terapijaCollection = terapijaCollection;
    }
    
}
