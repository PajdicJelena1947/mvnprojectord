
package com.repository;

import com.entity.Pacijent;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jelena.pajdic
 */
public interface PacijentRepository extends CrudRepository<Pacijent, Long> {
	Long countByJmbgAndSifra(String jmbg, String sifra);

	Pacijent findByJmbg(String jmbg);

	List<Pacijent> findDistinctBypregledCollectionFizijatarBrojLicence(String brojLicence);

}
