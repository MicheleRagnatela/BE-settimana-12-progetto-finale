package it.epicode.be.epicenergyservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import it.epicode.be.epicenergyservice.exception.EpicEnergyException;
import it.epicode.be.epicenergyservice.model.StatoFattura;
import it.epicode.be.epicenergyservice.repository.StatoFatturaRepository;


@Service
public class StatoFatturaService {

	@Autowired
	StatoFatturaRepository statoFatturaRepository;
	
	public Optional<StatoFattura> findById(Long id) {
		Optional<StatoFattura> stato = statoFatturaRepository.findById(id);
		if (stato.isPresent())
			return statoFatturaRepository.findById(id);
		else
			throw new EpicEnergyException(
					"Stato fattura non trovato! Inserisci un altro id");

	}

	public Page<StatoFattura> findAll(Pageable pageable) {
		return statoFatturaRepository.findAll(pageable);
	}
	
	public List<StatoFattura> findAll() {
		return statoFatturaRepository.findAll();
	}

	public StatoFattura saveStato(StatoFattura statoFattura) {
		return statoFatturaRepository.save(statoFattura);
	}

	public StatoFattura updateStato(Long id, StatoFattura statoFattura) {
		Optional<StatoFattura> statoResult = statoFatturaRepository.findById(id);
		if (statoResult.isPresent()) {
			StatoFattura statoUpdate = statoResult.get();
			statoUpdate.setNome(statoFattura.getNome());
			return statoFatturaRepository.save(statoUpdate);

		}
		throw new EpicEnergyException(
				"Stato fattura non trovato! Non è possibile effettuare l'aggiornamento. Inserisci un altro id");

	}

	public void deleteStato(Long id) {
		statoFatturaRepository.deleteById(id);
	}
	
	
}
