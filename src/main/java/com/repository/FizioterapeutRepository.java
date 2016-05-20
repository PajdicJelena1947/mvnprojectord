
package com.repository;

import com.entity.Fizioterapeut;
import com.entity.Fizijatar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jelena.pajdic
 */
public interface FizioterapeutRepository extends CrudRepository<Fizioterapeut, Long> {
	Fizioterapeut findByBrojLicence(String brojLicence);

	Long countByBrojLicenceAndSifra(String brojLicence, String sifra);

}
