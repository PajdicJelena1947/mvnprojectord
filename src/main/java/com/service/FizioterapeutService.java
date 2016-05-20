
package com.service;

import com.entity.Fizioterapeut;
import com.entity.Terapija;
import com.excepitons.PostojiTerapija;
import com.repository.FizioterapeutRepository;
import com.repository.TerapijaRepository;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servis FizioterapeutService predstavlja servis potrebih metoda za
 * fizioterapeuta
 * 
 * @author jelena.pajdic
 */

@Service
public class FizioterapeutService {
	@Autowired
	private TerapijaRepository terapijaRepository;

	@Autowired
	private FizioterapeutRepository fizoterapeutRepository;

	@Transactional(readOnly = true)
	public boolean postojiFizioterapeut(String brojLicence, String sifra) {
		int broj = fizoterapeutRepository.countByBrojLicenceAndSifra(brojLicence, sifra).intValue();

		if (broj == 0) {
			return false;
		}
		return true;

	}

	@Transactional(readOnly = true)
	public boolean postojiTerapija(Terapija t) {
		int broj = terapijaRepository
				.countByPocetakAndTipAndPregledVreme(t.getPocetak(), t.getTip(), t.getPregled().getVreme()).intValue();

		if (broj == 0) {
			return false;
		}
		return true;

	}

	@Transactional
	public void dodajTerapiju(Terapija t) throws PostojiTerapija {
		if (!postojiTerapija(t)) {
			terapijaRepository.save(t);
		} else {
			throw new PostojiTerapija();
		}
	}

	@Transactional(readOnly = true)
	public Fizioterapeut vratiFizoterapeuta(String brojLicence) {
		return fizoterapeutRepository.findByBrojLicence(brojLicence);
	}

	@Transactional(readOnly = true)
	public List<Terapija> sveTerapijeFizioterapeuta() {
		return terapijaRepository.findTop30ByOrderByIdDesc();
	}

	@Transactional(readOnly = true)
	public Terapija vratiTerapiju(int id) {
		return terapijaRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Terapija> terapijePacijenta(String jmbg) {
		return terapijaRepository.findFirst10ByJmbgPacijentaOrderByIdDesc(jmbg);
	}

	@Transactional
	public void dodajFizioterapeuta(Fizioterapeut f) {
		fizoterapeutRepository.save(f);
	}

}
