/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.Lekar;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jelena.pajdic
 */
public interface LekarRepository extends CrudRepository<Lekar, Long> {
    Lekar findByBrojLicence(String brojLicence);
   
    Long countByBrojLicenceAndSifra(String brojLicence,String sifra);
    @Query(value="SELECT `email` FROM `lekar` JOIN `pregled` ON lekar.broj_licence=pregled.lekar_id AND pregled.jmbg=?1 LIMIT 1",nativeQuery=true)
    String findByEmail(String brojLicence);
}
