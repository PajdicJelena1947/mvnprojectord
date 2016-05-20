
package com.controller;

import com.entity.Fizioterapeut;
import com.entity.Terapija;
import com.excepitons.NemaTerapije;
import com.excepitons.PostojiTerapija;
import com.excepitons.PraznaLista;
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
 * Klasa FizioterapeutRestController sadrzi potrebne metoda za
 * fizioterapeuta,vraca JSON.
 * 
 * @author jelena.pajdic
 */
@RestController
@RequestMapping("/fizioterapeut")
public class FizioterapeutRestController {
	@Autowired
	private FizioterapeutService fService;

	@RequestMapping(value = "/sveTerapijePacijenta/{jmbg}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Terapija>> sveTerapijePacijenta(@PathVariable("jmbg") String jmbg) throws PraznaLista {
		List<Terapija> terapije = fService.terapijePacijenta(jmbg);
		if (terapije.size() == 0) {
			throw new PraznaLista("terapija pacijenata");
		}
		return new ResponseEntity<List<Terapija>>(terapije, HttpStatus.OK);
	}

	@RequestMapping(value = "/vratiTerapiju/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Terapija> vratiTerapiju(@PathVariable("id") int id) throws NemaTerapije {
		Terapija terapija = fService.vratiTerapiju(id);
		if (terapija == null)
			throw new NemaTerapije(id);
		return new ResponseEntity<Terapija>(terapija, HttpStatus.OK);
	}

	@RequestMapping(value = "/sveTerapije", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Terapija>> sveTerapijeFizioterapeuta() throws PraznaLista {
		List<Terapija> terapije = fService.sveTerapijeFizioterapeuta();
		if (terapije.size() == 0) {
			throw new PraznaLista("terapija fizioterapeuta");
		}
		return new ResponseEntity<List<Terapija>>(terapije, HttpStatus.OK);
	}

	@RequestMapping(value = "/dodajTerapiju/", method = RequestMethod.POST)
	public ResponseEntity<Void> dodajTerapiju(@RequestBody Terapija tl) throws MessagingException {

		try {
			fService.dodajTerapiju(tl);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (PostojiTerapija e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
}
