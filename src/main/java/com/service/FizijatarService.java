
package com.service;

import com.entity.Pacijent;
import com.entity.Fizijatar;
import com.entity.Pregled;
import com.entity.Terapija;
import com.entity.Tip;
import com.excepitons.IstiTip;
import com.excepitons.PostojiPregled;
import com.repository.FizijatarRepository;
import com.repository.PacijentRepository;
import com.repository.PregledRepository;
import com.repository.TerapijaRepository;
import com.repository.TipRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Klasa FizijatarService klasa predstavlja servis potrebih metoda za lekare
 * 
 * @author jelena.pajdic
 */
@Service
public class FizijatarService {

	@Autowired
	private PregledRepository pregledRepository;
	@Autowired
	private FizijatarRepository lekarRepository;
	@Autowired
	private PacijentRepository kartonRepository;
	@Autowired
	private TerapijaRepository terapijaRepository;
	@Autowired
	private TipRepository tipRepository;

	@Transactional
	public void dodajPregled(Pregled p) throws PostojiPregled {
		if (!postojiPregled(p)) {
			pregledRepository.save(p);
		} else {
			throw new PostojiPregled();
		}
	}

	@Transactional(readOnly = true)
	public Fizijatar vratiLekara(String brojLicence) {
		return lekarRepository.findByBrojLicence(brojLicence);
	}

	@Transactional(readOnly = true)
	public List<Pregled> sviPregledi(String brojLicence) {

		return pregledRepository.findByFizijatarBrojLicenceOrderByIdDesc(brojLicence);

	}

	@Transactional(readOnly = true)
	public List<Pacijent> sviPacijenti(String brojLicence) {
		return kartonRepository.findDistinctBypregledCollectionFizijatarBrojLicence(brojLicence);
	}

	@Transactional(readOnly = true)
	public boolean postojiLekar(String brojLicence, String sifra) {
		int broj = lekarRepository.countByBrojLicenceAndSifra(brojLicence, sifra).intValue();

		if (broj == 0) {
			return false;
		}
		return true;

	}

	@Transactional(readOnly = true)
	public boolean postojiPregled(Pregled p) {
		int broj = pregledRepository.countByVremeAndFizijatar(p.getVreme(), p.getFizijatar()).intValue();
		if (broj == 0) {
			return false;
		}
		return true;

	}

	@Transactional(readOnly = true)
	public Terapija vratiTerapiju(int id) {
		return terapijaRepository.findById(id);
	}

	@Transactional
	public void dodajTerapiju(Terapija t) throws IstiTip {
		if (!postojiIstiTip(t)) {
			terapijaRepository.save(t);
		} else {
			throw new IstiTip("isti tip");
		}

	}

	@Transactional(readOnly = true)
	public boolean postojiIstiTip(Terapija t) {

		int broj = terapijaRepository.countDistinctByTipAndPregled(t.getTip(), t.getPregled()).intValue();

		int brojT = t.getPregled().getTerapijaCollection().size();

		if (brojT == 0 || brojT == broj || broj == 0) {
			return false;
		}
		return true;

	}

	@Transactional(readOnly = true)
	public Pregled vratiPregled(int id) {
		return pregledRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Tip vratiTip(int id) {
		return tipRepository.findBySifra(id);
	}

	@Transactional
	public void saveLekar(Fizijatar l) {
		lekarRepository.save(l);
	}

	@Transactional(readOnly = true)
	public Pacijent vratiPacijenta(String jmbg) {
		return kartonRepository.findByJmbg(jmbg);
	}

	@Transactional(readOnly = true)
	public List<Pregled> preglediPacijenta(String jmbg) {
		return (List<Pregled>) pregledRepository.findByPacijentJmbgOrderByIdDesc(jmbg);
	}

}
