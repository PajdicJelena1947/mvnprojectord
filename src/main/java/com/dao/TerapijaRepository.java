/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.Fizioterapeut;
import com.entity.Lekar;
import com.entity.Pregled;
import com.entity.Terapija;
import java.util.Date;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jelena.pajdic
 */
public interface TerapijaRepository extends CrudRepository<Terapija, Long> {
    @Query(value="SELECT COUNT(DISTINCT tip_id) FROM `terapija` WHERE pregled_id=?1;",nativeQuery=true)
    Long countDistinctByTipId(Pregled p);
    Terapija findById(int id);
    Long countByPocetakAndFizioterapeutId(Date pocetak,Fizioterapeut fizioterapeut);
    List<Terapija> findByJmbgPacijetaOrderByIdDesc(String jmbg);
    
    List<Terapija> findFirst10ByJmbgPacijetaOrderByIdDesc(String jmbg);
    
    @Query(value="SELECT * FROM `terapija` WHERE fizioterapeut_id=?1 ORDER BY id DESC;",nativeQuery=true)
    List<Terapija> findByfizioterapeutIdOrderByIdDesc(String brojLicence);
    
}
