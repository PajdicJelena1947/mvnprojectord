
package com.controller;

import com.entity.Pacijent;
import com.entity.Fizijatar;
import com.entity.Pregled;
import com.entity.Terapija;
import com.excepitons.IstiTip;
import com.excepitons.NemaPacijenta;
import com.excepitons.NemaPregleda;
import com.excepitons.PostojiPregled;
import com.excepitons.PraznaLista;
import com.service.FizijatarService;
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
 * Klasa FizijatarRestController sadrzi potrebne metoda za fizijatra,vraca JSON.
 * 
 * @author jelena.pajdic
 */
@RestController
@RequestMapping("/fizijatar")
public class FizijatarRestController {
	@Autowired
	private FizijatarService lService;

	@RequestMapping(value = "/sviPacijenti/{brojLicence}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pacijent>> sviPacijentiLekar(@PathVariable("brojLicence") String brojLicence)
			throws PraznaLista {
		List<Pacijent> kartoni = lService.sviPacijenti(brojLicence);
		if (kartoni.size() == 0) {
			throw new PraznaLista("pacijenata");
		}

		return new ResponseEntity<List<Pacijent>>(kartoni, HttpStatus.OK);
	}

	@RequestMapping(value = "/vratiPacijenta/{jmbg}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pacijent> vratiPacijenta(@PathVariable("jmbg") String jmbg) throws NemaPacijenta {
		Pacijent p = lService.vratiPacijenta(jmbg);
		if (p == null) {
			throw new NemaPacijenta(jmbg);
		}
		return new ResponseEntity<Pacijent>(p, HttpStatus.OK);
	}

	@RequestMapping(value = "/sviPregledi/{brojLicence}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pregled>> sviPreglediLekar(@PathVariable("brojLicence") String brojLicence)
			throws PraznaLista {
		List<Pregled> pregledi = lService.sviPregledi(brojLicence);
		if (pregledi.size() == 0) {
			throw new PraznaLista("pregleda");
		}
		return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
	}

	@RequestMapping(value = "/sviPreglediPacijenta/{jmbg}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pregled>> sviPreglediPacijenta(@PathVariable("jmbg") String jmbg) throws PraznaLista {
		List<Pregled> pregledi = lService.preglediPacijenta(jmbg);
		if (pregledi.size() == 0) {
			throw new PraznaLista("pregleda pacijenta");
		}

		return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
	}

	@RequestMapping(value = "/vratiPregled/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pregled> vratiPregled(@PathVariable("id") int id) throws NemaPregleda {
		Pregled p;
		p = lService.vratiPregled(id);
		if (p == null) {
			throw new NemaPregleda(id);
		}

		return new ResponseEntity<Pregled>(p, HttpStatus.OK);
	}

	@RequestMapping(value = "/dodajPregled/", method = RequestMethod.POST)
	public ResponseEntity<Void> dodajPregled(@RequestBody Pregled p, Principal principal) {
		try {
			Fizijatar l = (Fizijatar) principal;
			p.setFizijatar(l);
			lService.dodajPregled(p);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (PostojiPregled e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/dodajTerapiju/", method = RequestMethod.POST)
	public ResponseEntity<Void> dodajTerapiju(@RequestBody Terapija tl) {
		try {
			lService.dodajTerapiju(tl);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (IstiTip e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/vratiLekara/{brojLicence}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Fizijatar> vratiLekara(@PathVariable("brojLicence") String brojLicence) {
		Fizijatar p = lService.vratiLekara(brojLicence);
		return new ResponseEntity<Fizijatar>(p, HttpStatus.OK);
	}

	@RequestMapping(value = "/dodajLekara/", method = RequestMethod.POST)
	public ResponseEntity<Fizijatar> vratiLekara(@RequestBody Fizijatar p) {
		lService.saveLekar(p);
		return new ResponseEntity<Fizijatar>(p, HttpStatus.OK);
	}

}
