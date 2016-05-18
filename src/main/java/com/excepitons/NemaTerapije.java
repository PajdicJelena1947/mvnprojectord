package com.excepitons;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author jelena.pajdic
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Nema terapije")
public class NemaTerapije extends  RuntimeException {
    
    public NemaTerapije(int terapija) {
		super(terapija + " nema terapije");
	}
}
