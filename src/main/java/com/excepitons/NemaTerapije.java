package com.excepitons;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Nema terapije")
public class NemaTerapije extends  RuntimeException {
	private static final long serialVersionUID = 100L;
	
    public NemaTerapije(int terapija) {
		super(terapija + " nema terapije");
	}
    public NemaTerapije(){
    	super("Nema terapije");
    }
}
