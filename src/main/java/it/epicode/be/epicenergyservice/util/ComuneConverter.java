package it.epicode.be.epicenergyservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import it.epicode.be.epicenergyservice.model.Comune;
import it.epicode.be.epicenergyservice.service.ComuneService;

@Component
public class ComuneConverter implements Converter<Long, Comune> {

	@Autowired	
	ComuneService comuneService;

	@Override
	public Comune convert(Long id) {
		return comuneService.findById(id).get();
	}
}
