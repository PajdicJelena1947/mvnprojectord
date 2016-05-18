/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entity.Terapija;
import com.entity.Tip;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jelena.pajdic
 */
public interface TipRepository extends CrudRepository<Tip,Long> {
    Tip findBySifra(int sifra);
}
