package it.epicode.be.epicenergyservice.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.epicenergyservice.exception.EpicEnergyException;
import it.epicode.be.epicenergyservice.model.Cliente;
import it.epicode.be.epicenergyservice.model.Fattura;
import it.epicode.be.epicenergyservice.repository.ClienteRepository;
import it.epicode.be.epicenergyservice.repository.FatturaRepository;

@Service
public class FatturaService {

	@Autowired
	FatturaRepository fatturaRepository;

	@Autowired
	ClienteRepository clienteRepository;

	public Optional<Fattura> findById(Long id) {
		Optional<Fattura> fattura = fatturaRepository.findById(id);
		if (fattura.isPresent())
			return fatturaRepository.findById(id);
		else
			throw new EpicEnergyException("Fattura non trovata! Inserisci un altro id");

	}

	public Page<Fattura> findAll(Pageable pageable) {
		return fatturaRepository.findAll(pageable);
	}

	public Fattura saveFattura(Fattura fattura) {
		return fatturaRepository.save(fattura);
	}

	public Fattura update(Long id, Fattura fattura) {
		Optional<Fattura> fatturaResult = fatturaRepository.findById(id);
		if (fatturaResult.isPresent()) {
			Fattura fatturaUpdate = fatturaResult.get();
			fatturaUpdate.setAnno(fattura.getAnno());
			fatturaUpdate.setData(fattura.getData());
			fatturaUpdate.setImporto(fattura.getImporto());
			fatturaUpdate.setNumero(fattura.getNumero());
			fatturaUpdate.setStatoFattura(fattura.getStatoFattura());
			fatturaUpdate.setCliente(fattura.getCliente());
			return fatturaRepository.save(fatturaUpdate);

		}
		throw new EpicEnergyException(
				"Fattura non trovata! Non è possibile effettuare l'aggiornamento. Inserisci un altro id");

	}

	public void deleteFattura(Long id, Pageable pageable) {
		Optional<Fattura> fattura = fatturaRepository.findById(id);
		if (fattura.isPresent()) {
			List<Cliente> clienti = clienteRepository.findByFattureId(id);
			for (Cliente c : clienti)
				c.getFatture().remove(fattura.get());
		} else {
			throw new EpicEnergyException(
					"Fattura non trovata! Non è possibile effettuare l'eliminazione. Inserisci un altro id");

		}
		fatturaRepository.deleteById(id);
	}

	public Page<Fattura> findByClienteId(Long id, Pageable pageable) {
		return fatturaRepository.findByClienteId(id, pageable);
	}

	public Page<Fattura> findByStatoFattura(Long id, Pageable pageable) {
		return fatturaRepository.findByStatoFatturaId(id, pageable);
	}

	public Page<Fattura> findByData(LocalDate data, Pageable pageable) {
		return fatturaRepository.findByData(data, pageable);
	}

	public Page<Fattura> findByAnno(Integer anno, Pageable pageable) {
		return fatturaRepository.findByAnno(anno, pageable);
	}

	public Page<Fattura> findByImportoBetween(BigDecimal importo, BigDecimal importo2, Pageable pageable) {
		return fatturaRepository.findByImportoBetween(importo, importo2, pageable);
	}

}