
package com.repository;

import com.entity.Fizioterapeut;
import com.entity.Fizijatar;
import com.entity.Pregled;
import com.entity.Terapija;
import com.entity.Tip;

import java.util.Date;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jelena.pajdic
 */
public interface TerapijaRepository extends CrudRepository<Terapija, Long> {
	
	Long countDistinctByTipAndPregled(Tip tip,Pregled pregled);
	

	Terapija findById(int id);

	List<Terapija> findByJmbgPacijentaOrderByIdDesc(String jmbg);

	List<Terapija> findFirst10ByJmbgPacijentaOrderByIdDesc(String jmbg);

	List<Terapija> findTop30ByOrderByIdDesc();

	Long countByPocetakAndTipAndPregledVreme(Date pocetak, Tip tip,Date vreme);

}
