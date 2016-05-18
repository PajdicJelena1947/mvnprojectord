/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest;

import com.entity.Karton;
import com.entity.Lekar;
import com.entity.Pregled;
import com.entity.Terapija;
import com.excepitons.NemaPregleda;
import com.service.LekarService;
import java.security.Principal;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jelena.pajdic
 */
@RestController
public class LekarRestController {
    @Autowired
    private LekarService lService;
    
    
    @RequestMapping(value = "/svipacijentiLekar/{brojLicence}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Karton>> sviPacijentiLekar(@PathVariable("brojLicence") String brojLicence) {
        List<Karton> kartoni=lService.sviPacijenti(brojLicence);
        return new ResponseEntity<List<Karton>>(kartoni, HttpStatus.OK);
    }
     @RequestMapping(value = "/svipreglediLekar/{brojLicence}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pregled>> sviPreglediLekar(@PathVariable("brojLicence") String brojLicence) {
        List<Pregled> pregledi=lService.sviPregledi(brojLicence);
        return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
    }
    @RequestMapping(value = "/svipreglediPacijentLekar/{jmbg}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pregled>> sviPreglediPacijent(@PathVariable("jmbg") String jmbg) throws NemaPregleda {
        List<Pregled> pregledi=lService.preglediPacijenta(jmbg);
        
       
        return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
    }
     
    @RequestMapping(value="/vratiPregledLekar/{id}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pregled> vratiPregled(@PathVariable("id") int id) throws NemaPregleda  {
        Pregled p;
        p=lService.vratiPregled(id);
        if(p==null) {
            throw new NemaPregleda(id);
        }
        
        return new ResponseEntity<Pregled>(p, HttpStatus.OK);
    }
    @RequestMapping(value="/dodajPregled", method=RequestMethod.POST)
    public ResponseEntity<Void> dodajPregled(@RequestBody Pregled p, Principal principal){
        Lekar l = (Lekar) principal;
        p.setLekarId(l);
        lService.dodajPregled(p);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @RequestMapping(value="/dodajTerapiju", method=RequestMethod.POST)
    public ResponseEntity<Void> dodajTerapiju(@RequestBody Terapija tl)   {
     lService.dodajTerapiju(tl);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/vratiLekara/{brojLicence}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lekar> vratiLekara(@PathVariable("brojLicence") String brojLicence) {
        Lekar p=lService.vratiLekara(brojLicence);
        return new ResponseEntity<Lekar>(p, HttpStatus.OK);
    }
    @RequestMapping(value="/dodajLekara/" ,method = RequestMethod.POST)
    public ResponseEntity<Lekar> vratiLekara(@RequestBody Lekar p) {
        lService.saveLekar(p);
        return new ResponseEntity<Lekar>(p, HttpStatus.OK);
    }
    
}
