/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.FizioterapeutRepository;
import com.dao.KartonRepository;
import com.dao.LekarRepository;
import com.dao.PregledRepository;
import com.dao.TerapijaRepository;
import com.entity.Karton;
import com.entity.Pregled;
import com.entity.Terapija;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * KartonService predstavlja servis koji implementira potrebne metode sa Pacijenta
 * @author jelena.pajdic
 */

@Service
public class KartonService {
    
    @Autowired
    private KartonRepository kartonRepository;
    @Autowired 
    private PregledRepository pregledRepository;
    @Autowired 
    private LekarRepository lekarRepository;
    @Autowired
    private TerapijaRepository terapijaRepository;
    @Autowired
    private FizioterapeutRepository fizioterapeutRepository;
    @Autowired 
    private SlanjeMejlaService mejlService;
    
    @Transactional(readOnly=true)
    public Karton vratiPacijenta(String jmbg){
        return kartonRepository.findByJmbg(jmbg);
    }
    @Transactional(readOnly=true)
    public boolean postojiPacijent(String jmbg,String sifra){
        int broj=kartonRepository.countByJmbgAndSifra(jmbg, sifra).intValue();
        if(broj==0){
            return false;
        }
        return true;
    }
    @Transactional(readOnly=true)
    public List<Pregled> sviPreglediPacijenta(String jmbg){
        return pregledRepository.findByJmbgOrderByIdDesc(jmbg);
        
    }
    @Transactional(readOnly=true)
    public String emailFizioterapeuta(String jmbg){
        return fizioterapeutRepository.findByEmail(jmbg);
    }
    
  @Transactional(readOnly=true)
    public String emailLekara(String jmbg){
        return lekarRepository.findByEmail(jmbg);
    }
    @Transactional(readOnly=true)
    public List<Terapija> sveTerapijePacijenta(String jmbg){
        return terapijaRepository.findByJmbgPacijetaOrderByIdDesc(jmbg);
    }
    @Transactional(readOnly=true)
    public Terapija vratiTerapiju(int id){
        return terapijaRepository.findById(id);
    }
    @Transactional(readOnly=true)
    public Pregled vratiPregled(int id){
        return pregledRepository.findById(id);
    }
    public void sendMailLekar(Karton k,String message) throws MessagingException{
        mejlService.generateAndSendEmail(k,emailLekara(k.getJmbg()), message);
        
    }
    public void sendMailFizioterapeut(Karton k,String message) throws MessagingException{
         mejlService.generateAndSendEmail(k,emailFizioterapeuta(k.getJmbg()), message);
    }
    public void saveKarton(Karton k){
    	kartonRepository.save(k);
    }
}
