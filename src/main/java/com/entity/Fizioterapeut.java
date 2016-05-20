
package com.entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Klasa Fizioterapeut predstavlja enitet tabele lekar
 * @author jelena.pajdic
 */
@Entity
@DiscriminatorValue(value=Enum.Values.F)  
@XmlRootElement
public class Fizioterapeut extends Lekar implements Serializable {
	private static final long serialVersionUID = 1L;
     public Fizioterapeut() {
        super();
    }

    public Fizioterapeut(String brojLicence) {
        super(brojLicence);
    }

    public Fizioterapeut(String brojLicence, String password, String ime, String prezime, String email) {
       super(brojLicence,password,ime,prezime,email);
    }
}
