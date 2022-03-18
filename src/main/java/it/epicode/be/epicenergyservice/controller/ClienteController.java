package it.epicode.be.epicenergyservice.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.epicenergyservice.model.Cliente;
import it.epicode.be.epicenergyservice.service.ClienteService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping("/findallclienti")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findAll(Pageable pageable) {
		Page<Cliente> findAll = clienteService.findAll(pageable);
		if (!findAll.isEmpty())
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/findByidcliente/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Cliente> findById(@PathVariable(required = true) Long id) {
		Optional<Cliente> findById = clienteService.findById(id);

		return new ResponseEntity<>(findById.get(), HttpStatus.OK);
		

	}

	@GetMapping("/orderbyfatturato")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findAllByOrderByFatturatoAnnuale(Pageable pageable) {
		Page<Cliente> orderBy = clienteService.findAllByOrderByFatturatoAnnuale(pageable);
		return new ResponseEntity<>(orderBy, HttpStatus.OK);

	}

	@GetMapping("/orderbydatainserimento")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findAllByOrderByDataInserimento(Pageable pageable) {
		Page<Cliente> orderBy = clienteService.findAllByOrderByDataInserimento(pageable);
		return new ResponseEntity<>(orderBy, HttpStatus.OK);

	}

	@GetMapping("/orderbydataultimocontatto")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findAllByOrderByDataUltimoContatto(Pageable pageable) {
		Page<Cliente> orderBy = clienteService.findAllByOrderByDataUltimoContatto(pageable);
		return new ResponseEntity<>(orderBy, HttpStatus.OK);

	}

	@GetMapping("/orderbyprovincia")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findAllByOrderBySedeLegaleComuneProvinciaId(Pageable pageable) {
		Page<Cliente> orderBy = clienteService.findAllByOrderBySedeLegaleComuneProvinciaId(pageable);
		return new ResponseEntity<>(orderBy, HttpStatus.OK);

	}

	@GetMapping("/findbyfatturato/{fatturatoAnnuale}/{fatturatoAnnuale2}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findByFatturatoAnnualeBetween(
			@PathVariable(required = true) int fatturatoAnnuale, @PathVariable(required = true) int fatturatoAnnuale2,
			Pageable pageable) {
		Page<Cliente> findBy = clienteService.findByFatturatoAnnualeBetween(fatturatoAnnuale, fatturatoAnnuale2,
				pageable);
		return new ResponseEntity<>(findBy, HttpStatus.OK);

	}

	@GetMapping("/findbydatainserimento/{dataInserimento}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findByDataInserimento(
			@PathVariable(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInserimento,
			Pageable pageable) {
		Page<Cliente> findBy = clienteService.findByDataInserimento(dataInserimento, pageable);
		return new ResponseEntity<>(findBy, HttpStatus.OK);

	}

	@GetMapping("/findbydataultimocontatto/{dataUltimoContatto}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findByDataUltimoContatto(
			@PathVariable(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataUltimoContatto,
			Pageable pageable) {
		Page<Cliente> findBy = clienteService.findByDataUltimoContatto(dataUltimoContatto, pageable);
		return new ResponseEntity<>(findBy, HttpStatus.OK);

	}

	@GetMapping("/findbyragionesociale/{parteNome}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findByRagioneSocialeContaining(String parteNome, Pageable pageable) {
		Page<Cliente> findBy = clienteService.findByRagioneSocialeContaining(parteNome, pageable);
		return new ResponseEntity<>(findBy, HttpStatus.OK);

	}

	@PostMapping("/cliente")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente save = clienteService.saveCliente(cliente);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping("/updatecliente/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Cliente> update(@PathVariable(required = true) Long id, @RequestBody Cliente cliente) {
		Cliente update = clienteService.update(id, cliente);
		return new ResponseEntity<>(update, HttpStatus.OK);

	}

	@DeleteMapping("/deletecliente/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		clienteService.deleteCliente(id);
		return new ResponseEntity<>("Cliente eliminato", HttpStatus.OK);

	}

}
