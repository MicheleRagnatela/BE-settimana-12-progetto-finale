package it.epicode.be.epicenergyservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.epicenergyservice.exception.EpicEnergyException;
import it.epicode.be.epicenergyservice.model.Cliente;
import it.epicode.be.epicenergyservice.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public Optional<Cliente> findById(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent())
			return clienteRepository.findById(id);
		else
			throw new EpicEnergyException("Cliente non trovato! Inserisci un altro id");

	}

	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente update(Long id, Cliente cliente) {
		Optional<Cliente> clienteResult = clienteRepository.findById(id);
		if (clienteResult.isPresent()) {
			Cliente clienteUpdate = clienteResult.get();
			clienteUpdate.setRagioneSociale(cliente.getRagioneSociale());
			clienteUpdate.setPartitaIva(cliente.getPartitaIva());
			clienteUpdate.setEmail(cliente.getEmail());
			clienteUpdate.setDataInserimento(cliente.getDataInserimento());
			clienteUpdate.setDataUltimoContatto(cliente.getDataUltimoContatto());
			clienteUpdate.setFatturatoAnnuale(cliente.getFatturatoAnnuale());
			clienteUpdate.setPec(cliente.getPec());
			clienteUpdate.setTelefono(cliente.getTelefono());
			clienteUpdate.setEmailContatto(cliente.getEmailContatto());
			clienteUpdate.setTelefono(cliente.getTelefono());
			clienteUpdate.setNomeContatto(cliente.getNomeContatto());
			clienteUpdate.setCognomeContatto(cliente.getCognomeContatto());
			clienteUpdate.setTelefonoContatto(cliente.getTelefonoContatto());
			clienteUpdate.setTipoCliente(cliente.getTipoCliente());
			clienteUpdate.setSedeLegale(cliente.getSedeLegale());
			clienteUpdate.setSedeOperativa(cliente.getSedeOperativa());
			clienteUpdate.setFatture(cliente.getFatture());
			return clienteRepository.save(clienteUpdate);

		}
		throw new EpicEnergyException(
				"Cliente non trovato! Non è possibile effettuare l'aggiornamento. Inserisci un altro id");

	}

	public void deleteCliente(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			List<Cliente> clienti = clienteRepository.findAll();
			clienti.remove(cliente.get());
		}

		else {
			throw new EpicEnergyException(
					"Cliente non trovato! Non è possibile effettuare l'eliminazione. Inserisci un altro id");

		}
		clienteRepository.deleteById(id);
	}

	public Page<Cliente> findAllByOrderByFatturatoAnnuale( Pageable pageable) {
		return clienteRepository.findAllByOrderByFatturatoAnnuale( pageable);
	}

	public Page<Cliente> findAllByOrderByDataInserimento(Pageable pageable) {
		return clienteRepository.findAllByOrderByDataInserimento(pageable);
	}

	public Page<Cliente> findAllByOrderByDataUltimoContatto(Pageable pageable) {
		return clienteRepository.findAllByOrderByDataUltimoContatto(pageable);
	}

	public Page<Cliente> findAllByOrderBySedeLegaleComuneProvinciaId( Pageable pageable) {
		return clienteRepository.findAllByOrderBySedeLegaleComuneProvinciaId( pageable);
	}

	public Page<Cliente> findByFatturatoAnnualeBetween(int fatturatoAnnuale, int fatturatoAnnuale2, Pageable pageable) {
		return clienteRepository.findByFatturatoAnnualeBetween(fatturatoAnnuale, fatturatoAnnuale2, pageable);
	}

	public Page<Cliente> findByDataInserimento(LocalDate dataInserimento, Pageable pageable) {
		return clienteRepository.findByDataInserimento(dataInserimento, pageable);
	}

	public Page<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable) {
		return clienteRepository.findByDataUltimoContatto(dataUltimoContatto, pageable);
	}

	public Page<Cliente> findByRagioneSocialeContaining(String parteNome, Pageable pageable) {
		return clienteRepository.findByRagioneSocialeContaining(parteNome, pageable);
	}

	

}
