/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.Karton;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jelena.pajdic
 */
public interface KartonRepository extends CrudRepository<Karton, Long> {
     Long countByJmbgAndSifra(String jmbg,String sifra);
     Karton findByJmbg(String jmbg);
     
     @Query(value="SELECT DISTINCT * FROM `karton` JOIN `pregled` ON karton.jmbg=pregled.jmbg AND pregled.lekar_id=?1",nativeQuery=true)
     List<Karton> findDistinctBypregledCollection(String brojLicence);
    
}
