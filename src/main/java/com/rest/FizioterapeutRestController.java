/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest;

import com.entity.Fizioterapeut;
import com.entity.Terapija;
import com.excepitons.NemaTerapije;
import com.service.FizioterapeutService;
import java.security.Principal;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
public class FizioterapeutRestController {
    @Autowired
    private FizioterapeutService fService;
    
    @RequestMapping(value = "/sveTerapijePacijentFizioterapeut/{jmbg}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Terapija>> sveTerapijePacijenta(@PathVariable("jmbg") String jmbg) {
        List<Terapija> terapije=fService.terapijePacijenta(jmbg);
        return new ResponseEntity<List<Terapija>>(terapije, HttpStatus.OK);
    }
    
    @RequestMapping(value="/vratiTerapijuFizioterapeut/{id}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Terapija> vratiTerapiju(@PathVariable("id") int id) throws NemaTerapije {
        Terapija terapija=fService.vratiTerapiju(id);
        if(terapija==null) throw new NemaTerapije(id);
        return new ResponseEntity<Terapija>(terapija, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/sveTerapijePacijentFizioterapeut/{brojLicence}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Terapija>> sveTerapijeFizioterapeuta(@PathVariable("brojLicence") String brojLicence) {
        List<Terapija> terapije=fService.sveTerapijeFizioterapeuta(brojLicence);
        return new ResponseEntity<List<Terapija>>(terapije, HttpStatus.OK);
    }
    @RequestMapping(value="/dodajTerapijuFizioterapeut", method=RequestMethod.POST)
    public ResponseEntity<Void> dodajTerapiju(@RequestBody Terapija tl ,Principal principal) throws MessagingException {
        Fizioterapeut f=(Fizioterapeut) principal;
        tl.setFizioterapeutId(f);
        fService.dodajTerapiju(tl);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
