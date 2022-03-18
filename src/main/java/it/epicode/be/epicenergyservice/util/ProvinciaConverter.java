package it.epicode.be.epicenergyservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.epicode.be.epicenergyservice.model.Provincia;
import it.epicode.be.epicenergyservice.service.ProvinciaService;


@Component
public class ProvinciaConverter implements Converter<Long, Provincia>{

	@Autowired
	ProvinciaService provinciaService;
	
	@Override
	public Provincia convert(Long id) {
		return provinciaService.findById(id).get();
	}

}
