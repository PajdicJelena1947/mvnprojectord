/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jelena.pajdic
 */

@Entity
@DiscriminatorValue(value=EnumRola.Values.L)  
@XmlRootElement
public class Lekar extends AbstractEntity implements Serializable {
    public Lekar() {
        super();
    }

    public Lekar(String brojLicence) {
        super(brojLicence);
    }

    public Lekar(String brojLicence, String password, String ime, String prezime, String email) {
       super(brojLicence,password,ime,prezime,email);
    }

    
}
