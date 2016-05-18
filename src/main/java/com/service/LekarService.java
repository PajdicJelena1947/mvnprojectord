/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.KartonRepository;
import com.dao.LekarRepository;
import com.dao.PregledRepository;
import com.dao.TerapijaRepository;
import com.dao.TipRepository;
import com.entity.Karton;
import com.entity.Lekar;
import com.entity.Pregled;
import com.entity.Terapija;
import com.entity.Tip;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *LekarService klasa predstavlja implementaciju potrebih metoda za lekare
 * @author jelena.pajdic
 */
@Service
public class LekarService {
    
    @Autowired
    private PregledRepository pregledRepository;
    @Autowired 
    private LekarRepository lekarRepository;
    @Autowired
    private KartonRepository kartonRepository;
    @Autowired
    private TerapijaRepository terapijaRepository;
    @Autowired 
    private TipRepository tipRepository;
    
    @Transactional
   public void dodajPregled(Pregled p) {
       if(!postojiPregled(p)){
       pregledRepository.save(p);
   }
       else{
           System.out.println("vec postoji pregled");
       }
   }
   @Transactional(readOnly=true)
   public Lekar vratiLekara(String brojLicence){
       return lekarRepository.findByBrojLicence(brojLicence);
   }
   
   @Transactional(readOnly=true)
   public List<Pregled> sviPregledi(String brojLicence){
           
           return  pregledRepository.findByLekarIdOrderByIdDesc(brojLicence);
       
   }
   @Transactional(readOnly=true)
   public List<Karton> sviPacijenti(String brojLicence){
       return kartonRepository.findDistinctBypregledCollection(brojLicence);
   }
   @Transactional(readOnly=true)
   public boolean postojiLekar(String brojLicence,String sifra){
           int broj=lekarRepository.countByBrojLicenceAndSifra(brojLicence, sifra).intValue();
           
           if(broj==0){
               return false;
           }
           return true;
    
}
   @Transactional(readOnly=true)
   public boolean postojiPregled(Pregled p){
       int broj= pregledRepository.countByVremeAndLekarId(p.getVreme(),p.getLekarId()).intValue();
       if(broj==0){
           return false;
       }
       return true;
       
   }
   @Transactional(readOnly=true)
   public Terapija vratiTerapiju(int id){
       return terapijaRepository.findById(id);
   }
   @Transactional
   public void dodajTerapiju(Terapija t){
       if(!postojiIstiTip(t)){
       terapijaRepository.save(t);
   }
       else{
           System.out.println("isti tip");
       }
  
}
   
   @Transactional(readOnly=true)
   public boolean postojiIstiTip(Terapija t){
    
    int broj=terapijaRepository.countDistinctByTipId(t.getPregledId()).intValue();
    Pregled p =t.getPregledId();
    int brojT=p.getTerapijaCollection().size();
    
    if(broj==brojT){
        return false;
    }
    return true;
    
}
   @Transactional(readOnly=true)
   public Pregled vratiPregled(int id){
       return pregledRepository.findById(id);
   }
   @Transactional(readOnly=true)
   public Tip vratiTip(int id){
       return tipRepository.findBySifra(id);
   }
   
   @Transactional
   public void saveLekar(Lekar l){
       lekarRepository.save(l);
   }
   @Transactional(readOnly=true)
   public Karton vratiPacijenta(String jmbg){
       return kartonRepository.findByJmbg(jmbg);
   }
   @Transactional(readOnly=true)
   public List<Pregled> preglediPacijenta(String jmbg){
       return (List<Pregled>) pregledRepository.findByJmbgOrderByIdDesc(jmbg);
   }
}
