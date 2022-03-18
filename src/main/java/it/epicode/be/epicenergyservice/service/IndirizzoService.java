package it.epicode.be.epicenergyservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.epicenergyservice.exception.EpicEnergyException;
import it.epicode.be.epicenergyservice.model.Indirizzo;
import it.epicode.be.epicenergyservice.repository.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired
	IndirizzoRepository indirizzoRepository;
	
	public Optional<Indirizzo> findById(Long id) {
		Optional<Indirizzo> indirizzo = indirizzoRepository.findById(id);
		if (indirizzo.isPresent())
			return indirizzoRepository.findById(id);
		else
			throw new EpicEnergyException(
					"Indirizzo non trovato! Inserisci un altro id");

	}

	public Page<Indirizzo> findAll(Pageable pageable) {
		return indirizzoRepository.findAll(pageable);
	}
	
	public List<Indirizzo> findAll() {
		return indirizzoRepository.findAll();
	}

	public Indirizzo saveIndirizzo(Indirizzo indirizzo) {
		return indirizzoRepository.save(indirizzo);
	}

	public Indirizzo update(Long id, Indirizzo indirizzo) {
		Optional<Indirizzo> indirizzoResult = indirizzoRepository.findById(id);
		if (indirizzoResult.isPresent()) {
			Indirizzo indirizzoUpdate = indirizzoResult.get();
			indirizzoUpdate.setVia(indirizzo.getVia());
			indirizzoUpdate.setCivico(indirizzo.getCivico());
			indirizzoUpdate.setCap(indirizzo.getCap());
			indirizzoUpdate.setLocalita(indirizzo.getLocalita());
			indirizzoUpdate.setComune(indirizzo.getComune());
			return indirizzoRepository.save(indirizzoUpdate);

		}
		throw new EpicEnergyException(
				"Indirizzo non trovato! Non è possibile effettuare l'aggiornamento. Inserisci un altro id");

	}

	public void deleteIndirizzo(Long id) {
		indirizzoRepository.deleteById(id);
	}
	
}
