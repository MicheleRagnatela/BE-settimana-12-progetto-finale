package it.epicode.be.epicenergyservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import it.epicode.be.epicenergyservice.model.StatoFattura;
import it.epicode.be.epicenergyservice.service.StatoFatturaService;

@Component
public class StatoFatturaConverter implements Converter<Long, StatoFattura>{

	@Autowired
	StatoFatturaService statofatturaService;
	
	@Override
	public StatoFattura convert(Long id) {
		
		return statofatturaService.findById(id).get();
	}

	
	
	
}
