package com.excepitons;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Nema pregleda")
public class NemaPregleda extends RuntimeException {
    private static final long serialVersionUID = 100L;
    public NemaPregleda(int pregled) {
		super(pregled + " nema pregleda");
	}
    public NemaPregleda(){
        super("nema pregleda");
    }
}

