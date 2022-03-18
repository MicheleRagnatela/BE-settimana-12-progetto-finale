package it.epicode.be.epicenergyservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import it.epicode.be.epicenergyservice.model.StatoFattura;
import it.epicode.be.epicenergyservice.service.StatoFatturaService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class StatoFatturaController {

	@Autowired
	StatoFatturaService statoFatturaService;
	
	@GetMapping("/findallstati")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<StatoFattura>> findAll(Pageable pageable) {
		Page<StatoFattura> findAll = statoFatturaService.findAll(pageable);
		if (!findAll.isEmpty())
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/findbyidstato/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<StatoFattura> findById(@PathVariable(required = true) Long id) {
		Optional<StatoFattura> findById = statoFatturaService.findById(id);

		if (findById.isPresent()) {
			return new ResponseEntity<>(findById.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}


	@PostMapping("/statofattura")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<StatoFattura> save(@RequestBody StatoFattura statoFattura) {
		StatoFattura save = statoFatturaService.saveStato(statoFattura);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping("/updatestato/{id}") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<StatoFattura> update(@PathVariable(required=true) Long id, @RequestBody StatoFattura statoFattura) {
		StatoFattura update = statoFatturaService.updateStato(id, statoFattura);
		return new ResponseEntity<>(update, HttpStatus.OK);

	}

	@DeleteMapping("/deletestato/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		statoFatturaService.deleteStato(id);
		return new ResponseEntity<>("Stato fattura eliminato", HttpStatus.OK);

	}
	
}
