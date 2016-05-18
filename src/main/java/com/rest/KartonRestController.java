/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest;

import com.entity.Karton;
import com.entity.Pregled;
import com.entity.Terapija;
import com.excepitons.NemaPregleda;
import com.excepitons.NemaTerapije;
import com.service.KartonService;
import java.security.Principal;
import java.util.List;
import javax.mail.MessagingException;
import static javax.management.Query.value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author jelena.pajdic
 */
@RestController
public class KartonRestController {
    @Autowired
    private KartonService kService;
    
    @RequestMapping(value = "/svipreglediPacijent/{jmbg}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pregled>> sviPreglediPacijent(@PathVariable("jmbg") String jmbg) {
        List<Pregled> pregledi=kService.sviPreglediPacijenta(jmbg);
        return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/sveTerapijePacijent/{jmbg}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Terapija>> sveTerapijePacijenta(@PathVariable("jmbg") String jmbg) {
        List<Terapija> terapije=kService.sveTerapijePacijenta(jmbg);
        return new ResponseEntity<List<Terapija>>(terapije, HttpStatus.OK);
    }
    
    @RequestMapping(value="/vratiTerapijuPacijent/{id}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Terapija> vratiTerapiju(@PathVariable("id") int id) throws NemaTerapije {
        Terapija terapija=kService.vratiTerapiju(id);
        if(terapija==null){
        	throw new NemaTerapije(id);
        }
        return new ResponseEntity<Terapija>(terapija, HttpStatus.OK);
    }
    @RequestMapping(value="/vratiPregledPacijent/{id}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pregled> vratiPregled(@PathVariable("id") int id) throws NemaPregleda {
        Pregled p=kService.vratiPregled(id);
        if(p==null){
        	throw new NemaPregleda(id);
        }
        return new ResponseEntity<Pregled>(p, HttpStatus.OK);
    }

    @RequestMapping(value="/posaljiMejlLekaru",method=RequestMethod.POST)
    public ResponseEntity<Void> posaljiMejlLekaru(@RequestParam String poruka, Principal principal) throws MessagingException {
        Karton k= (Karton) principal;
        kService.sendMailLekar(k, poruka);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @RequestMapping(value="/posaljiMejlFizioterapetu", method=RequestMethod.POST)
    public ResponseEntity<Void> posaljiMejlFizioterapeutu(@RequestParam String poruka, Principal principal) throws MessagingException {
        Karton k= (Karton) principal;
        kService.sendMailFizioterapeut(k, poruka);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @RequestMapping(value="/login/" ,method=RequestMethod.POST)
    public String login(@RequestParam String jmbg,@RequestParam String sifra){
      boolean ima=  kService.postojiPacijent(jmbg, sifra);
   
      if(ima){
         return "redirect:/svipreglediPacijent/"+jmbg;
      }
      return "Error";
    }
}
