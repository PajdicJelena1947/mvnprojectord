/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.Fizioterapeut;
import com.entity.Lekar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jelena.pajdic
 */
public interface FizioterapeutRepository extends CrudRepository<Fizioterapeut, Long> {
Fizioterapeut findByBrojLicence(String brojLicence);
   
    Long countByBrojLicenceAndSifra(String brojLicence,String sifra);
    @Query(value="SELECT `email` FROM `lekar` JOIN `terapija` ON lekar.broj_licence=terapija.fizioterapeut_id AND terapija.jmbg_pacijeta=?1 LIMIT 1;",nativeQuery=true)
    String findByEmail(String jmbg);
    
    
}
