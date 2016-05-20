
package com.service;

import com.entity.Fizijatar;
import com.entity.Pacijent;
import com.entity.Pregled;
import com.entity.Terapija;
import com.repository.FizijatarRepository;
import com.repository.FizioterapeutRepository;
import com.repository.PacijentRepository;
import com.repository.PregledRepository;
import com.repository.TerapijaRepository;

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
 * PacijentService predstavlja servis potrebnih metoda za pacijenta
 * 
 * 
 * @author jelena.pajdic
 */

@Service
public class PacijentService {

	@Autowired
	private PacijentRepository kartonRepository;
	@Autowired
	private PregledRepository pregledRepository;
	@Autowired
	private FizijatarRepository lekarRepository;
	@Autowired
	private TerapijaRepository terapijaRepository;
	@Autowired
	private FizioterapeutRepository fizioterapeutRepository;
	@Autowired
	private SlanjeMejlaService mejlService;

	@Transactional(readOnly = true)
	public Pacijent vratiPacijenta(String jmbg) {
		return kartonRepository.findByJmbg(jmbg);
	}

	@Transactional(readOnly = true)
	public boolean postojiPacijent(String jmbg, String sifra) {
		int broj = kartonRepository.countByJmbgAndSifra(jmbg, sifra).intValue();
		if (broj == 0) {
			return false;
		}
		return true;
	}

	@Transactional(readOnly = true)
	public List<Pregled> sviPreglediPacijenta(String jmbg) {
		return pregledRepository.findByPacijentJmbgOrderByIdDesc(jmbg);

	}

	@Transactional(readOnly = true)
	public String emailFizijatra(String jmbg) {
		Fizijatar f = lekarRepository.findFirstByPregledCollectionPacijentJmbg(jmbg);
		return f.getEmail();

	}

	@Transactional(readOnly = true)
	public List<Terapija> sveTerapijePacijenta(String jmbg) {
		return terapijaRepository.findByJmbgPacijentaOrderByIdDesc(jmbg);
	}

	@Transactional(readOnly = true)
	public Terapija vratiTerapiju(int id) {
		return terapijaRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Pregled vratiPregled(int id) {
		return pregledRepository.findById(id);
	}

	public void sendMailLekar(Pacijent k, String message) throws MessagingException {
		mejlService.generateAndSendEmail(k, emailFizijatra(k.getJmbg()), message);

	}

	@Transactional
	public void saveKarton(Pacijent k) {
		kartonRepository.save(k);
	}
}
