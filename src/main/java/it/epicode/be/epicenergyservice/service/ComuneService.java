package it.epicode.be.epicenergyservice.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.epicenergyservice.exception.EpicEnergyException;
import it.epicode.be.epicenergyservice.model.Comune;
import it.epicode.be.epicenergyservice.repository.ComuneRepository;

@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepository;
	
	public Optional<Comune> findById(Long id) {
		Optional<Comune> cliente = comuneRepository.findById(id);
		if (cliente.isPresent())
			return comuneRepository.findById(id);
		else
			throw new EpicEnergyException(
					"Comune non trovato! Inserisci un altro id");

	}

	public Page<Comune> findAll(Pageable pageable) {
		return comuneRepository.findAll(pageable);
	}
	
	public List<Comune> findAll() {
		return comuneRepository.findAll();
	}

	public Comune saveComune(Comune comune) {
		return comuneRepository.save(comune);
	}

	public Comune update(Long id, Comune comune) {
		Optional<Comune> comuneResult = comuneRepository.findById(id);
		if (comuneResult.isPresent()) {
			Comune comuneUpdate = comuneResult.get();
			comuneUpdate.setNome(comune.getNome());
			comuneUpdate.setProvincia(comune.getProvincia());
			return comuneRepository.save(comuneUpdate);

		}
		throw new EpicEnergyException(
				"Comune non trovato! Non è possibile effettuare l'aggiornamento. Inserisci un altro id");

	}

	public void deleteComune(Long id) {
		comuneRepository.deleteById(id);
	}
}
