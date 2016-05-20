
package com.repository;

import com.entity.Pacijent;
import com.entity.Fizijatar;
import com.entity.Pregled;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jelena.pajdic
 */
public interface PregledRepository extends CrudRepository<Pregled, Long> {

	Long countByVremeAndFizijatar(Date vreme, Fizijatar lekar);

	Pregled findById(int id);

	List<Pregled> findByPacijentJmbgOrderByIdDesc(String jmbg);

	List<Pregled> findByFizijatarBrojLicenceOrderByIdDesc(String brojLicence);

}
