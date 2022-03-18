package it.epicode.be.epicenergyservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.epicode.be.epicenergyservice.model.Indirizzo;
import it.epicode.be.epicenergyservice.service.IndirizzoService;


@Component
public class IndirizzoConverter implements Converter<Long, Indirizzo> {

	@Autowired
	IndirizzoService indirizzoService;
	
	

	@Override
	public Indirizzo convert(Long id) {
		return indirizzoService.findById(id).get();
	}
}
