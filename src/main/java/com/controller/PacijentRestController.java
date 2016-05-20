
package com.controller;

import com.entity.Pacijent;
import com.entity.Pregled;
import com.entity.Terapija;
import com.excepitons.NemaPregleda;
import com.excepitons.NemaTerapije;
import com.excepitons.PraznaLista;
import com.service.PacijentService;

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
 * Klasa PacijentRestController sadrzi potrebne metoda za pacijenta,vraca JSON.
 * 
 * @author jelena.pajdic
 */
@RestController
@RequestMapping("/pacijent")
public class PacijentRestController {
	@Autowired
	private PacijentService kService;

	@RequestMapping(value = "/sviPregledi/{jmbg}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pregled>> sviPreglediPacijent(@PathVariable("jmbg") String jmbg) throws PraznaLista {
		List<Pregled> pregledi = kService.sviPreglediPacijenta(jmbg);
		if (pregledi.size() == 0) {
			throw new PraznaLista("pregleda pacijenata");
		}
		return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
	}

	@RequestMapping(value = "/sveTerapije/{jmbg}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Terapija>> sveTerapijePacijenta(@PathVariable("jmbg") String jmbg) throws PraznaLista {
		List<Terapija> terapije = kService.sveTerapijePacijenta(jmbg);
		if (terapije.size() == 0) {
			throw new PraznaLista("terapija pacijenta");
		}
		return new ResponseEntity<List<Terapija>>(terapije, HttpStatus.OK);
	}

	@RequestMapping(value = "/vratiTerapiju/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Terapija> vratiTerapiju(@PathVariable("id") int id) throws NemaTerapije {
		Terapija terapija = kService.vratiTerapiju(id);
		if (terapija == null) {
			throw new NemaTerapije(id);
		}
		return new ResponseEntity<Terapija>(terapija, HttpStatus.OK);
	}

	@RequestMapping(value = "/vratiPregled/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pregled> vratiPregled(@PathVariable("id") int id) throws NemaPregleda {
		Pregled p = kService.vratiPregled(id);
		if (p == null) {
			throw new NemaPregleda(id);
		}
		return new ResponseEntity<Pregled>(p, HttpStatus.OK);
	}

	@RequestMapping(value = "/posaljiMejlLekaru", method = RequestMethod.POST)
	public ResponseEntity<Void> posaljiMejlLekaru(@RequestParam String poruka, Principal principal)
			throws MessagingException {
		Pacijent k = (Pacijent) principal;
		kService.sendMailLekar(k, poruka);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public boolean login(@RequestParam String jmbg, @RequestParam String sifra) {
		boolean ima = kService.postojiPacijent(jmbg, sifra);

		if (ima) {
			return true;
		}
		return false;
	}
}
