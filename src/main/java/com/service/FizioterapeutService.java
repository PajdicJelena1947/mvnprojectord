/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.FizioterapeutRepository;
import com.dao.TerapijaRepository;
import com.entity.Fizioterapeut;
import com.entity.Terapija;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servis FizioterapeutService predstavlja implementaciju potrebih metoda za fizioterapeuta
 * @author jelena.pajdic
 */

@Service
public class FizioterapeutService {
    @Autowired
    private TerapijaRepository terapijaRepository;
    
    @Autowired
    private FizioterapeutRepository fizoterapeutRepository;
    
   
    
    
    @Transactional(readOnly=true)
   public boolean postojiFizioterapeut(String brojLicence,String sifra){
           int broj=fizoterapeutRepository.countByBrojLicenceAndSifra(brojLicence, sifra).intValue();
           
           if(broj==0){
               return false;
           }
           return true;
    
}
    
    @Transactional(readOnly=true)
    public boolean postojiTerapija(Terapija t){
        int broj=terapijaRepository.countByPocetakAndFizioterapeutId(t.getPocetak(), t.getFizioterapeutId()).intValue();
        
        if(broj==0){
            return false;
        }
        return true;
        
    }
    @Transactional
    public void dodajTerapiju(Terapija t){
        if(!postojiTerapija(t)){
            terapijaRepository.save(t);
        }
        else{
            System.out.println("ima terapija");
        }
    }
    @Transactional(readOnly=true)
    public Fizioterapeut vratiFizoterapeuta(String brojLicence){
        return fizoterapeutRepository.findByBrojLicence(brojLicence);
    }
    @Transactional(readOnly=true)
    public List<Terapija> sveTerapijeFizioterapeuta(String brojLicence){
        return terapijaRepository.findByfizioterapeutIdOrderByIdDesc(brojLicence);
    }
    @Transactional(readOnly=true)
    public Terapija vratiTerapiju(int id){
        return terapijaRepository.findById(id);
    }
    
    @Transactional(readOnly=true)
    public List<Terapija> terapijePacijenta(String jmbg){
        return terapijaRepository.findFirst10ByJmbgPacijetaOrderByIdDesc(jmbg);
    }
    @Transactional
    public void dodajFizioterapeuta(Fizioterapeut f){
        fizoterapeutRepository.save(f);
    }
    
    
}
